package beakjoon.d220212.q2527;
//https://www.acmicpc.net/problem/2527
//Solved : 22/02/12

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] sq1;
        int[] sq2;

        for(int tc=1; tc<=4; tc++){
            sq1 = new int[4];
            sq2 = new int[4];
            st = new StringTokenizer(br.readLine());
            for(int i =0; i<4; i++){
                sq1[i] = Integer.parseInt(st.nextToken());
            }
            for(int i =0; i<4; i++){
                sq2[i] = Integer.parseInt(st.nextToken());
            }
            if(sq2[2]<sq1[0]||sq2[1]>sq1[3]||sq1[2]<sq2[0]||sq2[3]<sq1[1]){ //안만나는것
                sb.append("d\n");
            }else{
                if(sq2[2]==sq1[0]||sq1[2]==sq2[0]){                         //
                    if(sq2[3] == sq1[1] || sq2[1] == sq1[3]){
                        sb.append("c\n");
                    }else{
                        sb.append("b\n");
                    }
                }else if(sq2[3] == sq1[1] || sq2[1] == sq1[3]){
                    sb.append("b\n");
                }else{
                    sb.append("a\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
