package multithreading;

public class Thread_Error_Handling {

    public static void main(String[] args) {

        /*
         * Lets say we created a thread and the thread throws some runtime exception , so we can handle
         * this exception using #UncaughtExceptionHandler.
         */

        Thread thread = new Thread(() -> {
            System.out.println("This is the new Thread : " + Thread.currentThread().getName());
            throw new RuntimeException();
        });

        thread.setUncaughtExceptionHandler((t , e) -> {
            System.out.println("Error occurred while execution of thread : " + t.getName());
        });

        thread.start();
    }
}
