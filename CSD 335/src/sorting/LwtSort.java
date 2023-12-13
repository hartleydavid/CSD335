
package sorting;

/** Sorting file for Assignment 5
 * @author David Hartley
 */
public class LwtSort {
    public static long swapCount;

    /** Method will perform an insertion sort on the argument array
     *
     * @param a The array of type T we are going to sort
     * @param <T> The type of the array
     */
    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        //Exit Case: If the array entered is null
        if(a == null) return;

        swapCount = 0;
        int j;
        
        for( int p = 1; p < a.length; p++ ) {
            T tmp = a[ p ];
            for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ]) < 0; j-- ) {
                swapCount++;
                a[ j ] = a[ j - 1 ];
            }
            a[ j ] = tmp;
        }
    }

    /** Method will perform a shell sort using Hibbards increments
     * on the argument array
     *
     * @param a The array of type T we are going to sort
     * @param <T> The type of the array
     */
    public static <T extends Comparable<T>> void shellSort(T[] a) {
        //Exit Case: If the array entered is null
        if(a == null) return;
        swapCount = 0;  //Restart the swap count at the start of the sort
        int j;          //Swappable index value

        //For each of the k values in Hibbard's Increment decrementing
        for( int k = (a.length-1) / 2 ; k >= 1; k--){
            //Calculate hibbards increment (2^k -1)
            int increment = (int) Math.pow(2,k) - 1;

            //For all the indexes from the increment to the end of the list
            for(int index = increment; index < a.length; index++){
                //Get a temp variable for the current index
                T temp = a[index];

                //For if the index is >= the increment (in range) and if the temp value is less than the next increments
                //value. Swap values and go decrement back in the list by the increments value
                for (j = index; j >= increment && temp.compareTo(a[j - increment]) < 0; j -= increment) {
                    a[j] = a[j - increment];
                    swapCount++;
                }
                //Swap temp value to the last value
                a[j] = temp;
            }
        }

    }

    /** Method will perform an heap sort on the argument array using a min heap
     *
     * @param a The array of type T we are going to sort
     * @param <T> The type of the array
     */
    public static <T extends Comparable<T>> void heapSort(T[] a) {
        //Exit Case: If the array entered is null
        if(a == null) return;
        //Instantiate a min heap
        BinaryMinHeap<T> minHeap = new BinaryMinHeap();
        //Restart swap counter
        swapCount = 0;

        //Insert each value in the array we are sorting to the min heap
        for (T t : a)
            minHeap.insert(t);

        //For all the indexes in the array
        for (int i = 0; i < a.length; i++) {
            //If the heap is not empty
            if (!minHeap.isEmpty()) {
                //Try-Catch for Underflow exception
                try {
                    //Try to delete the min value (insert back to array in sorted order)
                    a[i] = minHeap.deleteMin();
                    //If error not thrown, item was swapped
                    swapCount++;
                } catch (UnderflowException ex) {
                    System.out.println("underflow detected in heap sort");
                    return;
                }
            }
        }

    }

    /** Method will perform an merge sort on the argument array
     *
     * @param a The array of type T we are going to sort
     * @param <T> The type of the array
     */
    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        //Exit Case: If the array entered is null
        if(a == null) return;

        swapCount = 0;
        T[] tmp = (T[]) new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    /** Private method that will split the calling arrays in half until there is one value and the recursively
     * sort and merge the arrays back in sorted order.
     * Method provided by @Tom Abbott
     * @param a The first array we are going to merge. Can be an empty array if 'tmp' is not i.e. sorting just 'a'
     * @param tmp The second array we are going to merge. Can be an empty array if 'a' is not i.e. sorting just 'tmp'
     * @param left The left starting index
     * @param right The right starting index
     * @param <T> The type of the arrays we are going to sort
     */
    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmp, int left, int right) {
        //If the left starting point is less than the right starting point
        if (left < right) {
            //Find the center point
            int center = (left + right) / 2;
            //Split the left and right lists in half until there is only one value left in a and tmp
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);

            //Merge the lists together in sorted fashion
            merge(a, tmp, left, center + 1, right);
        }
    }

    /** Private method that will merge two arrays together
     * Method provided by @Tom Abbott
     * @param a One of the arrays of type T to be merged
     * @param tmp The second array of type T to be merged
     * @param leftPos The left position of the arrays to start at
     * @param rightPos The right position of the array we will start at
     * @param rightEnd The ending index value
     * @param <T> The type of the array we are merging together
     */
    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmp, int leftPos, int rightPos, int rightEnd) {

        int leftEnd = rightPos - 1;                 //The left end of the left array (max iteration distance)
        int tmpPos = leftPos;                       //Temp position value to hold the starting left position
        int numElements = rightEnd - leftPos + 1;   //The number of elements in the array to sort through

        // main loop - While we can still iterate through arrays
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            //If the left index value is <= right index value
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                //Add value
                tmp[tmpPos++] = a[leftPos++];
            //Else left > right
            } else {
                //Add value
                tmp[tmpPos++] = a[rightPos++];
            }
            swapCount++;
        }

        while (leftPos <= leftEnd){ // copy rest of first half
            tmp[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) {// copy rest of right half

            tmp[tmpPos++] = a[rightPos++];
        }

        // copy tmp back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

    /** Method will perform an quick sort on the argument array
     *
     * @param a The array of type T we are going to sort
     * @param <T> The type of the array
     */
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        //Restart the swap counter
        swapCount = 0;
        //Perform the quicksort
        quickSort( a, 0, a.length - 1);
    }

    /** Private method that recursively quick sorts array
     * Uses median-of-three partitioning and a cutoff of 10.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <T extends Comparable<T>> void quickSort( T[] a, int left, int right) {
        final int CUTOFF = 10;

        if( left + CUTOFF <= right )
        {
            T pivot = median3( a, left, right );

            // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j ) {
                    swapCount++;
                    swapReferences(a, i, j);
                }else
                    break;
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            quickSort( a, left, i - 1);
            quickSort( a, i + 1, right);
        }
        else {  // Do an insertion sort on the sub-array
            long tempSwapCount = swapCount;
            insertionSort(a);
            swapCount += tempSwapCount;
        }
    }

    /** Method will calculate the median 3 of an array and its left and right boundaries
     * Method provided by @Tom Abbott
     * @param a The array we are sorting
     * @param left The left index boundary
     * @param right The right index boundary
     * @param <T> The type of the array we are sorting
     * @return The median three of the current argument set
     */
    public static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        //Calculate the center index
        int center = (left + right) / 2;
        //If the center index value is < the left index value
        if (a[center].compareTo(a[left]) < 0) {
            //swap
            swapReferences(a, left, center);
        }
        //If the right index value is < the left index value
        if (a[right].compareTo(a[left]) < 0) {
            //swap
            swapReferences(a, left, right);
        }
        //If the right index value is < the center index value
        if (a[right].compareTo(a[center]) < 0) {
            //swap
            swapReferences(a, center, right);
        }

        //Swap the center index with the right -1 index
        swapReferences(a, center, right - 1);
        //Return the
        return a[right - 1];
    }

    /** Private method that will swap two values from the array 'a'
     *
     * @param a The array of type T we are going to swap the values of
     * @param i The i index of the swap
     * @param j The j index of the swap
     * @param <T> The type of the values in the array 'a'
     */
    private static <T> void swapReferences(T[] a, int i, int j) {
        //Swap values at i and j
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        swapCount++;
    }
}
