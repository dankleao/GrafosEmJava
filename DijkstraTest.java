public class DijkstraTest
{
	
	public static void main( String [] args )
	{
		Graph g = GraphIO.read();
		g.printGraph(Viewer.BASIC);	

		Dijkstra dijkstra = new Dijkstra(g,new Vertex(0));	
		dijkstra.execute();
		dijkstra.showDijkstra();

	}

}