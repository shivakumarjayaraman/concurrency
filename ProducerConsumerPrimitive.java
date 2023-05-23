public class ProducerConsumerPrimitive {
    int size;
    String [] buffer;

    int numberOfElements = 0;
    int readIndex = 0;
    int writeIndex = 0;

    public int bufsize() {
        return buffer.length;
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public boolean isFull() {
        return numberOfElements == bufsize();
    }

    public synchronized void put(String s) {
        while (isFull()) {
            try {
                wait();
            } catch (Exception e) {}
        }

        buffer[writeIndex] = s;
        writeIndex = (writeIndex+1)%size;
        numberOfElements++;

        notifyAll();
    }

    public synchronized String get() {
        while (isEmpty()) {
            try {
                wait();
            } catch (Exception e) {}
        }

        String ret = buffer[readIndex];
        readIndex = (readIndex+1)%size;
        numberOfElements--;

        notifyAll();
        return ret;
    }

    public ProducerConsumerPrimitive(int size) {
        this.size = size;
        this.buffer = new String[size];
    }

    public static class Producer extends Thread {
        int numberOfItemsToProduce;
        String name;
        ProducerConsumerPrimitive shared;

        public Producer(String name, int numberOfItemsToProduce, ProducerConsumerPrimitive shared) {
            super(name);
            this.name = name;
            this.numberOfItemsToProduce = numberOfItemsToProduce;
            this.shared = shared;
        }

        public void run() {
            for (int i = 0; i < numberOfItemsToProduce; i++) {
                shared.put("Item " +  i + " made by " + name);
            }
        }
    }

    public static class Consumer extends Thread {
         String name;
         ProducerConsumerPrimitive shared;

         public Consumer(String name, ProducerConsumerPrimitive shared) {
             super(name);
             this.name = name;
             this.shared = shared;
         }

         public void run() {
             while (true) {
                  System.out.println(name + " is consuming " + shared.get());
             }
         }
    }


    public static void main(String [] args) {
        ProducerConsumerPrimitive shared = new ProducerConsumerPrimitive(10);

        for (int i = 0; i < 5; i++) {
             Producer p = new Producer("P" + i, 10, shared);
             p.start();
        }

        for (int i = 0; i < 10; i++) {
             Consumer c = new Consumer("C" + i, shared);
             c.start();
        }
    }
}
