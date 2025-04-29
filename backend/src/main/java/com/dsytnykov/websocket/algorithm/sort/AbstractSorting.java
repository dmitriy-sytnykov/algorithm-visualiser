package com.dsytnykov.websocket.algorithm.sort;

import com.dsytnykov.websocket.model.ArrayState;

import java.util.function.Consumer;

public abstract class AbstractSorting {

    private Consumer<ArrayState> stateUpdateCallback;
    protected static final int DEFAULT_DELAY = 200; // Default delay between steps in milliseconds


    public abstract void sort(int[] array);

    public void setStateUpdateCallback(Consumer<ArrayState> callback) {
        this.stateUpdateCallback = callback;
    }

    /**
     * Helper method to send the current array state to connected clients
     * @param state Current state of the array
     */
    protected void sendArrayState(ArrayState state) {
        if (stateUpdateCallback != null) {
            stateUpdateCallback.accept(state);
        }
    }

    /**
     * Helper method to pause execution between steps
     * @param milliseconds Pause duration in milliseconds
     */
    protected void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
