#!/bin/bash
# 현재 시간을 알기 위한 변수 선언
NOW= $(date +"%H")
date= $(date +"%m%d")
# 금일 스크래피 사용 확인을 위한 Count 변수 추가
count= 0
while true;
	do
		if [$NOW = 1 -a count = 1]
		then
			count = 0
			fi
			if [$NOW = 1 -a count = 0 ]
			then
				# 스크래피 명령 실행
				scrapy runspider DaeguLibSpider.py
				# HDFS 상으로 CSV input
				hadoop fs -put $HOME/Scarpy*.csv 
				# 스크래피 분석 Map Ruduce 실행
				hadoop jar DeaguLibCount.java /input /output
				count= 1
				# 끝난 시간을 log날짜.txt에 저장
				date > ~/log$[date].txt
				fi
	done
