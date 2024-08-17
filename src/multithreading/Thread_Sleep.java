package multithreading;

public class Thread_Sleep {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {

                /*  Sleep method is used to make the thread to pause for mentioned time period.
                 *  The thread goes to TIMED_WAITING state.
                 *  The actual time depends on system time or schedulers.
                 *  Threads under sleep does not loose any lock/monitor/resource. */

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.setName("New Thread-1");
        thread1.start();
    }
}
