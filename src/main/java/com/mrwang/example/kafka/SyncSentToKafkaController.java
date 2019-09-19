package com.mrwang.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrwang.example.kafka.operation.ConsumerOperation;
import com.mrwang.example.kafka.operation.ProducerOperation;
import com.mrwang.example.kafka.workcount.WordCountLambdaExample;

@RestController
@RequestMapping
public class SyncSentToKafkaController {

	@Autowired
	ProducerOperation producerConfig;
	@Autowired
	ConsumerOperation consumeConfig;
	@Autowired
	WordCountLambdaExample wordCountExample;

	@RequestMapping("hi")
	public String hello() {
		return "hi wanghanchao";
	}

	@RequestMapping("/kafka/streams/start")
	public String startKafkaStreams() throws Exception {
		wordCountExample.start();
		return "kafka streams wordcount program start....";
	}

	@RequestMapping("/push")
	public String push(String message) {
		producerConfig.push(message);
		return "ok";
	}

	@RequestMapping("/subscrite")
	public String subscrite() {
		consumeConfig.subscrite();
		return "ok";
	}

	@RequestMapping("/syncSubscrite")
	public String syncSubscrite() {
		consumeConfig.syncSubscrite();
		return "ok";
	}
}
