package beak1931;
//https://www.acmicpc.net/problem/1931

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int rst=1;
        beak1931.Classes[] lists = new beak1931.Classes[n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lists[i] = new beak1931.Classes(x, y);
        }
        Arrays.sort(lists);/*
        for(int i =0; i<n; i++){
            System.out.println(lists[i].start+" "+lists[i].finish);
        }*/
        int now_time = lists[0].finish;
        for(int i=1; i<n; i++){
            if(now_time<=lists[i].start){
                rst++;
                now_time=lists[i].finish;
            }
        }
        System.out.println(rst);
    }
}


class Classes implements Comparable<beak1931.Classes>{
    int start;
    int finish;
    Classes(int x, int y){
        start=x;
        finish=y;
    }

    @Override
    public int compareTo(beak1931.Classes o) {
        if(this.finish!=o.finish) {
            return this.finish - o.finish;
        }else {
            return this.start - o.start;
        }
    }
}

/*
11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14

 */