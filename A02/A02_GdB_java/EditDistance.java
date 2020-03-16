package A02_GdB;

import java.io.FileReader;
import java.io.IOException;

/**
 * Compute edit distance using dynamic programming
 * Anastasia Grekova, Huajie Chen
 * 27.04.2018
 */
public class EditDistance {

    /**
     * computes the edit distance between two sequences using dynamic programming
     *  This method sets up and fills the dynamic programming matrix
     *
     * @param word1 first sequence
     * @param word2 second sequence
     * @return edit distance
     */
    public int align(String word1, String word2) {
        // PLEASE IMPLEMENT (first assignment task)
        int len1 = word1.length();
        int len2 = word2.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }


    /**
     * perform traceback and print an optimal alignment to  the console (standard output)
     *  This method assumes that the method align has already been run and that the dynamic programming
     *  matrix has been computed and is stored in the class
     */

    public void traceBackAndShowAlignment(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // 码好最初的行列
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //计算矩阵
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        String stack = "";
        for(int i = 1, j= 1; i <= len1 || j <= len2;){
            char c1 = word1.charAt(len1 - i);
            char c2 = word2.charAt(len2 - j);

            if(c1 == c2){
                stack += c1;
                ++i;
                ++j;
            }else {
                int replace = dp[len1 - i][len2 - j];
                int insert = dp[len1 - i + 1][len2 - j];
                int delete = dp[len1 - i][len2 - j + 1];
                if(dp[len1 - i + 1][len2 - j + 1] - 1 == replace){
                    stack += c2;
                    ++i;
                    ++j;
                }else if(dp[len1 - i + 1][len2 - j + 1] - 1 == insert){
                    stack += "-";
                    ++j;
                }else if(dp[len1 - i + 1][len2 - j + 1] - 1 == delete){
                    stack += "";
                    ++i;
                }
            }
        }

        stack = new StringBuilder(stack).reverse().toString();

        System.out.println(stack);
    }

    /**
     * main program: reads two sequences in fastA format and computes their optimal alignment score.
     *
     * @param args commandline arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Anastasia Grekova, Huajie Chen");

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
            editDistanceDP.traceBackAndShowAlignment(fastA.getSequence(0), fastA.getSequence(1));
        }
    }
}
