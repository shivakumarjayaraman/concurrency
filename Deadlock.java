public class Deadlock {

    public static void main(String [] args) throws Exception {
        final Lock1 l1 = new Lock1();
        final Lock2 l2 = new Lock2();

        l1.setLock2(l2); l2.setLock1(l1);

        Thread t1 = new Thread(()-> { l1.doSomething() ; });
        Thread t2 = new Thread(()-> { l2.doSomething() ; });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("All done");
    }

    public static class Lock1 {
        Lock2 lock2;
        public Lock1() {}

        public void setLock2(Lock2 lock2) {
           this.lock2 = lock2;
        }
        public synchronized void doSomething() {
           System.out.println(Thread.currentThread().getName() + ":Inside L1::doSomething");
           lock2.doSomething();
        }
    }

   public static class Lock2 {
        Lock1 lock1;
        public Lock2() {}

        public void setLock1(Lock1 lock1) {
           this.lock1 = lock1;
        }
        public synchronized void doSomething() {
           System.out.println(Thread.currentThread().getName() + ":Inside L2::doSomething");
           lock1.doSomething();
        }
   }
}
