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
            System.err.println("Usage error : 2���� �μ��� �ʿ��մϴ�.");
            System.exit(2);
        }
        
        // �ϵ� ȯ�漳�� �ҷ�����
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "ArrivalDelayCount");
        
        // �Է�, ��� ��� ����
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
        // job class �����ϱ�
        job.setJarByClass(DaeguLibCount.class);
        // Mapper class �����ϱ�
        job.setMapperClass(DaeguLibCountMapper.class);
        // Reducer class �����ϱ�
        job.setReducerClass(DaeguLibCountReducer.class);
        //  �Է�  data format �����ϱ� 
        job.setInputFormatClass(TextInputFormat.class);
        // ��� data format �����ϱ�
        job.setOutputFormatClass(TextOutputFormat.class);
        // output key, value �����ϱ� 
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        job.waitForCompletion(true);
    }
}