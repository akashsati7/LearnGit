from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.chrome import options
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
import json
import pdb
import time
# import schedule


class ZerodhaSelenium(object):

    def __init__(self):
        self.options = webdriver.FirefoxOptions()
        self.options.add_argument("--headless")
        self.options.add_argument("window-size=1400,1500")
        self.options.add_argument("--disable-gpu")
        self.options.add_argument("--no-sandbox")
        self.options.add_argument("start-maximized")
        self.options.add_argument("enable-automation")
        self.options.add_argument("--disable-infobars")
        self.options.add_argument("--disable-dev-shm-usage")
        self.timeout = 5
        self.loadCredentials()
        self.driver = webdriver.Firefox(options=self.options)
        # self.driver = webdriver.Firefox()

    def getCssElement(self, cssSelector):
        '''
        To make sure we wait till the element appears
        '''
        return WebDriverWait(self.driver, self.timeout).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, cssSelector)))

    def loadCredentials(self):
        # with open("credentials.json") as credsFile:
        #     data = json.load(credsFile)
            data = {"username":"UV8580","password":"Leo@373737","security":"373737"}
            self.username = data['username']
            self.password = data['password']
            self.security = data['security']  # for 2FA

    def doLogin(self):
        # let's login
        self.driver.get("https://kite.zerodha.com/")

        # Only need to do this once per session

        passwordField = self.getCssElement("input[placeholder=Password]")
        passwordField.send_keys(self.password)
        userNameField = self.getCssElement("input[placeholder='User ID']")
        userNameField.send_keys(self.username)
        loginButton = self.getCssElement("button[type=submit]")
        loginButton.click()

        time.sleep(2);

        # 2FA
        pin = self.getCssElement("input[type='password']") or self.getCssElement("input[placeholder=PIN]")
        pin.send_keys(self.security)
        print("Pin "+(str)(pin));

        buttonSubmit = self.getCssElement("button[type=submit]")
        self.driver.execute_script("arguments[0].click();", buttonSubmit)

        time.sleep(4);

        searchBar = self.getCssElement("input[id=search-input]")
        print("searchBar "+(str)(searchBar))
        searchBar.send_keys("ACC");
        # firstItem = self.getCssElement("li.search-result-item.selected");
        # firstItem.click();
        # time.sleep(5);

        # self.getCssElement("li.search-result-item.selected")

        action = ActionChains(self.driver)
        firstLevelMenu = self.getCssElement("li.search-result-item.selected")
        action.move_to_element(firstLevelMenu).perform()

        buyButton = self.driver.find_element_by_xpath("//button[(@class='button-blue') and (@type='button')]")
        # buyButton = self.getCssElement("li.search-result-item.selected")
        self.driver.execute_script("arguments[0].click();", buyButton)

        time.sleep(2);


        advancedButton = self.getCssElement("a.advanced-options-open")
        self.driver.execute_script("arguments[0].click();", advancedButton)

        quantity = self.getCssElement("input[type=number]")
        quantity.click();
        quantity.clear();
        quantity.send_keys(1);

        coButton = self.driver.find_element_by_xpath("//input[(@type='radio') and (@title='After market order')]")

        self.driver.execute_script("arguments[0].click();", coButton)

        limitOrder = self.driver.find_element_by_xpath("//input[(@type='radio') and (@title='Limit')]")
        self.driver.execute_script("arguments[0].click();", limitOrder)

        price = self.driver.find_element_by_xpath("//input[(@type='number') and (@label='Price')]")
        price.clear()
        price.send_keys(10)


        self.getCssElement("button[type=submit]").click()

        print("RUnning")

        pdb.set_trace()
        # close chrome
        self.driver.quit()



    def do_something(self):
        print("Doing stuff...")


if __name__ == "__main__":
    obj = ZerodhaSelenium()
    obj.doLogin()
    # schedule.every().seconds.do(obj.do_something)

# <input type="number" placeholder="" autocorrect="off" min="0" step="0.05" noerror="true" staticlabel="true" animate="true" label="Price" rules="[object Object]" dynamicwidthsize="8">