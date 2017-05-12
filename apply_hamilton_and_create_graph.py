#! /usr/bin/python3.5

import numpy as np

from os.path import expanduser
from subprocess import call

home_path = expanduser("~")

for symbols in range(2, 6):
    for length in range(2, 8):
        call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])

symbols = 3
length = 4

call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])

folder_path = home_path+"/Documents/string_combinations"
file_sc_path = folder_path+"/sc_{}_{}.txt".format(symbols, length)
print("file_sc_path: {}".format(file_sc_path))

with open(file_sc_path, "rb") as f:
    lines = f.readlines()
    lines = [list(map(int, l.decode("ascii").split(","))) for l in lines]

# for l in lines:
#     print("l: {}".format(l))

l = np.array(lines[0]).astype(np.uint8)

# print("l: {}".format(l))

matrix = np.zeros((length, len(l))).astype(np.uint8)
matrix[0] = l
# shift rows and make a matrix!
for i in range(1, length):
    matrix[i] = np.hstack((l[i:], l[:i]))
matrix = matrix.T[:-length+1]
matrix_str = ["["+",".join(map(str, row))+"]" for row in matrix]

for i, row_str in enumerate(matrix_str):
    print("i: {}, row_str: {}".format(i, row_str))
