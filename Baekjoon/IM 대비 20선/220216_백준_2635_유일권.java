package beakjoon.d220213.q2635;
//https://www.acmicpc.net/problem/2635
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
        for(int i=N/2; i<=N; i++){                          //N/2이하는 무조건 3이므로 N/2부터 확인
            int next = i;
            int before = N;
            int tmp;
            int length = 1;
            while(next>=0){                                 //다음수가 음수가 아닐때까지 값을 구해서 길이 확인
                length++;
                tmp = next;
                next = before - next;
                before = tmp;
            }
            if(length>time){                                //길이가 더 길다면, 길이와 그때의 첫번째수를 저장
                time = length;
                num = i;
            }
        }
        sb.append(time).append("\n");
        sb.append(N).append(" ");
        int before = N;
        int tmp;
        while(num>=0){                                      //첫번째수를 기준으로 sb만들기.
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
