package com.dsytnykov.websocket.model;

/**
 * Model class representing a request to start a sorting algorithm.
 */
public class AlgorithmRequest {
    private String algorithmType;
    private int[] array;
    private int delay;

    public String getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
