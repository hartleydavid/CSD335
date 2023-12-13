/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorting;

/**
 *
 * @author Thomas.Abbott
 */
public class BinaryMinHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 16;
    private int currentSize;    // number of elements in heap
    private T[] array;         // the heap array

    public BinaryMinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryMinHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }

    public void insert(T x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        // Percolate up
        int hole = ++currentSize;
        for (array[0] = x;
                x.compareTo(array[hole / 2]) < 0;
                hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    public T findMin() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return array[1];
    }

    public T deleteMin() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        
        return minItem;

    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && 
                    array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }
            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    private void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");

        for(int i = 1; i <= currentSize; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append("]");
        
        sb.append("\tcurrentSize=").append(this.currentSize);
        sb.append(", capacity=").append(array.length - 1).append("\n");
        return sb.toString();
    }
}
