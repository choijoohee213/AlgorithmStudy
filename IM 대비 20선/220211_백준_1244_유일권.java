package beakjoon.before.q1244;
//https://www.acmicpc.net/problem/1244
//22/02/04

import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int time = Integer.parseInt(br.readLine());
        for(int t=0; t<time; t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(x==1){
                for(int i=num-1; i<arr.length; i+=num){
                    change(i);
                }
            }else{
                int start = num-1, fin = num-1;
                change(start);
                while(true){
                    if(start-1>=0&&fin+1<arr.length){
                        if(arr[start-1]==arr[fin+1]){
                            start--;
                            fin++;
                            change(start);
                            change(fin);
                        }else break;
                    }else break;
                }
                //System.out.println(start+" "+fin);
            }
            //System.out.println(Arrays.toString(arr));
        }
        for(int i=0; i< arr.length; i++){
            sb.append(arr[i]).append(" ");
            if(i%20==19){
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void change(int n){
        if(arr[n]==1){
            arr[n] = 0;
        }else{
            arr[n] = 1;
        }
    }
}