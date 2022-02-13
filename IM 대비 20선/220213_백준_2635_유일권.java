package beakjoon.d220213.q2635;
//https://www.acmicpc.net/problem/
//Solved : 22/02/13

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int time = 0, num=N-1;
        for(int i=N/2; i<=N; i++){
            int next = i;
            int before = N;
            int tmp;
            int length = 1;
            while(next>=0){
                length++;
                tmp = next;
                next = before - next;
                before = tmp;
            }
            if(length>time){
                time = length;
                num = i;
            }
        }
        sb.append(time).append("\n");
        sb.append(N).append(" ");
        int before = N;
        int tmp;
        while(num>=0){
            sb.append(num).append(" ");
            tmp = num;
            num = before - num;
            before = tmp;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
