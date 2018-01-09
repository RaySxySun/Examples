package algorithm.sort.mergesort;

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

public class mergeSort {
    static int number = 0;

    public static void main(String[] args) {
        int[] a = {26, 199, 2, 49, 58, 23, 18, 48};
        printArray("original sequence: ", a);
        runMergeSort(a);
        printArray("sorted sequence: ", a);
    }

    private static void runMergeSort(int[] a) {
        System.out.println("start sorting the array");
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int[] tmp = new int[a.length];
        int r1 = mid + 1;
        int tIndex = left;
        int cIndex = left;
        while (left <= mid && r1 <= right) {
            if (a[left] <= a[r1]) {
                tmp[tIndex++] = a[left++];
            } else {
                tmp[tIndex++] = a[r1++];
            }
        }
        while (left <= mid) {
            tmp[tIndex] = a[left++];
        }
        while (r1 <= right) {
            tmp[tIndex++] = a[r1++];
        }

        System.out.println("# " + (++number) + " sorted result:\t");
        while (cIndex <= right) {
            a[cIndex] = tmp[cIndex];
            System.out.print(a[cIndex] + "\t");
            cIndex++;
        }
        System.out.println();
    }

    private static void printArray(String s, int[] a) {
        System.out.println(s + "\n");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }


}
