import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.Charset;
/**
 * Created by Daniel on 02/11/16.
 */
public class TSPTest{

    //static final int NUM_GRAPHS = 5;
    static final int numTest = 3;

    public static void main(String[] args) throws IOException {

        int i = 0;
        Graph g = null;
        PCV pcv = null;
        while( i < numTest ) {

            int numVertex = 0;
            switch ( i )
            {
                case 0:
                    numVertex = 3;
                    break;
                case 1:
                    numVertex = 7;
                    break;
                case 2:
                    numVertex = 11;
                    break;
            }

            g = new CompleteGraph(numVertex);
            g.printGraph(Viewer.BASIC);

            pcv = new PCV(g, new Vertex(0));

            long _start = System.currentTimeMillis();
            pcv.execute();
            long _final =  (System.currentTimeMillis() - _start);

            System.out.println("SmallerCost: " + pcv.getLowerCost());
            pcv.showSmallerPath();
            System.out.println("Runtime: " + _final);

            ++i;
        }

    }

}