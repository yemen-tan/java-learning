package com.simon.kafka;

import com.alibaba.fastjson.JSON;
import com.simon.pojo.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyKafkaProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();

        // 设置kafka集群的主机，集群有多个用逗号分割
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Const.KAFKA_IP + ":9092");

        properties.put(ProducerConfig.CLIENT_ID_CONFIG, Const.MY_TOPIC);

        /*
            对kafka的key和value序列化,key决定投递到该主题的那个分区
            Q:为什么要序列化
            A:kafka的broker只能处理二进制的数据
         */
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 100; i++) {
            // 构建record
            User user = new User();
            user.setId(i);
            user.setName("simon" + i);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(Const.MY_TOPIC, JSON.toJSONString(user));
            producer.send(record);
        }
        producer.close();
    }
}
