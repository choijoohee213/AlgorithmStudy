package beakjoon.before.q2491;
//https://www.acmicpc.net/problem/2491
//Solved : 22/02/07

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int rst = 2;
        int line = 0;
        int updown = 0;         //1업 -1다운
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        arr[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i]>=arr[i-1]) {

            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
