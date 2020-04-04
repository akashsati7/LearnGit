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
        self.options = Options()
        self.options.add_argument("--no-sandbox")
        self.options.add_argument("--headless")
        self.options.add_argument("--disable-extensions")
        self.options.add_argument("--disable-notifications")
        self.options.add_argument("--window-size=1920,1080");
        self.options.add_argument("--start-maximized");
        self.options.add_argument("--disable-gpu");
        self.options.add_argument("--proxy-server='direct://'");
        self.options.add_argument("--proxy-bypass-list=*");
        self.options.add_argument("--disable-gpu")
        self.options.add_argument("--enable-automation")
        self.options.add_argument("--disable-infobars")
        self.options.add_argument("--disable-dev-shm-usage")
        self.timeout = 5
        self.loadCredentials()
        self.driver = webdriver.Chrome(options=self.options)
        # self.driver = webdriver.Chrome()

    def getCssElement(self, cssSelector):
        '''
        To make sure we wait till the element appears
        '''
        return WebDriverWait(self.driver, self.timeout).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, cssSelector)))

    def getByXPath(self, cssSelector):
        '''
        To make sure we wait till the element appears
        '''
        # time.sleep(.5);
        return WebDriverWait(self.driver, self.timeout).until(
            EC.presence_of_element_located((By.XPATH, cssSelector)))

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

        # passwordField = self.getCssElement("input[placeholder=Password]")
        pwd = self.getByXPath("//*[(@placeholder='Password')]")
        pwd.send_keys(self.password)
        self.getByXPath("//*[(@placeholder='User ID' and @type='text')]").send_keys(self.username)
        self.getByXPath("//button[(@type='submit')]").click()

        # 2FA
        pin = self.getByXPath("//*[(@animate='true') and (@label='PIN')]").send_keys(self.security)
        print("Pin "+(str)(pin));

        self.getByXPath("//button[(@type='submit')]").click()

        searchBar = self.getByXPath("//input[(@id='search-input')]")
        print("searchBar "+(str)(searchBar))
        searchBar.send_keys("ACC");

        firstItem = self.getByXPath("//li[(@class='search-result-item selected')]");
        firstItem.click();

        buyButton = self.getByXPath("//button[(@class='button-blue')]")
        self.driver.execute_script("arguments[0].click();", buyButton)

        advancedButton = self.getByXPath("//*[(@class='advanced-options-open')]")
        self.driver.execute_script("arguments[0].click();", advancedButton)

        quantity = self.getByXPath("//input[(@type='number')]")
        quantity.click();
        quantity.clear();
        quantity.send_keys(1);

        coButton = self.driver.find_element_by_xpath("//input[(@type='radio') and (@title='After market order')]")

        self.driver.execute_script("arguments[0].click();", coButton)

        limitOrder = self.driver.find_element_by_xpath("//input[(@type='radio') and (@title='Limit')]")
        self.driver.execute_script("arguments[0].click();", limitOrder)

        price = self.getByXPath("//input[(@type='number') and (@label='Price')]")
        price.send_keys(10)



        self.getByXPath("//button[(@type='submit')]").click()

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