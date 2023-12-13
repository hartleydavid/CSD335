package DisjoinSet;

public class TestDisJoinSet {

    public static void main(String[] args){
        DisjointSets disjointSet = new DisjointSets(8);
        System.out.println(disjointSet);
        disjointSet.union(3,4);
        disjointSet.union(7,6);
        disjointSet.union(0,1);
        disjointSet.union(1,2);
        disjointSet.union(3,5);
        disjointSet.union(1,3);
        disjointSet.union(0,7);
        System.out.println(disjointSet);

        disjointSet.findWithPathCompression(5);
        System.out.println(disjointSet);

        DisjointSets disjointSetByHeight = new DisjointSets(8);
        disjointSetByHeight.unionByHeight(0,1);
        disjointSetByHeight.unionByHeight(2,0);
        disjointSetByHeight.unionByHeight(3,0);
        disjointSetByHeight.unionByHeight(4,5);
        disjointSetByHeight.unionByHeight(6,4);
        disjointSetByHeight.unionByHeight(6,7);
        disjointSetByHeight.unionByHeight(4,0);

        System.out.println(disjointSetByHeight);
    }
}
