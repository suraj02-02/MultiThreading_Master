package multithreading;

/*
 *  This is a basic example for wait-notify of threads performing addition and substraction
 *  on a common resource. This will be a never ending loop
 *
 *  To ensure that our program is running fine , we will be checking the counter with some other thread
 *  If the counter value is anything apart from '0' or '1' the data validation thread will throw
 *  a runtime exception stopping the JVM instance.
 *
 *  Analyses :  Although the two threads are synchronised with a shared lock resource ,there can be scenario where
 *  The counter value can go beyond 1 or 0 and we cannot control it since we don't have thread execution control.
 *
 */

public class Thread_Wait_Notify {

    /*
     * This is a common resource used as a shared lock
     */
    private static final Object lock = new Object();
    /*
      Global counter which will be modified by adder and substractor threads
     */
    private static int counter = 0;

    public static void main(String[] args) {

        /*
         * Create Adder thread
         */
        Thread thread1 = new Thread(() -> {
            try {
                doAddition();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.setName("Adder-Thread");

        /*
         * Create Substractor thread
         */
        Thread thread2 = new Thread(() -> {
            try {
                doSubstraction();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.setName("Subtraction-Thread");
        thread1.start();
        thread2.start();

        /*
         Create a counter validation thread
         */

        Thread thread3 = new Thread(() -> {
             while(true) {
                 if(counter == 0 || counter == 1){
                     System.out.println("Validation passed!");
                 }else {
                     System.out.println("Validation failure!");
                     System.exit(0);
                 }
             }
        });
        thread3.setName("Data-Validator");
        thread3.start();
    }

    private static void doAddition() throws InterruptedException {

        while (counter < 10) {
            synchronized (lock) {
                if (counter % 2 == 0) {
                    counter++;
                    System.out.println(Thread.currentThread().getName() + ": " + counter);
                    lock.notify();
                }else {
                    lock.wait();
                }
            }
        }
    }

    private static void doSubstraction() throws InterruptedException {

        while (counter < 10) {
            synchronized (lock) {
                if (counter % 2 != 0) {
                    counter--;
                    System.out.println(Thread.currentThread().getName() + ": " + counter);
                    lock.notify();
                }else {
                    lock.wait();
                }
            }
        }
    }

}
