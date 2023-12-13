/*
 * This implementation of DisjointSets is from the text, "Data Structures and 
 * Algorithm Analysis in Java", third edition, Mark Allen Weiss
 */
package DisjoinSet;

import java.util.Arrays;
import java.util.Formatter;

/**
 *
 * @author ...from the course text...
 */
public class DisjointSets {
    private int[] s;
    
    /**
     * Construct the disjoint sets object.
     * @param numElements the initial number of disjoint sets
     */
    public DisjointSets( int numElements ) {
        // figure 8.7
        s = new int[ numElements ];
        for( int i = 0; i < s.length; i++ ) {
            s[ i ] = -1;
        }
    }
    
    /**
     * Union two disjoint sets.
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names
     * @param root1 the root of set1
     * @param root2 the root of set2
     */
    public void union( int root1, int root2 ) {
        // figure 8.8 and 8.14
        s[ root2 ] = root1;
    }
    
    /**
     * Perform a find
     * Error checks omitted again for simplicity
     * @param x the element  being searched  for
     * @return the set containing x
     */
    public int find( int x ) {
        // figure 8.9 and 8.16
        if( s[ x ] < 0 ) 
            return x;
        return find( s[ x ]);
    }
    
    /**
     * Union two disjoint sets using the height heuristic.
     * For simplicity, we assume root1 and root2 are distinct 
     * and represent set names
     * @param root1 the root of set 1
     * @param root2 the root of set 2
     */
    public void unionByHeight( int root1, int root2 ) {
        if( s[ root2 ] < s[ root1 ]) {
            s[ root1 ] = root2;
        } else {
            if( s[ root1 ] == s[ root2 ]) {
                s[ root1 ]--;
            }
            s[ root2 ] = root1;
        }
        
    }
    
    /**
     * Perform a find with path compression.
     * Error checks omitted again for simplicity.
     * @param x the element being searched for.
     * @return the set containing x.
     */
    public int findWithPathCompression( int x ) {
        if( s[ x ] < 0 ) 
            return x;
        else
            return s[ x ] = findWithPathCompression( s[ x ]);
    }

    @Override
    public String toString() {
        StringBuilder valueLine = new StringBuilder();
        StringBuilder indexLine = new StringBuilder();
        Formatter fmt = new Formatter(new StringBuilder());
        valueLine.append("Values: ");
        indexLine.append("Index:   ");

        for(int index = 0; index < s.length; index++){
            int value = s[index];
            if(value < 0){
                valueLine.append(s[index]).append("| ");
            }else {
                valueLine.append(" ").append(s[index]).append("| ");
            }
            indexLine.append(index).append("|  ");
        }

        return valueLine + "\n" + indexLine + "\n";
    }
}
