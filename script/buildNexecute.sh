#!/bin/bash

hadoop com.sun.tools.javac.Main MovieAvgRate.java
jar cf movie.jar MovieAvgRate*.class

#Excute
INPUT_PATH="/tmp/movie"
OUTPUT_PATH="/tmp/result"

hadoop fs -rm $OUTPUT_PATH/*
hadoop fs -rmdir $OUTPUT_PATH
hadoop jar movie.jar MovieAvgRate $INPUT_PATH $OUTPUT_PATH