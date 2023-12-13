package sorting;

import java.util.Random;

/**
 * TODO - replace my name with your name 
 * @author David Hartley
 */
public class DataSet {

    public enum SortOrder {
        RANDOM, ASCENDING, DESCENDING
    };

    private final int lineLength = 25;
    private final int constOffset = 1;
    private final int variableOffset = 4;
    
    private final Integer[] randomizedArray;
    private final Integer[] sortedAscendingArray;
    private final Integer[] sortedDescendingArray;

    private Random rand = new Random();
    
    public DataSet(int n) {
        randomizedArray = new Integer[n];
        sortedAscendingArray = new Integer[n];
        sortedDescendingArray = new Integer[n];

        // initialize the ascending sorted array
        randomizedArray[0] = sortedAscendingArray[0] = rand.nextInt(variableOffset); 
        for (int i = 1; i < n; i++) {
            randomizedArray[i] = sortedAscendingArray[i] 
                    = sortedAscendingArray[i - 1] 
                    + rand.nextInt(variableOffset) 
                    + constOffset;
        }

        // initialize the descending sorted array
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            sortedDescendingArray[i] = sortedAscendingArray[j];
        }

        // initialize the randomized sorted array with
        // the Modified Fischer-Yates algorithm
        for (int i = n - 1; i > 0; i--) {
            int rIndex = rand.nextInt(i);
            int tmp = randomizedArray[i];
            randomizedArray[i] = randomizedArray[rIndex];
            randomizedArray[rIndex] = tmp;
        }
    }

    public Integer[] getRandomizedArray() {
        Integer[] nReturn = new Integer[randomizedArray.length];
        System.arraycopy(randomizedArray, 0, nReturn, 0, randomizedArray.length);
        return nReturn;
    }

    public Integer[] getSortedAscendingArray() {
        Integer[] nReturn = new Integer[sortedAscendingArray.length];
        System.arraycopy(sortedAscendingArray, 0, nReturn, 0, sortedAscendingArray.length);
        return nReturn;
    }

    public Integer[] getSortedDescendingArray() {
        Integer[] nReturn = new Integer[sortedDescendingArray.length];
        System.arraycopy(sortedDescendingArray, 0, nReturn, 0, sortedDescendingArray.length);
        return nReturn;
    }

    @Override
    public String toString() {
        return toString(SortOrder.ASCENDING);
    }

    public String toString(SortOrder order) {
        StringBuilder sb = new StringBuilder();
        int i;

        switch (order) {
            case RANDOM:
                i = 0;
                while (i < randomizedArray.length) {
                    sb.append(randomizedArray[i++]).append((i % lineLength == 0) ? "\n" : " ");
                }
                break;

            case ASCENDING:
                i = 0;
                while (i < sortedAscendingArray.length) {
                    sb.append(sortedAscendingArray[i++]).append((i % lineLength == 0) ? "\n" : " ");
                }
                break;
                
            case DESCENDING:
                i = 0;
                while (i < sortedDescendingArray.length) {
                    sb.append(sortedDescendingArray[i++]).append((i % lineLength == 0) ? "\n" : " ");
                }
                break;
        }
        return sb.toString();
    }

}
