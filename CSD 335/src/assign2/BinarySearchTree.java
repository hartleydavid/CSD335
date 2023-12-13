/* Programming Assignment 2 - Binary Search Tree
 * David Hartley
 */

package assign2;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;


public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Public static generic method that will return all values from a BinarySearchTree that are within the range of min and max (inclusive).
     * The method will return an arraylist of all items is in-order
     * Tests errors including if the min > max and if the BinarySearchTree is empty
     *
     * @param tree      The BinarySearchTree object to traverse through
     * @param min       The lower bound of the range of items to return
     * @param max       The upper bound of the range of items to return
     * @param <AnyType> Generic method making AnyType resemble any type of class that can be consistently compared to
     * @return An arraylist of all objects within the search range, null if errors are thrown
     */
    public static <AnyType extends Comparable<? super AnyType>> ArrayList<AnyType> CutTree(BinarySearchTree<AnyType> tree, AnyType min, AnyType max) {

        //Check if min is <= max
        if (min.compareTo(max) > 0) {
            //System.err.println("ERROR: Min parameter must be <= to Max parameter");
            System.out.println("ERROR: Min parameter must be <= to Max parameter");
            return null;
        }
        //If the tree is empty
        if (tree.isEmpty()) {
            //return null, nothing to cut from
            return null;
        }

        //Return the "cut" elements from the tree
        return CutTree(tree.root, min, max);
    }

    /**
     * Private overloaded method that will recursively through a BinarySearchTree's nodes to return all the values that are within
     * a specific range (min to max (inclusive)). Follows In-order traversal by going all the way to the left of each node
     * then moving on to the right node. Each recursive iteration that has the nodes element within the min to max range
     * will have that element added to the generic type arraylist that will be returned at the end of recursion.
     *
     * @param n         The current BinaryNode that we are going to traverse through to to get elements in the parameterized range
     * @param min       The lower bound of the range of items to return
     * @param max       The upper bound of the range of items to return
     * @param <AnyType> Generic method making AnyType resemble any type of class that can be consistently compared to
     * @return The current nodes (and child nodes) arraylist of elements within the specified range. The "root" node will
     * return all of the elements in the tree within the range.
     */
    private static <AnyType extends Comparable<? super AnyType>> ArrayList<AnyType> CutTree(BinaryNode<AnyType> n, AnyType min, AnyType max) {
        //Instantiate the current iterations arraylist
        ArrayList<AnyType> returnableArray = new ArrayList<>();
        //If at the end of a branch
        if (n == null) {
            //Return as this is leaf node
            return returnableArray;
        }

        // Get the current element
        AnyType currentElement = n.element;

        //Compare current nodes elements to min and max
        int compareToMin = currentElement.compareTo(min);
        int compareToMax = currentElement.compareTo(max);

        //If the current value is not smaller than the minimum value (Greater than)
        if (compareToMin > 0) {
            //Go to the left child as there is still more values that could be returned
            returnableArray.addAll(CutTree(n.left, min, max));
        }

        //If min <= current element <= max
        if (compareToMin >= 0 && compareToMax <= 0) {
            //Add the element to the list
            returnableArray.add(currentElement);
        }

        //if the current value is less than the max value
        if (compareToMax < 0) {
            //continue further to the right child
            returnableArray.addAll(CutTree(n.right, min, max));
        }

        //Return the arraylist of AnyType
        return returnableArray;
    }

    /* Code provided by book author below */
    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public AnyType findMax() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    public void printTree() {
        /*4.57*/
        if (isEmpty())
            System.out.println("Empty Tree");
        else
            printTree(root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        //Figure 4.18
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        //Figure 4.20
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);

    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        //Fig 4.20
        if (t != null)
            while (t.right != null)
                t = t.right;
        return t;

    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        //Fig 4.22
        if (t == null)
            return new BinaryNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);

        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        //Fig 4.25
        if (t == null)
            return t;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;


    }

    private void printTree(BinaryNode<AnyType> t) {
        /*4.57*/

        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    private static class BinaryNode<AnyType> {
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        /*Figure 4.16*/
        BinaryNode(AnyType theElement) {

            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

    }

}
