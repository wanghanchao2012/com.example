package com.mrwang.example.kafka.operation;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProducerOperation {

	@Autowired
	@Qualifier("producerProperties")
	Properties producerProperties;

	public void push(String message) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("wordcount-input", message);
		// 创建一个kafkaProdecer对象，传入上面创建的Properties对象
		@SuppressWarnings("resource")
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProperties);
		long startTime = System.currentTimeMillis();

		try {
			System.out.println("send message:" + message);
			// 发送前面创建的消息对象record到kafka集群
			// 发送消息过程中可能发送错误，如无法连接到kafka集群，所以在这里使用捕获异常代码
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}

}
