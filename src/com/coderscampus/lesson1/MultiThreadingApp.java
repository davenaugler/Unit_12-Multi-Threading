package com.coderscampus.lesson1;

public class MultiThreadingApp {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            
        }
    }

    class SomeTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Hey look at me, I'm a task! I'm running on thread: " + Thread.currentThread().getName());
        }
    }
}