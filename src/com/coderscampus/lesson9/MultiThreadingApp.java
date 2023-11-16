package com.coderscampus.lesson9;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String message = "Starting";
        System.out.println(message); // + Starting
        System.out.println("Thread-" + Thread.currentThread().getName()); // + Thread-main

//      ExecutorService cpuBoundTask = Executors.newSingleThreadExecutor();
      ExecutorService cpuBoundTask = Executors.newFixedThreadPool(3);
      ExecutorService ioBoundTask = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            CompletableFuture.supplyAsync(() -> new SomeTask(), ioBoundTask)
                             .thenApplyAsync(someTask -> someTask.doSomeWork(), cpuBoundTask)
                             .thenApplyAsync(someTask -> someTask.markComplete(), ioBoundTask)
                             .thenAcceptAsync(dto -> System.out.println("dto: " + dto), ioBoundTask);
        }
        message = "Done";
        System.out.println(message);
    }
}























