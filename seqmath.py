#!/usr/bin/env python3

import time
import random

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

start = time.time()
for i in range(1000000):
    rand = random.randint(20000, 10000000)
    pfs = calcPrimeFactors(rand)
    #print (rand, pfs)
end = time.time()
print (f"Execution time is {end-start} sec")
    
