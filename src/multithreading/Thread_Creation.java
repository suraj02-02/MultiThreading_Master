package multithreading;

public class Thread_Creation {

    public static void main(String[] args) {

        /**
         *  Threads are the most basic unit which runs a program on a processor.
         *  When ever a program is run (it can be any app/webapp / desktop app) then it creates a certain number of
         *  threads which are responsible for actual work done.
         *
         *  At any point of time any new number of threads can be created inorder to execute the task through
         *  different threads.
         *
         *  Benefits :
         *  Java threads are lightweight compared to processes.
         *  Context switching b/w threads is less expensive than b/w processes.
         *  Thread communication is easier compared to process communication.
         *
         *  JVM creates a #main thread to start the application.
         */

        /* Creating a thread / platform thread. Here we have created a new platform thread using thread class
           and passing runnable impl using lambda expression. */

        Thread thread = new Thread(() -> System.out.println("New thread created : " + Thread.currentThread().getName()));
        thread.setName("New Thread");
        thread.start();
    }
}

