import java.util.concurrent.*;
import java.util.*;

public class RoomWithCapacity {
    static Semaphore semaphore = new Semaphore(10);

    public static void main(String [] args) throws Exception {
          semaphore.acquire();
          for (int i = 0; i < 10; i++) {
              Thread t = new Thread(() -> { 
                 try { semaphore.acquire(); } catch (Exception e) {}
		 System.out.println(Thread.currentThread().getName() + " is inside");

                 // THIS IS BAD CODE PROVIDED JUST FOR ILLUSTRATING SEMAPHORES
                 // IDEALLY THE ACQUIRER NEEDS TO RELEASE IN A FINALLY BLOCK
	      });
              t.start();
          }

	  new Scanner(System.in).nextLine();
	  semaphore.release();
    }
}
