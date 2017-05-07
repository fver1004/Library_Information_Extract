import scrapy
#import csv  - csv 모듈 사용해도 될듯함. 일단은 그냥 함

class BookSpider(scrapy.Spider):
    name = "DUBook"  #스파이더 네임
    pageIndex = 1
    link = open('C:\\Users\\Kiyeon\\git\\scrapyTest\\scrapyTest\\spiders\\Daegu_lib-1.csv')
    compare = ""
    lineList = []

    def start_requests(self):
        line = BookSpider.link.readline()
        while line != BookSpider.compare:
            #line = line.rstrip()#마지막 개행문자 꼭 지울필요가 있을까 좀있다가 없애보기
            BookSpider.lineList = line.split(",")
            print("one")
            #global a = 1
            yield scrapy.Request(BookSpider.lineList[6], callback=self.pars)
            print("two")
            line = BookSpider.link.readline()


    def pars(self, response):
        sel = response.selector
        filename = 'Book-%d.html' % BookSpider.pageIndex
        BookSpider.pageIndex +=1
        trNum = 0#도서 수
        borrowNum = 0#대출 수
        
        print("three")
        borrowStatList = sel.xpath('//table[@class="listtable"]/tbody/tr/td[@class="point"]/text()').extract()
        for borrowStat in borrowStatList:
            trNum += 1
            if borrowStat == "대출중":
                borrowNum += 1

        BookSpider.lineList.append(trNum)
        BookSpider.lineList.append(borrowNum)
        with open('output.txt', 'a+') as f:
            f.write("%s\n" % ",".join([str(i) for i in BookSpider.lineList]))



        #line 뒤에 ,대출현황  추가하고 다시 저장하면 될듯
