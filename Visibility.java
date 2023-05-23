public class Visibility {
    private static boolean flag;

    public static void main(String [] args) throws Exception {
        // Thread waits for flag to change and then ends
        Thread t = new Thread(() -> {
            while (!flag) {
                // System.out.print("..");
                // try { Thread.sleep(2000); } catch (Exception e) {}
            }
        });
        t.start();

        // Thread first changes val and then changes flag
        Thread.sleep(10000);
        flag = true;
        System.out.println("Main thread has set flag to true");

        t.join();
    }
}
