import java.util.concurrent.*;
import java.util.*;

public class Callables {
    public static void main(String [] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        // Submit some runnables
        for (int i = 0; i < 50; i++) {
            final int val = i;
            pool.submit(() -> {System.out.println(val); });
        }

        // Submit some callables
        List<Future> results = new ArrayList<Future>();
        for (int i = 0; i < 50; i++) {
            final int val = i;
            Future f = pool.submit(()->{
                 String s = "Hello " + val;
                 System.out.println(s);
                 return s;
            });
            results.add(f);
        }

        pool.shutdown();

        Scanner s = new Scanner(System.in);
        s.nextLine();

        // Later .. 
        for (Future f : results) {
            System.out.println(f.get());
        }
    }
}
