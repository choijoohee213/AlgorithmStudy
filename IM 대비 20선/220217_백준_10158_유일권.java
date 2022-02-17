package beakjoon.d220217.q10158;
//https://www.acmicpc.net/problem/10158
//Solved : 22/02/17
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int T = Integer.parseInt(br.readLine());

        //이동 횟수 줄이는 조건
        if(W==H) T=T%(W+H);                     //양변의 길이가 같으면 H*2회이동하고 다시 원점
        else if(W%2==H%2&&W%2==0) T=T%(W*H);    //양변이 짝수면 W*H회 이동하고 원점
        else T=T%(W*H*2);                       //한변이라도 홀수이면 W*H*2회 이동하고 원점

        int dx = 1, dy = 1, dist = 1;
        while(true){
            if(X+dx>W) dx = -1;                 //X방향 설정
            else if(X+dx<0) dx = 1;

            if(Y+dy>H) dy=-1;                   //Y방향 설정
            else if(Y+dy<0) dy=1;

            if(dx==1) dist = W - X;                     //X방향으로 갈수 있는 최대치
            else dist = X;
            if(dy==1) dist = dist < H-Y ? dist : H-Y;   //Y방향으로 갈수 있는 최대치와 비교해서
            else dist = dist < Y ? dist : Y;            //둘중 더 작은 수가 이동 가능한 최대값

            if(T<=dist){                                //남은 길이가 최대 길이보다 작은경우
                X += dx*T;                              //남은 길이만큼만 이동후 루프 탈출
                Y += dy*T;
                break;
            }else{                                      //최대 길이만큼 이동하고 남은길이에서 뺌
                X += dx*dist;
                Y += dy*dist;
                T -= dist;
            }
        }

        sb.append(X).append(" ").append(Y);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
