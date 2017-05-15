

/**
 * Created by Daniel on 09/11/16.
 */
public class HorseRide extends Search {

    private int boardSize;
    private int [][] gameBoard;
    private int steps = 0;

    public HorseRide(int boardSize){
        super(new UndirectedGraph((int) Math.pow(boardSize,2.0)),new Vertex(0));
        setBoardSize(boardSize);
        setGameBoard(new int[getBoardSize()][getBoardSize()]);
        bootGraph();
    }

    private void bootGraph(){
        int size = (int)Math.pow(getBoardSize(),2.0);
        int [][]graph = new int[size][size];
        for( int i = 0 ; i < getBoardSize() ; ++i ){
            for( int j = 0 ; j < getBoardSize() ; ++j ) {
                for( int k = 0 , x = -1 ; k < 2 ; ++k, x += 2 ){
                    for( int l = 0, y = -2 ; l < 2 ; ++l, y += 4 ){
                        if( isValidMove(i+x,j+y,getBoardSize()) ){
                            int valueSquare = (((i+x)*getBoardSize())+(j+y));
                            graph[(i*getBoardSize())+j][valueSquare] = 1;
                            graph[valueSquare][(i*getBoardSize())+j] = 1;

                        }
                    }
                }
                for( int k = 0 , x = -2 ; k < 2 ; ++k, x += 4 ){
                    for( int l = 0, y = -1 ; l < 2 ; ++l, y += 2 ){
                        if( isValidMove(i+x,j+y,getBoardSize()) ){
                            int valueSquare = (((i+x)*getBoardSize())+(j+y));
                            graph[(i*getBoardSize())+j][valueSquare] = 1;
                            graph[valueSquare][(i*getBoardSize())+j] = 1;

                        }

                    }
                }
            }
        }
        Conversion.mtx2List(graph,getGraph());
    }

    private boolean isValidMove( int x, int y , int limit ){
        return ( ( x >= 0 && x < limit) && ( y >= 0 && y < limit) );
    }

    private int warnsdorff( int u ){
        if( graph.getDegreeOf(u) != 0 ){
            int i = 0;
            while( i < graph.getDegreeOf(u) && color[graph.getAdjacentVertex(u,i).getValue()] != WHITE )++i;
            if( i < (graph.getDegreeOf(u)) ){
                Vertex v = graph.getAdjacentVertex(u,i);
                for( int j = i+1 ; j < graph.getDegreeOf(u) ; ++j ){
                    Vertex w = graph.getAdjacentVertex(u,j);
                    if( graph.getDegreeOf(w.getValue()) <= graph.getDegreeOf(v.getValue()) && color[w.getValue()] == WHITE ){
                        v = w;
                    }
                }
                return v.getValue();
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }

    private void execute( int u ){
        color[u] = BLACK;
        gameBoard[u/getBoardSize()][u-((u/getBoardSize())*getBoardSize())] = ++steps;
        int v = warnsdorff(u);
        while( v != -1 ){
            color[v] = BLACK;
            u = v;
            gameBoard[u/getBoardSize()][u-((u/getBoardSize())*getBoardSize())] = ++steps;
            v = warnsdorff(u);
        }
    }

    private void setBoardSize( int boardSize ){
        this.boardSize = boardSize;
    }

    private int getBoardSize() {
        return boardSize;
    }

    private int[][] getGameBoard() {
        return gameBoard;
    }

    private void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void execute(){
        //inicializacao
        for (int i = 0; i < graph.getOrder(); ++i) {
            color[i] = WHITE;
        }

        //Para cada vértice u arbitrário do grafo, visitar(u).
        for (int i = 0; i < graph.getOrder();) {
            if (color[i] == WHITE) {
                execute(arbitraryVertex.getValue());
            }
            arbitraryVertex = new Vertex(++i);
        }
    }

    public void printGameboard(){
        System.out.println("\nKnight's tour\n");
        for( int i = 0 ; i < getGameBoard().length ; ++i ){
            for( int j = 0 ; j < getGameBoard()[0].length ; ++j ){
                System.out.printf(" %3d", getGameBoard()[i][j] );
            }
            System.out.println();
        }
    }

}

