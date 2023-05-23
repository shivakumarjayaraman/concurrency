import java.math.*;
import java.util.*;
import java.util.concurrent.*;

public class ConcMath1 {
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
	if (n > 1) {
	    pfs.add(n);
	}

	return pfs;
    }

    public static void main(String [] args) throws Exception {
        long start = new Date().getTime();
        List<Thread> tlist = new ArrayList<Thread>();
	Random rand = new Random();
        for (int batch = 0; batch < 100; batch++) {
	    Thread t = new Thread(() -> {
	        for (int i = 0; i < 10000; i++) {
		    int rnum = rand.nextInt(9800000) + 20000;
		    List<Integer> pfs = calcPrimeFactors(rnum);
		    //System.out.println(rnum + "->" + pfs);
		}
	    });
	    tlist.add(t);
	    t.start();
	}
	for (Thread t : tlist) {
	    t.join();
	}
        long end = new Date().getTime();
	System.out.println("Time taken is " + (end - start) + " ms");
    }
}
