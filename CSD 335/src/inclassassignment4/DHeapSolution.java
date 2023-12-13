/**
 * In class assignment problem 3
 * With parent to children and child to parent
 * Actual equations are at lines 59 and 82
 * @author David Hartley
 */
package inclassassignment4;

import java.util.Arrays;

public class DHeapSolution {

    // The D-size of the heap array
    public static int D = 2;
    // The height of the d-heap tree
    public static int HEIGHT = 2;
    // The total number of nodes in the D-heap of the given height. (The length of the array representation)
    public static int LENGTH = sigma(HEIGHT, D) + 1;


    public static void main(String[] args) {
        //Print out all the indexes in the array as if its an array of the numbers
        printDHeap();
        //Print out the parents children for the root and their children
        parentToChild();
        //Print out all the child's parent node after the root node
        childToParent();
    }

    /**
     * Method will print out the root nodes and first set of child nodes child node index represented as an array
     * of their indexes
     */
    private static void parentToChild() {
        //Print all the children of the root and their children
        //From index 1 to d number of iterations
        for (int index = 1; index <= LENGTH; index++) {
            System.out.println("Index " + index + "'s child indexes: "
                    + Arrays.toString(getChildren(index)));
        }
    }

    /**
     * Method will get all of the children of a parent node (i) in a array representation of a d-heap tree
     * and return all the values in a new array.
     * Follows the equation  d(i-1) + [0,d) + 2
     * (Old equation -> di - (d-[0,d)-2) )
     *
     * @param i int i is the current parent node index we are trying to get the children of
     * @return An array of all the child indexes of the parent node i
     * Null if the the node we are at (i) is a leaf node
     */
    private static int[] getChildren(int i) {
        int[] childArray = new int[D];

        //For children [0,d)
        for (int n = 0; n < D; n++) {
            //For the ith child, add the ith child from the array. Children are ordered by 0,1,2...d-1
            int index = D * (i - 1) + n + 2;//(d*i) - (d-n-2);
            //If we are at a leaf node (no children)
            if (index >= LENGTH) {
                //Return null as there are no child nodes
                return null;
            }
            childArray[n] = index;
        }

        return childArray;
    }

    /**
     * Method Will find the parent nodes of every child node in the d-heap. Meaning every index except for index 0 and 1.
     * Calculates the child nodes index to find the parents node with the following equation: (i-2)/d + 1
     * Prints out the indexes parent node
     * Method assumes that there are at least a child of the root node
     */
    private static void childToParent() {
        //For each value in the heap starting from the first child of the root node
        for (int i = 2; i < LENGTH; i++) {

            //Calculate
            int index = (i - 2) / D + 1;//(i/d) + ((i%d)/2);//(i-2)/d + 1
            System.out.println("Index " + i + "'s parent is at index " + index);

            //If we are moving to the next parents set of children
            if ((i - 1) % D == 0) {
                //Print a new line (spacer line)
                System.out.println();
            }
        }
    }

    /**
     * Method will print the d-heap to have a "|" divider between each set of d-children. For example
     * a 4-heap array would look like [0, 1 | 2, 3, 4, 5| ...] because 1 is the root, and 2-5 are the children of that
     * node. The next set of d-numbers would represent 2's children, then 3's children and so on.
     * Method assumes that the heap as at least 3 values (and probably a full d-heap array, no testing has been done for
     * edge cases)
     */
    private static void printDHeap() {
        StringBuilder builder = new StringBuilder();
        //Append the first
        builder.append("[");

        //for each index in the list
        for (int index = 0; index < LENGTH; index++) {
            builder.append(index);
            //If we are at a dth element
            if ((index - 1) % D == 0) {
                //Append set separator
                builder.append(" | ");
            } else {
                //Append a comma
                builder.append(", ");
            }
        }
        //Close off array and print out to console
        builder.append("]");
        System.out.println(builder);
    }

    /**
     * Method calculates the number of nodes that will be in a dheap tree array of a given height
     * and returns it
     *
     * @param height int height value of the tree being made
     * @param d      int d for the number of children in the heap
     * @return The number of nodes that will be in the tree
     */
    private static int sigma(int height, int d) {
        int sum = 0;
        //For the number of rows in the tree
        for (int i = 0; i <= height; i++) {
            //Add d^i for that rows number of nodes
            sum += (int) Math.pow(d, i);
        }
        return sum;
    }
}
