/** MaxHeap class for Assignment 4
 *  @Author David Hartley
 */
package assign4;

import java.util.LinkedList;
import java.util.List;

public class LwtMaxHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10; //Default capacity of the heap

    private int currentSize;    // number of elements in heap
    private T [] array;         // the underlying data structure

    /** Default constructor. Calls the One parameter int constructor to
     *  create a default sized heap
     */
    public LwtMaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    /** One Arg Constructor. Instantiates the array of the capacity parameter.
     *
     * @param capacity The capacity of the heap we are creating
     */
    public LwtMaxHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }

    /** One Arg Constructor. Argument is an array of items we want to copy into the classes array
     *
     * @param items The array of T to copy to the classes array
     */
    public LwtMaxHeap(T[] items) {
        //The size of the array is of the length of the array
        currentSize = items.length;
        //Instantiate the new array of the same size as the parameterized array
        array = (T[]) new Comparable[currentSize + 1];
        //Copy the parameter array to the class array
        System.arraycopy(items,0,array,1,currentSize);
        //Build the heap to ensure heap structure is correct
        buildHeap();
    }

    /** Public method that will traverse through the max heap and get all the nodes that are greater
     * than the key parameter value. All greater than nodes will be returned in a linked list
     *
     * @param key The key value we are trying to find all the nodes greater than
     * @return The list of all nodes that are greater than the parameter argument
     */
    public List<T> allNodesGreaterThan(T key ) {
        //If the first index is less than the key
        if(array[1].compareTo(key) < 0)
            //Return an empty list, no node is greater than the max value (root)
            return new LinkedList<>();

        //Traverse through the tree and find the nodes > key
        return recursiveTraversal(key, 1);
    }

    /** Private helper method that will recursively traverse through the tree and find all the nodes
     * that are greater than the key parameter value.
     *
     * @param key The key value that we are comparing if the current node is greater than
     * @param index The current index we are at in traversing the tree
     * @return A List of T items that have a greater value than the parameter key
     */
    private List<T> recursiveTraversal(T key, int index){
        //Instantiate an empty list
        List<T> newList = new LinkedList<>();

        //Exit case: Index out of range or current node is <= the key (no need to check child nodes)
        if(index > currentSize || array[index].compareTo(key) <= 0){
            return newList;
        }

        //If the current index value is > the key
        if(array[index].compareTo(key) > 0 )
            //add value
            newList.add(array[index]);

        //Get the children that are > key
        newList.addAll(recursiveTraversal(key, index*2));
        newList.addAll(recursiveTraversal(key, index*2+1));

        //Return the list
        return newList;
    }

    /** Public method that will insert the given value 'x' into the heap. Will keep the Max heap
     * structure true by percolating nodes to follow the order
     *
     * @param x The value we are inserting into our Max Heap
     */
    public void insert(T x) {
        //If the current size is full
        if( currentSize == array.length - 1 )
            //Double the size of the heap
            enlargeArray( array.length * 2 + 1 );

        //Hole index is at the end of the values in heap.
        int hole = ++currentSize;

        //For each time we need to percolate the hole up
        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) > 0; hole /= 2 )
            //Move parent value down to child
            array[ hole ] = array[ hole / 2 ];
        //Hole has now been percolated up
        array[ hole ] = x;
    }

    /** Public method will find and return the max value of the classes heap data structure
     * This value is also the root of the heap as this is a Max Heap
     * Will throw an error in the case that the heap is empty
     * @return The value of the root node in the heap
     * @throws UnderflowException Throws exception if the heap array is empty
     */
    public T findMax() throws UnderflowException {
        //If the heap is empty, throw error
        if( isEmpty( ) )
            throw new UnderflowException( );
        //Otherwise return the root as it is the max value
        return array[1];
    }

    /** Public method that will remove the largest item of the heap (root)
     * and then percolate the hole made at the root down to the leaf nodes
     * Will return the max item as well.
     * @return The max T value in the tree (root)
     */
    public T deleteMax() throws UnderflowException {

        //Find and get the max value of the max heap. findMax() will throw an error if heap is empty
        T maxValue = findMax();

        //Set the root as the value of the last leaf node and decrement the size
        array[1] = array[currentSize--];
        //Percolate the new root down to keep max heap order
        percolateDown(1);

        //Return the max value
        return maxValue;
    }

    /** Method will test whether the Max heap is empty or not
     * This is done by checking if there are any values in heap
     * @return True if heap is empty, false if there is at least 1 value
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /** Method will make the heap array of this class empty. It will replace the
     * current array with a new array following the default array length of the class
     */
    public void makeEmpty() {
        //Set the current size to be 0 as there are no values
        currentSize = 0;
        //Instantiate a new array for the classes array field
        array = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    /** Private method that will perform a percolate down on the heap data structure i.e. move the
     * 'hole' down to where there will be no gap in the binary tree
     *
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole ) {
        //Child int for the child index
        int child;
        //Temp value holding the holes value
        T tmp = array[ hole ];

        //For all possible holes when percolating
        for( ; hole * 2 <= currentSize; hole = child ) {
            //Get the child's index
            child = hole * 2;

            //If we are not at the end of the list and the right child is greater than the left child
            if( child != currentSize && array[ child + 1 ].compareTo( array[ child ] ) > 0 )
                //Go to the right child
                child++;

            //If the current child is greater than the holes value
            if( array[ child ].compareTo( tmp ) > 0 )
                //Move the current node into the hole
                array[ hole ] = array[ child ];
            else
                //If current child is < hole's value, exit
                break;
        }
        //Fill the hole
        array[ hole ] = tmp;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( ) {
        //For each set of parents in the heap
        for( int i = currentSize / 2; i > 0; i-- )
            //Percolate indexes down
            percolateDown( i );
    }

    /** Method will enlarge the array to the new parameter size. In the case that this method is called
     * with a new size value that is smaller or the same size, it will not enlarge the array as a smaller size is
     * not going to enlarge the array. Overall algorithm of method came from the BinaryHeap.java file provided
     * by @author Thomas.Abbott
     *
     * @param newSize The new size of the array we are going to enlarge the array size to.
     */
    private void enlargeArray(int newSize) {
        //If the new size of the array is not greater than the current size, we cannot enlarge the array.
        if(newSize <= array.length){
            System.out.println("ERROR: Cannot enlarge an array to a smaller value.");
            return;
        }

        //Copy the old array into the newly enlarged array
        T[] old = array;
        array = (T[]) new Comparable[newSize];
        System.arraycopy(old, 0, array, 0, old.length);
    }

    /** Override of the toString method to print out the contents of the classes array with
     * the current size of the array and capacity of the array
     * Method comes from the book authors code.
     * @author Mark Allen Weiss
     * @return A string representation of the MaxHeap class
     */
    @Override
    public String toString() {
        //Create a string builder to build the string representation of the class
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");

        //Append all the values in the array
        for(int i = 1; i <= currentSize; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append("]");

        //Append the size and capacity
        sb.append("\tcurrentSize=").append(this.currentSize);
        sb.append(", capacity=").append(array.length - 1).append("\n");
        //Return the string
        return sb.toString();
    }
}