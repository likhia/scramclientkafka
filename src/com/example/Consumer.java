package com.example;

import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.Duration;

import org.apache.kafka.clients.consumer.KafkaConsumer; 
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;


public class Consumer {

    public static void main(String[] args) {

        String certPath="..";
     
	if (args.length ==0) {
                System.out.println("Please set the url of external load balancer as 1st parameter and sasl.jaas.config as 2nd parameter.");
		return;
        }

	Properties properties = new Properties();
        properties.put("bootstrap.servers", args[0] + ":9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("security.protocol", "SASL_SSL");
        properties.put("ssl.truststore.location", certPath + "/truststore.p12");
        properties.put("ssl.truststore.password",  "welcome1");
        properties.put("ssl.truststore.type", "PKCS12");
        
        properties.put("sasl.mechanism", "SCRAM-SHA-512");
        properties.put("sasl.jaas.config" , args[1]);
        properties.put("group.id", "my-group");

        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add("my-topic");
        kafkaConsumer.subscribe(topics);
	System.out.println("subscribing..  ");
        
        try{
            while (true){
                ConsumerRecords records = kafkaConsumer.poll(Duration.ofMillis(1000));
		Iterator recordsiter = records.iterator();
		while(recordsiter.hasNext())
		{
		    ConsumerRecord record = (ConsumerRecord) recordsiter.next();
                    System.out.println(String.format("Topic - %s, Partition - %d, Value: %s", record.topic(), record.partition(), record.value()));
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            kafkaConsumer.close();
        }
    }
}

