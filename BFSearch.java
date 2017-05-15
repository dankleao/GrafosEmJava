
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 09/11/16.
 */
public class BFSearch extends Search{

    private int[] distance;

    private BFSearch(Graph g,Vertex v) {
        super(g,v);
        setDistance();
        execute();
    }

    private void setDistance() {
        if( graph != null ){
            distance = new int[getGraph().getOrder()];
        }
    }

    public static BFSearch execute( Graph g,Vertex v ) {
        System.out.println("\nBFSearch executed!\n");
        return new BFSearch(g,v);
    }

    @Override
    protected void execute() {
        for( int i = 0 ; i < graph.getOrder() ; ++i ){
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = NULL;
        }
        distance[getArbitraryVertex().getValue()] = 0;
        color[getArbitraryVertex().getValue()] = GRAY;
        predecessor[getArbitraryVertex().getValue()] = NULL;

        List<Vertex> row = new ArrayList<Vertex>();
        row.add(getArbitraryVertex());
        while (!row.isEmpty()) {
            Vertex u = row.remove(0);
            for (int i = 0; i < graph.getDegreeOf(u.getValue()); ++i) {
                Vertex v = graph.getAdjacentVertex(u.getValue(), i);
                if (color[v.getValue()] == WHITE) {
                    color[v.getValue()] = GRAY;
                    distance[v.getValue()] = distance[u.getValue()] + 1;
                    predecessor[v.getValue()] = u.getValue();
                    row.add(v);
                }
            }
            color[u.getValue()] = BLACK;
        }
    }

    public void showDistance(){
        System.out.println("\nDistance");
        for( int i = 0 ; i < distance.length ; ++i )
            System.out.printf(" %c", (char)(i + 97));
        System.out.println();
        for( int i = 0 ; i < distance.length ; ++i )
            System.out.printf(" %d",distance[i]);
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

    public int[] getDistance() {
        return distance;
    }
}
