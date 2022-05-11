import java.io.*;
import java.util.*;
public class Main_bj_14502_연구소 {
    static class Virus {
        int r, c;
        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, arr[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1}, max=Integer.MIN_VALUE;
    static boolean[][] V; // 벽1, 바이러스2 를 방문처리함
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M]; V = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]!=0) V[i][j]=true;
            }
        }
        dfs(0);
        System.out.println(max);
        br.close();
    }
    static void dfs(int depth) { // 벽 채우기
        if(depth==3) {spread(); return;}
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(V[i][j]) continue;
                V[i][j]=true;
                dfs(depth+1);
                V[i][j]=false;
            }
        }
    }
    static void spread() { // 바이러스 전파하기
        Queue<Virus> q = new ArrayDeque<>();
        boolean[][] copy = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            copy[i]=V[i].clone();
            for (int j = 0; j < M; j++) if(arr[i][j]==2) q.offer(new Virus(i, j));
        }
        while (!q.isEmpty()) {
            Virus cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ni = cur.r + di[i];
                int nj = cur.c + dj[i];
                if(ni<0||nj<0||ni>=N||nj>=M||copy[ni][nj]) continue;
                copy[ni][nj]=true;
                q.offer(new Virus(ni, nj));
            }
        }
        // 안전영역 탐색
        int cnt=0;
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if(!copy[i][j]) cnt++;
        if(max<cnt) max=cnt;
    }
}
