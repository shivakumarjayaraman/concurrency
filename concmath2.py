#!/usr/bin/env python3

import time
import random
from multiprocessing import Process

def calcPrimeFactors(n):
    primeFactors = []
    d = 2
    while d*d < n :
        while (n%d == 0) :
            primeFactors.append(d)
            n //= d
        d = d+1
    if n > 1 :
       primeFactors.append(n)
    return primeFactors

def batch():
    for i in range(10000):
        rand = random.randint(20000, 10000000)
        pfs = calcPrimeFactors(rand)
        #print (rand, pfs)

def main() :
    start = time.time()
    processes = []
    for i in range(100) :
        process = Process(target=batch)
        processes.append(process)
        process.start()
    for p in processes:
        p.join()
    end = time.time()
    print (f"Execution time is {end-start} sec")
    
if __name__ == "__main__" :
    main()
