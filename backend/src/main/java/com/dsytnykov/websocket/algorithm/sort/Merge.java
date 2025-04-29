package com.dsytnykov.websocket.algorithm.sort;

import com.dsytnykov.websocket.model.ArrayState;

public class Merge extends AbstractSorting {
    /**
     * Merge Sort algorithm entry point
     * @param array The array to sort
     */
    public void sort(int[] array) {
        ArrayState state = new ArrayState(array.clone());

        // Send initial state
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        int[] tempArray = new int[array.length];
        mergeSortImpl(array, 0, array.length - 1, tempArray);
    }

    /**
     * Merge Sort implementation
     */
    private void mergeSortImpl(int[] array, int left, int right, int[] tempArray) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Show division
            ArrayState state = new ArrayState(array.clone());
            state.setCurrentIndex(left);
            state.setCompareIndex(right);
            state.setStep(String.format("Dividing array from index %d to %d", left, right));
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            // Recursively split the array
            mergeSortImpl(array, left, middle, tempArray);
            mergeSortImpl(array, middle + 1, right, tempArray);

            // Merge the sorted halves
            merge(array, left, middle, right, tempArray);
        }
    }

    /**
     * Merge function for Merge Sort
     */
    private void merge(int[] array, int left, int middle, int right, int[] tempArray) {
        // Show merge operation
        ArrayState state = new ArrayState(array.clone());
        state.setCurrentIndex(left);
        state.setCompareIndex(right);
        state.setStep(String.format("Merging subarrays from index %d to %d", left, right));
        sendArrayState(state);
        pause(DEFAULT_DELAY);

        // Copy data to temp arrays
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            // Compare elements from both halves
            state = new ArrayState(array.clone());
            state.setCurrentIndex(i);
            state.setCompareIndex(j);
            state.setStep(String.format("Comparing %d and %d", tempArray[i], tempArray[j]));
            state.incrementCompareCount();
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            if (tempArray[i] <= tempArray[j]) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
            }

            // Show after placing an element
            state = new ArrayState(array.clone());
            state.setCurrentIndex(k);
            state.setStep(String.format("Placed %d at position %d", array[k], k));
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            k++;
        }

        // Copy remaining elements
        while (i <= middle) {
            array[k] = tempArray[i];

            // Show remaining copy
            state = new ArrayState(array.clone());
            state.setCurrentIndex(k);
            state.setStep(String.format("Copied remaining element %d to position %d", array[k], k));
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            i++;
            k++;
        }

        while (j <= right) {
            array[k] = tempArray[j];

            // Show remaining copy
            state = new ArrayState(array.clone());
            state.setCurrentIndex(k);
            state.setStep(String.format("Copied remaining element %d to position %d", array[k], k));
            sendArrayState(state);
            pause(DEFAULT_DELAY);

            j++;
            k++;
        }
    }
}
