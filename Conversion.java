

/**
 * Classe Conversion
 * Created by Daniel on 03/11/16.
 */
public class Conversion {

    public static final int UNDIRECTED = 0;
    public static final int DIRECTED   = 1;

    public static Graph mtx2List( int[][] mtx, int type ){
        if( mtx != null) {
            Graph g = null;
            switch (type){
                case 0:
                    g = new UndirectedGraph(mtx.length);
                    for( int i = 0 ; i < mtx.length - 1 ; ++i ){
                        for( int j = i + 1 ; j < mtx[i].length ; ++j ){
                            if( mtx[i][j] != 0 )
                                g.insertEdge(i,j,mtx[i][j]);
                        }
                    }
                    break;
                case 1:
                    g = new DirectedGraph(mtx.length);
                    for(int i = 0 ; i < mtx.length ; ++i ){
                        for( int j = 0 ; j < mtx[i].length ; ++j ){
                            if( mtx[i][j] != 0 )
                                g.insertEdge(i,j,mtx[i][j]);
                        }
                    }
            }
            return g;
        }
        else {
            return null;
        }
    }

    public static void mtx2List( int[][] mtx, Graph g ){
        if( mtx != null ){
            if( g instanceof UndirectedGraph ){
                for( int i = 0 ; i < mtx.length - 1 ; ++i ){
                    for( int j = i + 1 ; j < mtx[i].length ; ++j ){
                        if( mtx[i][j] != 0 )
                            g.insertEdge(i,j,mtx[i][j]);
                    }
                }
            }
            else if( g instanceof DirectedGraph ) {
                for(int i = 0 ; i < mtx.length ; ++i ){
                    for( int j = 0 ; j < mtx[i].length ; ++j ){
                        if( mtx[i][j] != 0 )
                            g.insertEdge(i,j,mtx[i][j]);
                    }
                }
            }
        }
    }

}
