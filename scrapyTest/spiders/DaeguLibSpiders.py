#_*_ coding:utf-8 _*_

import scrapy
from scrapyTest.items import BookInfoItem

class DaeguLibSpider(scrapy.Spider):
    name = "DULib"  #스파이더 네임
    #pageIndex = 1   #페이지 인덱스
    ignoreTd = [1,8]#도서 목록 table의 td 넘기기용 리스트
    link = ""



    def start_requests(self):
        numberOfPage = 2    #페이지 반복수-1
        for i in range(1,numberOfPage):## (Get 방식)참고:: cpp-페이지당 목록 수, pn-페이지넘버, briefType-노출타입(T-테이블형)
            yield scrapy.Request('https://lib.daegu.ac.kr/searchS/caz/result?os=&cpp=2&sNo=0&sq=005&st=SUBJ&oi=&msc=2000&pn=%d&briefType=T' % i, callback=self.pars)



    def pars(self, response):

        sel = response.selector#requests에 대한 response를 sel에 저장
        #filename = 'Daegu_lib-%d.csv' % DaeguLibSpider.pageIndex#페이지 인덱스로 파일이름 설정
        #DaeguLibSpider.pageIndex += 1#pars 실행될때마다 인덱스값 증가시킴
        #with open(filename, 'a+') as f:#a: append. file 내용에 append.
        oddNum = True #공백인 짝수 tr을 넘기기 위한 변수. for문을 한번 돌때마다 true와 false로 계속 바뀌게 함
        for tr in sel.css("table#briefTable>tbody>tr"):#css: xpath와 유사. 테이블 태그 찾아서 해당 테이블의 tr수만큼 반복
            if oddNum:#tr이 홀수일 경우
                #item = BookItem()#지금은 아이템 안써서 필요없음
                selTd = 1#Td index 초기화
                bookInfoList = []
                for td in tr.css("td"):#해당 td 수만큼 반복
                    if selTd in DaeguLibSpider.ignoreTd:#8개 td 중 2,3,4,5,6,7만 뽑기 위한 조건문
                        pass
                    elif selTd == 2:
                        #narmalize-space(): 개행문자, \t \n \r 등 없애줌, join(): list 문자열 합쳐줌, replace():특정 문자 바꿔줌
                        bookInfoList += td.xpath('normalize-space(p/text())').extract()
                    elif selTd == 3:
                        bookInfoList.append("".join(td.xpath('normalize-space(span/a/text())').extract()).replace(',','/'))
                        DaeguLibSpider.link = "https://lib.daegu.ac.kr" + " ".join(td.xpath('span/a/@href').extract())
                    elif selTd == 4:
                        bookInfoList.append("".join(td.xpath('normalize-space(text())').extract()).replace(',','/'))
                    elif selTd == 5:
                        bookInfoList.append("".join(td.xpath('normalize-space(text())').extract()).replace(',','/'))
                    elif selTd == 6:
                        bookInfoList.append("".join(td.xpath('normalize-space(text())').extract()).replace(',','/'))
                    elif selTd == 7:
                        bookInfoList += td.xpath('normalize-space(text())').extract()#list + list 가능!
                        bookInfoList.append(DaeguLibSpider.link)

                        #도서 url 파싱
                        item = BookInfoItem()#bookInfoList를 yield할때 인자로 담아서 같이 보내기 위한 용도
                        item['bookInfo'] = bookInfoList
                        requestBookInfo = scrapy.Request(DaeguLibSpider.link, callback=self.pars2)
                        requestBookInfo.meta['item'] = item
                        yield requestBookInfo
                            
                    else:
                        pass
                    selTd += 1

                oddNum = False
            else:
                oddNum = True



    def pars2(self, response):
        item = response.meta['item']#pars에서 extract한 북인포 리스트
        sel = response.selector
        trNum = 0#도서 수
        borrowNum = 0#대출 수
        
        borrowStatList = sel.xpath('//table[@class="listtable"]/tbody/tr/td[@class="point"]/text()').extract()
        for borrowStat in borrowStatList:
            trNum += 1
            if borrowStat.encode("utf-8") == "대출중":
                borrowNum += 1
                
        bookInfoItem = item['bookInfo']
        bookInfoList = bookInfoItem
        print("##########",bookInfoList)
        bookInfoList.append(str(trNum))
        bookInfoList.append(str(borrowNum))
        with open('ScrapyOutput.csv', 'a+') as f:
            f.write("%s\n" % ",".join([str(i.encode("utf-8")) for i in bookInfoList]))







