/**
 * Created by Daniel on 07/04/17.
 */
public class PCV {

    private Graph graph;
    private static int count = 0;
    private static int[]p;
    private Vertex arbitraryVertex;
    private int lowerCost = Integer.MAX_VALUE;
    private int[]smallerPath;

    public PCV( Graph graph, Vertex arbitraryVertex )
    {
        setGraph(graph);
        setArbitraryVertex(arbitraryVertex);
        setSmallerPath(new int[graph.getOrder()+1]);
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    private void permutation( int[] vet )
    {
        p = new int[vet.length];
        permutation(vet,0);

    }

    private void permutation(int[] vet, int i)
    {
        if( i == vet.length )
        {
            count++;
            //showPermutation();
            walk();
        }
        else
        {
            for( int j = 0 ; j < vet.length ; ++j )
            {
                boolean detect = false;
                for( int k = 0 ; k < i ; ++k )
                {
                    if(p[k]==vet[j])
                        detect = true;
                }

                if(!detect)
                {
                    p[i] = vet[j];
                    permutation(vet,i+1);
                }
            }
        }
    }

    private void walk() {

        int currentCost = (int)graph.getAdjacentVertex(arbitraryVertex.getValue(),graph.existEdge(arbitraryVertex.getValue(),p[0])).getWeight();
        for( int i = 0 ; i < p.length - 1 ; ++i )
        {
            //System.out.print(p[i]+" "+p[i+1]+" ");
            int pos = graph.existEdge(p[i],p[i+1]);
            if( pos != -1 )
                currentCost += (int)graph.getAdjacentVertex(p[i],pos).getWeight();
        }

        currentCost += (int)graph.getAdjacentVertex(arbitraryVertex.getValue(),graph.existEdge(arbitraryVertex.getValue(),p[p.length-1])).getWeight();
        if( currentCost < getLowerCost())
        {
            setLowerCost(currentCost);
            smallerPath[0] = arbitraryVertex.getValue();
            for( int i = 1, j = 0 ; j < p.length ; ++i, ++j )
            {
                smallerPath[i] = p[j];
            }
            smallerPath[smallerPath.length-1] = arbitraryVertex.getValue();
        }
    }

    public void showSmallerPath()
    {

        for( int i = 0 ; i < smallerPath.length-1 ; ++i )
        {
            System.out.print(smallerPath[i]+"->");
        }
        System.out.println(smallerPath[smallerPath.length-1]);
    }

    private void initialization(int [] vet)
    {
        for( int i = 0, j = 0 ; i < vet.length ; ++i, ++j )
        {
            if( i != getArbitraryVertex().getValue() )
            {
                vet[i] = j;
            }
            else
            {
                vet[i] = ++j;
            }
        }
    }

    public void execute()
    {
        int vet[] = new int[graph.getOrder()-1];
        initialization(vet);
        permutation(vet);
    }


    public Vertex getArbitraryVertex() {
        return arbitraryVertex;
    }

    public void setArbitraryVertex(Vertex arbitraryVertex) {
        this.arbitraryVertex = arbitraryVertex;
    }

    public int[] getSmallerPath() {
        return smallerPath;
    }

    public void setSmallerPath(int[] smallerPath) {
        this.smallerPath = smallerPath;
    }

    public int getLowerCost() {
        return lowerCost;
    }

    public void setLowerCost(int lowerCost) {
        this.lowerCost = lowerCost;
    }
}
