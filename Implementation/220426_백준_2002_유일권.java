//https://www.acmicpc.net/problem/2002
//Solved : 22/04/26

import java.util.*;
import java.io.*;

class Main_2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++)  map.put(br.readLine(), i);

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = map.get(br.readLine());

        int rst = 0;
        for(int i = 0; i< N-1; i++){
            for(int j = i+1; j< N; j++){
                if(arr[i] > arr[j]){
                    rst++;
                    break;
                }
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}