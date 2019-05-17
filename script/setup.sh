#!/bin/bash

# hadoop startup
hadoop namenode -format
start-all.sh

# upload data to hadoop
INPUT_PATH="/tmp/movie"
hadoop fs -mkdir -p $INPUT_PATH
hadoop fs -put ../data/ratings $INPUT_PATH
