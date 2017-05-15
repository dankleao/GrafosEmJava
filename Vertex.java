
/**
 *  Classe Vertex representa e demonstra as capacidades de um vértice.
 *  Criado por Daniel em 22/10/16.
 */
public class Vertex{

    //Atributos da Classe Vertex
    private int value;// identificador(Rótulo)
    private double weight;// peso
    private boolean isTraveled;

    public Vertex( int value ){
        this( value, 1.0 );
    }

    public Vertex( int value, double weight ){
        this(value,weight,false);
    }

    /**
     * Contrutor Vertex assegura a inicializacao das variáveis de instancias:
     * @param value - identificador.
     * @param weigth - peso.
     * */
    public Vertex( int value, double weigth, boolean isTraveled ){
        setValue(value);
        setWeight(weigth);
        setTraveled(isTraveled);
    }

    /**
     * Obtém o identificador da classe Vertex que representa um vértice.
     * @return retorna o identificador do vértice.
     */
    public int getValue() {
        return value;
    }

    /**
     * Configura o identificador do vértice.
     * @param value - valor do identificador.
     * */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Obtém o peso.
     * */
    public double getWeight() {
        return weight;
    }

    /**
     * Configura o peso.
     * @param weight - peso.
     * */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Sobrescreve o método toString() da classe Object.
     * @return representacao <code>String</code> da classe Vertex.
     * */
    @Override
    public String toString(){
        return String.format("%d:%.2f",getValue(),getWeight());
    }

    public boolean isTraveled() {
        return isTraveled;
    }

    public void setTraveled(boolean traveled) {
        isTraveled = traveled;
    }
}

