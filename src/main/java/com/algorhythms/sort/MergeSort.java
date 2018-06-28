package com.algorhythms.sort;

import java.lang.Comparable;

class MergeSort {

    public static void sort(Comparable[] input) {
        Comparable[] aux = new Comparable[input.length];
        sort(input, aux, 0, input.length - 1);
        assert(isSorted(input, 0, input.length - 1));
    }

    private static void sort(Comparable[] input, Comparable[] aux, int lo, int hi) {
        if(hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo)/2;

        // TODO: Optimization 1: Introduce CUTOFF for smaller sub-arrays. Use insertion sort for smaller arrays.
        sort(input, aux, lo, mid);
        sort(input, aux, mid+1, hi);
        // TODO: Optimization 2: If array1's last element is less than array2's first element, it means that entire array is already sorted.
        // So no need to sort in that case.
        merge(input, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] input, Comparable[] aux, int lo, int mid, int hi) {

        //First assert the both halves are sorted.
        assert(isSorted(input, lo, mid));
        assert(isSorted(input, mid+1, hi));

        //Copy over the input array to the auxiliary array.
        for(int k=lo; k<=hi; k++) {
            aux[k] = input[k];
        }

        int i = lo;
        int j = mid+1;

        for(int k=lo; k<=hi; k++) {
            if(i > mid) {
                input[k] = aux[j++];
            } else if(j > hi) {
                input[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                input[k] = aux[j++];
            } else {
                input[k] = aux[i++];
            }
        }

        //Assert that the entire input array is sorted.
        assert(isSorted(input, lo, hi));
    }

    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) == -1);
    }

    public static boolean isSorted(Comparable[] input, int start, int end) {
        for(int i=start; i<end; i++) {
            if(i != (end - 1)) {
                if(!(input[i].compareTo(input[i+1]) == -1 || input[i].compareTo(input[i+1]) == 0)) {
                    return false;
                }
            }
        }
        return true;
    }
}