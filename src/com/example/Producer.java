package com.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;

public class Producer {

    public static void main(String[] args){

	String certPath="..";

	if (args.length == 0) {
		System.out.println("Please set the url of external load balancer as 1st parameter and sasl.jaas.config as 2nd parameter.");
		return; 
	}

        Properties properties = new Properties();
        properties.put("bootstrap.servers", args[0] + ":9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	properties.put("security.protocol", "SASL_SSL");
	properties.put("ssl.truststore.location", certPath + "/truststore.p12");
	properties.put("ssl.truststore.password",  "welcome1");
        properties.put("ssl.truststore.type", "PKCS12");
        
	properties.put("sasl.mechanism", "SCRAM-SHA-512");
        properties.put("sasl.jaas.config" , args[1]);

	properties.put("group.id", "my-group");

        KafkaProducer kafkaProducer = new KafkaProducer(properties);


        try{

            for(int i = 0; i < 3; i++){
                System.out.println(i);
                kafkaProducer.send(new ProducerRecord("my-topic", Integer.toString(i), "my-topic message - " + i ));
	    	kafkaProducer.flush();
	    }
            System.out.println("closing"); 
	    kafkaProducer.close();
            System.out.println("closed");
        
	}catch (Exception e){
            e.printStackTrace();
        }finally {
            //kafkaProducer.close();
        }
	
	System.out.println("end");
    }
}
