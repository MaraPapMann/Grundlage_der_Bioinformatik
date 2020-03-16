package A02_GdB;

import java.io.FileReader;
import java.io.IOException;

public class Task3 {
    public static void main(String[] args) throws IOException{
        if (args.length != 2)
            throw new IOException("Usage: EditDistanceDP fileName");

        String fileName1 = args[0];
        FileReader reader1 = new FileReader(fileName1);

        FastA fastA = new FastA();
        fastA.read(reader1);
        reader1.close();

        EditDistance editDistanceDP = new EditDistance();

        if (fastA.size() == 2) {
            int editDistance = editDistanceDP.align(fastA.getSequence(0), fastA.getSequence(1));

            System.out.println("Edit distance between 'NG_033933.1' and 'NC_000075.6' is=" + editDistance);
        }

        String fileName2 = args[1];
        FileReader reader2 = new FileReader(fileName2);

        FastA fastA2 = new FastA();
        fastA2.read(reader2);
        reader2.close();

        EditDistance editDistanceDP2 = new EditDistance();

        if (fastA2.size() == 2) {
            int editDistance = editDistanceDP2.align(fastA2.getSequence(0), fastA2.getSequence(1));

            System.out.println("Edit distance between 'ACQ41831.1' and 'Q9EQU3.3' is=" + editDistance);
        }
    }
}
