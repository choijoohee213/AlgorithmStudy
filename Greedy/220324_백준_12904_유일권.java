//https://www.acmicpc.net/problem/12904
//Solved : 22/03/25

import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb;
        String S = br.readLine();
        String T = br.readLine();
        int size = T.length()-1;
        while(size>S.length()-1){
            char tmp = T.charAt(size);
            T = T.substring(0,size);
            if(tmp=='B'){
                sb = new StringBuffer(T);
                T = sb.reverse().toString();
            }
            size--;
        }

        //System.out.println(S+"\n"+T);
        if(S.equals(T)) bw.write("1");
        else bw.write("0");

        bw.close();
        br.close();
    }
}