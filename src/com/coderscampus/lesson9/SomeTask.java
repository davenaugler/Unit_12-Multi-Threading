package com.coderscampus.lesson9;

import java.util.Random;

public class SomeTask {
// * Dto stands for data transfer object
    private static int taskID = 0;
    private TaskDto taskDto;
    static final double INT_MULTIPLIER = 1.5;

    public SomeTask doSomeWork() {
        taskDto = new TaskDto();
        for (int i = 0; i < 10_000_000; i++) {
            Integer randomInt_1 = new Random().nextInt();
            Integer randomInt_2 = new Random().nextInt();
            Double something = (randomInt_1 * randomInt_2) * INT_MULTIPLIER;
            taskDto.setValue(something);
//                System.out.println("Random Value: " + randomValue);
        }
        taskID++;
        System.out.println("Hey look at me, I'm taskID: " + taskID + "! I'm running on thread: " + Thread.currentThread().getName());
        return this;
    }
    public TaskDto markComplete () {
        taskDto.setFinished(true);
        return taskDto;
    }
}
