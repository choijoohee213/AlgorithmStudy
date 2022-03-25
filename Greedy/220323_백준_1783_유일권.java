//https://www.acmicpc.net/problem/1783
//Solved : 22/03/23

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long rst;
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        if(N==1) rst = 0;
        else if(N==2) rst = (M-1)/2 < 3 ? (M-1)/2 : 3;
        else if(M<7) rst = (M-1) < 3 ? (M-1) : 3;
        else rst = 2 + (M-5);
        rst++;
        bw.write(Long.toString(rst));
        bw.close();
        br.close();
    }
}