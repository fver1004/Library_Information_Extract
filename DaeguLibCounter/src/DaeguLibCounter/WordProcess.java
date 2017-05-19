package DaeguLibCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class WordProcess {
	Path pt=new Path("hdfs:/dic.txt");//Location of file in HDFS
	BufferedReader br;
	
	WordProcess() throws IOException{
		
	    FileSystem fs = FileSystem.get(new Configuration());
	    br=new BufferedReader(new InputStreamReader(fs.open(pt)));
	    
	}
	

    public List<String> Processing(String bookname) throws IOException{

        	List<String> tokenList = new ArrayList<String>();
        	
        	String line;
    	    line=br.readLine();
    	    while (line != null){
    	        if(bookname.toUpperCase().contains(line))
    	        	tokenList.add(line);
    	        
    	        line=br.readLine();
    	    }
    	
    	return tokenList;
    }
    
    
    

}
