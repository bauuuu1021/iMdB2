import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MovieAvgRate {
    public static class WordCountMapper
            extends Mapper<Object, Text, Text, FloatWritable>{
                
        private FloatWritable rate  = new FloatWritable();
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer st = new StringTokenizer(value.toString(), "\n");
            while (st.hasMoreTokens()) {
				String[] array = st.nextToken().split(",");
				System.out.println(array[1]+" "+array[2]);

                // multipy by 10 to avoid type mismatch 
                // (expected integer but rating score might be float)
                word.set(array[1]);
                rate.set(Float.parseFloat(array[2]));
                context.write(word, rate);
            }
        }
    }

    public static class WordCountReducer
            extends Reducer<Text,FloatWritable,Text,FloatWritable> {

        private FloatWritable result = new FloatWritable();

        @Override
        public void reduce(Text key, Iterable<FloatWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            float reduceSum = 0;
            int iter = 0;
            for (FloatWritable val : values) {
                reduceSum += val.get();
                iter += 1;
            }
            result.set((float)reduceSum/(iter));
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        Job job = Job.getInstance(config, "hadoop word count example");
        job.setJarByClass(MovieAvgRate.class);
        job.setReducerClass(WordCountReducer.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);

        // first argument : input dir.
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // second argument : output dir.
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
