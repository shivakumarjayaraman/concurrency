import java.util.concurrent.*;

public class ClosingAPoll {


    private static void doStuff (CyclicBarrier barrier) {
        try  { 
            Thread.sleep(1000); 
	    System.out.println(Thread.currentThread().getName() + " done ");
	    barrier.await();
        } catch (Exception e){}
    }

    public static void main(String [] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
             System.out.println("All 5 guys are done .. ");
        });

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                doStuff(barrier);
            }).start();
	}
    }

}
