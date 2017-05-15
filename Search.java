

/**
 * Created by Daniel on 02/11/16.
 */
public abstract class Search {

    protected int[]color;
    protected int[] predecessor;
    protected Graph graph;
    protected Vertex arbitraryVertex;
    protected static final int NULL = -1;
    protected static final int WHITE = 0;
    protected static final int GRAY = 1;
    protected static final int BLACK = 2;

    public Search( Graph graph, Vertex arbitraryVertex ){
        setGraph(graph);
        setArbitraryVertex(arbitraryVertex);
        setColor();
        setPredecessor();
    }

    private void setPredecessor(){
        if( graph != null )
            predecessor = new int [graph.getOrder()];
    }
    private void setColor(){
        if( graph != null )
            color = new int [graph.getOrder()];
    }
    protected abstract void execute();
    protected void setGraph( Graph graph ){
        this.graph = graph;
    }
    protected Graph getGraph(){
        return graph;
    }
    public void setArbitraryVertex( Vertex arbitraryVertex ){
        this.arbitraryVertex = arbitraryVertex;
    }
    protected Vertex getArbitraryVertex(){
        return arbitraryVertex;
    }


    public int[] getColor(){
        return color;
    }
    public int[] getPredecessor(){ return predecessor; }
}
