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
import traceback

import pdb
import time
# import schedule

from xvfbwrapper import Xvfb


class ZerodhaSelenium(object):

    def __init__(self):
        self.vdisplay = Xvfb()
        self.vdisplay.start()
        print("Started")
        self.options = Options()
        self.timeout = 5
        self.loadCredentials()
        self.options.add_argument("--window-size=1920,1080");
        self.driver = webdriver.Chrome(options=self.options)
        # self.driver = webdriver.Chrome()
        self.advancedSelectedOnce = False

    def getCssElement(self, cssSelector,wait=-1):
        '''
        To make sure we wait till the element appears
        '''
        if wait is -1:
            waitTime = self.timeout
        else:
            waitTime = wait
        return WebDriverWait(self.driver, waitTime).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, cssSelector)))


    def getByXPath(self, cssSelector,advanceButton = False,wait=-1):
        '''
        To make sure we wait till the element appears
        '''
        # time.sleep(.5);
        if wait is -1:
            waitTime = self.timeout
            if advanceButton and self.advancedSelectedOnce:
                waitTime = 0;
                print(waitTime)
        else:
            waitTime = wait

        return WebDriverWait(self.driver, waitTime).until(
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
        print("Pin Submitted")

        while True:
            self.placeOrder();
            time.sleep(10);

        pdb.set_trace()
        # close chrome
        self.driver.quit()

    def placeOrder(self):
        try:
            self.getByXPath("//input[(@id='search-input')]").send_keys("ACC");
            print("Typed ACC")

            firstItem = self.getCssElement("li.search-result-item.selected");
            hov = ActionChains(self.driver).move_to_element(firstItem)
            hov.perform()

            buyButton = self.getCssElement("button[class=button-blue]")
            self.driver.execute_script("arguments[0].click();", buyButton)

            try:
                if not self.advancedSelectedOnce:
                    self.advancedSelectedOnce = True
                advancedButton = self.getByXPath("//*[(@class='advanced-options-open')]",True)
                if advancedButton is not None:
                    self.driver.execute_script("arguments[0].click();", advancedButton)
            except:
                print("advanced-options not found")

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
            self.goToOrdersPage()
            # self.sortAccordingToTime();
        except Exception:
            traceback.print_exc()


    def goToOrdersPage(self):
        self.getByXPath("//*[(@href='/orders')]").click();

    def sortAccordingToTime(self):
        # desc = self.getByXPath("//*[(@class='order-timestamp.sortable.sorted.desc)",wait=1)
        completedOrders = self.getCssElement("th.order-timestamp.sortable.sorted.desc")
        print(completedOrders)
        desc = completedOrders.find_elements(By.TAG_NAME, "thead")
        if not desc is None:
            print("Found desc "+str(desc))
        else:
            print("Not Found desc...")

        asc = self.getByXPath("order-timestamp sortable sorted asc sortable",wait=1)
        if not asc is None:
            print("Found asc "+str(asc))
        else:
            print("Not Found asc...")

if __name__ == "__main__":
    obj = ZerodhaSelenium()
    obj.doLogin()
    # schedule.every().seconds.do(obj.do_something)

# <input type="number" placeholder="" autocorrect="off" min="0" step="0.05" noerror="true" staticlabel="true" animate="true" label="Price" rules="[object Object]" dynamicwidthsize="8">