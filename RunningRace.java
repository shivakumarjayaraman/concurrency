import java.util.concurrent.*;
import java.lang.Math;
import java.util.Scanner;

public class RunningRace {
    public static void main(String [] args) throws Exception {
        int numberOfRunners = 10;
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(numberOfRunners);

        for (int i = 0; i < numberOfRunners; i++) {
            Thread t = new Thread(()->{
	       try {
	           startGate.await();
		   long sleepTime = Math.round(Math.random() * 1000);
		   Thread.sleep(sleepTime);
                   System.out.println(Thread.currentThread().getName() + " is processing for " + sleepTime);
		   endGate.countDown();
	       } catch (Exception e) {
	       }
            });
	    t.start();
        }

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
	System.out.println("Let the race begin");
        startGate.countDown();
    }
}
