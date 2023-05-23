#!/usr/bin/env python3

from urllib.request import urlretrieve as download
import time
from threading import Thread

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
    threads = []
    for url in urls :
        t = Thread(target=get, args=(url, f"file{count}.html"))
        threads.append(t)
        t.start()
        count += 1
    for t in threads :
        t.join()
    end = time.time()
    print (f"Total time is {end-start}")

if __name__ == '__main__' :
    main()
