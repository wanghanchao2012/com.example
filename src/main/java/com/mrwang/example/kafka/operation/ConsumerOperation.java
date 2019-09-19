package com.mrwang.example.kafka.operation;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConsumerOperation {

	@Autowired
	@Qualifier("consumerAutoCommitProperties")
	Properties consumerAutoCommitProperties;

	@Autowired
	@Qualifier("consumerProperties")
	Properties consumerProperties;

	public String syncSubscrite() {
		String topic = "wordcount-output";

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerProperties);
		consumer.subscribe(Arrays.asList(topic));
		try {
			while (true) {
				Long start = System.currentTimeMillis();
				System.out.println("start--------------->>" + start);
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
				Long end = System.currentTimeMillis();
				System.out.println("end--------------->>" + end);
				for (TopicPartition partition : records.partitions()) {
					List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
					for (ConsumerRecord<String, String> record : partitionRecords) {
						System.out.println("syncSubscrite----->" + record.offset() + ": " + record.value());
					}
					long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
					System.out.println("sync subscrite current partition : " + partition.partition() + ",offset is : "
							+ lastOffset);
					consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
				}
			}
		} finally {
			consumer.close();
		}
	}

	public String subscrite() {
		String topic = "wordcount-output";

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerAutoCommitProperties);
		consumer.subscribe(Arrays.asList(topic));
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(1000);
					// System.out.println(records.count());
					for (ConsumerRecord<String, String> record : records) {
						System.out.println("offset:" + record.offset() + ",partition:" + record.partition() + ",topic:"
								+ record.topic() + ",value:" + record.value());
//		                consumer.seekToBeginning(new TopicPartition(record.topic(), record.partition()));
					}
				}
			}
		};
		new java.lang.Thread(runnable).start();
		return "ok";
	}
}
