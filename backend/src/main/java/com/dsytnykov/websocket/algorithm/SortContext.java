package com.dsytnykov.websocket.algorithm;

import com.dsytnykov.websocket.algorithm.sort.AbstractSorting;
import com.dsytnykov.websocket.model.ArrayState;

import java.util.function.Consumer;

public class SortContext {

    private AbstractSorting sortingAlgorithm;
    private Consumer<ArrayState> stateUpdateCallback;

    public void setStateUpdateCallback(Consumer<ArrayState> callback) {
        this.stateUpdateCallback = callback;
    }

    public void setSortingAlgorithm(AbstractSorting sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public void sort(int[] array) {
        sortingAlgorithm.setStateUpdateCallback(stateUpdateCallback);
        sortingAlgorithm.sort(array);
    }

}
