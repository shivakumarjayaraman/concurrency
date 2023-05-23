import java.util.concurrent.*;

public class ProducerConsumerBQ {
    BlockingQueue<String> queue;

    public void put(String s) {
         try { 
	     queue.put(s); 
	 } catch (Exception e) {
	     e.printStackTrace();
	 }
    }
    public String get() { 
        try {
            return queue.take(); 
	} catch (Exception e) {
	    e.printStackTrace();
            return null;
	}
    }

    public ProducerConsumerBQ(int size) {
        this.queue = new ArrayBlockingQueue<String>(size);
    }

    public static class Producer extends Thread {
        int numberOfItemsToProduce;
        String name;
        ProducerConsumerBQ shared;

        public Producer(String name, int numberOfItemsToProduce, ProducerConsumerBQ shared) {
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
         ProducerConsumerBQ shared;

         public Consumer(String name, ProducerConsumerBQ shared) {
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
        ProducerConsumerBQ shared = new ProducerConsumerBQ(10);

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
