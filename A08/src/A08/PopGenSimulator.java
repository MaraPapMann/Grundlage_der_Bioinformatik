package A08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Wright-Fischer & Kingman coalescent-based pop gen simulator
 * Grundlagen der Bioinformatik, University of Tuebingen, 2018, Prof. Daniel Huson
 * YOUR_NAME_HERE_PLEASE
 */
public class PopGenSimulator {
    public static final String authors = "Huajie Chen & Anastasia Grekova";
    Random rand = new Random();


    /**
     * setup the extant (present-day) generation (generation 0). Name the 2*N=n2 individuals as A01, A02, A03, A04...
     * We will assume that the first k individuals in the present-day generation are the ones to be tracked backward through time
     *
     * @param k  the number of individuals in the "sample" to be tracked.
     * @param n2 the total number of individuals in a generation, 2*N
     * @return
     */
    public String[] setupExtantGeneration(int k, int n2) {
        String[] extantGeneration = new String[n2];
        for (int i=0; i<n2; i++){
            if (0 <= i && i <= 8){
                extantGeneration[i] = "A0"+Integer.toString(i+1);
            }
            else {
                extantGeneration[i] = "A"+Integer.toString(i+1);
            }
        }
        return extantGeneration;
    }

    /**
     * given an array of strings, each string representing one individual,
     * randomly generate previous generation. In previous generation, use lexicographically first strings of all
     * children as label of parent, or ---, if parent has no child
     *
     * @param currentGeneration
     * @return previous generation
     */
    public String[] simulatePreviousGeneration(String[] currentGeneration) {
        String[] previousGeneration = new String[currentGeneration.length];

        // Clear the array
        for (int i=0; i<previousGeneration.length; i++){
            previousGeneration[i] = "---";
        }

        for (int i=0; i<previousGeneration.length; i++){
            int index = 0;
            index = rand.nextInt(currentGeneration.length); // Select a parent randomly

            if (previousGeneration[index] == "---")
                previousGeneration[index] = currentGeneration[i];
            else {
                int j = 0;
                while (j < previousGeneration[index].length()){ // lexicographically comparison
                    if (previousGeneration[index].charAt(j) == currentGeneration[i].charAt(j))
                        j++;
                    else {
                        if (Character.getNumericValue(previousGeneration[index].charAt(j))
                                >
                                Character.getNumericValue(currentGeneration[i].charAt(j))){
                            previousGeneration[index] = currentGeneration[i];
                            break;
                        }
                        else break;
                    }
                }
            }
        }

        return previousGeneration;
    }



    /**
     * how many different individuals are ancestors of the k the individuals that we are tracking?
     *
     * @param currentGeneration
     * @param k
     * @return number of ancestors of the k individuals
     */
    public int determineNumberOfAncestorsOfSample(String[] currentGeneration, int k, String[] kObjects) {
        int counter = 0;
        for (int i=0; i<currentGeneration.length; i++){
            for (int j=0; j<kObjects.length; j++){
                if (kObjects[j] == currentGeneration[i]){
                    counter++;
                }
            }
        }

        if (counter >= k)
            return k;
        else
            return (counter);
    }


    public static void main(String[] args) throws IOException {
        System.out.println("A08.PopGenSimulator by " + authors);

        System.out.print("Enter population size (2*N): ");
        System.out.flush();
        final int n2 = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
        System.out.print("Enter sample size (k): ");
        System.out.flush();
        final int k = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());

        final PopGenSimulator popGenSimulator = new PopGenSimulator();

        // Set up array for choosing object to be tracked randomly.
        int[] randomlyChosenIndex = new int[k];
        // Initialize the array
        for (int i=0; i<randomlyChosenIndex.length; i++){
            randomlyChosenIndex[i] = -1;
        }

        for (int i=0; i<randomlyChosenIndex.length; i++){
            int j=0;
            int randNum = 0;
            while (j<randomlyChosenIndex.length){// If randNum = any number in the array then random the number again.
                if (j == 0)
                    randNum = popGenSimulator.rand.nextInt(n2);
                if (randNum != randomlyChosenIndex[j]){
                    j++;
                }else {
                    j = 0;
                }
            }
            randomlyChosenIndex[i] = randNum;
        }




                // PLEASE IMPLEMENT the following using your implementations of the above methods:

        // 1 setup the extant generation
        String[] extantGeneration = popGenSimulator.setupExtantGeneration(k, n2);

        // Select the observation objects
        String[] kObjects = new String[k];
        for (int i=0; i<kObjects.length; i++){
            int index = randomlyChosenIndex[i];
            kObjects[i] = extantGeneration[index];
        }

        // 2 until we have found the MRCA of the first k individuals, simulate the previous generation and produce line of output
        int round = 0;
        int tempK = k;
        while (tempK > 1){
            System.out.print(round+" ");
            for (int i=0; i<extantGeneration.length; i++){
                System.out.print(extantGeneration[i]+" ");
            }
            tempK = popGenSimulator.determineNumberOfAncestorsOfSample(extantGeneration, tempK, kObjects);
            System.out.println(tempK);
            extantGeneration = popGenSimulator.simulatePreviousGeneration(extantGeneration);
            round++;
        }
        // Output should  consist of the generation, the population and the number of lineages left for the sample of size k, example:

        /*
            input: k=4, n2=8:

                gen.         population            lineages
                 0 A01 A02 A03 A04 A05 A06 A07 A08 4
                -1 A04 A01 A03 --- A01 A08 A04 --- 3
                -2 A04 --- A03 A01 --- A08 A08 --- 3
                -3 A01 --- A04 --- --- A01 A08 --- 2
                -4 --- A04 --- A08 --- --- --- --- 1
                */
    }
}

