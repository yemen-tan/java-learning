package com.simon.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class MyKafkaConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();

        // 设置kafka集群的主机，集群有多个用逗号分割
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Const.KAFKA_IP + ":9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my_group_id");

        // 会话超时，5秒
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 9000);

        // 开启自动提交，没5秒提交一次
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 5000);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 订阅感兴趣的主题
        consumer.subscribe(Collections.singletonList(Const.MY_TOPIC));

        try {
            while (true) {
                // 获取主题下所有的消息（所有分区），最大阻塞时间为1秒
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                Set<TopicPartition> partitions = records.partitions();
                for (TopicPartition topicPartition : partitions) {
                    // 获取到每一个分区的消息
                    List<ConsumerRecord<String, String>> partitionRecodes = records.records(topicPartition);
                    // 当前分区下的消息的数量
                    int size = partitionRecodes.size();
                    // 获取主题的名字
//                String topic = topicPartition.topic();

                    for (int i = 0; i < size; i++) {
                        // 获取每一个消息的value
                        ConsumerRecord<String, String> stringStringConsumerRecord = partitionRecodes.get(i);
                        String value = stringStringConsumerRecord.value();
                        // 获取当前的偏移量
                        long offset = stringStringConsumerRecord.offset();
                        System.out.println("********************" + value + "************************");
                    }
                }
            }
        } finally {
            consumer.close();
        }

    }
}
