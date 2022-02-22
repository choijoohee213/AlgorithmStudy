package beakjoon.d220219.q9663;
//https://www.acmicpc.net/problem/9663
//Solved : 22/02/19

import java.io.*;

public class Main {
    static int rst = 0;
    static int[] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        map = new int[N];               //퀸은 1줄에 하나이므로 1차원 배열

        nQueen(N, 0);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void nQueen(int N, int idx){         //1줄마다 확인
        if(N==idx){             //끝까지 도착하면 결과+1
            rst++;
            return;
        }
        loop:for(int i=0; i<N; i++){                //N번째 줄에 모든 칸 확인
            for(int j=0; j<idx; j++){               //만약 위에있는 퀸때문에 놓을수 없다면 옆자리로 이동
                if(i==map[j]-(idx-j)||i==map[j]||i==map[j]+(idx-j)) continue loop;
            }
            map[idx] = i;
            nQueen(N, idx+1);
            map[idx] = 0;
        }
    }
}
