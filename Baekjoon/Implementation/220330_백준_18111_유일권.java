//https://www.acmicpc.net/problem/18111
//Solved : 22/03/30

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int high =Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        int rstH = 0;
        int rstT = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                high = high > map[i][j] ? high : map[i][j];
                low = low < map[i][j] ? low : map[i][j];
            }
        }

        for(int height = low; height<=high; height++){
            int block = B;
            int time = 0;
            boolean flag = true;

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] > height){
                        time += (map[i][j] - height)*2;
                        block += map[i][j] - height;
                    }
                }
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] < height){
                        if(block >= height - map[i][j]) {
                            time += height - map[i][j];
                            block -= (height - map[i][j]);
                        }else flag = false;
                    }
                }
            }
            if(flag){
                if(time <= rstT){
                    rstT = time;
                    rstH = height;
                }
            }
        }
        sb.append(rstT).append(" ").append(rstH);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}