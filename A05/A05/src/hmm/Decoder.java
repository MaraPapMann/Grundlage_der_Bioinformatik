package hmm;

import java.io.FileReader;
import java.io.Reader;

public class Decoder {
    private HMM h;

    public Decoder (String HmmFile) throws Exception {

        Reader fr= new FileReader(HmmFile);
        h = new hmm.HMM(fr);
    }

    public HMM getHmm() {
        return h;
    }

    public static void main(String[] args) throws Exception {

        Decoder decoder=new Decoder("casino.hmm");
        decoder.getHmm();
        //here input the sequence
        String a=decoder.h.runViterbi("6666231542134512433666666666");
        System.out.println("Success decoding! The result is: " +a );

    }
}
