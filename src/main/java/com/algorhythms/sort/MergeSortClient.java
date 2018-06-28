package com.algorhythms.sort;

class MergeSortClient {
    public static void main(String[] args) {
        Integer[] input = {5, 3, 9, 0, 1, 3, 2, 7, 6, 4, 8, 5, 3, 9, 0, 1, 3, 2, 7, 6, 4, 8, 5, 3, 9, 0, 1, 3, 2, 7, 6, 4, 8};
        MergeSort.sort(input);
        System.out.println(MergeSort.isSorted(input, 0, input.length));
    }
}