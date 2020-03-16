package A08;

public class Scratch {
    public static void main(String[] args){
        PopGenSimulator pgs = new PopGenSimulator();
        String[] a = pgs.setupExtantGeneration(4,8);
        for (int i=0; i<a.length; i++)
            System.out.print(a[i]+" ");

        String sdf = "123";

        for (int i=0; i<100; i++){
            for (int j=0; j<10; j++){
                System.out.println(j);
                if (j==5){
                    break;
                }
            }
        }
    }
}
