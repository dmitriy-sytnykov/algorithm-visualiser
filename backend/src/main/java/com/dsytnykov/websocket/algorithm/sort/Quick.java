package com.dsytnykov.websocket.algorithm.sort;

import com.dsytnykov.websocket.model.ArrayState;

public class Quick extends AbstractSorting {

    /**
     * Quick Sort algorithm entry point
     * @param array The array to sort
     */
    public void sort(int[] array) {
        ArrayState state = new ArrayState(array.clone());

        sendArrayState(state);
        pause(DEFAULT_DELAY);

        quickSortImpl(array, 0, array.length - 1);
    }

    /**
     * Quick Sort implementation
     */
    private void quickSortImpl(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            // Recursively sort elements before and after partition
            quickSortImpl(array, low, pi - 1);
            quickSortImpl(array, pi + 1, high);
        }
    }

    /**
     * Partition function for Quick Sort
     */
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        ArrayState state = new ArrayState(array.clone());

        // Show pivot selection
        state.setCurrentIndex(high);
        state.setStep(String.format("Selected pivot: %d", pivot));
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // Compare with pivot
            state = new ArrayState(array.clone());
            state.setCurrentIndex(j);
            state.setCompareIndex(high);
            state.setStep(String.format("Comparing %d with pivot %d", array[j], pivot));
            state.incrementCompareCount();
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            // If current element is smaller than the pivot
            if (array[j] < pivot) {
                i++;

                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                // Show after swapping
                state = new ArrayState(array.clone());
                state.setCurrentIndex(i);
                state.setCompareIndex(j);
                state.setStep(String.format("Swapped %d and %d", array[i], array[j]));
                state.incrementSwapCount();
                sendArrayState(state);
                pause(DEFAULT_DELAY);
            }
        }

        // Swap array[i+1] and array[high] (put pivot in its correct position)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // Show after placing pivot
        state = new ArrayState(array.clone());
        state.setCurrentIndex(i + 1);
        state.setStep(String.format("Placed pivot %d at position %d", array[i + 1], i + 1));
        state.incrementSwapCount();
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        return i + 1;
    }
}
