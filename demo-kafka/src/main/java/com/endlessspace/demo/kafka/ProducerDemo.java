package com.endlessspace.demo.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kafka Producer API
 */
public class ProducerDemo {

	public static void main(String[] args) {
		ProducerDemo demo = new ProducerDemo();
		demo.startDemo();
	}

	/**
	 * 新版客户端Producer为线程安全的, 可以在多个线程间共享一个实例
	 */
	public void startDemo() {
		Producer<String, String> producer = new KafkaProducer<>(initProducerConfig());

		for (int i = 0; i < 100; i++) {
			LOG.info("send record: (" + Integer.toString(i) + ", " + Integer.toString(i) + ")");
			producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
		}

		producer.close();
	}

	/**
	 * 初始化Kafka配置信息
	 */
	private Properties initProducerConfig() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.33.11:9090");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		return props;
	}

	private static final Logger LOG = LoggerFactory.getLogger(Producer.class);
}
