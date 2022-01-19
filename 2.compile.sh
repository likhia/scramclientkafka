#!/bin/bash

export KAFKA_LIB=/Users/lilim/temp/kafka/kafka_2.12-3.0.0

export CLASSPATH=$CLASSPATH:.:$KAFKA_LIB/libs/kafka-clients-3.0.0.jar:$KAFKA_LIB/libs/lz4-java-1.7.1.jar:$KAFKA_LIB/libs/snappy-java-1.1.8.1.jar:$KAFKA_LIB/libs/zstd-jni-1.5.0-2.jar:$KAFKA_LIB/libs/slf4j-api-1.7.30.jar

cd src

javac com/example/*.java

cd ..
