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
    
    /*생성자처럼 map단계가 시작되면 setup을 시작함.
     *wordProcess class로 dic.txt의 단어 미리 로드.
     */
    protected void setup(Context context) throws IOException, InterruptedException {
    	wordProcess = new WordProcess();
    	System.out.println("dic.txt is loaded");
    }
    
    
    
    /* map단계 순서
     * 1.ScrapyOutput.csv에서 '책이름'과 '대출건수' 출력을 위해 parse 객체에서 csv파일의 라인단위로 파싱.
     * 2.대출건수가 0보다 크면, wordProcess.Processing() 메소드로 => 책이름에서 dic.txt에 존재하는 단어를 추출.
     * 3.각 단어와 건수를 reduce로 보냄.
     */
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