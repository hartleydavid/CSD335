/* Programming Assignment 2 - Binary Search Tree
 * David Hartley
 */

package assign2;

import java.util.ArrayList;

public class TestBinarySearchTree {

    public static void main(String[] args) {
        //Int values for generating random tree
        int numberOfValues = 10000000;
        int randomNumberRange = 500000;
        //Get a binary search tree of random values with random number of values
        BinarySearchTree<Integer> integerBinarySearchTree = randomTree(numberOfValues, randomNumberRange);
        testTime(integerBinarySearchTree, 9000, 10000);

        //Hand-controlled test with integer
        integerBinarySearchTree.makeEmpty();
        integerBinarySearchTree.insert(9000);
        integerBinarySearchTree.insert(300);
        integerBinarySearchTree.insert(3);
        integerBinarySearchTree.insert(2);
        integerBinarySearchTree.insert(1);
        integerBinarySearchTree.insert(4);
        integerBinarySearchTree.insert(5);
        integerBinarySearchTree.insert(340);
        integerBinarySearchTree.insert(301);
        integerBinarySearchTree.insert(341);
        integerBinarySearchTree.insert(303);
        integerBinarySearchTree.insert(302);
        integerBinarySearchTree.insert(380);
        integerBinarySearchTree.insert(370);
        testTime(integerBinarySearchTree, 302, 9500);

        //Hand-controlled test with string
        BinarySearchTree<String> stringBinarySearchTree = new BinarySearchTree<>();
        stringBinarySearchTree.insert("The ROOOOOOOT!!!");
        stringBinarySearchTree.insert("Apples are cool");
        stringBinarySearchTree.insert("Azzles are better?");
        stringBinarySearchTree.insert("Shelby");
        stringBinarySearchTree.insert("Zebra");
        stringBinarySearchTree.insert("Walrus");
        stringBinarySearchTree.insert("Watermelon");
        stringBinarySearchTree.insert("Batman");
        stringBinarySearchTree.insert("Mustang");
        stringBinarySearchTree.insert("Protein");
        stringBinarySearchTree.insert("Chrome");
        stringBinarySearchTree.insert("!sdrawkcab");
        testTime(stringBinarySearchTree, "!", "j");

        //Hand-controlled test with characters
        BinarySearchTree<Character> characterBinarySearchTree = new BinarySearchTree<>();
        characterBinarySearchTree.insert('g');
        characterBinarySearchTree.insert('o');
        characterBinarySearchTree.insert('t');
        characterBinarySearchTree.insert('o');
        characterBinarySearchTree.insert('m');
        characterBinarySearchTree.insert('b');
        characterBinarySearchTree.insert('e');
        characterBinarySearchTree.insert('s');
        characterBinarySearchTree.insert('t');
        characterBinarySearchTree.insert('p');
        characterBinarySearchTree.insert('r');
        characterBinarySearchTree.insert('o');
        characterBinarySearchTree.insert('f');
        testTime(characterBinarySearchTree, 'e', 'f');

    }

    /**
     * Method will test the elapsed time it takes for the cutTree() method for a given tree and a given range
     * Prints out what cutTree returns, the time elapsed, and the number of keys (length of cutTree) returned.
     * All parameters are assumed not null as that is not the purpose of this method, this method will assume all values
     * are entered correctly in order to test time-complexity of method
     *
     * @param tree      The binarySearchTree to test.
     * @param min       The lower bound of the range of items to return
     * @param max       The upper bound of the range of items to return
     * @param <AnyType> Generic method making AnyType resemble any type of class that can be consistently compared to
     */
    private static <AnyType extends Comparable<? super AnyType>> void testTime(BinarySearchTree<AnyType> tree, AnyType min, AnyType max) {

        long startTime;
        long timeElapsed;
        ArrayList<AnyType> cutTree;

        startTime = System.nanoTime();
        cutTree = BinarySearchTree.CutTree(tree, min, max);
        //End time and time elapsed
        timeElapsed = System.nanoTime() - startTime;

        //Convert to s (for ms change 9 to 6)
        double timeElapsedMS = (timeElapsed / Math.pow(10, 9));
        //Print out the output of method and time elapsed
        System.out.println("CutTree Returned: " + cutTree);
        System.out.println("Time Elapsed    : " + timeElapsedMS + "s");
        System.out.println("Number of Keys  : " + cutTree.size());

    }

    /** Method will create and return a Binary Search Tree with random values within a range specified
     * of a specified length. Length may not be the exact number inputted as as duplicate values are not
     * entered into the tree
     * @param numberOfValues    int value that will determine how many numbers will be inserted to the tree.
     *                          Note that due to the random insertion and Binary Tree's not allowing duplicate
     *                          values, it may not be the exact number of values expected (depends of the random number range)
     * @param randomNumberRange int value that will determine the range of the random numbers that are inserted.
     *                          The range will be 0 to randomNumberRange - 1
     * @return The random Integer BinarySearchTree object
     */
    private static BinarySearchTree<Integer> randomTree(int numberOfValues, int randomNumberRange) {
        BinarySearchTree<Integer> returnTree = new BinarySearchTree<>();
        //For the numbers in range
        for (int i = 0; i < numberOfValues; i++) {
            //Get a random number 0 to range - 1 and insert into tree
            int randomNum = (int) Math.ceil(Math.random() * randomNumberRange);
            returnTree.insert(randomNum);
        }
        //return tree
        return returnTree;
    }
}
