package com.coderscampus.lesson9;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MultiThreadingApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String message = "Starting";
        System.out.println(message); // + Starting
        System.out.println("Thread-" + Thread.currentThread().getName()); // + Thread-main

//      ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            CompletableFuture.supplyAsync(() -> new SomeTask())
                             .thenApply(someTask -> someTask.call())
                             .thenAccept(dto -> System.out.println("dto: " + dto));
        }
        message = "Done";
        System.out.println(message);
    }
}























