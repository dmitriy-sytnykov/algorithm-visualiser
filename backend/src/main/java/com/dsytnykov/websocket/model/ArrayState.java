package com.dsytnykov.websocket.model;

/**
 * Model class representing the current state of the array during sorting.
 */
public class ArrayState {
    private int[] array;
    private int currentIndex;    // Current position being processed
    private int compareIndex;    // Position being compared with currentIndex
    private boolean complete;    // Whether the sorting is complete
    private String step;         // Description of the current step
    private int swapCount;       // Number of swaps performed so far
    private int compareCount;    // Number of comparisons performed so far

    public ArrayState() {
    }

    public ArrayState(int[] array) {
        this.array = array;
        this.currentIndex = -1;
        this.compareIndex = -1;
        this.complete = false;
        this.swapCount = 0;
        this.compareCount = 0;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getCompareIndex() {
        return compareIndex;
    }

    public void setCompareIndex(int compareIndex) {
        this.compareIndex = compareIndex;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getSwapCount() {
        return swapCount;
    }

    public void setSwapCount(int swapCount) {
        this.swapCount = swapCount;
    }

    public int getCompareCount() {
        return compareCount;
    }

    public void setCompareCount(int compareCount) {
        this.compareCount = compareCount;
    }

    public void incrementCompareCount() {
        this.compareCount++;
    }

    public void incrementSwapCount() {
        this.swapCount++;
    }
}
