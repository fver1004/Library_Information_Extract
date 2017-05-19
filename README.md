ProjectName
===================

----------

프로젝트 참여: **권필진, 김기연** e-mail[^1]
<br>


![intro](http://i.imgur.com/X2i1h1B.png) : Introduction 
--------------------------------------------------------------


**ProjectName**은 오픈소스 scrapy와 Hadoop을 이용하여, 특정 사이트 페이지를 크롤링-파싱, 분석처리까지 자동화하는 데 목표를 두고 있는 간단한 프로젝트입니다. 소스코드에 들어있는 scrapy와 MapReduce 예제는 도서관의 내용을 파싱하여 최근의 컴퓨터공학분야에서 관심이 많은 항목들에 대한 분석을 위한 데모-프로그램/소스코드 입니다.
 

> **응용 오픈소스/오픈소스 소프트웨어:**

> - [Ubuntu](https://www.ubuntu.com/)
> - [Scrapy](https://scrapy.org/) (BSD 3-clause "New" or "Revised" License)
> - [Hadoop](http://hadoop.apache.org/) (APACHE LICENSE, VERSION 2.0) 


![env](http://i.imgur.com/FjjTjKG.png) : Build Environment 
------------------------------------------------------------


> **응용 프로그램 환경:**

> - Ubuntu 16.04.2 LTS 
> - Scrapy 1.3.3 :: Python 3.6.0 :: Anaconda 4.3.1
> - Hadoop 2.7.3 :: Java 1.8.0_121

![used](http://i.imgur.com/rDNTaGc.png) : How Can I used? 
-------------------------------------------------------


사용법에 대한 설명 : [WiKi](https://github.com/fver1004/scrapyTest/wiki/Installation-&-Configuration#how-to-install) 를 참조

![fucntion](http://i.imgur.com/4FLD2AM.png) : Implemented function
---------------------
### <i class="icon-refresh"></i> Scrapy

**도서 목록 현황을 Crawling함**
> - 도서관 웹페이지를 주기적 Crawling함
> - Crawling 데이터를 저장함

### <i class="icon-refresh"></i> Hadoop-MapReduce

> - 도서 목록 현황에서 대출 현황을 분석함
> - 대출 현황에서 인기 분야를 분석함

### <i class="icon-refresh"></i> Bash Shell을 이용한 자동화 

#### **주기적 scrapy를 통한 데이터 수집**
> -  일 단위로 분석 데이터 수집
> -  디렉토리를 날짜 별로 생성 후 구분  

#### **주기적으로 MapReduce 실행**
> -  HDFS 상으로 분석 데이터 Input
> -  MapReduce Run
> -  분석 결과 Ouput

----------

![ex](http://i.imgur.com/XPqPXiH.png) : examples
-------------
#### 동영상을 통해 실행과정을 볼 수 있습니다.
[![demo](http://i.imgur.com/97kz0DT.png)](https://player.vimeo.com/video/218344037)

----------
#### 실행결과는 다음과 같습니다.
![0517](http://i.imgur.com/oVuGzOc.png)![0521](http://i.imgur.com/sPZ5Y4U.png)
----------



![report](http://i.imgur.com/m6j6RuT.png) : Report
--------------------

> 2017.05.21 **Shell**  :: 빌드 :: [Shell 주기적 수행 구현 완료](https://github.com/fver1004/scrapyTest/commit/8ad27057e8bfacd627e319b6cd3a1be74f1b514b)<P>
> 2017.05.21 **MapReduce** :: 수정 :: [정규표현식 + 단어로 패턴 검색](https://github.com/fver1004/scrapyTest/commit/d470d3ce9ae90aee2f492b0b338fa5eec4a2f3eb)<p>
> 2017.05.19 **MapReduce** :: 수정 :: 형태소 분석 오픈소스 안쓰고 저장된 단어로 연산<p>
> 2017.05.19 **MapReduce** :: 빌드 :: 형태소 분석을 위한 MapReduce 임시 구현<p>
> 2017.05.14 **Scrapy** :: 수정 :: [인코딩 에러 수정](https://github.com/fver1004/scrapyTest/commit/4093f448cde791c39c6f90b606fc3752c5730ca4)<p>
> 2017.05.14 **MapReduce** :: 빌드 :: 맵리듀스 임시 구현<p>
> 2017.05.12 **Shell**  :: 빌드 :: [Shell과 crontab을 이용하여 주기적으로 수행](https://github.com/fver1004/scrapyTest/commit/f7326e5b11bd83c20e0c94885b60b4c1311626ac)<p>
> 2017.05.10 **Shell**  :: 빌드 :: Scrapy와 MapReduce 주기적으로 수행할 Shell 코딩<p>
> 2017.05.08 **Scrapy** :: 빌드 :: 'BookSpider.py', 'DaeguLibSpider.py' 통합<p>
> 2017.05.07 **Scrapy** :: 빌드 :: column '도서 수', '대출 건수' 추가.<p>
> 2017.05.04 **Scrapy** :: 빌드 :: column '도서 URL' 추가.<p>
> 2017.05.02 **Scrapy** :: 빌드 :: 파싱 후 '.csv' file로 저장<p>
> 2017.05.02 **Scrapy** :: 빌드 :: 'Daegu Univ. 도서 목록' 크롤링<p>
> 2017.05.02 **Scrapy** :: (Ubuntu env) 테스트<p>
> 2017.04.29 Project 시작<p>



  [^1]: **e-mail**<br>
  권필진 [kwon7508@daegu.ac.kr](mailto:kwon7508@daegu.ac.kr) <br>
  김기연 [fver1004@gmail.com](mailto:fver1004@gmail.com) 
