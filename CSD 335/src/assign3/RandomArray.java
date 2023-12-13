/** Assignment 3 Hash Table
 * Supporting Class that will generate a random int array
 * @author David Hartley
 */

package assign3;

public class RandomArray {

    /** Static method that will generate a random array of a given length with numbers of a given value range
     * and return it.
     *
     * @param arrayLength Int value representing the arrays length (number of ints in the array)
     * @param valueRange Int value representing the range of values inserted into the array. 0 to number inclusive
     * @return The randomly generated array
     */
    public static int[] generateRandomIntArray(int arrayLength, int valueRange){
        int[] randomArray = new int[arrayLength];

        //For each index in the array
        for (int i = 0; i < arrayLength; i++) {
            //Insert a random number in the range of 0 -> valueRange inclusive
            randomArray[i] = (int) Math.floor(Math.random() * valueRange + 1);
        }
        return randomArray;
    }
}
