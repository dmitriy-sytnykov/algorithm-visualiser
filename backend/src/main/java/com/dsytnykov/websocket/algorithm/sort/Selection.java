package com.dsytnykov.websocket.algorithm.sort;

import com.dsytnykov.websocket.model.ArrayState;

public class Selection extends AbstractSorting{

    /**
     * Selection Sort algorithm implementation
     * @param array The array to sort
     */
    public void sort(int[] array) {
        int n = array.length;
        ArrayState state = new ArrayState(array.clone());

        // Send initial state
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            // Mark current position
            state = new ArrayState(array.clone());
            state.setCurrentIndex(i);
            state.setStep("Finding minimum element...");
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            for (int j = i + 1; j < n; j++) {
                // Update comparison index
                state = new ArrayState(array.clone());
                state.setCurrentIndex(i);
                state.setCompareIndex(j);
                state.setStep(String.format("Comparing %d and %d", array[minIdx], array[j]));
                state.incrementCompareCount();
                sendArrayState(state);
                pause(DEFAULT_DELAY);

                if (array[j] < array[minIdx]) {
                    minIdx = j;

                    // Update minimum index
                    state = new ArrayState(array.clone());
                    state.setCurrentIndex(i);
                    state.setCompareIndex(minIdx);
                    state.setStep(String.format("New minimum found: %d", array[minIdx]));
                    sendArrayState(state);
                    pause(DEFAULT_DELAY);
                }
            }

            // Swap the found minimum element with the element at index i
            if (minIdx != i) {
                int temp = array[minIdx];
                array[minIdx] = array[i];
                array[i] = temp;

                // Send state after swapping
                state = new ArrayState(array.clone());
                state.setCurrentIndex(i);
                state.setCompareIndex(minIdx);
                state.setStep(String.format("Swapped %d and %d", array[i], array[minIdx]));
                state.incrementSwapCount();
                sendArrayState(state);
                pause(DEFAULT_DELAY);
            }
        }
    }
}
