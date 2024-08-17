package multithreading;

public class Thread_Joining {

    public static void main(String[] args) throws InterruptedException {

       /*
         It can be used when we want the current thread to wait till the thread on which #join is called
         gets its task done and die.

         The main use case for #join is for thread coordination.
         Lets say multiple child threads are performing some task , then using join the main / parent
         thread will wait till all the child threads are done with there tasks.

         Now lets say for the below example , i want the Child-Thread : 1 to complete the task first then only the
         Child-Thread : 2 should start its execution.
        */

        Thread t1 = new Thread(Thread_Joining::doSomeTask);
        Thread t2 = new Thread(Thread_Joining::doSomeTask);
        t1.setName("Child-Thread : 1");
        t2.setName("Child-Thread : 2");
        t1.start();
        t1.join();
        // Now till thread t1 is not done with its task , main thread will be blocked and thread t2 will not be started.

        t2.start();
    }

    // This acts as some processing task performed
    private static void doSomeTask() {

        for (int i = 0; i < 5 ; i++) {
            System.out.println("Do some task : " + Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
