# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy.item import Item, Field

class BookInfoItem(scrapy.Item):
        bookInfo = scrapy.Field()

class BookItem(scrapy.Item):
        bookType = scrapy.Field()
        bookName = scrapy.Field()
        author = scrapy.Field()
        publisher = scrapy.Field()
        symbol = scrapy.Field()
        date = scrapy.Field()


class ScrapytestItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass
