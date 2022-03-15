package beak11399;
//https://www.acmicpc.net/problem/11399

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        //사람 수
        int[] times = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);
        int rst = 0;
        int before = 0;
        for(int i=0; i<n; i++){
            rst = rst+before+times[i];
            before = before+times[i];
            //System.out.println(rst+" "+before);
        }
        System.out.println(rst);
    }
}
