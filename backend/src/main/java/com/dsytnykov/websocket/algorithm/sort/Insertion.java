package com.dsytnykov.websocket.algorithm.sort;

import com.dsytnykov.websocket.model.ArrayState;

public class Insertion extends AbstractSorting {
    /**
     * Insertion Sort algorithm implementation
     * @param array The array to sort
     */
    public void sort(int[] array) {
        int n = array.length;
        ArrayState state = new ArrayState(array.clone());

        // Send initial state
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Mark the key being considered
            state = new ArrayState(array.clone());
            state.setCurrentIndex(i);
            state.setStep(String.format("Current key: %d", key));
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            while (j >= 0 && array[j] > key) {
                // Compare and shift elements
                state = new ArrayState(array.clone());
                state.setCurrentIndex(i);
                state.setCompareIndex(j);
                state.setStep(String.format("Comparing %d and %d", key, array[j]));
                state.incrementCompareCount();
                sendArrayState(state);
                pause(DEFAULT_DELAY);

                // Shift element to the right
                array[j + 1] = array[j];

                // Show after shifting
                state = new ArrayState(array.clone());
                state.setCurrentIndex(j + 1);
                state.setStep(String.format("Shifted %d to position %d", array[j], j + 1));
                state.incrementSwapCount();
                sendArrayState(state);
                pause(DEFAULT_DELAY);

                j--;
            }

            // Place the key in its correct position
            array[j + 1] = key;

            // Show the key placement
            state = new ArrayState(array.clone());
            state.setCurrentIndex(j + 1);
            state.setStep(String.format("Placed key %d at position %d", key, j + 1));
            sendArrayState(state);
            pause(DEFAULT_DELAY);
        }
    }
}
