/** Assignment 4 Hash Table
 *  HashTable Class
 * @athor David Hartley
 */

package assign3;

public class HashTable {

    //Bucket list is an array of binary search tree objects
    public BinarySearchTree[] bucketList;

    //Default Constructor
    public HashTable() {
        bucketList = null;
    }

    //One Arg Constructor
    public HashTable(int size) {
        bucketList = new BinarySearchTree[size];
    }


    /**
     * Method will insert a key value into the hash table bucket list. If the list is null (default constructor used),
     * method will instantiate a new list and insert value into the list. Insertion is done with a hash value (key % length)
     * If the load factor of the bucket list reaches 1.0 (or all indexes not null), then the Hash table will rehash and
     * reinsert all values back into the new list
     *
     * @param key The Integer reference we are going to add to the Hash Table
     */
    public void insert(Integer key) {

        //If the hash table was instantiated with the default constructor
        if (bucketList == null)
            //Instantiate bucket list to be for the one value. Will rehash after insertion.
            bucketList = new BinarySearchTree[1];

        int hashValue;

        //If the key is negative
        if(key.compareTo(0) < 0){
            //Get the hash value with |key|
            hashValue = Math.abs(key) % bucketList.length;
        }else {
            //Get the hash value of the inserted value
            hashValue = key % bucketList.length;
        }

        //If the current position is empty
        if (bucketList[hashValue] == null) {
            //Instantiate new BST and add value to it
            bucketList[hashValue] = new BinarySearchTree();
        }
        //Add value to the BTS
        bucketList[hashValue].add(key);

        //If after the insert the load factor reached 1.0
        if (loadFactor() == 1.0)
            //rehash the table
            rehash();
    }

    /** Public method that will remove a given integer from the Hash Table's bucket list. Will remove all duplicate
     *  instances of the Integer in the respective Binary Search Tree. Method will catch error cases such as the BST
     *  at a given index is null, key inputted is negative, and key was not found
     *
     * @param key The Integer we want to remove from the Hash Table
     */
    public void remove(Integer key) {
        //Int value of the hash value of the key parameter
        int keyHashValue;

        //If the key is negative
        if(key.compareTo(0) < 0){
            //Get the hash value with |key|
            keyHashValue = Math.abs(key) % bucketList.length;
        }else {
            //Get the hash value of the inserted value
            keyHashValue = key % bucketList.length;
        }

        //Go to that BST
        BinarySearchTree keyBST = bucketList[keyHashValue];
        //Error case: Key does not exist as the BST does not exist
        if(keyBST == null){
            System.out.println("ERROR: No bucket list exists with Integer " + key);
            return;
        }

        //Attempt to remove the Integer
        boolean wasRemoved = keyBST.remove(key);
        //Int to keep a running total of how many keys have been removed from the bucket list
        int keysRemoved;

        //If the key was removed
        if(wasRemoved){
            //Instantiate the keys removed counter to be 1
            keysRemoved = 1;
            //See if there is another element in the BST equal to the key
            wasRemoved = keyBST.remove(key);
        }else {
            //If False, tell user integer was not found
            System.out.println("Error: Integer " + key + " was not found.");
            //Exit method
            return;
        }

        //While there are repeat values in the BTS
        while(wasRemoved){
            //Keep removing all duplicate values
            wasRemoved = keyBST.remove(key);
            keysRemoved++;
        }
        System.out.println(keysRemoved + " instance(s) of the Integer " + key + " were removed.");
    }

    /** Method searches for an item in the hash table and if found, method will return a reference to the Integer
     * from the Binary Search Tree, if not found, return null
     *
     * @param key The Integer we are searching for
     * @return The reference of the integer we are searching for, null if not found, BST does not exist, or invalid key
     */
    public Integer search(Integer key) {

        //Int value of the hash value of the key parameter
        int keyHashValue;

        //If the key is negative
        if(key.compareTo(0) < 0){
            //Get the hash value with |key|
            keyHashValue = Math.abs(key) % bucketList.length;
        }else {
            //Get the hash value of the inserted value
            keyHashValue = key % bucketList.length;
        }

        //Go to that BST
        BinarySearchTree keyBST = bucketList[keyHashValue];

        //If the BTS is not null traverse the tree, if null, return null as no value exists
        return keyBST != null ? traverseTree(keyBST.root, key) : null;
    }

