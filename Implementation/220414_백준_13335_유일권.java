//https://www.acmicpc.net/problem/13335
//Solved : 22/04/14

import java.util.*;
import java.io.*;

class Main{
    static int N, W, L, rst=0;
    static int[] trucks;
    static boolean[] used;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        trucks = new int[N];
        used = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> que = new LinkedList<>();
        int wait=0;
        int total=0;
        //int pin=0;
        while (wait<N){
            rst++;
            if(!que.isEmpty()) {
                int[] tmp = que.peek();
                if(tmp[1]>=W){
                    que.poll();
                    total-=tmp[0];
                }
            }
            if(total+trucks[wait]<=L){
                que.add(new int[]{trucks[wait], 0});
                total += trucks[wait++];
            }
            int tmp=que.size();
            for(int i=0; i<tmp; i++){
                int[] now = que.poll();
                now[1]++;
                que.add(now);
            }
        }
        rst+=W;
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

}

