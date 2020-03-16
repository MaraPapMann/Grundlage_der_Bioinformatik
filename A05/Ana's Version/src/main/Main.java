package main;

import java.io.FileNotFoundException;



public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Generator hmm = null;
        try {
            hmm = new Generator(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        hmm.printHMM();

        //System.out.print(hmm.chooseRandomWeighted(hmm.getMODEL().getStateNames(), 2));
        hmm.generateSequence();
        System.out.println(hmm.getSequenceOfStates());
        System.out.println(hmm.getSequence());
    }
}
