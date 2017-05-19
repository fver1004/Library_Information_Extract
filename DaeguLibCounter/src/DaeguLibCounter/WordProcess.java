package DaeguLibCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/*
 * dic.txt파일은 다음과 같이 이루어짐
 * key
 * value
 * 
 * key
 * value	=><key, array> 쌍으로 생성자에서 저장한 뒤  Processing()에서 처리
 */
public class WordProcess {
	Path pt=new Path("hdfs:/dic.txt");//Location of file in HDFS
	LinkedHashMap<String, String[]> listMap = new LinkedHashMap<String, String[]>();
	BufferedReader br;
	
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

        	        for(String token : value){
        	        	if(bookname.toUpperCase().contains(token))
        	        		tokenList.add(key);
        	        }
    	    	}
    	        
    	    
    	
    	return tokenList;
    }
    
    
    

}
