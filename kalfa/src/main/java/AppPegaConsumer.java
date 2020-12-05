import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppPegaConsumer {
    public static String topicName = "appPega";

    public static void main(String[] args) throws TimeoutException, StreamingQueryException {
        Logger.getLogger("org").setLevel(Level.OFF);

        SparkSession spark = SparkSession
                .builder()
                .config("spark.driver.memory", "1g")
                .config("spark.executor.memory", "1g")
                .appName("Kafka-appPega")
                .master("local")
                .getOrCreate();

        Dataset<Row> lines = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", topicName)
                .load();

        StreamingQuery read = lines
                .writeStream()
                .outputMode("complete")
                .format("console")
                .start();
        read.awaitTermination();

    }
}
