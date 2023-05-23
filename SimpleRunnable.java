public class SimpleRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("... inside thread " + Thread.currentThread().getId());
    }

    public static void main(String [] args) throws Exception {
        Thread t = new Thread(new SimpleRunnable());
        t.start();
        System.out.println("Spawned off t1 thread");
        t.join();


        Thread t2 = new Thread() {
            public void run() {
              System.out.println("Anonymous inner class Thread .. ");
            }
        };
        t2.start();
        System.out.println("Spawned off t2 thread");
        t2.join();
    }
}
