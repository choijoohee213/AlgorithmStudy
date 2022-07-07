//https://www.acmicpc.net/problem/
//Solved :

import java.util.*;
import java.io.*;

class Main2805{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M, min = 0, max = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] trees = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            max = max > trees[i] ? max : trees[i];
        }

        while (min <= max){
            int mid = (max+min)/2;
            long length = 0;
            for (int i : trees) {
                if (i > mid) {
                    length += (i-mid);
                }
            }

            if(length >= M){
                min = mid+1;
            }else{
                max = mid-1;
            }
        }

        bw.write(Integer.toString(max));
        bw.close();
        br.close();
    }
}