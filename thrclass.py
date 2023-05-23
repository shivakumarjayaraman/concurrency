#!/usr/bin/env python3
from threading import Thread
import time

class MyThread(Thread):
    def __init__(self) :
        print ("MyThread::Creating thread")
        Thread.__init__(self)
    def run(self):
        #time.sleep(3)
        print ("MyThread::Thread has started")


myThread = MyThread()
print ("Main::Going to start thread")
myThread.start();
print ("Main::Started thread")
myThread.join()
print ("Main::All done")