    /** Recursive search method that traverses through the tree and returns the Integer that equals the key value or
     * null if not found. Traverses left or right based on if the current value is greater than or less than the key
     * value we are searching for. Returns the first instance found, if found.
     *
     * @param t The current node we are searching from
     * @param key The integer we are searching for
     * @return The integer from the BST we are searching for or null if not found
     */
    private Integer traverseTree(Node t, Integer key){

        //If we get to a leaf node
        if(t == null){
            return null;
        }

        //Get the current value
        Integer currentValue = t.value;

        //If we found the key, return the reference to it
        if(currentValue.equals(key))
            return currentValue;

        //Integer value for the next child we will go to
        Integer nextValue;

        //If the current nodes value is greater than the key
        if(currentValue.compareTo(key) > 0){
            //Traverse left, key is less than current node
            nextValue = traverseTree(t.left, key);
        }else{
            //Traverse right, key is greater than current node
            nextValue = traverseTree(t.right, key);
        }

        //Return whatever value is returned from traversal
        return nextValue;
    }

    /**
     * Method overrides the toString method to better print out the hash table. This is done with a StringBuilder
     * and will print out each index of the table with the BST printed out in order traversal. If index is empty
     * then it will print out null instead of empty tree.
     *
     * @return A string of all values of the bucket list in the Hash table.
     * Example:
     * 0| null
     * 1| { 49 }
     * 2| { 2 }
     * 3| { 39 47 }
     */
    @Override
    public String toString() {
        StringBuilder returnableString = new StringBuilder();

        //Running total for labeling purposes
        int index = 0;
        //For each element in the bucket list
        for (BinarySearchTree binarySearchTree : bucketList) {
            //Add the current indexes BST to the string that will be returned
            returnableString.append(index++).append("| ").append(binarySearchTree).append("\n");
        }

        //Return the string representation
        return "HashTable Contents: \n" + returnableString;
    }

    /**
     * Public method that will find the number of elements in the hashtable
     *
     * @return The number of elements in the hash table
     */
    public int size() {
        //Int to keep a running total
        int size = 0;

        //For each value of bucket list
        for (BinarySearchTree binarySearchTree : bucketList) {
            //If the current index has a value
            if (binarySearchTree != null) {
                //Get the number of keys that are in this BST
                size += getTreeSize(binarySearchTree.root);
            }
        }

        //Return the sizes
        return size;
    }

    /**
     * Method will return the number of elements in the Binary Search Tree at the provided parameter index value
     * in the bucket list
     *
     * @param index Int value of the index we are going to get the number of elements in
     * @return -1 if the index provided is not in range. 0 If the index is empty (0 values at index).
     * Otherwise, The number of elements at the given index
     */
    public int size(int index) {
        //If the index is out of range
        if (index < 0 || index >= bucketList.length) {
            //Print error message and return -1
            //System.err.println("ERROR: Index provided is out of the HashTable's range");
            System.out.println("ERROR: Index provided is out of the HashTable's range");
            return -1;
        }

        //If the index is empty, return 0
        if (bucketList[index] == null) {
            return 0;
        }

        //Return the number of elements at the index
        return getTreeSize(bucketList[index].root);
    }

    /**
     * Method will traverse through a Binary Search Tree and return the number of elements that are in the tree
     * Every non-null Node counts as 1 element.
     *
     * @param t The current node we are traversing through and getting all the child elements counted for
     * @return The number of elements that are in a given BTS based on the original call of method.
     */
    private int getTreeSize(Node t) {
        //Exit Case: If null, return 0. Do not count
        if (t == null)
            return 0;
        //Return the total of both children and current node
        return getTreeSize(t.left) + 1 + getTreeSize(t.right);
    }

