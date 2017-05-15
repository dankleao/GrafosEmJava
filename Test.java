import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.Charset;
/**
 * Created by Daniel on 02/11/16.
 */
public class Test{

    public static void main(String[] args) throws IOException {

        /* Demonstracao de criacao e manipulacao da estrutura grafos */
        Graph g = new UndirectedGraph(5);//grafo nao direcionado de ordem 5.
        Graph h = new DirectedGraph(6);//grafo direcionado de ordem 6.

        g.insertEdge(3,2,7);
        g.insertEdge( new Edge(0,1,9) );
        g.insertEdge( new Edge(0,3,7) );
        g.printGraph(Viewer.BASIC);

        h.insertEdge(3,4,1);
        h.insertEdge(0,1,9);
        h.insertEdge( new Edge(2,1,20) );
        h.insertEdge( new Edge(4,3,1) );
        h.printGraph(Viewer.COSTUMIZED);

        /*  Teste dos algoritmos busca em largura, busca em profundidade e 
            implementacao da heuristica baseada na regra de Warnsdorff para 
            solucao do passeio do cavalo, respectivamente.
         */

        /* Leitura de um grafo a partir da entrada padrao */    
        Graph graph = GraphIO.read();

        BFSearch bfSearch = BFSearch.execute(graph,new Vertex(0));
        bfSearch.showPredecessor();
        bfSearch.showDistance();

        DFSearch dfSearch = DFSearch.execute(graph,new Vertex(0));
        dfSearch.showPredecessor();
        dfSearch.showTimestamp();

        HorseRide horseRide = new HorseRide(5);
        horseRide.setArbitraryVertex(new Vertex(4));
        horseRide.execute();
        horseRide.printGameboard();
    }

}