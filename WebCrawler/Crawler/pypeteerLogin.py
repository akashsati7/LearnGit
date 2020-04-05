# import os
#
# import asyncio
# import time
# from os import path
#
# from pyppeteer import launch
#
#
# def screen_size():
#     """ use tkinter to get screen size """
#     import tkinter
#     tk = tkinter.Tk()
#     width = tk.winfo_screenwidth()
#     height = tk.winfo_screenheight()
#     tk.quit()
#     return width, height
#
#
# async def main():
#     js1 = '''() =>{
#
#         Object.defineProperties(navigator,{
#         webdriver:{
#             get: () => false
#             }
#         })
#     }'''
#
#     js2 = '''() => {
#         alert (
#             window.navigator.webdriver
#         )
#     }'''
#
#
#     # browser = await launch({'headless': True, 'args': ['--no-sandbox','--disable-dev-shm-usage','--disable-setuid-sandbox']})
#     # browser = await launch({'args': ['--no-sandbox','--disable-dev-shm-usage','--disable-setuid-sandbox']})
#
#     browser = await launch(headless=True, ignoreHTTPSErrors=True, args=['--no-sandbox','--disable-dev-shm-usage','--disable-setuid-sandbox'])
#
#     filename = 'trace.json'
#
#     print(browser)
#
#     page = await browser.newPage()
#     await page.tracing.start({'path': filename});
#     await page.setViewport({ 'width': 1600, 'height': 800 });
#     width, height = screen_size()
#     await page.setViewport({  # maximize window
#         "width": width,
#         "height": height
#     })
#     await page.goto('https://kite.zerodha.com/')
#     # await page.evaluate(js1)
#     # await page.evaluate(js2)
#
#     name = await page.waitForXPath("//*[(@placeholder='User ID' and @type='text')]")
#     await name.type("UV8580")
#     print("uv")
#
#     pwd = await page.waitForXPath("//*[(@placeholder='Password')]")
#     await pwd.type('Leo@373737')
#
#     submit = await page.waitForXPath("//button[(@type='submit')]")
#     await submit.click()
#
#     pin = await page.waitForXPath("//*[(@animate='true') and (@label='PIN')]")
#     await pin.type('373737')
#
#     submit = await page.waitForXPath("//button[(@type='submit')]")
#     await submit.click()
#
#     print("submitted")
#
#     searchBar = await page.waitForXPath("//input[(@id='search-input')]")
#     await searchBar.type("ACC");
#
#     firstItem = await page.waitForXPath("//li[(@class='search-result-item selected')]");
#     print(firstItem)
#     await firstItem.hover();
#
#
#     buyButton = await page.waitForXPath("//button[(@class='button-blue')]")
#     await buyButton.click();
#
#     advancedButton = await page.waitForXPath("//*[(@class='advanced-options-open')]")
#     await advancedButton.click();
#
#     quantity = await page.waitForXPath("//input[(@type='number')]")
#     print(quantity)
#     await quantity.click();
#     await quantity.type("1");
#
#     coButton = await page.waitForXPath("//input[(@type='radio') and (@title='After market order')]")
#     await coButton.click();
#
#     limitOrder = await page.waitForXPath("//input[(@type='radio') and (@title='Limit')]")
#     await limitOrder.click();
#
#     price = await page.waitForXPath("//input[(@type='number') and (@label='Price')]")
#     await price.type("10");
#
#     submit = await page.waitForXPath("//button[(@type='submit')]")
#     await submit.click();
#
#     while True:
#         print()
#
#     # click_yzm = await page.xpath('//form/section[1]/button[1]')
#     # input_yzm = await page.xpath('//form/section[2]/input[1]')
#     # but = await page.xpath('//form/section[2]/input[1]')
#     # print(input_sjh)
#     # await input_sjh[0].type('***** mobile phone no. ********')
#     # await click_yzm[0].click()
#     # ya = input(' please enter the verification code ：')
#     # await input_yzm[0].type(str(ya))
#     # await but[0].click()
#     # await page.goto('https://www.ele.me/home/')
#     # await asyncio.sleep(100)
#     # await browser.close()
#
#
# asyncio.get_event_loop().run_until_complete(main())