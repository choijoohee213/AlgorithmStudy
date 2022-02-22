package beakjoon.d220222.q16198;
//https://www.acmicpc.net/problem/16198
//Solved : 22/02/22

import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> ball;
    //static Stack<int[]> used;     //굳이
    static int rst = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        ball = new ArrayList<>(N);          //무게 담을 리스트
        //used = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            ball.add(Integer.parseInt(st.nextToken())); //입력
        }

        dfs(N, 0);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void dfs(int N, int weight){
        if(N==2){
            rst = rst > weight ? rst : weight;  //2개 남으면 최대값 비교후 저장
            return;
        }
        for(int i=1; i<N-1; i++){
            //used.push(new int[]{i, ball.get(i)});
            int removed = ball.get(i);           //삭제될 무게
            int now = ball.get(i-1) * ball.get(i + 1);
            ball.remove(i);                     //값 삭제
            dfs(N-1, weight+now);
            ball.add(i,removed);                //다시 추가
        }
    }
}
