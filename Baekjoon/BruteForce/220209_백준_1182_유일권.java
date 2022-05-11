package beakjoon.d220209.q1182;
//https://www.acmicpc.net/problem/1182
//Solved : 22/02/09

import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static int rst = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mk_sum(S, 0, 0,0);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void mk_sum(int target, int now, int score, int time){
        if(target==score&&time>0&&now==arr.length){
            rst++;
            return;
        }
        if(now==arr.length) return;
        mk_sum(target, now+1, score+arr[now],time+1);
        mk_sum(target, now+1, score, time);
    }
}
