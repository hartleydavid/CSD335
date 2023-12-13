/*
 * 
 */
package assign4;

/**
 *
 * @author Thomas.Abbott
 */
public class TheBigTest {
    public static void main( String[] args ) {
        test1();
    }
    
    private static void test1() {
        BinaryHeap<Integer> bHeap = new BinaryHeap<>();
        bHeap.insert(13);
        bHeap.insert(21);
        bHeap.insert(16);
        System.out.println(bHeap);
        
        bHeap.insert(24);
        bHeap.insert(31);
        bHeap.insert(19);
        bHeap.insert(68);
        System.out.println(bHeap);

        bHeap.insert(65);
        bHeap.insert(26);
        bHeap.insert(32);
        System.out.println(bHeap);

        bHeap.makeEmpty();
        System.out.println(bHeap);
    }
}
