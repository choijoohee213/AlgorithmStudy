package beakjoon.before.q2309;
//https://www.acmicpc.net/problem/2309
//Solved : 22/02/06

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] small = new int[9];
        int sum =0;

        for(int i=0; i<9; i++){
            small[i] = Integer.parseInt(br.readLine());
            sum += small[i];
        }
        Arrays.sort(small);
        loop:for(int i=0; i<9; i++){
            for(int j=i+1; j<9; j++){
                if(sum - small[i] - small[j] == 100){
                    for(int k=0; k<9; k++){
                        if(k==i||k==j){
                            continue;
                        }
                        sb.append(small[k]).append("\n");
                    }
                    break loop;
                }
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
