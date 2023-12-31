package com.coderscampus.lesson1;

import java.util.Random;
import java.util.concurrent.Callable;

public class SomeTask implements Callable<TaskDto> {
    private static int taskID = 0;
    static final double INT_MULTIPLIER = 1.5;

    @Override
    public TaskDto call() {
        TaskDto taskDto = new TaskDto();
        for (int i = 0; i < 1000000; i++) {
            Integer randomInt_1 = new Random().nextInt();
            Integer randomInt_2 = new Random().nextInt();
            Double something = (randomInt_1 * randomInt_2) * INT_MULTIPLIER;
            taskDto.setValue(something);
        }
        taskID++;
        System.out.println("Hey look at me, I'm taskID: " + taskID + "! I'm running on thread: " + Thread.currentThread().getName());
        taskDto.setFinished(true);
        return taskDto;
    }
}
