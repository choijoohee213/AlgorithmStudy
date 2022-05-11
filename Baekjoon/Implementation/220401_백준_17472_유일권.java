//https://www.acmicpc.net/problem/17472
//Solved : 22/04/01

import java.util.*;
import java.io.*;

class Main{
    static int[] parents;
    static int[][] map;
    static boolean[][] chk;
    static int N, M;
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        chk = new boolean[N][M];
        int islands = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 1 && !chk[i][j]){
                    islands++;
                    chk(i, j, islands);
                }
            }
        }
        parents = new int[islands+1];
        for(int i=1; i<=islands; i++) parents[i] = i;

        PriorityQueue<int[]> que = new PriorityQueue<>((x, y) -> x[2]-y[2]);
        for(int now = 1; now<islands; now++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if(map[r][c] == now){
                        for(int i=0; i<4; i++){
                            int dist=0;
                            int nr = r+dr[i];
                            int nc = c+dc[i];
                            while (nr>=0&&nr<N&&nc>=0&&nc<M){
                                if(map[nr][nc]>now){
                                    if(dist>1){
                                        que.add(new int[]{now, map[nr][nc], dist});
                                        //System.out.println(r+" "+c+" "+now+" "+map[nr][nc]);
                                    }
                                    break;
                                }else if(map[nr][nc]>0) break;
                                dist++;
                                nr += dr[i];
                                nc += dc[i];
                            }
                        }
                    }
                }
            }
        }
        //for(int[] i : map) System.out.println(Arrays.toString(i));
        int rst = 0;
        while (!que.isEmpty()){
            int[] now = que.poll();
            //System.out.println(Arrays.toString(now));
            if(find(now[0]) != find(now[1])){
                rst += now[2];
                union(now[0], now[1]);
            }
        }
        for(int i=2; i<=islands; i++){
            if(find(i)!=find(1)) rst = -1;
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x<y) {
            parents[y] = x;
        }else{
            parents[x] = y;
        }
    }
    static int find(int x){
        if(parents[x]!=x) return find(parents[x]);
        else return x;
    }
    static void chk(int r, int c, int num){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r,c});
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            map[now[0]][now[1]] = num;
            for(int i=0; i<4; i++){
                int nr = now[0]+dr[i];
                int nc = now[1]+dc[i];
                if(nr<0||nc<0||nr>=N||nc>=M||map[nr][nc]==0||chk[nr][nc]) continue;
                chk[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }
    }
}