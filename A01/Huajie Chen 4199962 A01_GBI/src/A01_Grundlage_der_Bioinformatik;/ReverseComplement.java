package A01_Grundlage_der_Bioinformatik;

import java.io.*;

/**
 * Created by JWP on 04.22.2018
 */
public class ReverseComplement {

    public static void main(String[] args) throws IOException {

        if (args.length == 2) { // need exactly two commandline arguments: infile and outfile


            System.out.println("Constructing reverse complement DNA Seqs...");


            String infile = args[0];
            Reader r = new FileReader(infile);

            A01_Grundlage_der_Bioinformatik.FastA inputFasta = new A01_Grundlage_der_Bioinformatik.FastA();
            inputFasta.read(r);
            r.close();

            A01_Grundlage_der_Bioinformatik.FastA resultFasta = new A01_Grundlage_der_Bioinformatik.FastA();

            for (int i = 0; i < inputFasta.size(); i++) {
                resultFasta.add(inputFasta.getHeader(i) + " (Reverse Complemented)",
                        Construct_Reverse_Complement(inputFasta.getSequence(i)));
            }

            String outfile = args[1];
            Writer w = new FileWriter(outfile);
            resultFasta.write(w); // write the result
            w.close(); // finished writing, close
        } else {
            System.out.println("Please set up your arguments");
        }
    }


    public static String Construct_Reverse_Complement(String sequence) {
        String reverseComplement = "";


        for (int i = 0; i < sequence.length(); i++)
            switch (sequence.charAt(i)) {
                case 'A':
                    reverseComplement = "T" + reverseComplement;
                    break;
                case 'T':
                    reverseComplement = "A" + reverseComplement;
                    break;
                case 'G':
                    reverseComplement = "C" + reverseComplement;
                    break;
                case 'C':
                    reverseComplement = "G" + reverseComplement;
                    break;


            }
        return reverseComplement;
    }
}
