package graph;

/** Programming Assignment 7 - Shortest Paths Algorithm
 * @author David Hartley
 */
public class UsingDirectedGraph {

    public static void main(String[] args) {
        test01(1);
        test02(2);
        test03(5);
    }
    
    private static void test01(int vertex) {
        DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
            new Vertex(0),  // place holder so vertex labels == array indices
            new Vertex(1),
            new Vertex(2),
            new Vertex(3),
            new Vertex(4)
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[2],v[4]);

        //int vertex = 1;
        g.unweighted(v[vertex]);
        displayTest(g, vertex);
    }

    private static void test02(int vertex) {
        DirectedGraph g = new DirectedGraph();
        Vertex[] v = {
            new Vertex(0),  // place holder so vertex labels == array indices
            new Vertex(1),
            new Vertex(2),
            new Vertex(3),
            new Vertex(4), 
            new Vertex(5),
            new Vertex(6)
        };

        g.addVertex(v[1]);
        g.addVertex(v[2]);
        g.addVertex(v[3]);
        g.addVertex(v[4]);
        g.addVertex(v[5]);
        g.addVertex(v[6]);

        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[3]);
        g.addEdge(v[3],v[4]);
        g.addEdge(v[4],v[5]);
        g.addEdge(v[5],v[6]);
        g.addEdge(v[1],v[5]);

        //int vertex = 1;
        g.unweighted(v[vertex]);
        displayTest(g, vertex);
    }

    //Test case designed after the in class assignment problem 1 graph
    private static void test03(int vertex) {
        DirectedGraph g = new DirectedGraph();
        int numOfVertices = 7;
        //One plus the size so vertex label == index
        Vertex[] v = new Vertex[numOfVertices + 1];

        //Create all the vertices
        for(int index = 0; index < v.length; index++) v[index] = new Vertex(index);
        //For each value besides the first value (placeholder)
        for(int index = 1; index < v.length; index++) g.addVertex(v[index]);

        //Add the edges
        g.addEdge(v[1],v[2]);
        g.addEdge(v[2],v[5]);
        g.addEdge(v[3],v[1]);
        g.addEdge(v[3],v[6]);
        g.addEdge(v[4],v[2]);
        g.addEdge(v[4],v[3]);
        g.addEdge(v[4],v[5]);
        g.addEdge(v[4],v[7]);
        g.addEdge(v[5],v[7]);
        g.addEdge(v[6],v[4]);
        g.addEdge(v[7],v[6]);

        //Starting vertex
        //int vertex = 1;
        g.unweighted(v[vertex]);
        displayTest(g, vertex);
    }

    /** Method will print out the adjacency list and shortest path for each vertex from a starting vertex
     * in the graph to the console
     *
     * @param g The graph object we want to display
     * @param startingVertex The vertex we are starting our shortest path algorithm from
     */
    private static void displayTest(DirectedGraph g, int startingVertex){
        System.out.println("graph adjacency list:");
        System.out.println(g);
        System.out.println("\nShortest paths from vertex " + startingVertex + ". [Vertex, Distance, Path Found]");
        System.out.println(g.displayConfigTable());
    }

}
