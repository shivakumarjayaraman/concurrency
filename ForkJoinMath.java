import java.math.*;
import java.util.*;
import java.util.concurrent.*;

public class ForkJoinMath {
    private static List<Integer> calcPrimeFactors(int n) {
        List<Integer> pfs = new ArrayList<Integer>();

	int d = 2;
	while (d*d < n) {
	    while (n%d == 0) {
	        pfs.add(d);
		n = n/d;
	    }
	    d++;
	}
	if (n > 1) pfs.add(n);

	return pfs;
    }

    private static Collection<Callable<List<Integer>>> createTasks() {
	Random rand = new Random();
        List<Callable<List<Integer>>> tasks = new ArrayList<>();
	for (int i = 0; i < 1000000; i++) {
	    final int rnum = rand.nextInt(9800000) + 20000;
	    Callable<List<Integer>> task = new Callable<List<Integer>>() {
	        public List<Integer> call() {
		    List<Integer> factors = calcPrimeFactors(rnum);
	            // System.out.println(rnum + "->" + factors);
		    return factors;
	        }
	    };
	    tasks.add(task);
	}
	return tasks;
    }

    public static void main(String [] args) throws Exception {
        long start = new Date().getTime();
	ForkJoinPool fjp = new ForkJoinPool(10);
	List<Future<List<Integer>>> results = fjp.invokeAll(createTasks());
	for (Future f : results) {
	    f.get();
	}
        long end = new Date().getTime();
	System.out.println("Time taken is " + (end - start) + " ms");
    }
}
