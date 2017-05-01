import scrapy

class DaeguLibSpider(scrapy.Spider):
    name = "quotes1"
    pageTest = 1

    def start_requests(self):
        numberOfPage = 5
        for i in range(1,numberOfPage):
            yield scrapy.Request('https://lib.daegu.ac.kr/searchS/caz/result?os=&cpp=50&sNo=0&sq=005&st=SUBJ&oi=&msc=2000&pn=%d&briefType=T' % i, callback=self.pars)

    def pars(self, response):
        page = response.url.split("/")[-2]
        filename = 'quotes-%d.html' % DaeguLibSpider.pageTest
        DaeguLibSpider.pageTest +=1
        print("페이지:", DaeguLibSpider.pageTest)
        with open(filename, 'wb') as f:
            f.write(response.body)
        self.log('Saved file %d' % filename)

