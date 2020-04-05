

from selenium import webdriver
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
from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from time import sleep

driver = webdriver.Firefox()
driver.get("https://www.edureka.co")

action = ActionChains(driver);

parent_level_menu = driver.find_element_by_id("tabscathome")
action.move_to_element(parent_level_menu).perform()

child_level_menu = driver.find_element_by_xpath("//a[contains(text(),'DevOps')]")
child_level_menu.click();

sleep(10)

driver.close()