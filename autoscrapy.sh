# Scrapy Project folder안에 shell, Mapper.jar을 위치시켜야 정상작동

# 환경에 맞게 각각의 PATH 설정
export HOME=/home/user_name
export SCRAPY_HOME=/scrapy_path
export RESULT_HOME=/result_path

# 오늘 날짜를 확인하기 위한 변수 선언
MD=$(date +"%m%d")
YMD=$(date +"%y%m%d")

# 결과 파일을 분류 저장하기 위한 폴더 생성
mkdir $RESULT_HOME/$MD

# 스크래피 명령 실행
scrapy runspider $SCRAPY_HOME/DaeguLibSpider.py

# 실행결과 파일을 result 폴더로 이동
mv ScrapyOutput.csv $RESULT_HOME/$MD/

# HDFS 상에 일별 폴더 생성
hadoop fs -mkdir /$MD

# HDFS 상으로 금일 crawling file input
hadoop fs -put $RESULT_HOME/$MD/* /$MD

# crawling file mapreduce 실행
hadoop jar DaeTestSetUp.jar /$MD /$YMD

#---------------------------------#
# 일단위 크롤링을 위한 crontab 설정 #
# 00 01 * * * $path/autoscrapy.sh #
#     매일 새벽 한시 크롤링 실행    #
#---------------------------------#
#------------------------------------------------------------------#
# crontab의 경우 path가 해제됨.                                     
# 따라서 다음과 같이 명령어를 수행하여야함
# Hadoop 명령을 실행하기 위해서는 crontab PATH를 수정하여야함 
# PAHT=hadoop_home/bin 경로를 추가해야됨
# 분 시간 일 월 요일 user 명령어                                    
# * * * * * cd scrapy_project_forder/project_name && autoscrapy.sh
# 경로를 이동하지 않으면 runspider: error: Unable to load 에러 발생
#------------------------------------------------------------------#
