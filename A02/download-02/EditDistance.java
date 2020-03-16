import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Compute edit distance using dynamic programming
 * YOUR_NAME_HERE_PLEASE
 * GBI, Daniel Huson, 4.2018
 */
public class EditDistance {

    /**
     * computes the edit distance between two sequences using dynamic programming
     *  This method sets up and fills the dynamic programming matrix
     *
     * @param x first sequence
     * @param y second sequence
     * @return edit distance
     */
    public int align(String x, String y) {
        // PLEASE IMPLEMENT (first assignment task)
        return 0;
    }

    /**
     * perform traceback and print an optimal alignment to  the console (standard output)
     *  This method assumes that the method align has already been run and that the dynamic programming
     *  matrix has been computed and is stored in the class
     */
    public void traceBackAndShowAlignment() {
        // PLEASE IMPLEMENT (second assignment task)
    }

    /**
     * main program: reads two sequences in fastA format and computes their optimal alignment score.
     *
     * @param args commandline arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("YOUR_NAME_HERE_PLEASE");

        if (args.length != 1)
            throw new IOException("Usage: EditDistanceDP fileName");

        String fileName = args[0];
        FileReader reader = new FileReader(fileName);

        FastA fastA = new FastA();
        fastA.read(reader);
        reader.close();

        EditDistance editDistanceDP = new EditDistance();

        if (fastA.size() == 2) {
            int editDistance = editDistanceDP.align(fastA.getSequence(0), fastA.getSequence(1));

            System.out.println("Edit distance is=" + editDistance);

            System.out.println("An optimal alignment=");
            editDistanceDP.traceBackAndShowAlignment();
        }
    }
}
