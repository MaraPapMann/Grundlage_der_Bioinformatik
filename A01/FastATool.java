package assignment_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.*;

/**
 * Created by sangdelu on 2016/11/1.
 */
public class FastATool {
    private java.util.List<String> headers;
    private java.util.List<String> sequences;
    private java.util.List<String> raw;
    private int width;


    public FastATool(){
        this.headers=new ArrayList<String>();
        this.sequences = new ArrayList<String>();
        this.raw= new ArrayList<String>();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setSequences(String[] strings) {
        List<String> temp=new ArrayList<String>();
        for (String str: strings) {
            temp.add(str);
        }
        this.sequences=temp;
    }

    public void setSequences(java.util.List<String> sequences) {
        this.sequences=sequences;
    }

    public void setWidth(int width){
        this.width=width;
    }

    public List<String> getSequences() {
        return sequences;
    }


    public void read(Reader r) throws IOException {
        BufferedReader br = new BufferedReader(r);
        String rea;
        rea = br.readLine();
        while(rea != null){
            if (!Objects.equals(rea, "")) {
                raw.add(rea);
                rea = br.readLine();
            }
        }
        br.close();
    }

    public void combineMultiLineSequence(){
        this.sequences = new ArrayList<String>();
        int s=raw.size();
        String label="need header";
        String temp="";
        for(int i=0;i<s;i++) {
            if (raw.get(i).charAt(0)=='>'&& Objects.equals(label, "need header")){
                this.headers.add(raw.get(i));
                label="need seq";
            }else if(raw.get(i).charAt(0)!='>'&& Objects.equals(label, "need seq")){
                temp=temp+raw.get(i);
                if(i+1<s){
                    if(raw.get(i+1).charAt(0)=='>'){
                        label="need header";
                        this.sequences.add(temp);
                        temp="";
                    }
                }else if(i+1==s){
                    this.sequences.add(temp);
                    temp="";
                }
            }
        }
    }

    private int maxSeqLength(){
        java.util.List<String> s = this.sequences;
        int max=s.get(0).length();

        for (String value : s) {
            if (value.length() > max) {
                max = value.length();
            }
        }
        return max;
    }

    private void equalHeader(){
        int n=this.headers.size();
        java.util.List<String> temp=new ArrayList<String>();;

        for (String header : this.headers) {
            if(header.charAt(0)=='>') {
                temp.add(format("%1$-40s", header.substring(1)));
            }else{
                temp.add(format("%1$-40s", header.substring(0)));
            }
        }
        setHeaders(temp);
    }


    public String printFormat(boolean showHeaders,boolean ifCutGap) {
        equalHeader();
        if(ifCutGap){
            cutGap();
        }
        java.util.List<String> h = this.headers;
        java.util.List<String> s = this.sequences;
        int n = s.size();
        int bound=this.width;
        int time= maxSeqLength()/bound+1;
        String a= format("%1$-40s","");

        StringBuffer sBuffer = new StringBuffer("\n");

        for(int t=0;t<time;t++) {
            int start = t * bound;
            int end = bound+t * bound;
            if (maxSeqLength() < end) {
                end = maxSeqLength();
            }

            sBuffer.append( "\n");
            for (int i = 0; i < n; i++) {
                if (s.get(i).length() < end&&s.get(i).length()>start) {
                    if (showHeaders) {
                        sBuffer.append(h.get(i) + s.get(i).substring(start) + "\n");
                    }else{
                        sBuffer.append(a + s.get(i).substring(start) + "\n");
                    }
                } else if(s.get(i).length() > end) {
                    if (showHeaders) {
                    sBuffer.append(h.get(i) + s.get(i).substring(start,end) + "\n");
                    }else{
                        sBuffer.append(a + s.get(i).substring(start,end) + "\n");
                    }
                }
            }

        }
        return sBuffer.toString();
    }

    public void cutGap() {
        java.util.List<String> s = new ArrayList<String>();
        StringBuffer sb=new StringBuffer();
        for (String str:this.sequences) {
            for (int i=0;i<str.length();i++) {
                if(str.charAt(i)!='-'){
                    sb.append(str.charAt(i));
                }
            }
            s.add(sb.toString());
        }
        setSequences(s);
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
