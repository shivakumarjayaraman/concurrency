#!/usr/bin/env python3

from urllib.request import urlretrieve as download
import time

urls = [
    "https://www.hp.com",
    "https://www.cisco.com",
    "https://www.google.com",
]

def get(url, file) :
    print (f"Downloading {url}")
    download(url, file)
    

def main() :
    start = time.time()
    count = 0
    for url in urls :
        get(url, f"file{count}.html")
        count += 1
    end = time.time()
    print (f"Total time is {end-start}")

if __name__ == '__main__' :
    main()
