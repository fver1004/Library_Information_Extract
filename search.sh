#!/bin/bash
echo "검색하고자 하는 날짜를 입력하세요"
echo " ------ example--------- "
echo "     단일검색 170513     "
echo " 기간 검색 170513-170517 "
echo " ----------------------- "

path="/part-r-00000"
esipman=200000
read s_date



if [ `echo $s_date | grep -c "-" ` -gt 0 ]
then

  IFS='-' read -ra ADDR <<< "$s_date"
    
  startdate=${ADDR[0]}
  enddate=${ADDR[1]}

  dates=()
  count=0
  for (( date="$startdate"; date != enddate+1; )); do
    dates+=( "$date" )
    date="$(date --date="$date + 1 days" +'%g%m%d')"
    fnames[count]="/"$date$path
    count=$(($count+1))	
    
  done


  var=$( IFS=$' '; echo "${fnames[*]}" )
  echo "$var"
  echo "분석중... 결과파일없으면(20일없음) 오류남"
  hadoop jar /home/a7508/MCounter.jar $var /rm8 2>/dev/null | awk "{}"
  echo "결과:"
  hadoop fs -cat /rm8/part-r-00000 | sort -k2 -n -r -t$'\t'
  hadoop fs -rm -r /rm8/
 

else

  hadoop fs -cat /$s_date/* | sort -k2 -n -r -t$'\t'
  echo $s_date
  
fi

