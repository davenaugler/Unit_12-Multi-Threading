package com.coderscampus.lesson1;

public class TaskDto {
    private Double value;
    private Boolean finished = false;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "value=" + value +
                ", finished=" + finished +
                '}';
    }
}
