

/**
 * Created by Daniel on 09/11/16.
 */
public class DFSearch extends Search {

    private int[] discovery;
    private int[] finalization;
    private int timestamp;

    private DFSearch( Graph g, Vertex v ) {
        super(g,v);
        setDiscovery();
        setFinalization();
        execute();
    }

    private void setDiscovery() {
        if( getGraph() != null )
            discovery = new int[getGraph().getOrder()];
    }

    private void setFinalization(){
        if( getGraph() != null )
            finalization = new int[getGraph().getOrder()];
    }

    public static DFSearch execute(Graph g, Vertex v){
        System.out.println("\nDFSearch executed!\n");
        return new DFSearch(g,v);
    }

    @Override
    public void execute(){

        for( int i = 0 ; i < graph.getOrder() ; ++i )
            predecessor[i] = NULL;

        for( int i = 0 ; i < graph.getOrder() ; ++i ){
            if( color[i] == WHITE )
                execute(i);
        }
    }

    private void execute( int u ){
        timestamp = timestamp + 1;
        discovery[u] = timestamp;
        color[u] = GRAY;
        for( int i = 0 ; i < graph.getDegreeOf(u) ; ++i ){
            Vertex v = graph.getAdjacentVertex(u,i);
            if( color[v.getValue()] == WHITE ){
                predecessor[v.getValue()] = u;
                execute(v.getValue());
            }
        }
        color[u] = BLACK;
        timestamp = timestamp + 1;
        finalization[u] = timestamp;
    }

    public int[] getDiscovery(){
        return discovery;
    }

    public int[] getFinalization(){
        return finalization;
    }

    public void showTimestamp(){
        System.out.println("\nDiscovery");
        for( int i = 0 ; i < discovery.length ; ++i )
            System.out.printf(" %c", (char)(i + 97));
        System.out.println();
        for( int i = 0 ; i < discovery.length ; ++i )
            System.out.printf(" %d",discovery[i]);
        System.out.println();
        System.out.println("\nFinalization");
        for( int i = 0 ; i < finalization.length ; ++i )
            System.out.printf(" %c", (char)(i + 97));
        System.out.println();
        for( int i = 0 ; i < finalization.length ; ++i )
            System.out.printf(" %d",finalization[i]);
        System.out.println();
    }

    public void showPredecessor(){
        System.out.println("\nPredecessor");
        for( int i = 0 ; i < predecessor.length ; ++i )
            System.out.printf(" %c", (char)(i + 97));
        System.out.println();
        for( int i = 0 ; i < predecessor.length ; ++i ){
            System.out.printf(" %c", (char)( predecessor[i] != -1 ? (predecessor[i] + 97) : 45 ) );
        }
        System.out.println();

    }




}
