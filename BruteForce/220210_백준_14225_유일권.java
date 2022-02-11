package beakjoon.d220210.q15658;
//https://www.acmicpc.net/problem/15658
//Solved : 22/02/10

import java.io.*;
import java.util.*;

public class Main {
    static Queue<Integer> que;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        que = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mk_num(0,0,0);

        int before=0, rst=0;
        //System.out.println(que.toString());
        while(que.size()>0){
            int tmp = que.poll();
            //System.out.println("tmp, before : "+tmp+" "+before);
            if(tmp-before==1){
                before = tmp;
                while (true){
                    if(que.size()>0){
                        if(que.peek()==tmp){
                            que.poll();
                        } else break;
                    }else break;
                }
                rst = tmp+1;
            }else {
                rst = before+1;
                break;
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void mk_num(int n, int rst, int time){
        if(n==arr.length&&time>0){
            que.add(rst);
            return;
        }
        if(n== arr.length) return;
        mk_num(n+1, rst+arr[n], time+1);
        mk_num(n+1, rst, time);
    }
}
