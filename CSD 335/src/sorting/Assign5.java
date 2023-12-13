package sorting;

/** My testing file for Sort.java
 * @author David Hartley
 */

public class Assign5 {

    public static void main(String[] args) {

        DataSet dataSet;

        /* Manual dataset length outside of later loops
        dataSet = new DataSet(1000000);
        testSort(dataSet.getRandomizedArray(), "SHELL");
        testSort(dataSet.getRandomizedArray(), "HEAP");
        testSort(dataSet.getRandomizedArray(), "MERGE");
        testSort(dataSet.getSortedAscendingArray(), "SHELL");
        testSort(dataSet.getSortedAscendingArray(), "HEAP");
        testSort(dataSet.getSortedAscendingArray(), "MERGE");
        testSort(dataSet.getSortedDescendingArray(), "SHELL");
        testSort(dataSet.getSortedDescendingArray(), "HEAP");
        testSort(dataSet.getSortedDescendingArray(), "MERGE");*/

        //Overall Structure of dataset generation provided by @Tom Abbott
        System.out.println("Randomized");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            testSort(dataSet.getRandomizedArray(), "INSERTION");
            testSort(dataSet.getRandomizedArray(), "SHELL");
            testSort(dataSet.getRandomizedArray(), "HEAP");
            testSort(dataSet.getRandomizedArray(), "MERGE");
            testSort(dataSet.getRandomizedArray(), "QUICK");
            System.out.println();
        }

        System.out.println("\nSorted Ascending");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            testSort(dataSet.getSortedAscendingArray(), "INSERTION");
            testSort(dataSet.getSortedAscendingArray(), "SHELL");
            testSort(dataSet.getSortedAscendingArray(), "HEAP");
            testSort(dataSet.getSortedAscendingArray(), "MERGE");
            testSort(dataSet.getSortedAscendingArray(), "QUICK");
            System.out.println();
        }

        System.out.println("\nSorted Descending");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            testSort(dataSet.getSortedDescendingArray(), "INSERTION");
            testSort(dataSet.getSortedDescendingArray(), "SHELL");
            testSort(dataSet.getSortedDescendingArray(), "HEAP");
            testSort(dataSet.getSortedDescendingArray(), "MERGE");
            testSort(dataSet.getSortedDescendingArray(), "QUICK");
            System.out.println();
        }
    }

    /** Method will test a given sorting algorithm on a given array
     *  The possible sorting algorithms are Insertion, Shell, Heap, Merge and Quick sort
     *  Method will also time the algorithm to see the time complexity displayed in milliseconds
     * @param dataSet The array dataset we are going to sort
     * @param sort A string representation of what sort we want. As long as the sorting algorithm are one of the 5
     *             and spelled correctly, it will sort the array.
     */
    private static void testSort(Integer[] dataSet, String sort) {
        long startTime;     //The starting time of the array
        long timeElapsed;   //The time that has elapsed during the sort (current time - start time)

        //Switch case for which sort we want. Always uppercase for simplicity
        switch (sort.toUpperCase()) {
            case "INSERTION" -> {
                startTime = System.nanoTime();
                LwtSort.insertionSort(dataSet);
                timeElapsed = System.nanoTime() - startTime;
            }
            case "SHELL" -> {
                startTime = System.nanoTime();
                LwtSort.shellSort(dataSet);
                timeElapsed = System.nanoTime() - startTime;

            }
            case "HEAP" -> {
                startTime = System.nanoTime();
                LwtSort.heapSort(dataSet);
                timeElapsed = System.nanoTime() - startTime;

            }
            case "MERGE" -> {
                startTime = System.nanoTime();
                LwtSort.mergeSort(dataSet);
                timeElapsed = System.nanoTime() - startTime;

            }
            case "QUICK" -> {
                startTime = System.nanoTime();
                LwtSort.quickSort(dataSet);
                timeElapsed = System.nanoTime() - startTime;
            }
            default -> {
                System.out.println(sort + " is not a valid sorting algorithm");
                timeElapsed = 0;
            }
        }
        //Time elapsed in ms
        double timeElapsedMS = (timeElapsed / Math.pow(10, 6));
        //System.out.printf(" -> %9s sort -> %s\nMoves made: %d\n", sort, Arrays.toString(dataSet),LwtSort.swapCount);
        System.out.printf("%9s sort of length %d | Moves made: %16d | Time Elapsed: %fms\n", sort, dataSet.length, LwtSort.swapCount, timeElapsedMS);
    }
}
