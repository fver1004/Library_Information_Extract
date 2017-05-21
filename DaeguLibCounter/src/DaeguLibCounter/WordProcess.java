package DaeguLibCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/*
 * dic.txt파일은 다음과 같이 이루어짐
 * key
 * value
 * 
 * key
 * value
 * -------------------------
 * WordProcess() : Map이 실행되면 hdfs 내의 dic.txt를 LinkedHashMap에 로드함.
 * <key, array> 쌍으로 로드된 뒤  Processing()에서 처리
 */
public class WordProcess {
	Path pt=new Path("hdfs:/dic.txt");//Location of file in HDFS
	LinkedHashMap<String, String[]> listMap = new LinkedHashMap<String, String[]>();
	BufferedReader br;
	String patternL = "(^|[^A-Z]|\\s)";
	String patternR = "(\\s|[^A-Z+]|$)";
	Pattern p;
	Matcher m;
	
	/* LinkedHashMap 구성
	 * <String, String[]>
	 * <"단어","value,value2,value3...">
	 * 
	 * 정규식(patternL, patternR) 이용하여 예외문자들 어느정도 필터링
	 * 
	 */
	WordProcess() throws IOException{
		
	    FileSystem fs = FileSystem.get(new Configuration());
	    br=new BufferedReader(new InputStreamReader(fs.open(pt)));
	    
	    String lineKey=br.readLine();
	    
	    while (lineKey != null){
	    	
	    	String lineValue = br.readLine();//값들 읽기
	    	String[] columns = lineValue.toString().split(",");
	    	listMap.put(lineKey, columns);
	    	
	    	br.readLine();//공백
	    	lineKey=br.readLine();//인덱스키 읽기
	    }
	}
	

    public List<String> Processing(String bookname) throws IOException{
    	
        	List<String> tokenList = new ArrayList<String>();
        	
        	
    	    for(Map.Entry<String, String[]> entry : listMap.entrySet()){
    	    	String key = entry.getKey();
    	    	String[] value = entry.getValue();
    	    	
    	    	//HASH value 배열 값들 비교. 정규표현식으로 아닌거 걸러내기
        	        for(String token : value){
        	        	
        	        	String pattern = patternL + token + patternR;
        	        	
        	        	p = Pattern.compile(pattern);
        	        	m = p.matcher(bookname);
        	        	
        	        	if(m.find()){
        	        		tokenList.add(key);
        	        		break;}//중복토큰 없도록 for문 break
        	        	
        	        }
    	    	}
    	        
    	    
    	
    	return tokenList;
    }
    
    
    

}
