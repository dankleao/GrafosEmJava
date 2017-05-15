

/**
 * Classe DirectedGraph representa e demostra as capacidades de um dígrafo.
 * Created by Daniel on 28/10/16.
 */
public class DirectedGraph extends Graph{

    /**
     * Construtor Graph cria um grafo nulo com |V| vértices.
     * @param order - cardinalidade de V.
     */
    public DirectedGraph(int order) {
        super(order);
    }

    @Override
    public void insertEdge(int u, int v, double w) {
        super.insertEdge(u,v,w);
        ++length;
    }

    @Override
    public void insertEdge(Edge edge) {
        super.insertEdge(edge);
        ++length;
    }

    @Override
    public Edge removeEdge(int u, int v ){
        --length;
        return super.removeEdge(u,v);
    }

    @Override
    public void printGraph( int opt ) {
        System.out.println("\n"+DirectedGraph.class.getSimpleName());
        super.printGraph(opt);
    }
}
