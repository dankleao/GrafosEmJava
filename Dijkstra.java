/**
 * Created by Daniel on 05/04/17.
 */

public class Dijkstra extends Search{

    private int[] distance;
    private String[]path;
    private boolean[] vertexVisited;

    public Dijkstra( Graph graph, Vertex vertex ){
        super(graph,vertex);
        setDistance(new int[graph.getOrder()]);
        setPath( new String[graph.getOrder()]);
        setVertexVisited( new boolean[graph.getOrder()]);
        setArbitraryVertex(vertex);
    }

    private void initialization()
    {
        for( int i = 0 ; i < graph.getOrder() ; ++i )
        {
            distance[i] = Integer.MAX_VALUE-1;
            path[i] = "";
            vertexVisited[i] = false;
        }
    }

    @Override
    protected void execute() {
        execute(getArbitraryVertex().getValue());
    }

    public void execute( int arbitraryVertex ){

        //initialization
        initialization();
        vertexVisited[arbitraryVertex] = true;
        distance[arbitraryVertex] = 0;
        path[arbitraryVertex] = arbitraryVertex + "";
        for( int i = 0 ; i < graph.getDegreeOf(arbitraryVertex) ; ++i )
        {
            Vertex v = graph.getAdjacentVertex(arbitraryVertex,i);
            if( graph.existEdge(arbitraryVertex,v.getValue()) != -1 )
            {
                distance[v.getValue()] = (int)v.getWeight();
                path[v.getValue()] = (arbitraryVertex + "" + v.getValue());
            }
        }

        do {

            int minimumDistance = Integer.MAX_VALUE;
            int idVertex = 0;
            for( int i = 0 ; i < graph.getOrder() ; ++i )
            {
                if( vertexVisited[i] ) 
                    continue;

                if( distance[i] < minimumDistance  )
                {
                    minimumDistance = distance[i];
                    idVertex = i;
                }
            }

            Vertex u = new Vertex(idVertex,minimumDistance);
            
            if( graph.isNull( u.getValue() ) )
                return;

            vertexVisited[u.getValue()] = true;
            for( int i = 0 ; i < graph.getDegreeOf(u.getValue()) ; ++i  )
            {
                Vertex v = graph.getAdjacentVertex(u.getValue(),i);
                if( vertexVisited[v.getValue()] )
                    continue;

                if( distance[u.getValue()] + (int)v.getWeight() < distance[v.getValue()] )
                {
                    distance[v.getValue()] = distance[u.getValue()] + (int)v.getWeight();
                    path[v.getValue()] = path[u.getValue()] + "" + v.getValue();
                }
            }

        }while( existNotVertexVisited() );

    }

    private boolean existNotVertexVisited()
    {       
        for( int i = 0 ; i < vertexVisited.length ; ++i )
        {
            if( ! vertexVisited[i] )
                return true;
        }
        return false;
    }

    public int[] getDistance() {
        return distance;
    }

    public void setDistance(int[] distance) {
        this.distance = distance;
    }

    public boolean[] getVertexVisited() {
        return vertexVisited;
    }

    public void setVertexVisited(boolean[] vertexVisited) {
        this.vertexVisited = vertexVisited;
    }


    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    public void showDijkstra(){
        System.out.println("\nDistance:");
        for( int i = 0 ; i < distance.length ; ++i )
            System.out.printf("%s ", distance[i] != Integer.MAX_VALUE-1 ? distance[i] + "" : "Infinity" );
        System.out.println("\nPath:");
        for( int i = 0 ; i < path.length ; ++i ){
            System.out.printf("%s ", path[i] == null ? "empty" : path[i] );
        }
        System.out.println();
    }

}
