/* Assignment 1 - Lists
 *  Spring 2023
 *  David Hartley
 */

package assign1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLwtList {

    public static void main(String[] args) {

        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();

        //Fill each list of a random length with random numbers itself
        int numberRange = 10000;
        int lengthRange = 100000;
        //For each number up to the range
        for (int i = 0; i <= lengthRange; i++) {

            //If the current iteration number is divisible by a random number 1-10
            if (i % ((int) Math.floor(Math.random() * 10) + 1) == 0)
                //add a random value in another range in the first list
                listOne.add((int) Math.floor(Math.random() * numberRange));
            //If the current iteration number is divisible by a random number 1-10
            if (i % ((int) Math.floor(Math.random() * 10) + 1) == 0)
                //add a random value in another range in the second list
                listTwo.add((int) Math.floor(Math.random() * numberRange));
        }

        //Sort the lists
        Collections.sort(listOne);
        Collections.sort(listTwo);

        //Print out the contents of each list
        //System.out.println("L1:" + listOne);
        //System.out.println("L2:" + listTwo);

        //Print out the intersection test
        System.out.println("Random Values Intersect (with lists swapped):");
        outputTestCase(listOne, listTwo, true);
        outputTestCase(listTwo, listOne, true);

        //Print out the union test
        System.out.println("Random Values Union (with lists swapped):");
        outputTestCase(listOne, listTwo, false);
        outputTestCase(listTwo, listOne, false);

        //Set test cases for both methods
        intersectTest();
        unionTest();
    }

    /**
     * Method that tests and prints out the intersection method for comparison. Also shows the time taken for
     * each intersection to be finished in ms
     * Test cases are set ones (not randomly generated)
     */
    public static void intersectTest() {
        List<Integer> L1;
        List<Integer> L2;

        System.out.println("Intersect Tests:");
        //For each of the tests (9 total)
        for (int i = 1; i <= 9; i++) {
            //For each case
            switch (i) {
                case 1 -> {
                    //Fill the lists
                    L1 = new ArrayList<Integer>();
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5, 6));
                    System.out.println("Test Case " + i + ":");
                    //Test method
                    outputTestCase(L1, L1, true);
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);

                    System.out.println("Test Case " + i + " Solution: []\n");
                }
                case 2 -> {
                    //Fill List
                    L1 = new ArrayList<Integer>(Arrays.asList(1));
                    L2 = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 5, 6));
                    System.out.println("Test Case " + i + ":");
                    //Test
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: []\n");
                }
                case 3 -> {
                    //Fill list
                    L1 = new ArrayList<Integer>(Arrays.asList(1, 1));
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5, 6));
                    System.out.println("Test Case " + i + ":");
                    //Test
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: [1]\n");
                }
                case 4 -> {
                    //Fill list
                    L1 = new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 5, 6));
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 2, 3));
                    System.out.println("Test Case " + i + ":");
                    //Test
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: [1,3]\n");
                }
                case 5 -> {
                    //Fill lists and test
                    L1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    L1.add(5);
                    L2.add(5);
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,5,6]\n");
                }
                case 6 -> {
                    //Fill lists and test
                    L1 = new ArrayList<Integer>(Arrays.asList(13));
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    L1.add(14);
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: [13]\n");
                }
                case 7 -> {
                    //Fill lists and test
                    L1 = new ArrayList<Integer>(Arrays.asList(14, 15));
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: []\n");
                }
                case 8 -> {
                    //Fill lists and test
                    L1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
                    L2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, true);
                    outputTestCase(L2, L1, true);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,5,6,7]\n");
                }
            }
        }
        System.out.println();
    }


    /**
     * Method that tests and prints out the union method for comparison. Also shows the time taken for
     * each union to be finished in ms
     * Test cases are set ones (not randomly generated)
     */
    public static void unionTest() {
        List<Integer> L1;
        List<Integer> L2;

        System.out.println("Union Tests:");
        //For each of the 9 test cases
        for (int i = 1; i <= 9; i++) {
            //For each individual test case
            switch (i) {
                case 1 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 3, 5);
                    L2 = Arrays.asList(1, 3, 5);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,3,5]\n");
                }
                case 2 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 3, 5);
                    L2 = Arrays.asList(2, 4, 6);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,5,6]\n");
                }
                case 3 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 5, 9, 11);
                    L2 = Arrays.asList(2, 20, 40);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,5,9,11,20,40]\n");
                }
                case 4 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 3, 5, 7, 8, 9);
                    L2 = Arrays.asList(2, 4, 6);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,5,6,7,8,9]\n");
                }
                case 5 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 3, 7);
                    L2 = Arrays.asList(2, 4, 6);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,6,7]\n");
                }
                case 6 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(2, 4, 6, 10);
                    L2 = Arrays.asList(1, 3, 7, 10);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,6,7,10]\n");
                }
                case 7 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 2, 3, 4, 5);
                    L2 = Arrays.asList(6, 7, 8, 9, 10);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,5,6,7,8,9,10]\n");
                }
                case 8 -> {
                    //Fill lists and test
                    L1 = Arrays.asList(1, 3, 5, 7, 9, 400);
                    L2 = Arrays.asList(2, 4, 401);
                    System.out.println("Test Case " + i + ":");
                    outputTestCase(L1, L2, false);
                    outputTestCase(L2, L1, false);
                    System.out.println("Test Case " + i + " Solution: [1,2,3,4,5,7,9,400,401]\n");
                }
            }


        }
        System.out.println();
    }

    /**
     * Method will intersect or unionize two lists and get the time elapsed to perform the task
     *
     * @param L1          The first list
     * @param L2          The second list
     * @param isIntersect Boolean value determining whether the method call will be intersect or union
     *                    True = intersect, false = union
     */
    private static void outputTestCase(List<Integer> L1, List<Integer> L2, boolean isIntersect) {
        List<Integer> returnList;
        long startTime;
        long timeElapsed;

        //If we are intersecting
        if (isIntersect) {
            //Start time
            startTime = System.nanoTime();
            //Intersect
            returnList = LwtList.Intersect(L1, L2);
            //End time and time elapsed
            timeElapsed = System.nanoTime() - startTime;
        } else {
            //Start time
            startTime = System.nanoTime();
            //Union
            returnList = LwtList.Union(L1, L2);
            //End time and time elapsed
            timeElapsed = System.nanoTime() - startTime;
        }

        //Convert to ms
        double timeElapsedMS = (timeElapsed / Math.pow(10, 6));
        //Print out the output of method and time elapsed
       // System.out.println("Method Returned: " + returnList);
        System.out.println("Time Elapsed: " + timeElapsedMS + "ms");
    }
}
