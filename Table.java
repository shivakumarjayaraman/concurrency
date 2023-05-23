public class Table {
    private int money;

    public void putMoney(int m) { 
       this.money += m; 
    }

    public String toString() { return "Table(" + this.money + ")"; }
    
    public static void main(String [] args) throws Exception {
        Table t = new Table();
        System.out.println("Table before game " + t);

        Thread gambler1 = makeGambler(t, 10000);
        Thread gambler2 = makeGambler(t, 10000);
        
        gambler1.start(); gambler2.start();
        gambler1.join(); gambler2.join();
    

        System.out.println("Table after game " + t);
    }

    public static Thread makeGambler(Table t, final int wallet) {
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
