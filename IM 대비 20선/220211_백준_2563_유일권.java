package beakjoon.d220210.q2563;
//https://www.acmicpc.net/problem/2563
//Solved : 22/02/10

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[][] paper = new boolean[100][100];
        int a = Integer.parseInt(br.readLine());
        int rst = 0;

        StringTokenizer st;
        for(int i=0; i<a; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for(int j=r; j<r+10; j++){
                for(int k=c; k<c+10; k++){
                    if(!paper[j][k]){
                        paper[j][k] = true;
                        rst++;
                    }
                }
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}
