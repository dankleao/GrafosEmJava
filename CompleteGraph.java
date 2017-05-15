/**
 * Created by Daniel on 02/11/16.
 */
public class CompleteGraph extends UndirectedGraph {
    /**
     * Construtor Graph cria um grafo completo(Kn).
     *
     * @param order - cardinalidade de V.
     */
    public CompleteGraph(int order) {
        super(order);
        for( int i = 0 ; i < order - 1 ; ++i ){
            for( int j = i + 1 ; j < order ; ++j ){
                insertEdge(i,j,GraphIO.nextRandom(100));
            }
        }
    }
}
