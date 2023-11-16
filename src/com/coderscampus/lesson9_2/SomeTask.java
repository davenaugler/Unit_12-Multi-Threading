package com.coderscampus.lesson9_2;

import java.util.Random;

public class SomeTask {
    private static int taskId = 0;
    private TaskDto taskDto;

    public SomeTask doSomeWork() {
        taskDto = new TaskDto();
        for (int i=0; i<10000000; i++) {
            Integer int1 = new Random().nextInt();
            Integer int2 = new Random().nextInt();
            Double something = (int1 + int2) * 1.5;
            taskDto.setValue(something);
        }
        taskId++;
        System.out.println("Hey look at me, I'm task-id: " + taskId + "! "
                + "I'm running on thread: " + Thread.currentThread().getName());

        return this;
    }

    public TaskDto markComplete () {
        taskDto.setFinished(true);
        return taskDto;
    }
}
