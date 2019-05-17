############################################
############### set variable ###############
############################################
# LINE : total lines of input file
# SPLIT : expected to split into # parts
# IN_FILE : location of input file
# OUT_PATH : loc. to store the split files
LINE = 27753444
SPLIT = 4
IN_FILE = 'big_data/ratings.csv'
OUT_PATH = 'input/'
############################################

size_per_part = LINE // SPLIT
read_file = open(IN_FILE,'r')

# the 0~(n-2)th parts
for i in range(SPLIT - 1):
    write_file = open('%spart_%d_ratings.csv'%(OUT_PATH,i),'w')
    for j in range(size_per_part):
        write_file.write(read_file.readline())
    write_file.close()

# the n-1 th part
write_file = open('%spart_%d_ratings.csv'%(OUT_PATH,SPLIT - 1),'w')
while (True):
    line = read_file.readline()
    write_file.write(line)
    if (not line):
        break

write_file.close()
read_file.close()

