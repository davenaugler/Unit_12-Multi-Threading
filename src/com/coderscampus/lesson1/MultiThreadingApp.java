package com.coderscampus.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadingApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String message = "Starting";
        System.out.println(message); // + Starting
        System.out.println("Thread-" + Thread.currentThread().getName()); // + Thread-main

        // * This starts up as many threads as there are iterations in the for loop.
        // * You only want to fire up a ton of threads, if those threads are going to
        // * be waiting a lot... i.e. sending requests across the internet.
//        for (int i = 0; i < 11; i++) {
//            SomeTask task = new SomeTask();
//            new Thread(task).start();
//        }

        // * For a CPU bound operation like we have in our "SomeTask" class,
        // * we should make use of an ExecutorService

        // # .newFixedThreadPool vs .newCachedThreadPool // Use on CPU bound situations
        // # With Fixed you need to specify the number of threads
        // + With Cached you do not need to specify the number of threads // Use with I/O or HTTP requests
        // +   CachedThreadPool there is not theoretically maximum really
        // +   It will continue to create new threads to support however many threads you need
        // +   What will happen. As the tasks finish, if the thread remains idle for 60 seconds it will
        // +   shut down that thread.
        // # Don't use a newCachedThreadPool when you are working with a CPU bound task because
        // # if were to run this you will see that it will quickly start up as many threads as it needs
        // # taxing the CPU extremely hard.

        // ? IF this were a more I/O or HTTP request environment where you are waiting a lot, where it's not a CPU task.
        // ? It alleviates the guessing game on how many threads would be needed.

//      ExecutorService executor = Executors.newSingleThreadExecutor();
        List<CompletableFuture<Void>> tasks = new ArrayList<>();

        // This is how you get access to the ForkJoinPool's common pool,
        //   which is the default thread pool that's used with CompletableFutures
        //   if you do not specify an executor
        for (int i = 0; i < 20; i++) {
            // * CompletableFuture came out in Java 8
            // + This gives us the ability of being notified when our task is completed, and execute that function for us
            // + The main difference is that it does not block the main thread. While the .get() does.

            CompletableFuture<Void> task = CompletableFuture.supplyAsync(() -> new SomeTask())
                             .thenApply(someTask -> someTask.call())
                             .thenAccept(dto -> System.out.println("dto: " + dto));
            tasks.add(task);
//            service.execute(new SomeTask());
            // * Futures were great prior to Java 8, but now we have something better
//            Future<TaskDto> futureTask = service.submit(new SomeTask());
            // ? The .get() will block the main thread and wait until that task is completed then we'll get the value for it
//            System.out.println("Future Task: " + i + " " + futureTask.get()); // + All 'Future Task's' (50 of them)
        }
        message = "Done";
        System.out.println(message); // + Done
        while(tasks.stream()
                   .filter(CompletableFuture::isDone)
                   .count() < 20) {
            // This just loops and keeps the main thread alive
            // until all threads are done working.
        }

    }
}























