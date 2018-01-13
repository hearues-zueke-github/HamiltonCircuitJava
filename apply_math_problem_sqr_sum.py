#! /usr/bin/python3.5

import numpy as np

from os.path import expanduser
from subprocess import call
from time import time

home_path = expanduser("~")

def solve_sqr_sum_problem(symbols, length):
	call(["java", "-jar", "target/HamiltonCircuitSearch-0.2.3.jar", "sqrsumproblem", str(symbols), str(length)])

symbols = 50
length = 8

solve_sqr_sum_problem(symbols, length)
