

/**
 * Classe UndirectedGraph representa e demonstra as capacidades de um grafo não direcionado.
 * Created by Daniel on 28/10/16.
 */
public class UndirectedGraph extends Graph {

    /**
     * Construtor Graph cria um grafo nulo com |V| vértices.
     * @param order - cardinalidade de V.
     */
    public UndirectedGraph(int order) {
        super(order);
    }

    /**
     * Determina se o grafo é completo.
     *
     * @return <code>true</code> se o grafo for completo.
     */
    public boolean isComplete() {
            boolean validity = true;
            int degree = getOrder() - 1;
            for (int i = 0; i < getOrder(); ++i) {
                if (degree != getDegreeOf(i)) {
                    validity = false;
                    break;
                }
            }
            return validity;
        }

    /**
     * Obtém o grafo complementar G' do grafo G.
     * @return G' de G.
     * */
    public UndirectedGraph getComplementaryGraph(){
        UndirectedGraph undirectedGraph = new UndirectedGraph(getOrder());
        int[] countAdjVertexs = new int[getOrder()];
        int degree = getOrder();
        for( int i = 0 ; i < getOrder() - 1 ; ++i ){
            //Verifica se gr(v) < gr(u), sendo u um vértice de um grafo completo(Kn).
            if( getDegreeOf(i) < degree ){
                for( int j = 0 ; j < getDegreeOf(i) ; ++j )
                    countAdjVertexs[super.getAdjacentVertex(i,j).getValue()] = 1;

                for( int j = i + 1 ; j < getOrder() ; ++j ) {
                    if (countAdjVertexs[j] == 0)
                        undirectedGraph.insertEdge(i, j, 1);
                    else
                        countAdjVertexs[j] = 0;
                }
            }
        }
        return undirectedGraph;
    }

    @Override
    public void insertEdge(int u, int v, double w) {
        super.insertEdge(u, v, w);
        super.insertEdge(v, u, w);
        ++length;
    }

    @Override
    public void insertEdge(Edge edge) {
        super.insertEdge(edge);
        ++length;
    }

    @Override
    public Edge removeEdge(int u, int v) {
        --length;
        super.removeEdge(v, u);
        return super.removeEdge(u, v);
    }

    @Override
    public void printGraph( int opt ) {
        System.out.println("\n"+UndirectedGraph.class.getSimpleName());
        super.printGraph(opt);
    }
}
