package DaeguLibCounter;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
 
public class DaeguLibCount {
 
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage error : 2개의 인수가 필요합니다.");
            System.exit(2);
        }
        
        // 하둡 환경설정 불러오기
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "ArrivalDelayCount");
        
        // 입력, 출력 경로 설정
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
        // job class 설정하기
        job.setJarByClass(DaeguLibCount.class);
        // Mapper class 설정하기
        job.setMapperClass(DaeguLibCountMapper.class);
        // Reducer class 설정하기
        job.setReducerClass(DaeguLibCountReducer.class);
        //  입력  data format 설정하기 
        job.setInputFormatClass(TextInputFormat.class);
        // 출력 data format 설정하기
        job.setOutputFormatClass(TextOutputFormat.class);
        // output key, value 설정하기 
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        job.waitForCompletion(true);
    }
}