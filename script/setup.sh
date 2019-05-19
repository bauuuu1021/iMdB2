#!/bin/bash

# hadoop startup
hdfs namenode -format
start-dfs.sh
start-yarn.sh

# upload data to hadoop
INPUT_PATH="/tmp/movie"
hadoop fs -mkdir -p $INPUT_PATH
hadoop fs -put data/ratings.csv $INPUT_PATH
