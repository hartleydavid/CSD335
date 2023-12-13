package graph;

import java.util.*;

/** Programming Assignment 7 - Shortest Paths Algorithm
 * Algorithm located starting at line 61
 * @author David Hartley
 */
public class DirectedGraph {

    private Map<Vertex, ArrayList<Vertex>> adjVertices = new HashMap<>();
    private ConfigEntry[] configTbl = new ConfigEntry[0];

    // add an element to the vertices set
    public void addVertex(Vertex v) {
        adjVertices.putIfAbsent(v, new ArrayList<>());
    }

    // add an  to the adjacency list of the graph
    public void addEdge(Vertex v, Vertex w) {
        List<Vertex> edges = adjVertices.get(v);
        edges.add(w);
    }

    /**
     * create the configuration table described in figure 9.15, page 370
     *
     * @param s distinguished vertex
     * @param tblSize number of entries in the adjVertices map
     * @return an array of entries described in the configuration table
     */
    private ConfigEntry[] initConfigTable(Vertex s, int tblSize) {
        configTbl = new ConfigEntry[tblSize];

        int i = 0;
        //For each set of Vertices
        for (Map.Entry<Vertex, ArrayList<Vertex>> mapEntry : adjVertices.entrySet()) {
            //Get the vertex and instantiate a new configEntry object
            Vertex v = mapEntry.getKey();
            ConfigEntry config = new ConfigEntry(v);

            //If the current vertex 'v' is equal to starting vertex 's'
            if (s.equals(v)) {
                //Set the distance to be 0
                config.setDist(0);
            } else {
                //If not, the distance is infinity
                config.setDist(Vertex.INFINITY);
            }

            //The next index in the table is the current vertices configEntry object.
            configTbl[i++] = config;
        }
        return configTbl;
    }

    /** Public method that implements a unweighted shortest path algorithm for a graph
     * Follows the algorithm pseudo code from Fig. 9.18 on page 372 in the textbook
     * @param s The starting vertex we are trying to find the shortest path from
     */
    public void unweighted(Vertex s){
        //Create a queue
        Queue<Vertex> q = new LinkedList<>();//new PriorityQueue<Vertex>();
        //Instantiate a new configTable
        int numVertices = adjVertices.size();
        ConfigEntry[] configTbl = initConfigTable(s, numVertices);

        //For each vertex in the classes map object
        for (Map.Entry<Vertex, ArrayList<Vertex>> mapEntry : adjVertices.entrySet()) {
            //Set each distance to INFINITY
            Vertex v = mapEntry.getKey();
            v.setDist(Vertex.INFINITY);
        }
        //Set the starting vertex 's' to be 0 as its the starting vertex
        s.setDist(0);
        //Path is known as its the starting vertex
        s.setKnown(true);
        //Queue the starting vertex
        q.add(s);

        //While the queue is not empty
        while(!q.isEmpty()) {
            //De-Queue the current vertex
            Vertex v = q.remove(); // q.poll();
            //Get the adjacent vertex list for the current vertex
            List<Vertex> adjList = adjVertices.get(v);
            //For each vertex adjacent to the current vertex
            for( Vertex adjVertex : adjList ) {
                //We know the path of the vertex
                // adjVertex.setKnown(true);
                configTbl[adjVertex.hashCode() - 1].setKnown(true);

                //If the shortest node has not been found yet
                if(adjVertex.getDist() == Vertex.INFINITY) {
                    //Update the distance and path
                    adjVertex.setDist(v.getDist() + 1);
                    adjVertex.setPath(v);
                    //Queue the node
                    q.add(adjVertex);
                }
            }
        }
    }

    public void unweighted_figure9dot16(Vertex s) {
        int numVertices = adjVertices.size();
        ConfigEntry[] configTbl = initConfigTable(s, numVertices);

        for (int currDist = 0; currDist < numVertices; currDist++) {
            int vIndex = 0;

            // for each Vertex v
            for (Map.Entry<Vertex, ArrayList<Vertex>> mapEntry : adjVertices.entrySet()) {
                Vertex v = mapEntry.getKey();

                if (!configTbl[vIndex].getKnown() && configTbl[vIndex].getDist() == currDist) {
                    configTbl[vIndex].setKnown(true);
                    // for each Vertex w adjacent to v
                    for (Vertex w : mapEntry.getValue()) {
                        if (w.getDist() == Vertex.INFINITY) {
                            w.setDist(currDist + 1);
                            w.setPath(v);
                        }
                    }
                }
                vIndex++;
            }
        }
    }

    /** Method will print out the config table (nodes paths)
     *
     * @return A string representation of all the nodes shortest path from the starting node
     */
    public String displayConfigTable() {
        StringBuilder sb = new StringBuilder();

        //For each vertex
        for (int i = 0; i < configTbl.length; i++) {
            //Add the value
            sb.append(configTbl[i]);
        }
        //Return
        return sb.toString();
    }

    /** ToString override that will print out the adjacency table of the graph
     *
     * @return A string representation of the adj. table
     */
    @Override
    public String toString() {
        return adjVertices.toString();
    }
}
