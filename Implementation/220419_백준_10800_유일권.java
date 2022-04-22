//https://www.acmicpc.net/problem/10800
//Solved : 22/04/19

import java.util.*;
import java.io.*;

class Main_10800{
    static class Ball{
        int color, size, idx;

        public Ball(int color, int size, int idx) {
            this.color = color;
            this.size = size;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Ball> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),i));
        }
        Collections.sort(list, (o1, o2) -> o1.size - o2.size);

        int[] rst = new int[N];
        int[] color = new int[N+1];
        int idx = 0;
        int sum =0;         //모든 총합
        for(int i=0; i<N; i++){
            Ball now = list.get(i);
            while(list.get(idx).size < now.size) {
                sum += list.get(idx).size;
                color[list.get(idx).color] += list.get(idx).size;
                idx++;
            }
            rst[now.idx] = sum - color[now.color];
        }

        for(int i = 0; i < N; i++) {
            sb.append(rst[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}