package DaeguLibCounter;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 
public class DaeguLibCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private IntWritable outputValue = new IntWritable();
    private Text outputKey = new Text();
    

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	
        DaeguLibParse parse = new DaeguLibParse();//탭별로 파싱
    	parse.Parsing(value);
    	
        outputKey.set(parse.getWord());
        outputValue.set(parse.getBorrowCount());
        context.write(outputKey, outputValue);

    }
}