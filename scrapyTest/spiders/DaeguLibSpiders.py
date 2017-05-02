import scrapy
from scrapyTest.items import BookItem
from scrapy.loader.processors import MapCompose

class DaeguLibSpider(scrapy.Spider):
    name = "quotes1"#스파이더 네임
    pageTest = 1    #페이지 넘기기용
    ignoreTd = [1,8]   #td 넘기기용

    def start_requests(self):
        numberOfPage = 2    #페이지 반복수-1
        for i in range(1,numberOfPage):
            yield scrapy.Request('https://lib.daegu.ac.kr/searchS/caz/result?os=&cpp=50&sNo=0&sq=005&st=SUBJ&oi=&msc=2000&pn=%d&briefType=T' % i, callback=self.pars)

    def pars(self, response):


        sel = response.selector
        filename = 'Daegu_lib-%d.csv' % DaeguLibSpider.pageTest
        DaeguLibSpider.pageTest += 1
        with open(filename, 'a+') as f:
            oddNum = True #공백인 짝수 tr을 넘기기 위한 변수임
            for tr in sel.css("table#briefTable>tbody>tr"):
                if oddNum:
                    item = BookItem()
                    selTd = 1
                    for td in tr.css("td"):
                        print("ㅡㅡㅡㅡㅡㅡㅡㅡ%d번째 td" % selTd)
                        #8개 td 중 2,3,4,5,6,7 만 뽑기 위한거
                        if selTd in DaeguLibSpider.ignoreTd:
                            pass
                        elif selTd == 2:
                            item['bookType'] = td.xpath('normalize-space(p/text())').extract()
                        elif selTd == 3:
                            item['bookName'] = td.xpath('normalize-space(span/a/text())').extract()
                        elif selTd == 4:
                            item['author'] = td.xpath('normalize-space(text())').extract()
                        elif selTd == 5:
                            item['publisher'] = td.xpath('normalize-space(text())').extract()
                        elif selTd == 6:
                            item['symbol'] = td.xpath('normalize-space(text())').extract()
                        elif selTd == 7:
                            item['date'] = td.xpath('normalize-space(text())').extract()
                        else:
                            pass
                        selTd += 1

                    f.write("%s\n" % item)
                    oddNum = False
                else:
                    oddNum = True
