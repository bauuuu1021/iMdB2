# iMdB2
contributed by < `bauuuu1021` >
## Install Java and Hadoop
* [reference](https://hackmd.io/s/rk72mDZj4)

## Setup environment
```
$ sh script/setup.sh
```
### Special case
* If you have started namenode/resourcemanger, please execute `sh stop-all.sh` before executing command above.
* After that, **Choose `n` when you see**
    ```
    Re-format filesystem in Storage Directory root= /tmp/hadoop-(nameOfComputer)/dfs/name; location= null ? (Y or N) 
    ```
## Build and Execute
```
$ sh script/buildNexecute.sh
```

## Dataset
* Data size (after decompression)
    * Small : ~3.3MB
    * Full  : ~1.2GB (please download by yourself)
* reference : [MovieLens - recommended for education and development](https://grouplens.org/datasets/movielens/)