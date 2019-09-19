package com.mrwang.example.kafka.config;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

	@Bean("producerProperties")
	public Properties producerProperties() {
		// 创建一个Properties对象，用于存储连接kafka所需的配置信息
		Properties myKafkaProps = new Properties();
		// 配置kafka集群地址
		myKafkaProps.put("bootstrap.servers", "localhost:9092");
		// 向kafka集群发送消息，出了消息值本身，还包括key信息，key信息用于消息在partition之间均匀分布
		// 发送消息的key，类型为String,使用String类型的序列化器
		myKafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 发送消息的value，类型为String,使用String类型的序列化器
		myKafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return myKafkaProps;
	}

	@Bean("consumerProperties")
	public Properties consumerProperties() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test2");
		props.put("enable.auto.commit", "false");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 9000);
		props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 100000000);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

	@Bean("consumerAutoCommitProperties")
	public Properties consumerAutoCommitProperties() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test3");
		props.put("enable.auto.commit", "true");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

	public KafkaConsumer<String, String> kafkaConsumer(
			@Autowired @Qualifier("consumerProperties") Properties consumerProperties) {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerProperties);
		return consumer;
	}

}
