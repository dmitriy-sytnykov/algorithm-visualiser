package com.dsytnykov.websocket.algorithm.sort;

public enum SortType {
    BUBBLE("bubble") {
        @Override
        public AbstractSorting getSortingAlgorithm() {
            return new Bubble();
        }
    },
    SELECTION("selection") {
        @Override
        public AbstractSorting getSortingAlgorithm() {
            return new Selection();
        }
    },
    INSERTION("insertion") {
        @Override
        public AbstractSorting getSortingAlgorithm() {
            return new Insertion();
        }
    },
    QUICK("quick") {
        @Override
        public AbstractSorting getSortingAlgorithm() {
            return new Quick();
        }
    },
    MERGE("merge") {
        @Override
        public AbstractSorting getSortingAlgorithm() {
            return new Merge();
        }
    };

    private final String value;

    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract AbstractSorting getSortingAlgorithm();
}
