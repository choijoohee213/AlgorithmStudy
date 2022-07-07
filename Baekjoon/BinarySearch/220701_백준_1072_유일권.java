//https://www.acmicpc.net/problem/1072
//Solved : 22/07/04

import java.util.*;
import java.io.*;

class Main1072{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        int Z = (int)((Y* 100)/X);

        if(Z<99){
            long min = 1;
            long max = X;
            long mid;
            int per;
            while(min <= max){
                mid = (max+min)/2;
                per = (int)(((Y+mid)*100)/(X+mid));

                if(per <= Z){
                    min = mid+1;
                }else{
                    max = mid-1;
                }
            }
            bw.write(Long.toString(max+1));
        }else{
            bw.write(Integer.toString(-1));
        }

        bw.close();
        br.close();
    }
}