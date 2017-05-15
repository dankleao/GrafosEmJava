

//pacotes java
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 28/10/16.
 */
public class GraphIO {

    private static BufferedReader br;
    private static Random random = new Random(0);//seed 0

    public static double nextRandom( int finalValue ) {
        return 0 + ( Math.abs( random.nextInt() + 0.0 ) % finalValue );
    }

    private static void readUndirectedGraph( Graph graph ) throws IOException{
        String line = "";
        for( int i = 0 ; i < graph.getOrder() ; ++i ){
            line = br.readLine();
            int x = 0;
            int y = 0;
            while( x < i ){
                y = line.indexOf(',',y);
                ++y;
                ++x;
            }
            x = y;
            double data;
            for( int j = i ; j < graph.getOrder() - 1  ; ++j ){
                y = line.indexOf(',',y);
                data = Double.parseDouble(line.substring(x,y));
                x = ++y;
                if( data != 0 )
                    graph.insertEdge(i,j,data);
            }
            data = Double.parseDouble(line.substring(x,line.length()) );
            if( data != 0 )
                graph.insertEdge(i,graph.getOrder()-1,data);
        }
    }

    private static void readDirectedGraph( Graph graph ) throws IOException{
        String line = "";
        for( int i = 0 ; i < graph.getOrder() ; ++i ){
            line = br.readLine();
            double data;
            int x = 0;
            for( int j = 0 , y = 0 ; j < graph.getOrder() - 1  ; ++j ){
                y = line.indexOf(',',y);
                data = Double.parseDouble(line.substring(x,y));
                x = ++y;
                if( data != 0 )
                    graph.insertEdge(i,j,data);
            }
            data = Double.parseDouble(line.substring(x,line.length()) );
            if( data != 0 )
                graph.insertEdge(i,graph.getOrder()-1,data);
        }
    }



    public static Graph read() {
        Graph g = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int type = Integer.parseInt(br.readLine());
            int order = Integer.parseInt(br.readLine());

            switch( type ){
                case 0:
                    g = new UndirectedGraph(order);
                    readUndirectedGraph(g);
                    break;
                case 1:
                    g = new DirectedGraph(order);
                    readDirectedGraph(g);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            br = null;
        }
        return g;
    }
}
