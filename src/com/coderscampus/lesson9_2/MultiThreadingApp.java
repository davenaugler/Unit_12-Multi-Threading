package com.coderscampus.lesson9_2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String message = "Starting";
        System.out.println(message);
        System.out.println("Thead-"+ Thread.currentThread().getName());

        // This starts up as many threads as there are iterations in the for loop.
        // You only want to fire up a ton of threads, if those threads are going to
        // be waiting a lot... i.e. sending requests across the internet.
//		for (int i=0; i<50; i++) {
//			SomeTask task = new SomeTask();
//			new Thread(task).start();
//		}

        // For a CPU bound operation like we have in our "SomeTask" class,
        //  we should make use of an ExecutorService

        ExecutorService cpuBoundTask = Executors.newFixedThreadPool(3);
        ExecutorService ioBoundTask = Executors.newCachedThreadPool();

        for (int i=0; i<20; i++) {
            CompletableFuture.supplyAsync(() -> new SomeTask(), ioBoundTask)
                    .thenApplyAsync(someTask -> someTask.doSomeWork(), cpuBoundTask)
                    .thenApplyAsync(someTask -> someTask.markComplete(), ioBoundTask)
                    .thenAcceptAsync(dto -> System.out.println(dto), ioBoundTask);
        }

        message = "Done";
        System.out.println(message);

    }
}
