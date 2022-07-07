//https://www.acmicpc.net/problem/2343
//Solved : 22/07/07

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] bluray = new int[N];
        long min = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            bluray[i] = Integer.parseInt(st.nextToken());
            min = min > bluray[i] ? min : bluray[i];
        }

        long max = Integer.MAX_VALUE;
        long mid;

        while (min <= max){
            mid = (max + min) / 2;
            if(isValid(mid, N, M, bluray)){
                min = mid+1;
            }else{
                max = mid-1;
            }
        }

        bw.write(Long.toString(min));
        bw.close();
        br.close();
    }
//    static boolean isValid(long mid,int N, int M, int[] bluray, int idx, int CD, int time){
//        if(idx ==  N && CD == M){
//            return true;
//        }else if(idx ==  N || CD == M){
//            return false;
//        }
//        int sum = time + bluray[idx];
//
//        if(sum<mid) {
//            if(isValid(mid, N, M, bluray, idx+1, CD+1, 0)) return true;
//            if(isValid(mid, N, M, bluray, idx+1, CD, sum)) return true;
//        }else{
//            if(isValid(mid, N, M, bluray, idx+1, CD+1, bluray[idx])) return true;
//        }
//        return false;
//    }
    static boolean isValid(long mid,int N, int M, int[] bluray) {
        int sum = 0;
        int cnt = 1;
        for(int i=0; i<N; i++){
            sum += bluray[i];
            if(sum > mid){
                sum = bluray[i];
                cnt++;
            }
        }
        return (cnt>M);
    }

}