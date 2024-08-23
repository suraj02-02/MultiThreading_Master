package threading_questions;

/*
 * Problem statement :
 *
 * In this exercise we are going to implement a  MultiExecutor .
 * The client of this class will create a list of Runnable tasks and provide that list into MultiExecutor's constructor.
 * When the client runs the . executeAll(),  the MultiExecutor,  will execute all the given tasks.
 * To take full advantage of our multicore CPU, we would like the MultiExecutor to execute all the tasks in parallel,
 *  by passing each task to a different thread.
 *
 */

import java.util.List;
import java.util.stream.IntStream;

public class MultiExecutor {

    public static void main(String[] args) {

        TaskData taskData = new TaskData(createRunnableTaskList());
        taskData.executeTask();
    }

    private static List<Runnable> createRunnableTaskList() {

        return IntStream.range(0, 10)
                .mapToObj(obj -> {
                    return (Runnable) () -> System.out.println("Runnable Task : " + obj);
                }).toList();
    }
}

class TaskData {

    private final List<Runnable> tasks;

    public TaskData(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeTask() {

        final List<Thread> threadList = this.tasks.stream()
                .map(obj -> {
                    Thread thread = new Thread(obj);
                    thread.start();
                    return thread;
                })
                .peek(thread -> System.out.println("Thread started : " + thread.getName()))
                .toList();

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}

