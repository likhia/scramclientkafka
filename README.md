###  How to run the sample 

Install kafka_2.12-3.0.0 on your local machine.
 
* Step 1:  Edit the environment variables in 1.generateCert.sh.
  * PROJECT - Name of the Openshift Project with the Kafka Cluster
  * KAFKA_CLUSTER - Name of the Kafka cluster
  * TLS_LISTENER - Name of the TLS Listener
  * USER - Name of the KafkaUser 

* Step 2: ./1.generateCert.sh

* Step 3: Update the path to kafka_2.12-3.0.0 in 2.compile.sh, 3.runConsumer.sh and 4.runProducer.sh.

* Step 4: ./2.compile.sh 

* Step 5: ./3.runConsumer.sh

* Step 6: Use another terminal to run 4.runProducer.sh
