//https://www.acmicpc.net/problem/1285
//Solved : 22/03/17

import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str;
        int N = Integer.parseInt(br.readLine());
        int dT = 0;
        boolean[][] map = new boolean[N][N];

        for(int i=0; i<N; i++){
            str = br.readLine();
            for(int j=0; j<N; j++) map[i][j] = str.charAt(j)=='H';
        }

        int ans = N*N;
        for(int bit=0; bit<(1<<N); bit++){          //3일경우 000 ~ 111 까지 비트마스킹으로 뒤집을 행 결정
            int sum=0;                              //비트마스킹 아에 생각 못해서 완전 접근 못함...
            for(int i=0; i<N; i++){                 //열 마다
                int t=0;
                for(int j=0; j<N; j++){             //행마다
                    boolean chk = map[i][j];        //뒤집어야 할수도 있으므로 체크하기 위하여 변수 생성
                    if((bit&(1<<j))!=0) chk = !chk; //뒤집어야하는 행이면 뒤집어서 체크
                    if(!chk) t++;
                }
                sum += t < N-t ? t : N-t;
            }
            ans = ans < sum ? ans : sum;
        }

        bw.write(Integer.toString(ans));
        bw.close();
        br.close();
    }
}