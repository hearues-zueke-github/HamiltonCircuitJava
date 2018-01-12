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
	length = 4

	start_graph = time()
	for symbols in range(2, 6):
			call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombo", str(symbols), str(length)])
	end_graph = time()

	start_determ = time()
	for symbols in range(26, 27):
			call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombodeter", str(symbols), str(length)])
	end_determ = time()

	print("Taken time for graph:         {:.5f}s".format(end_graph-start_graph))
	print("Taken time for deterministic: {:.5f}s".format(end_determ-start_determ))

def get_string_combinations(symbols, length):
	call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "getstringcombodeter", str(symbols), str(length)])

def check_string_combinations(line, symbols, length):
	m = np.zeros((symbols**length, length)).T.astype(np.int)
	m[0] = line
	for i in range(1, length):
		m[i] = np.hstack((line[i:], line[:i]))
	m = m.T
	m = m*[symbols**i for i in range(0, length)]
	m = np.sum(m, axis=1)
	m = np.sort(m)
	return np.all(m[1:]-m[:-1] == 1)

def check_string_combinations_determ(symbols, length):
	folder_path = home_path+"/Documents/string_combinations"
	file_sc_path = folder_path+"/sc_{}_{}_determ.txt".format(symbols, length)

	with open(file_sc_path, "rb") as f:
		line = f.readline().decode("ascii")[:-1]

	line = list(map(int, line))
	
	m = check_string_combinations(line, symbols, length)
	print("m: {}".format(m))

def string_combinations_convert_to_letters(symbols, length):
	folder_path = home_path+"/Documents/string_combinations"
	file_sc_path = folder_path+"/sc_{}_{}_determ.txt".format(symbols, length)
	file_sc_letters_path = folder_path+"/sc_{}_{}_letter.txt".format(symbols, length)

	with open(file_sc_path, "rb") as f:
		line = f.readline().decode("ascii")[:-1].split(",")
	m = {str(i): c for i, c in enumerate("ABCDEFGHIJKLMNOPQRSTUVWXYZ")}
	with open(file_sc_letters_path, "wb") as f:
		f.write(("".join(map(lambda x: m[x], line))).encode("utf-8"))

def mix_string_combinations(symbols, length):
	folder_path = home_path+"/Documents/string_combinations"
	file_sc_path = folder_path+"/sc_{}_{}_determ.txt".format(symbols, length)
	file_sc_letters_path = folder_path+"/sc_{}_{}_letter.txt".format(symbols, length)

	with open(file_sc_path, "rb") as f:
		line = f.readline().decode("ascii")[:-1].split(",")

	line = np.array(list(map(int, line))).astype(np.int)
	line = (line % symbols).astype(np.uint8)

	max_index = symbols**length
	print("line before:\n{}".format(line))
	for j in range(0, 10000):
		shift = np.random.randint(1, symbols**length)
		# m1 = check_string_combinations(line, symbols, length)
		line = np.roll(line, shift)
		# m2 = check_string_combinations(line, symbols, length)

		# print("m1: {}".format(m1))
		# print("m2: {}".format(m2))

		m = np.vstack((line, np.hstack((line[(length-1)*2:], line[:(length-1)*2]))))
		for i in range(1, length-1):
			m = np.vstack((m, np.hstack((line[i:], line[:i]))))
			m = np.vstack((m, np.hstack((line[(length-1)*2-i:], line[:(length-1)*2-i]))))
		m = m.T
		# print("m: {}".format(m))
		found_indices = np.where(np.all(m==m[0], axis=1))[0]+length-1
		# print("found_indices: {}".format(found_indices))

		# index = found_indices[2]
		# print("index: {}".format(index))
		if len(found_indices) > 1:
			first = found_indices[0]
			for k in found_indices[1:]:
				if k < max_index and k - first > length-1 or k >= max_index and first-(k%max_index) > length-1:
					line[first], line[k] = line[k], line[first]
					break
	# pos_zeros = np.where(line==0)[0]
	# sums = 0
	# for i in range(0, length-1):
	# 	diff = ((pos_zeros[i+1:-length+2+i] if i < length-2 else pos_zeros[i+1:])-pos_zeros[i:-length+1+i])==1
	# 	sums += diff
	
	# found_pos_zeros = np.where(sums==length-1)[0]
	# if len(found_pos_zeros) > 0:
	# 	line = np.roll(line, max_index-pos_zeros[found_pos_zeros[0]])
	# else:
	# 	for _ in range(0, length-1):
	# 		line = np.roll(line, 1)
	# 		if np.all(line[0: length] == np.zeros(length).astype(np.uint8)):
	# 			break

	print("line after:\n{}".format(line))


symbols = 2
length = 16

# save_one_string_combination(4, 9)
# test_brute_force_vs_deterministic_search()
# check_string_combinations_determ(10, 6)
get_string_combinations(symbols, length)
# string_combinations_convert_to_letters(symbols, length)
mix_string_combinations(symbols, length)
