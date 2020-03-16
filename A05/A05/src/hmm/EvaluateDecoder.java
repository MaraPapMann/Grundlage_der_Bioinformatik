package hmm;

import java.io.FileReader;
import java.io.Reader;

public class EvaluateDecoder {private HMM h;

    public EvaluateDecoder (String HmmFile) throws Exception {
        Reader fr= new FileReader(HmmFile);
        h = new hmm.HMM(fr);
    }

    public HMM getHmm() {
        return h;
    }

    public static void main(String[] args) throws Exception {

        EvaluateDecoder evadecoder=new EvaluateDecoder("casino.hmm");
        evadecoder.getHmm();

        String seq[]=new String[100];
        String path[]=new String[100];
        String decod[]=new String[100];

        int right=0;
        int wrong=0;

        for(int i=0;i<100;i++){

            seq[i]=evadecoder.h.generateSequence();
            path[i]=evadecoder.h.getGeneratingPath();
            decod[i]=evadecoder.h.runViterbi(seq[i]);

            int L=seq[i].length();
            for(int j=0;j<L;j++){
                if(path[i].charAt(j)==decod[i].charAt(j)){
                    right++;
                }else{
                    wrong++;
                }
            }
        }

        System.out.println("Correct decoded state are " + right + "times.");
        System.out.println("Wrong decoded state are " + wrong + "times.");
    }
}
