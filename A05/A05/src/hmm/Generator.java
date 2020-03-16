package hmm;

import java.io.FileReader;
import java.io.Reader;

public class Generator {
    private HMM h; //这里是在这个class下把HMM给private定义成了h

    public Generator (String HmmFile) throws Exception {
        Reader fr= new FileReader(HmmFile); //再建立一个FileReader, arg是HmmFile.
        h = new hmm.HMM(fr);
    }

    public HMM getHmm() {
        return h;
    }

    public static void main(String[] args) throws Exception {
        Generator generator=new Generator("casino.hmm");
        generator.getHmm();

        String a = generator.h.generateSequence();;
        System.out.println("Successfully generated! The simulated sequence is: ");
        System.out.println(a);

    }


}
