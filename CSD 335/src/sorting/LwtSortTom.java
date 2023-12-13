package sorting;

/**
 *
 * @author Tom
 */
public class LwtSortTom  {

    public static long swapCount;

    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        swapCount = 0;
        int j;

        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                swapCount++;
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static <T extends Comparable<T>> void shellSort(T[] a) {
        swapCount = 0;
        // TODO - enter the code for shell sort here

    }

    public static <T extends Comparable<T>> void heapSort(T[] a) {
        BinaryMinHeap minHeap = new BinaryMinHeap();

        for (int i = 0; i < a.length; i++) {
            minHeap.insert(a[i]);
        }

        for (int i = 0; i < a.length; i++) {
            if (!minHeap.isEmpty()) {
                try {
                    a[i] = (T) minHeap.deleteMin();
                } catch (UnderflowException ex) {
                    System.out.println("underflow detected in heap sort");
                    return;
                }
            }
        }
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmp[tmpPos++] = a[leftPos++];
            } else {
                tmp[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) // copy rest of first half
        {
            tmp[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) // copy rest of right half
        {
            tmp[tmpPos++] = a[rightPos++];
        }

        // copy tmp back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

    public static < T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmp = (T[]) new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] a) {
        swapCount = 0;
        // TODO - enter the code for quick sort here
    }

    public static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }

        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static <T> void swapReferences(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
