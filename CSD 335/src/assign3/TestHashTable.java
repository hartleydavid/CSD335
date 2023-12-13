/** Assignment 4 Hash Table
 * Testing Hash Table Class
 * @athor David Hartley
 */

package assign3;

public class TestHashTable {

    public static void main (String[] args){
        //In-class assignment hashtable (with a few extra values)
        HashTable testTable = new HashTable(10);
        int[] testValues = {4371,1323, 6173, 4199, 4199, 4344, 9679, 1989, 40, 46, 50007, 72, 31, 88, -1,-8};

        //Insert all test values into the hash table and display table
        for(int num : testValues)
            testTable.insert(num);
        displayHashTable(testTable);

        //Test Remove() method
        System.out.println("******************************");
        System.out.println("Test remove() method:");
        testTable.remove(4199); //Multiple instances
        testTable.remove(9679); //One instance
        testTable.remove(5);    //Null Tree
        testTable.remove(4);    //Does not exist
        testTable.remove(-1);   //Negative key
        System.out.println("******************************");

        //Test search
        System.out.println("///////////////////////////////");
        System.out.println("Test search() method");
        System.out.println("Searching for Int 4371: " + testTable.search(4371)); //Found
        System.out.println("Searching for Int 5: " + testTable.search(5));       //Tree = null
        System.out.println("Searching for Int 4: " + testTable.search(4));       //Not found
        System.out.println("Searching for Int -8: " + testTable.search(-8));     //Found
        System.out.println("///////////////////////////////");

        //Insert values to make load factor == 1.0
        System.out.println("Test rehash()");
        testTable.insert(5);
        displayHashTable(testTable);

        //Test size() method with all cases
        System.out.println("||||||||||||||||||||||||||||||||||");
        System.out.println("Size Tests: ");
        System.out.println("Overall size: " + testTable.size());        //Overall size
        System.out.println("Size at index 5 :" + testTable.size(5)); //Return value
        System.out.println("Size at index 20: " + testTable.size(20));//Return value
        System.out.println("Size at index 21: " + testTable.size(21));//Return value (0). Tree is null
        System.out.println("Size at index -1: " + testTable.size(-1));//index < 0. out of range
        System.out.println("Size at index 50: " + testTable.size(50));//index > length. out of range
        System.out.println("||||||||||||||||||||||||||||||||||");

        //Insert a few values at a time to see the rehash happen
        HashTable testTable2 = new HashTable();
        System.out.println("Empty HashTable insert()");
        testTable2.insert(47);
        testTable2.insert(-1);

        displayHashTable(testTable2);
        System.out.println("Rehash() tests");
        testTable2.insert(49);
        testTable2.insert(39);
        testTable2.insert(57);
        testTable2.insert(-55);
        displayHashTable(testTable2);
        testTable2.insert(2);
        displayHashTable(testTable2);
        testTable2.insert(4);
        displayHashTable(testTable2);

        //Test hash table with a randomly generated array
        HashTable testTableRandom = new HashTable();
        int[] random = RandomArray.generateRandomIntArray(100, 500);
        //Insert all test values into the hash table
        for(int num : random)
            testTableRandom.insert(num);
        //Display table
        System.out.println("Random Hash Table");
        displayHashTable(testTableRandom);

    }

    /** Method that will print out the hash table provided and the load factor
     *
     * @param table The HashTable we want to print
     */
    private static void displayHashTable(HashTable table){
        System.out.println("---------------------------");
        System.out.println(table);
        System.out.println("Load Factor: " + table.loadFactor());
        System.out.println("---------------------------");
    }

}
