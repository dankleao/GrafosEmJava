

/**
 * Classe Edge representa e demonstra as capacidades de uma aresta de um grafo.
 * Criado por Daniel em 22/10/16.
 */
public class Edge {

    //Atributos da classe
    private Vertex u;// vértice fonte
    private Vertex v;// vértice alvo


    public Edge( int u, int v, double w){
        this( new Vertex(u), new Vertex(v,w) );
    }

    /**
     * Construtor Edge cria uma aresta(conexao) {u,v}.
     * @param u - vértice fonte.
     * @param v - vértice final.
     * */
    public Edge( Vertex u, Vertex v ){

        setSourceVertex( new Vertex(u.getValue()) );
        setTargetVertex( new Vertex(v.getValue(),v.getWeight()));
    }

    /**
     *  Obtém o vértice fonte(u).
     *  @return o valor do vértice fonte.
     */
    public Vertex getSourceVertex() {
        return u;
    }

    /**
     *  Configura um novo vértice fonte.
     *  @param u - novo vértice fonte.
     * */
    public void setSourceVertex(Vertex u) {
        this.u = u;
    }

    /**
     * Obtém o vértice alvo(adjacente) do vértice fonte(u).
     * @return o valor do vértice alvo.
     * */
    public Vertex getTargetVertex() {
        return v;
    }

    /**
     *  Configura um novo vértice alvo.
     *  @param v - novo vértice alvo.
     * */
    public void setTargetVertex(Vertex v) {
        this.v = v;
    }

    /**
     * Obtém o peso associado a aresta.
     * @return retorna
     * */
    public double getWeight() {
        return getTargetVertex().getWeight();
    }

    /**
     *  Configura um novo peso.
     *  @param w - novo peso.
     * */
    public void setWeight(double w) {
        this.getTargetVertex().setWeight(w);
    }

    /**
     * Sobrescreve o método toString() da classe Object.
     * @return representacao <code>String</code> da classe Edge.
     * */
    @Override
    public String toString(){
        return String.format("{%d,%d}:%.2f",getSourceVertex().getValue(),getTargetVertex().getValue(),getWeight());
    }

}
