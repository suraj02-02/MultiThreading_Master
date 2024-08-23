package multithreading;

/*
 * #interrupt is used when there is a scenario to gracefully shutdown a long running / Blocked thread
 *
 */

import static java.lang.System.out;

public class Thread_Interrupt {

    public static void main(String[] args) {

        // creating a thread
        Thread thread = new Thread(Thread_Interrupt::longRunningTask);
        thread.start();

        // Sleeping the main thread before the thread interruption
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
           calling the #interrupt method changes the interrupt status for the thread to true

         */
        thread.interrupt();
    }

    private static void longRunningTask() {

        for (int i = 0 ; i < 10000000 ; i ++) {

            /*
              Checking the thread interruption status and breaking the thread if thread is interrupted
             */
            if (Thread.currentThread().isInterrupted()) {
                out.println(Thread.currentThread().getName() + " is interrupted");
                break;
            }

            try {
                Thread.sleep(100);
                out.println(Thread.currentThread().getName() + " :" + i);

            } catch (InterruptedException e) {
                out.println("Exception occurred by " + Thread.currentThread().getName() + " :" + i);
                Thread.currentThread().interrupt();
            }

        }
    }
}
