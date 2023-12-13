package assign1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Junk {

    public static List<Integer> Intersect(List<Integer> L1, List<Integer> L2){
        //Exit case for if a list is empty, there will be no intersection. Return empty list
        if(L1.isEmpty() || L2.isEmpty()) return new ArrayList<Integer>();
        //Initialize a new list to be returned
        List<Integer> intersectList = new ArrayList<>();
        //Create Iterators for each of the lists
        Iterator<Integer> iterOne = L1.iterator();
        Iterator<Integer> iterTwo = L2.iterator();

        //Get the first indexes of each list. Lists have at least one value
        Integer valueOne = iterOne.next();
        Integer valueTwo = iterTwo.next();

        //Iteration booleans to help with iteration control in while loop
        boolean canIterateOne = false, canIterateTwo = false;
        //While one of the lists have another index
        //One list could be at the end but still have a intersected value in the other list
        while(iterOne.hasNext() || iterTwo.hasNext()){
            //If there is another value for L1
            if(iterOne.hasNext()){
                //If we are going to iterate, iterate
                if(canIterateOne){
                    valueOne = iterOne.next();
                }
            }else{
                //If the last value of L1 is smaller than the current L2 value, return list, no intersecting values left
                if(!canIterateTwo){
                    return intersectList;
                }
            }

            //If there is another value in L2
            if(iterTwo.hasNext()){
                //If we are going to iterate, iterate
                if(canIterateTwo){
                    valueTwo = iterTwo.next();
                }
            }else{
                //If the last value of L2 is smaller than the current L1 value, return list, no similar values left
                if(!canIterateOne){
                    return intersectList;
                }
            }

            //If L1 == L2, add the intersection to returned list
            if(valueOne.equals(valueTwo)) {
                //If the value is not already in the list, add it
                if(!intersectList.contains(valueOne)) { //TODO: Do this without .contains()
                    intersectList.add(valueOne);
                }
                //Iterate both indexes further
                canIterateOne = true;
                canIterateTwo = true;

                //If L1 value is smaller than the L2 value
            } else if (valueOne < valueTwo){
                //Iterate L1 to get closer to L2's value
                canIterateOne = true;
                canIterateTwo = false;
            }else{
                //Iterate L2 to get closer to L1's value
                canIterateTwo = true;
                canIterateOne = false;
            }
        }
        //Return the intersected list
        return intersectList;
    }

    public static List<Integer> IntersectWorkingSorta(List<Integer> L1, List<Integer> L2){
        //Exit case for if a list is empty, there will be no intersection. Return empty list
        if(L1.isEmpty() || L2.isEmpty()) return new ArrayList<Integer>();
        //Initialize a new list to be returned
        List<Integer> intersectList = new ArrayList<>();
        //Create Iterators for each of the lists
        Iterator<Integer> iterOne = L1.iterator();
        Iterator<Integer> iterTwo = L2.iterator();

        //Get the first indexes of each list. Lists have at least one value
        Integer valueOne = iterOne.next();
        Integer valueTwo = iterTwo.next();

        //Iteration booleans to help with iteration control in while loop
        boolean canIterateOne = false, canIterateTwo = false;
        //While one of the lists have another index
        while(iterOne.hasNext() || iterTwo.hasNext()){
            //If the first or second list will be iterated and has another value, iterate that list.
            if(canIterateOne && iterOne.hasNext()) valueOne = iterOne.next();
            if(canIterateTwo && iterTwo.hasNext()) valueTwo = iterTwo.next();

            //If L1 == L2, add the intersection to returned list
            if(valueOne.equals(valueTwo)) {
                //If the value is not already in the list, add it
                if(!intersectList.contains(valueOne)) {
                    intersectList.add(valueOne);
                }
                //Iterate both indexes further
                canIterateOne = true;
                canIterateTwo = true;

                //If L1 value is smaller than the L2 value
            } else if (valueOne < valueTwo){
                //Iterate L1 to get closer to L2's value
                canIterateOne = true;
                canIterateTwo = false;
            }else{
                //Iterate L2 to get closer to L1's value
                canIterateTwo = true;
                canIterateOne = false;
            }
        }
        //Return the intersected list
        return intersectList;
    }
}