    /**
     * Method will calculate the load factor of the bucket list. The load factor being how many indexes are
     * filled or used in the bucket list with respect to the length of the list. Filled or used indexes are all non null
     * indexes in the bucket list
     * For example, if only 4 indexes are not null in the list of length 10. Then the load factor is 0.4.
     *
     * @return A double representing the load factor. Number is not rounded as that may cause premature rehashing
     * and null pointer errors. 0.999...9 would round to 1.0 causing a rehash
     */
    public double loadFactor() {

        int length = bucketList.length;
        double loadFactor = 0;

        //For each BST in hash table
        for (BinarySearchTree binarySearchTree : bucketList) {
            //If the current BST is not empty
            if (binarySearchTree != null)
                //Increment as index is "loaded"
                loadFactor++;
        }


        //Return the load factor of the list
        return loadFactor / length;//Math.round((loadFactor / length) * 100.0) / 100.0;
    }

    /**
     * Private method will rehash the bucket list and fill the newly made list with all values.
     * Rehashing is done by finding the next prime number that is twice the size of the original bucket list length,
     * setting the bucket list to be a new list of length of said number, and reinserted all of the old hash tables values
     * into the newly rehashed list.
     */
    private void rehash() {
        //Rehash length is next prime number twice the size of the original length
        int reHashLength = bucketList.length * 2;

        //While the number is not prime
        while (notPrime(reHashLength)) {
            //Increment the int until it is prime
            reHashLength++;
        }

        //Get a copy of the old list
        BinarySearchTree[] oldList = copyList(bucketList);
        //Clear old list and set new list with new length
        bucketList = new BinarySearchTree[reHashLength];
        //Insert the old list values into the new list
        rehashInsert(oldList);

    }


    /**
     * Private method that will insert the contents of the old bucket list into the newly rehashed bucket list
     *
     * @param oldList The old bucket list with a load factor of 1.0. Old list should be passed by value not by reference
     */
    private void rehashInsert(BinarySearchTree[] oldList) {

        //Traverse through each element in the old list
        for (BinarySearchTree currentTree : oldList) {
            //Add the tree to the new list
            traverseAddTree(currentTree.root);

        }
    }

    /**
     * Private method that traverses Inorder through a given BST root node and inserts the values into the bucket list
     *
     * @param tree The current tree node we are traversing through. Typically is called from the root node of the entire
     *             BST
     */
    private void traverseAddTree(Node tree) {
        //Exit Case: If node is null, return as leaf node is found
        if (tree == null)
            return;

        //In order traversal
        traverseAddTree(tree.left);
        insert(tree.value);
        traverseAddTree(tree.right);

    }

    /**
     * Private method that will determine whether a number (parameter num) is not Prime or is prime. Returns a boolean value
     * for the respective result
     *
     * @param num Int value to be checked if it is prime or not
     * @return True is the parameter value is not a Prime number, False if it is a prime number
     */
    private boolean notPrime(int num) {

        //Exit Case: number is 1
        if (num <= 1) {
            return true;
        }

        //Start from 2 and go to half of the parameter number
        for (int i = 2; i < num / 2; i++) {
            //If the number is divisible by any number
            if (num % i == 0)
                //It is not prime
                return true;
        }
        //Number is prime
        return false;
    }

    /**
     * Copy "Constructor" method that will copy the contents of the parameter array to a new array that will be returned
     *
     * @param arr BinarySearchTree Array to copy to new array
     * @return The copied array
     */
    private BinarySearchTree[] copyList(BinarySearchTree[] arr) {
        BinarySearchTree[] newList = new BinarySearchTree[arr.length];

        //For each value in array
        for (int i = 0; i < newList.length; i++) {
            //Copy contents to new array
            newList[i] = arr[i];
        }

        return newList;
    }
}