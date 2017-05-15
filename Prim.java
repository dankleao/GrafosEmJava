
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 11/11/16.
 */
public class Prim{

    private static Graph graph;
    private static UndirectedGraph minimumSpanningTree;
    private static int[] T;

    private static Edge getEdgeLowerWeight( List<Edge> list ){
        Edge e1 = list.get(0);
        for ( int i = 1 ; i < list.size() ; ++i ){
            Edge e2 = list.get(i);
            if( e2.getWeight() < e1.getWeight() )
                e1 = e2;
        }
        list.remove(e1);
        return e1;
    }

    private static void execute(){
        Vertex u = new Vertex(0);
        T[u.getValue()] = 1;
        List<Edge> list = new ArrayList<>();
        for( int i = 0 ; i < getGraph().getDegreeOf(u.getValue()) ; ++i ){
            Vertex v = graph.getAdjacentVertex(u.getValue(),i);
            list.add(new Edge(u,v));
        }

        while( 0 < list.size() ){
            Edge e1 = getEdgeLowerWeight(list);
            u = e1.getTargetVertex();
            if( T[u.getValue()] != 1 ){
                T[u.getValue()] = 1;
                minimumSpanningTree.insertEdge(e1);
                for( int i = 0 ; i < getGraph().getDegreeOf(u.getValue()) ; ++i ){
                    Vertex v = graph.getAdjacentVertex(u.getValue(),i);
                    list.add(new Edge(u,v));
                }
            }
        }

    }

    public static Graph execute(Graph graph){
        if( ! graph.isNull() ){
            setGraph(graph);
            setMinimumSpanningTree(new UndirectedGraph(graph.getOrder()));
            T = new int[graph.getOrder()];
            execute();
            return getMinimumSpanningTree();
        }
        else {
            return null;
        }
    }

    private static Graph getGraph() {
        return graph;
    }

    private static void setGraph(Graph g) {
        graph = g;
    }

    private static UndirectedGraph getMinimumSpanningTree() {
        return minimumSpanningTree;
    }

    private static void setMinimumSpanningTree(UndirectedGraph undirectedGraph) {
        minimumSpanningTree = undirectedGraph;
    }
}
