package multithreading;

public class Thread_Local {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            threadLocal.set(1);
            final Integer i = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + ":" + i);
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set(2);
            final Integer i = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + ":" + i);
        });

        t1.start();
        t2.start();
    }
}
