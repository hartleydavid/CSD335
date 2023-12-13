
package graph;

/**
 *
 * @author Thomas.Abbott
 * 
 * This is an implementation of the Vertex object described on
 * the top of page 370 of the course text book:
 * 
 *     "For each vertex, we will keep track of three pieces of information.
 *     First, we will keep its distance from s in the entry dv..."
 */
class Vertex {
    public final static int INFINITY = Integer.MAX_VALUE;
    
    private Integer label;
    
    // three pieces of information
    private int dist;
    private Vertex path;
    private boolean known;

    public Vertex(Integer label) {
        this.label = label;
        this.dist = INFINITY;
        this.known = false;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public void setPath(Vertex path) {
        this.path = path;
    }

    public Integer getLabel() {
        return label;
    }

    public Vertex getPath() {
        return path;
    }

    public int getDist() {
        return dist;
    }

    public boolean getKnown() {
        return known;
    }

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;
        if (this.label.equals(v.label)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label.toString();
    }
}
