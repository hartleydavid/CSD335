/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package sorting;

/**
 * TODO - replace my name with your name 
 * @author David Hartley
 */
public class Assign5Tom {

    public static void main(String[] args) {
        DataSet dataSet;

        System.out.println("\nrandomized");
        //for (int sz = 10; sz <= 10; sz *= 10) {
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            
            insertionSort(dataSet.getRandomizedArray());
            shellSort(dataSet.getRandomizedArray());
            heapSort(dataSet.getRandomizedArray());
            mergeSort(dataSet.getRandomizedArray());
            quickSort(dataSet.getRandomizedArray());
            System.out.println("");
        }
        
        System.out.println("\nsorted ascending");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            
            insertionSort(dataSet.getSortedAscendingArray());
            shellSort(dataSet.getSortedAscendingArray());
            heapSort(dataSet.getSortedAscendingArray());
            mergeSort(dataSet.getSortedAscendingArray());
            quickSort(dataSet.getSortedAscendingArray());
            System.out.println("");
        }

        System.out.println("\nsorted descending");
        for (int sz = 10; sz <= 100000; sz *= 10) {
            dataSet = new DataSet(sz);
            
            insertionSort(dataSet.getSortedDescendingArray());
            shellSort(dataSet.getSortedDescendingArray());
            heapSort(dataSet.getSortedDescendingArray());
            mergeSort(dataSet.getSortedDescendingArray());
            quickSort(dataSet.getSortedDescendingArray());
            System.out.println("");
        }
    }

    private static void insertionSort(Integer[] dataSet) {
        LwtSort.insertionSort(dataSet);
        System.out.println("\tinsertion sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount);
    }

    private static void shellSort(Integer[] dataSet) {
        LwtSort.shellSort(dataSet);
        System.out.println("\tshell sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount);
    }

    private static void heapSort(Integer[] dataSet) {
        LwtSort.heapSort(dataSet);
        System.out.println("\theap sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount);
    }

    private static void mergeSort(Integer[] dataSet) {
        LwtSort.mergeSort(dataSet);
        System.out.println("\theap sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount);
    }

    private static void quickSort(Integer[] dataSet) {
        LwtSort.quickSort(dataSet);
        System.out.println("\tquick sort on a " + dataSet.length + " element array, swap count=" + LwtSort.swapCount);
    }
}
