import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.LongAccumulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;


/**
 * dùng spark đọc data từ hdfs
 * subscribe vào topic appPega
 * truyền data vào topic appPega
 */

public class AppPegaProducer1 {
    public static String topicName = "testDemo";

//    public static KafkaProducer createProducer() {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        //If the request fails, the producer can automatically retry,
//        props.put("retries", 0);
//        //Specify buffer size in config
//        props.put("batch.size", 16384);
//        //Reduce the no of requests less than 0
//        props.put("linger.ms", 1);
//        //The buffer.memory controls the total amount of memory available to the producer for buffering.
//        props.put("buffer.memory", 33554432);
//
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        return new KafkaProducer(props);
//    }

    public static void main(String[] args) throws TimeoutException, StreamingQueryException {
        Logger.getLogger("org").setLevel(Level.OFF);

//        String path = "hdfs://10.3.71.86:8020/Data/Logging/kinghub/app/2020-09-01";
        String INPUT_DIRECTORY = "E:\\parquet_logfile_at_00h_00.snap";

        SparkSession spark = SparkSession
                .builder()
                .config("spark.driver.memory", "1g")
                .config("spark.executor.memory", "1g")
                .appName("Kafka-appPega")
                .master("local")
                .getOrCreate();

        Dataset<Row> data = spark
                .readStream()
                .parquet(INPUT_DIRECTORY);

        data.printSchema();
<<<<<<< HEAD

        StreamingQuery query = data
                .selectExpr("adId","type")
                .writeStream()
                .format("kafka")
                .option("kafka.bootstrap.servers","localhost:9092")
                .option("topic", "testDemo")
                .start();
=======
        StreamingQuery query = data
            .selectExpr("adId","type")
            .writeStream()
            .format("kafka")
            .option("kafka.bootstrap.servers","localhost:9092")
            .option("topic", "testDemo")
            .save();
>>>>>>> 7f014ba73adfc86035468002bdec74125f3a2073
        query.awaitTermination();

        System.out.println("Message sent successfully");
    }


}
