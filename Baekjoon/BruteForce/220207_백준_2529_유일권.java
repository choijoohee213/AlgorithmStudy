package beakjoon.q2529;
//https://www.acmicpc.net/problem/2529
//Solved : 22/02/08

import java.util.*;
import java.io.*;

public class Main {
    static long MAX;
    static long MIN;
    static char[] sign;
    static boolean[] chk;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        MAX = -1;
        MIN = Long.MAX_VALUE;
        int K = Integer.parseInt(br.readLine());
        String line = "%0"+(K+1)+"d";
        sign = new char[K];
        chk = new boolean[10];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<K; i++){
            sign[i] = st.nextToken().charAt(0);
        }

        mk_num(K, 0, 0, 0);
        //System.out.println(line);
        sb.append(String.format(line, MAX)).append("\n").append(String.format(line, MIN));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void mk_num(int k, int depth, long score, int before){
        if(depth==0){
            for(int i=0; i<10; i++){
                chk[i] = true;
                mk_num(k, depth+1, i, i);
                chk[i] = false;
            }
        }else if(depth==k+1){
            //System.out.println(score);
            MAX = Math.max(MAX, score);
            MIN = Math.min(MIN, score);
            return;
        }else{
            boolean tmp = sign[depth - 1] == '<';
            if(tmp){
                for(int i=before+1; i<10; i++){
                    if(!chk[i]) {
                        chk[i] = true;
                        mk_num(k, depth + 1, (score * 10) + i, i);
                        chk[i] = false;
                    }
                }
            }else{
                for(int i=0; i<before; i++){
                    if(!chk[i]) {
                        chk[i] = true;
                        mk_num(k, depth + 1, (score * 10) + i, i);
                        chk[i] = false;
                    }
                }
            }
        }
    }
}
