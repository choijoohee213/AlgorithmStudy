//https://www.acmicpc.net/problem/1654
//Solved : 22/06/30

import java.util.*;
import java.io.*;

class Main1654{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        long min = 1;
        long max = 0;

        for(int i=0; i<K; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = max > arr[i] ? max : arr[i];
        }

        long mid;

        while (min <= max){
            long count = 0;
            mid = (max + min) / 2;
            for(int i=0; i<K; i++){
                count += (arr[i]/mid);
            }
            if(count >= N){
                min = mid+1;
            }else{
                max = mid-1;
            }
        }

        bw.write(Long.toString(max));
        bw.close();
        br.close();
    }
}
/*
4 1
100
100
2
1

 */