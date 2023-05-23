import java.util.concurrent.atomic.*;

public class TableAtomicVariable {
    private AtomicInteger money = new AtomicInteger(0);

    public void putMoney(int m) { 
       this.money.getAndAdd(m);
    }

    public String toString() { return "Table(" + this.money.get() + ")"; }
    
    public static void main(String [] args) throws Exception {
        TableAtomicVariable t = new TableAtomicVariable();
        System.out.println("Table before game " + t);

        Thread gambler1 = makeGambler(t, 20000);
        Thread gambler2 = makeGambler(t, 20000);
        
        gambler1.start(); gambler2.start();
        gambler1.join(); gambler2.join();
    

        System.out.println("Table after game " + t);
    }

    public static Thread makeGambler(TableAtomicVariable t, final int wallet) {
        Thread t1 = new Thread() {
            public void run() {
                int wInternal = wallet;
                while (wInternal > 0) {
                    t.putMoney(1); wInternal --;
                }
            }

        };
        return t1;
    }

}
