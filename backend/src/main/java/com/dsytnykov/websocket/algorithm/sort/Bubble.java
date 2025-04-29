package com.dsytnykov.websocket.algorithm.sort;

import com.dsytnykov.websocket.model.ArrayState;

public class Bubble extends AbstractSorting {
    /**
     * Bubble Sort algorithm implementation
     * @param array The array to sort
     */
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        ArrayState state = new ArrayState(array.clone());
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                state = new ArrayState(array.clone());
                state.setCurrentIndex(j);
                state.setCompareIndex(j + 1);
                state.setStep(String.format("Comparing %d and %d", array[j], array[j+1]));
                state.incrementCompareCount();
                sendArrayState(state);
                pause(DEFAULT_DELAY);

                if (array[j] > array[j + 1]) {
                    // Swap the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;

                    // Send the state after swapping
                    state = new ArrayState(array.clone());
                    state.setCurrentIndex(j);
                    state.setCompareIndex(j + 1);
                    state.setStep(String.format("Swapped %d and %d", array[j], array[j+1]));
                    state.incrementSwapCount();
                    sendArrayState(state);
                    pause(DEFAULT_DELAY);
                }
            }

            // If no swapping occurred in this pass, the array is sorted
            if (!swapped)
                break;
        }
    }
}
