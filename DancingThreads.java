import java.util.concurrent.*;

public class DancingThreads implements Runnable {
    private String name;
    private BlockingQueue src;
    private BlockingQueue dest;

    public DancingThreads(String name, BlockingQueue src, BlockingQueue dest) {
        this.name = name;
	this.src = src;
	this.dest = dest;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
	        Object o = dest.take();
	        System.out.println("I am " + name + " " + i);
	        Thread.sleep(300); 
	        src.put(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
	}
    }

    public static void main(String [] args) throws Exception {
        BlockingQueue q1 = new ArrayBlockingQueue(1);
        BlockingQueue q2 = new ArrayBlockingQueue(1);

        Thread t1 = new Thread(new DancingThreads("One", q1, q2));
        Thread t2 = new Thread(new DancingThreads("Two", q2, q1));

	t1.start();
	t2.start();

        q2.put("Hi");

	t1.join(); t2.join();


    }
}
