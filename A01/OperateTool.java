package assignment_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sangdelu on 2016/11/7.
 */
public class OperateTool {
    private java.util.List<String> headers;
    private java.util.List<String> sequences;

    public OperateTool(List<String> headers, List<String> sequences) {
        this.headers = headers;
        this.sequences = sequences;
    }

    private List<String> getHeaders() {
        return this.headers;
    }

    public  List<String>  reverseAll() {
        java.util.List<String> res = new ArrayList<String>();
        List<String> seq = getSequences();
        for (int i = 0; i < this.sequences.size(); i++) {
            res.add(reverseSingle(seq.get(i)));
        }
        return res;
    }

    private String reverseSingle(String s){
        StringBuffer sb=new StringBuffer();
        for (int i = s.length()-1; i >=0 ; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public List<String> complementaryAll(){
        java.util.List<String> res = new ArrayList<String>();
        List<String> seq = getSequences();
        for (int i = 0; i < this.sequences.size(); i++) {
            res.add(complementarySingle(seq.get(i)));
        }
        return res;
    }

    private String complementarySingle(String s){
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <s.length() ; i++) {
            char temp=s.charAt(i);
            if(temp=='A'){
                sb.append('U');
            }else if(temp=='U'){
                sb.append('A');
            }else if(temp=='C'){
                sb.append('G');
            }else if(temp=='G'){
                sb.append('C');
            }else if(temp=='-'){
                sb.append('-');
            }else{
                return "invalid character!";
            }
        }
        return sb.toString();
    }

    public String join(String[] strings){
        StringBuffer sb=new StringBuffer();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    private List<String> getSequences(){
        return this.sequences;
    }

    public int getSingleLen(String SingleSeq){
        int SL= SingleSeq.length();
        int res=0;
        for(int i=0;i<SL;i++){
            if(SingleSeq.charAt(i)!='-'){
                res++;
            }
        }
        return res;
    }

    public int getLongest(){
        List<String> seq=getSequences();
        int max;
        max=getSingleLen(seq.get(0));

        for(int i=1;i<seq.size();i++){
            if(getSingleLen(seq.get(i))>max){
                max=getSingleLen(seq.get(i));
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        FastATool fastATool=new FastATool();
        fastATool.read(new FileReader("data01.fna"));
        fastATool.combineMultiLineSequence();
        OperateTool ot= new OperateTool(fastATool.getHeaders(),fastATool.getSequences());
        List<String> a=ot.reverseAll();
        for (int i=0;i< a.size(); i++){
          System.out.println(a.get(i));
            System.out.println(ot.sequences.get(i));
        }


    }
}


