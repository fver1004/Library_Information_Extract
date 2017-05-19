package DaeguLibCounter;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 
public class DaeguLibCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private IntWritable outputValue = new IntWritable();
    private Text outputKey = new Text();
    WordProcess wordProcess;
    
    protected void setup(Context context) throws IOException, InterruptedException {
    	wordProcess = new WordProcess();
    	System.out.println("dic.txt is loaded");
    }
    
    
    
    DaeguLibCountMapper() throws IOException{

    }
    
    //WordProcess 어디 선언할지
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	
        DaeguLibParse parse = new DaeguLibParse();
        
    	parse.Parsing(value);
        if (parse.getBorrowCount() > 0) {
        	
        	List<String> tokenList = wordProcess.Processing(parse.getName());
        	
        	for(String word : tokenList){
        		outputKey.set(word.toString());
            	outputValue.set(parse.getBorrowCount());
            	context.write(outputKey, outputValue);
        	}
        	
        }
    }
}