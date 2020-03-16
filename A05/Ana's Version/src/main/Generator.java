package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * This class reads the HMM description file as input and
 generates a sequence of state values (in this case die throws) as output,
 e.g.
 6665342151662542313142664536251425366352....
 TO DO:
 Your class should store the sequences
 of states that was actually used to to generate the sequence of symbols and make it available by a method
 called getStates(), as this will be needed in Problem 4 below.
 */
public class Generator extends HMM {
    private HMM MODEL ;

    public HMM getMODEL() {
        return MODEL;
    }

    private String sequenceOfStates;

    public String getSequenceOfStates() {
        return sequenceOfStates;
    }

    private String sequence;
    public Generator(String s) throws Exception {
        this.MODEL = extractHMM(s);
    }


    public String getSequence() {
        return sequence;
    }

    public void generateSequence() throws Exception {
        char currentState = '0';
        char NULL_STATE = '0';

        //compute first step from 0 to anything
        int nextIndexofState = chooseRandomWeighted(MODEL.getStateNames(), 0);
        sequenceOfStates = MODEL.getStateName(nextIndexofState) + "";
        sequence = "_";
        currentState = MODEL.getStateName(nextIndexofState);

        //choose a new state using the transition probabilities
        while (currentState != NULL_STATE) {
            //find the next state
            //choose a new state using the transition probabilities
            nextIndexofState = chooseRandomWeighted(MODEL.getStateNames(), nextIndexofState);
            currentState = MODEL.getStateName(nextIndexofState);
            sequence = sequence + chooseRandomWeightedEmit(MODEL.getSymbolNames(),nextIndexofState );
            sequenceOfStates = currentState + sequenceOfStates;

        }
    }


    public int chooseRandomWeighted(char[] items, int key) throws Exception {

// Compute the total weight of all items together
        double totalWeight = 0.0d;
        for (int i = 0; i< items.length; i++)
        {
            totalWeight += MODEL.getTransMatrix(key,i);
        }
// Now choose a random item
        int randomIndex = -1;
        double random = Math.random() * totalWeight;
        for (int i = 0; i < items.length; ++i)
        {
            random -= MODEL.getTransMatrix(key,i);
            if (random <= 0.0d)
            {
                randomIndex = i;
                break;
            }
        }
        //char myRandomItem = items[randomIndex];
        return randomIndex;
    }


    public char chooseRandomWeightedEmit(char[] items, int key) throws Exception {

// Compute the total weight of all items together
        double totalWeight = 0.0d;
        for (int i = 0; i< items.length; i++)
        {
            totalWeight += MODEL.getEmissionProb(key,i);
        }
// Now choose a random item
        int randomIndex = -1;
        double random = Math.random() * totalWeight;
        for (int i = 0; i < items.length; ++i)
        {
            random -= MODEL.getEmissionProb(key,i);
            if (random <= 0.0d)
            {
                randomIndex = i;
                break;
            }
        }
        char myRandomItem = items[randomIndex];
        return myRandomItem;
    }




    public static void main(String[] args) throws FileNotFoundException {

    }
    //import existing HMM from the source
    public static HMM extractHMM(String s) throws Exception {
        FileReader r;
        r = new FileReader(s);
            return readHMM(r);

    }

    public void printHMM(){
        try {
            System.out.println(MODEL.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static HMM readHMM(Reader r) throws Exception {
        HMM model = new HMM (r);
        return model;
    }
}
