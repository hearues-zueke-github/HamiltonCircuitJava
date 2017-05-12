#! /usr/bin/python3.5

import numpy as np

from os.path import expanduser
from subprocess import call
from time import time

home_path = expanduser("~")

def save_one_string_combination(symbols, length):
    call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])

def save_some_string_combinations():
    for symbols in range(2, 7):
        for length in range(2, 6):
            call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])

def test_brute_force_vs_deterministic_search():
    length = 5

    start_graph = time()
    for symbols in range(10, 11):
            call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])
    end_graph = time()

    start_determ = time()
    for symbols in range(10, 11):
            call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombodeter", str(symbols), str(length)])
    end_determ = time()

    print("Taken time for graph:         {:.5f}s".format(end_graph-start_graph))
    print("Taken time for deterministic: {:.5f}s".format(end_determ-start_determ))

save_one_string_combination(4, 6)

# folder_path = home_path+"/Documents/string_combinations"
# fout = open("allcombinations_together.txt", "w")

# for length in range(2, 6):
#     fout.write("length: {}\n".format(length))
#     for symbols in range(2, 7):
#         fout.write("  symbols: {}\n".format(symbols))
#         with open(folder_path+"/sc_{}_{}.txt".format(symbols, length), "r") as f:
#             fout.write(f.readline())
#     fout.write("\n")

# fout.close()

# symbols = 3
# length = 4

# call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])

# folder_path = home_path+"/Documents/string_combinations"
# file_sc_path = folder_path+"/sc_{}_{}.txt".format(symbols, length)
# print("file_sc_path: {}".format(file_sc_path))

# with open(file_sc_path, "rb") as f:
#     lines = f.readlines()
#     lines = [list(map(int, l.decode("ascii").replace("\n", ""))) for l in lines]

# # for l in lines:
# #     print("l: {}".format(l))

# l = np.array(lines[0]).astype(np.uint8)

# # print("l: {}".format(l))

# matrix = np.zeros((length, len(l))).astype(np.uint8)
# matrix[0] = l
# # shift rows and make a matrix!
# for i in range(1, length):
#     matrix[i] = np.hstack((l[i:], l[:i]))
# matrix = matrix.T[:-length+1]
# matrix_str = ["["+",".join(map(str, row))+"]" for row in matrix]

# for i, row_str in enumerate(matrix_str):
#     print("i: {}, row_str: {}".format(i, row_str))
