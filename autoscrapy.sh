#!/bin/bash
# 오늘 날짜를 확인하기 위한 변수 선언
NOW=$(date +"%m%d")
# 결과 파일을 분류 저장하기 위한 폴더 생성
mkdir ~/result/$NOW
# 스크래피 명령 실행
scrapy runspider $SCRAPY_HOME/DaeguLibSpider.py
# HDFS 상에 일별 폴더 생성
hadoop fs -mkdir /in_$NOW
# HDFS 상으로 CSV input
hadoop fs -put ~/result/$NOW/* 
# 스크래피 분석 Map Ruduce 실행
hadoop jar ~/DaeguLibCounter.jar DeguLinCounter.DaeguLibCount /$NOW /out_$NOW

#---------------------------------#
# 일단위 크롤링을 위한 crontab 설정 #
# 00 01 * * * $path/autoscrapy.sh #
#     매일 새벽 한시 크롤링 실행    #
#---------------------------------#
#------------------------------------------------------------------#
# crontab의 경우 path가 해제됨.                                     
# 따라서 다음과 같이 명령어를 수행하여야함                            
# 분 시간 일 월 요일 user 명령어                                    
# * * * * * cd scrapy_project_forder/project_name && autoscrapy.sh
# 경로를 이동하지 않으면 runspider: error: Unable to load 에러 발생
#------------------------------------------------------------------#
