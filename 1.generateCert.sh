#!/bin/bash

## Please remember to use oc login to your Openshift Cluster BEFORE running this script. 

## Name of the Openshift Project with the Kafka Cluster
export PROJECT=kafka

## Name of the Kafka cluster
export KAFKA_CLUSTER=example

## Name of the TLS Listener
export TLS_LISTENER=tls

## Name of the KafkaUser
export USER=user2


rm external.lb.url
rm ca.crt
rm user.auth


oc project $PROJECT

## Get URL of external load balancer
oc get service $KAFKA_CLUSTER-kafka-$TLS_LISTENER-bootstrap -o=jsonpath='{.status.loadBalancer.ingress[0].hostname}{"\n"}'  > external.lb.url

## Get the current certificate for the cluster CA
oc get secret $KAFKA_CLUSTER-cluster-ca-cert -o jsonpath='{.data.ca\.crt}' | base64 -d > ca.crt

## Get the sasl.jaas.config for the user
oc get secret $USER -o jsonpath='{.data.sasl\.jaas\.config}' | base64 -d > user.auth

keytool -keystore truststore.p12 -storepass welcome1 -noprompt -alias ca -import -file ca.crt -storetype PKCS12;
