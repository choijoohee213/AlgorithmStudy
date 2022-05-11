//https://www.acmicpc.net/problem/12886
//Solved : 22/03/04

import java.util.*;
import java.io.*;

class Main{
    static class Stone{
        int a,b,c;
        public Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public boolean isAllSame(){
            return a==b&&b==c;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        if((A+B+C)%3!=0) bw.write(Integer.toString(0));
        else bw.write(Integer.toString(BFS(A,B,C)));

        bw.close();
        br.close();
    }
    static int BFS(int A, int B, int C){
        Queue<Stone> q = new ArrayDeque<>();
        Stone s = new Stone(A, B, C);
        q.add(s);
        boolean[][] visited = new boolean[1501][1501];
        //원래 3차원 배열로 했으나 메모리 초과 -> 셋을 사용하였으나 시간 초과... 결국 구글링..
        //배열로 하면 2차원 배열로하고, 셋을 사용할꺼면 스트링으로 만들어서 사용
        while(!q.isEmpty()){
            s = q.poll();
            if(s.isAllSame()) return 1;
            int a = s.a, b = s.b, c = s.c;

            if (a != b) {
                int na = a > b ? a - b : a * 2;
                int nb = a > b ? b * 2 : b - a;

                if (!visited[na][nb]) {
                    q.add(new Stone(na, nb, c));
                    visited[na][nb] = true;
                }
            }

            if (a != c) {
                int na = a > c ? a - c : a * 2;
                int nc = a > c ? c * 2 : c - a;

                if (!visited[na][nc]) {
                    q.add(new Stone(na, b, nc));
                    visited[na][nc] = true;
                }
            }

            if (b != c) {
                int nb = b > c ? b - c : b * 2;
                int nc = b > c ? c * 2 : c - b;

                if (!visited[nb][nc]) {
                    q.add(new Stone(a, nb, nc));
                    visited[nb][nc] = true;
                }
            }
        }
        return 0;
    }
}