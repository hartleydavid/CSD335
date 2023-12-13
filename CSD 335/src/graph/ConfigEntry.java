
package graph;

/**
 *
 * @author Thomas.Abbott
 * 
 * This is a Java class implementation of an entry in
 * the configuration table described in figure 9.15, 
 * page 370 of the text book
 */
class ConfigEntry {
    private Vertex v;

    public ConfigEntry(Vertex v) {
        this.v = v;
    }

    public void setKnown(boolean known) {
        v.setKnown(known);
    }

    public void setDist(int dist) {
        v.setDist(dist);
    }

    public void setPath(Vertex path) {
        v.setPath(path);
    }
    
    public Vertex getVertex( ) {
        return v;
    }
    
    public boolean getKnown( ) {
        return v.getKnown();
    }
    
    public int getDist( ) {
        return v.getDist();
    }
    
    public Vertex getPath( ) {
        return v.getPath();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[").append(v.getLabel()).append(",").append(v.getDist()).append(",").append(v.getKnown()).append("]\n");
        
        return sb.toString();
    }
}
