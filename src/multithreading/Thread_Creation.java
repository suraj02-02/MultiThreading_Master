package multithreading;

public class Thread_Creation {

    public static void main(String[] args) {

        /*
         * Here we are doing the creation of our custom thread using @Thread class
         * While customThread creation below things are done internally :
         *
         *     1. Instantiation of Thread class object , no OS level thread has been created till now
         *     2. If no threadGroup is passed , then our custom thread will be part of main thread group
         *     3. Each newly created thread is contained inside a holder @FieldHolder class having below properties :
         *            -> ThreadGroup group
         *            -> Runnable task
         *            -> long stackSize
         *            -> int priority
         *            -> boolean daemon
         *     4.FieldHolder act as an Encapsulator for Thread class properties
         *
         *     5.Once we call the #start() method on the thread object then the execution of thread starts :
         *            -> start method is wrapped with a #synchronised(this) block so that no 2 same threads can be started again
         *            -> @threadStatus property of @FieldHolder class is checked everyTime #start() method is called on a thread object;
         *                          if(@threadStatus != 0)
         *                               throw Exception
         *                          else
         *                             start the execution by creating a new OS level thread
         *            -> The creation of OS level thread is done in native code
         *            -> Stack memory is assigned to the thread.
         *            -> Now there 2 ways in which the task can be performed by a thread :
         *                  1. Overriding #run() method.
         *                  2. Provide runnable implementation as lambda expression inside @Thread class constructor. The task is the assigned to a task property of
         *                     @FieldHolder class which is created while new thread object creation.
         *           -> Once the thread completes the task provided it gets terminated and it's resources are cleaned up
         *
         *
         *  Threads are the most basic unit which runs a program on a processor.
         *  When ever a program is run (it can be any app/webapp / desktop app) then it creates a certain number of
         *  threads which are responsible for actual work done.
         *
         *  At any point of time any new number of threads can be created inorder to execute the task through
         *  different threads.
         *
         *  Benefits :
         *  Java threads are lightweight compared to processes.
         *  Context switching b/w two threads in same process is much cheaper than context switching b/w two threads of
         *  diff processes.
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

