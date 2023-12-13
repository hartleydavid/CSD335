package assign4;

import java.util.List;

public class MaxHeapTest {

    public static void main(String[] args) {
        LwtMaxHeap<Integer> intHeap = new LwtMaxHeap<>();//default constructor
        LwtMaxHeap<Integer> intHeapTwo = new LwtMaxHeap<>(5);//capacity constructor

        for (int i = 1; i <= 10; i++){
            intHeap.insert(i);
            intHeapTwo.insert(i);
        }

        //Array of T constructor
        LwtMaxHeap<String> stringHeap = new LwtMaxHeap<>(new String[]{"a","b","c","m","p","l","c","z"});

        //Test methods
        System.out.println(intHeap);

        //Try-Catch as deleteMax throws an error
        try{
            //Test deleteMax()
            int max = intHeap.deleteMax();
            System.out.println(max + " was removed from the list.");
            System.out.println(intHeap);

            //Test the underflow exception form deleteMax()/findMax()
            LwtMaxHeap<Integer> underFlowHeap = new LwtMaxHeap<>();
            underFlowHeap.deleteMax();

        }catch (UnderflowException e){
            System.out.println("Error: \"" + e + "\" thrown");
        }


        System.out.println(intHeapTwo);
        System.out.println(stringHeap);
        int greaterThanKey = 6;
        List<Integer> list = intHeapTwo.allNodesGreaterThan(greaterThanKey);
        System.out.println("All values greater than " + greaterThanKey + ": " + list);
        intHeap.makeEmpty();
        System.out.println(intHeap);
    }
}
