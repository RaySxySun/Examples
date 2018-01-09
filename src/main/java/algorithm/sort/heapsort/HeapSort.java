package algorithm.sort.heapsort;

// https://www.cnblogs.com/skywang12345/p/3624343.html
/*
 *                  Best        Avg         Worst       mem         stable
 * Merge sort:      n(logn)     n(logn)     n(logn)     worst n         Y
 * Bubble sort:     n           n2          n2          1               Y
 * Selection:       n2          n2          n2          1               N
 * Insertion        n           n2          n2          worst n         Y
 * in-pace Merge    -           -           n(logn)2    1               Y
 * Quicksort        nlogn       nlogn       n2          logn, n         N
 * Heapsort         nlogn       nlogn       nlogn       1               N
 */

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3};
        System.out.println("Before sorting:");
        ArrayUtils.printArray(arr);

        heapSort(arr);
        System.out.println("After sorting:");
        ArrayUtils.printArray(arr);
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        buildMaxHeap(arr);

        for (int i = arr.length - 1; i >= 1; i--) {
            ArrayUtils.exchangeElements(arr, 0, i);
            maxHeap(arr, i, 0);
        }

    }

    private static void buildMaxHeap(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int half = arr.length / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(arr, arr.length, i);
        }
    }

    private static void maxHeap(int[] arr, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;
        if (left < heapSize && arr[left] > arr[index]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (index != largest) {
            ArrayUtils.exchangeElements(arr, index, largest);
            maxHeap(arr, heapSize, largest);
        }
    }
}
