/* Assignment 1 - Lists
 *  Spring 2023
 *  David Hartley
 */

package assign1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LwtList {

    //Handles the case of repeat values in returned list. Value is of the newly inserted value so that value
    //Cannot be inserted again
    private static Integer newlyInsertedValue;

    private static List<Integer> returnList;

    /** Method that intersects two lists in order with all duplicates removed. Intersected is defined as
     * all elements that are common between the two lists.
     * @param L1 The first list<Integer> that will be intersected
     * @param L2 The second list<Integer> that will be intersected
     * @return The intersected list<Integer> between L1 and L2
     * [1,2,3] and [3,4,5] -Returns-> [3]
     */
    public static List<Integer> Intersect(List<Integer> L1, List<Integer> L2){
        //Initialize a new list that will be returned
        returnList = new ArrayList<>();
        //Create Iterators for each of the lists
        Iterator<Integer> iterOne = L1.iterator();
        Iterator<Integer> iterTwo = L2.iterator();

        //Exit case for if a list is empty, there will be no intersection. Return empty list
        if(!iterOne.hasNext() || !iterTwo.hasNext()){
            return returnList;
        }
        //Instantiate two Integer references used for comparison between lists
        Integer valueOne = null;
        Integer valueTwo = null;
        //Instantiate global variable to be null (no values have been inserted)
        newlyInsertedValue = null;
        //Iteration booleans to help with iteration control in while loop
        boolean canIterateOne = true, canIterateTwo = true;
        //While one of the lists have another index
        //One list could be at the end but still have a intersected value later in the other list
        while(iterOne.hasNext() || iterTwo.hasNext()){
            //If there is another value for L1 and we are going to iterate to the next value
            if(iterOne.hasNext() && canIterateOne){
                //Iterate
                valueOne = iterOne.next();
            }else{
                //If the last value of L1 is smaller than the current L2 value
                if(!canIterateTwo)
                    //return list, no intersecting values left
                    return returnList;
            }
            //If there is another value in L2 and we are going to iterate to the next value
            if(iterTwo.hasNext() && canIterateTwo){
                //Iterate
                valueTwo = iterTwo.next();
            }else{
                //If the last value of L2 is smaller than the current L1 value, return list, no similar values left
                if(!canIterateOne)
                    return returnList;
            }

            //If L1's current value == L2's current value
            if(valueOne.equals(valueTwo)) {

                //Add the value to the list (if it can)
                addValue(valueOne);

                //Iterate both lists to the next value
                canIterateOne = true;
                canIterateTwo = true;

                //If L1's value is smaller than the L2's value
            } else if (valueOne.compareTo(valueTwo) < 0){
                //Iterate L1 to get closer to L2's value
                canIterateOne = true;
                canIterateTwo = false;
            }else{
                //Iterate L2 to get closer to L1's value
                canIterateOne = false;
                canIterateTwo = true;

            }
        }
        //Return the intersected list
        return returnList;
    }

    /** Public method that will unionize two lists together and return the new list. Union between two lists is defined
     * as a new list that contains all of the elements that appear in either parameter list in order with all
     * duplicates removed
     *
     * @param L1 The first list object to be unionized
     * @param L2 The second list object to be unionized
     * @return The unionized list
     *  [1,3,5,6] and [2,4,6,8] -Returns-> [1,2,3,4,5,6,8]
     */
    public static List<Integer> Union(List<Integer> L1, List<Integer> L2) {
        //Initialize a new list to be returned
        returnList = new ArrayList<>();
        //Create Iterators for each of the lists
        Iterator<Integer> iterOne = L1.iterator();
        Iterator<Integer> iterTwo = L2.iterator();

        //Exit case for if a list is empty
        //Both empty: Return empty list
        if(!iterOne.hasNext()&& !iterTwo.hasNext()){
            return returnList;
        //L1 is empty: Return L2
        }else if(!iterOne.hasNext()){
            return L2;
        //L2 is empty: Return L1
        }else if(!iterTwo.hasNext()){
            return L1;
        }

        //Instantiate two Integer references used for comparison between lists
        Integer valueOne = null;
        Integer valueTwo = null;
        //Instantiate global variable. Null = unionized list is empty
        newlyInsertedValue = null;

        //Iteration booleans to help with iteration control in while loop
        boolean canIterateOne = true, canIterateTwo = true;
        boolean oneHasNext = true, twoHasNext = true;

        //While both of the lists have another index
        while (oneHasNext && twoHasNext) {

            //If we are iterating to another L1 value
            if (canIterateOne) {
                //Iterate and update loop constraint
                valueOne = iterOne.next();
                oneHasNext = iterOne.hasNext();
            }

            //If we are iterating to another L2 value
            if (canIterateTwo) {
                //Iterate and update loop constraint
                valueTwo = iterTwo.next();
                twoHasNext = iterTwo.hasNext();
            }

            //Create local variable for the number that will be added to our list
            Integer numToBeAdded;
            //Int that returns the compareTo() value between L1's value and L2's value
            int compareToReturn = valueOne.compareTo(valueTwo);
            //If the current L1 value == L2 value
            if (compareToReturn == 0) {
                //Add value into the list
                numToBeAdded = valueOne;
                //Iterate both lists
                canIterateOne = true;
                canIterateTwo = true;

                   //If the current L1 value is less than the L2 value
            } else if (compareToReturn < 0) {
                //Add L1's value into the list
                numToBeAdded = valueOne;
                //Iterate L1's list forward and do not iterate L2 to "catch up"
                canIterateOne = true;
                canIterateTwo = false;

                //Else If the L1 value is greater than the L2 value
            } else {
                //Add L2's value into the list
                numToBeAdded = valueTwo;
                //Iterate L2's list forward and do not iterate L1 to "catch up"
                canIterateOne = false;
                canIterateTwo = true;
            }

            //Add the value to the list
            addValue(numToBeAdded);
        }


        //While L1 still has values
        while(oneHasNext) {
            //If L1 < L2 or L2 has already been inserted into the list
            if (valueOne.compareTo(valueTwo) <= 0 || valueTwo.compareTo(newlyInsertedValue) <= 0) {
                //Add L1 to list and iterate l1 further
                addValue(valueOne);
                valueOne = iterOne.next();
                oneHasNext = iterOne.hasNext();

            } else {//Else
                //Add L2 to list
                addValue(valueTwo);
            }
        }

        //While there are still values left in L2
        while(twoHasNext){
            //if L1 >= L2 or L1's value was already inserted
            if(valueOne.compareTo(valueTwo) >= 0 || valueOne.compareTo(newlyInsertedValue) <= 0) {
                //add L2 and iterate
                addValue(valueTwo);
                valueTwo = iterTwo.next();
                twoHasNext = iterTwo.hasNext();
            }else{
                //add L1
                addValue(valueOne);
            }
        }
        //Check if the last values of each list are in the unionised list
        endOfListAddValue(valueOne,valueTwo);
        //Return list
        return returnList;
    }

    /** Method adds the final values of each list if not already added. Deals with cases where only one list
     * needs to add its last value or where both values need to be added and does so in numerical order
     *
     * @param vOne The last value from L1
     * @param vTwo The last value from L2
     */
    private static void endOfListAddValue(Integer vOne, Integer vTwo){
        //If L1's value is greater than L2's value
        if(vOne.compareTo(vTwo) > 0){
            //If L2's value is greater than the newly inserted value (Else case: L2's value is already in list)
            if(vTwo.compareTo(newlyInsertedValue) > 0) {
                //add L2's last value first
                addValue(vTwo);
            }
            //Add L1's last value
            addValue(vOne);
        }else{
            //If L1's value is greater than the newly inserted value (Else case: L1's value is already in list)
            if(vOne.compareTo(newlyInsertedValue) > 0) {
                //add L1's last value
                addValue(vOne);
            }
            //Add l2's last value
            addValue(vTwo);
        }
    }

    /** Method will add the parameter value to the list that will be returned if the value is not already in the list
     * (No duplicate values)
     *
     * @param newNum The Integer at the current iteration of Union or Intersect that should be added to the list
     */
    private static void addValue(Integer newNum){
        //If the new value to be added is greater than the most recent insertion
        //If the most recent insertion is null that means the list is empty, always add the first value
        if(newlyInsertedValue == null || newNum.compareTo(newlyInsertedValue) > 0 ){
            //Add the number and update the most recent insertion
            returnList.add(newNum);
            newlyInsertedValue = newNum;
        }
    }
}