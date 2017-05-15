
import java.util.ArrayList;
import java.util.List;

/**
 * A classe abstrata Graph representa um grafo arbitrário G, implementado por lista de adjacencia.
 * Created by Daniel on 22/10/16.
 */
public abstract class Graph implements Viewer{

    //Atributos da classe Graph
    protected List<ArrayList<Vertex>> listOfAdj;//lista de lista com elementos do tipo Vertex.
    protected int length;//tamanho do grafo.

    /**
     * Construtor Graph cria um grafo nulo com |V| vértices.
     * */
    public Graph(int order) {
        this.listOfAdj = new ArrayList<ArrayList<Vertex>>();
        for (int i = 0 ; i < order ; ++i)
            this.listOfAdj.add(new ArrayList<Vertex>());
    }

    /**
     *	Verifica se aresta {u,v} existe no grafo.
     *	@param u - vértice inicial.
     *	@param v -  vértice final.
     *  @return a posicao da aresta na lista de adjacencia,caso a mesma exista.Senao retorna <b>-1</b>.
     * */
    public int existEdge(int u, int v){
        for( int i = 0 ; i < getDegreeOf(u) ; ++i ){
            Vertex w = listOfAdj.get(u).get(i);
            if( w.getValue() == v ){
                return i;
            }
        }
        return -1;
    }

    /**
     *	Libera o espaco ocupado por um grafo.
     * */
    public void freeGraph(Graph graph) {
        graph = null;
    }

    /**
     * Obtem a aresta de menor peso do grafo.
     * @return a aresta de menor peso do grafo.
     * */
    public Edge getEdgeLowerWeight(int u){
        if( getDegreeOf(u) > 0 ){
            Vertex v1 = getAdjacentVertex(u,0);
            for( int i = 1 ; i < getDegreeOf(u) ; ++i ){
                Vertex v2 = getAdjacentVertex(u,i);
                if( v2.getWeight() < v1.getWeight() ){
                    v1 = v2;
                }
            }
            return new Edge(new Vertex(u),v1);
        }
        else {
            return null;
        }
    }

    public Edge getEdgeLowerWeight(){
       if( getLength() > 0 ){

           int i = 0;
           while( getEdgeLowerWeight(i) == null ) ++i;

           Edge e1 = getEdgeLowerWeight(i);
           for( int j = i+1 ; j < getOrder() ; ++j ){
                Edge e2 = getEdgeLowerWeight(j);
                if( e2 != null && e2.getWeight() <  e1.getWeight() ){
                    e1 = e2;
                }
           }

           return e1;
       }
       else {
           return null;
       }

    }

    public Edge getEdgeHigherWeight(int u){
        if( getDegreeOf(u) > 0 ){
            Vertex v1 = getAdjacentVertex(u,0);
            for( int i = 1 ; i < getDegreeOf(u) ; ++i ){
                Vertex v2 = getAdjacentVertex(u,i);
                if( v2.getWeight() > v1.getWeight() ){
                    v1 = v2;
                }
            }
            return new Edge(new Vertex(u),v1);
        }
        else {
            return null;
        }
    }

    public Edge getEdgeHigherWeight(){
        if( getLength() > 0 ){

            int i = 0;
            while( getEdgeHigherWeight(i) == null ) ++i;

            Edge e1 = getEdgeHigherWeight(i);
            for( int j = i+1 ; j < getOrder() ; ++j ){
                Edge e2 = getEdgeHigherWeight(j);
                if( e2 != null && e2.getWeight() >  e1.getWeight() ){
                    e1 = e2;
                }
            }

            return e1;
        }
        else {
            return null;
        }

    }

    public Edge[] getIncidentEdges( int u ){
        Edge[] edges = new Edge[getDegreeOf(u)];
        for( int i = 0 ; i < getDegreeOf(u) ; ++i ){
            Vertex v = getAdjacentVertex(u,i);
            edges[i] = new Edge(new Vertex(u),v);
        }
        return edges;
    }


    /**
     * Obtém o vertice adjacente a um determinado vertice u na posicao i.
     * @param u - vértice fonte
     * @param pos - posicao do vértice adj.
     * @return o vértice adj de u.
     * */
    public Vertex getAdjacentVertex(int u, int pos) {
        return listOfAdj.get(u).get(pos);
    }

    /**
     * Obtém o grau de um determinado vértice u.
     * @param  u - vértice qualquer.
     * @return retorna o grau do vértice u.
     * */
    public int getDegreeOf(int u) {
            return listOfAdj.get(u).size();
    }
    /**
     * Obtém o tamanho( cardinalidade de E ) do grafo.
     * @return o número de arestas do grafo.
     * */
    public int getLength() {
        return length;
    }

    /**
     * Obtém a ordem( cardinalidade de V ) do grafo.
     * @return o número de vértices do grafo.
     * */
    public int getOrder() {
        return listOfAdj.size();
    }

    /**
     *	Insere uma aresta <i>{u,v}<i> com peso <i>w<i> no grafo.
     *	@param u - vértice fonte do grafo.
     *  @param v - vértice final do grafo.
     *	@param w - peso associado a aresta.
     * */
    public void insertEdge(int u, int v, double w) {
        listOfAdj.get(u).add( new Vertex(v,w) );
    }

    public void insertEdge( Edge edge ){
        insertEdge(edge.getSourceVertex().getValue(),edge.getTargetVertex().getValue(),edge.getWeight());
    }

    public boolean isNull(){
        return ( getLength() == 0 );
    }

    /**
     *  Imprime grafo.
     * */
    @Override
    public void printGraph( int opt ){
        switch (opt){
            case 0:
                for( int i = 0 ; i < getOrder(); ++i ){
                    System.out.printf("\n%d: ",i);
                    for( int j = 0 ; j < getDegreeOf(i) ; ++j ){
                        System.out.printf( "%d:%d -> ",(getAdjacentVertex(i,j).getValue()),(int)getAdjacentVertex(i,j).getWeight());
                    }
                }
                System.out.println();
            break;
            default:
                for( int i = 0 ; i < getOrder(); ++i ){
                    System.out.printf("\n%c: ",i+97);
                    for( int j = 0 ; j < getDegreeOf(i) ; ++j ){
                        System.out.printf( "%c:%d -> ",(getAdjacentVertex(i,j).getValue()+97),(int)getAdjacentVertex(i,j).getWeight());
                    }
                }
                System.out.println();
        }
    }

    /**
     * Determina se o grafo é euleriano.
     * @return <code>true</code> se o grafo for euleriano.
     * */
    public boolean isEulerian(){
        boolean validity = true;
        for( int i = 0 ; i < getOrder() ; ++i ){
            if( getDegreeOf(i) % 2 != 0 ){
                validity = false;
                break;
            }
        }
        return validity;
    }

    /**
     *	Remove uma aresta {u,v} do grafo.
     *  @param u - vértice fonte.
     *	@param v - vértice final.
     *	@return aresta{u,v} removida com seu peso associado.
     * */
    public Edge removeEdge(int u, int v) {
        int pos = existEdge(u,v);
        if( pos != -1 ){
            Vertex w = listOfAdj.get(u).remove(pos);
            return new Edge(u,w.getValue(),w.getWeight());
        }
        return null;
    }

    public boolean isNull(int u){
        return ( listOfAdj.get(u).size() == 0  );
    }
}




