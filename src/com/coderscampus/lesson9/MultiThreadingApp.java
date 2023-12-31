package com.coderscampus.lesson9;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingApp {
    public static void main(String[] args) {
        String message = "Starting";
        System.out.println(message);
        System.out.println("Thread-" + Thread.currentThread().getName());

        // Single Thread is more performant in this situation
      ExecutorService cpuBoundTask = Executors.newSingleThreadExecutor();
//      ExecutorService cpuBoundTask = Executors.newFixedThreadPool(3);
      ExecutorService ioBoundTask = Executors.newCachedThreadPool();

      // ADDED LINE OF CODE
      List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
//            CompletableFuture.supplyAsync(() -> new SomeTask(), ioBoundTask)

            // ADDED LINE OF CODE
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new SomeTask(), ioBoundTask)
                             .thenApplyAsync(someTask -> someTask.doSomeWork(), cpuBoundTask)
                             .thenApplyAsync(someTask -> someTask.markComplete(), ioBoundTask)
                             .thenAcceptAsync(dto -> System.out.println("dto: " + dto), ioBoundTask);
            futures.add(future);
        }

        message = "Done";
        System.out.println(message);

        // ADDED LINE OF CODE // Wait for all futures to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // ADDED LINES OF CODE // Shut down the executor service
        cpuBoundTask.shutdown();
        ioBoundTask.shutdown();
    }
}























