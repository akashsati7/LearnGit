from pyvirtualdisplay import Display

display = Display(visible=0, size=(800, 600))
display.start()

# Do Not use headless chrome option
# options.add_argument('headless')

url = 'https://10.11.227.21/tmui/'
driver.get(url + "login.jsp")

html_source = driver.page_source
print(html_source)

blocStatus = WebDriverWait(driver,    TIMEOUT).until(EC.presence_of_element_located((By.ID, "username")))
inputElement = driver.find_element_by_id("username")
inputElement.send_keys('actualLogin')
inputElement = driver.find_element_by_id("passwd")
inputElement.send_keys('actualPassword')
inputElement.submit()

display.stop()