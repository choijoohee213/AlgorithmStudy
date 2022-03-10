//https://www.acmicpc.net/problem/3055
//Solved : 22/03/10

import java.util.*;
import java.io.*;

class Main{
    static int R,C;
    static char[][] map;
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1}, S;
    static Queue<int[]> water;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        water = new ArrayDeque<>();
        visited = new boolean[2][R][C];         //0은 두더지, 1은 물

        for(int i=0; i<R; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++){
                if(map[i][j]=='*'){
                    water.add(new int[]{i,j});
                    visited[1][i][j] = true;
                }
                else if(map[i][j]=='S') S = new int[]{i,j};
            }
        }

        int rst = BFS();
        if(rst!=-1) bw.write(Integer.toString(rst));
        else bw.write("KAKTUS");
        bw.close();
        br.close();
    }

    static int BFS(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{S[0], S[1], 0});
        visited[0][S[0]][S[1]] = true;
        int cnt = q.size();
        //System.out.println(cnt);
        while(!q.isEmpty()){
            int[] n = q.poll();
            cnt--;
            //System.out.println(Arrays.toString(n));
            if(map[n[0]][n[1]]=='*') continue;
            for(int i=0; i<4; i++) {
                int nr = n[0] + dr[i];
                int nc = n[1] + dc[i];

                if (nr >= R || nr < 0 || nc >= C || nc < 0 || visited[0][nr][nc]) continue;
                if(map[nr][nc] == 'D') return n[2]+1;
                if(map[nr][nc] == '.') {
                    visited[0][nr][nc] = true;
                    q.add(new int[]{nr, nc, n[2] + 1});
                }
            }
            if(cnt == 0){
                Flow();
                cnt = q.size();
                //System.out.println(cnt);
            }
        }
        return -1;
    }
    static void Flow(){
        int cnt = water.size();
        while(cnt>0){
            int[] n = water.poll();
            cnt--;
            for(int i=0; i<4; i++){
                int nr = n[0]+dr[i];
                int nc = n[1]+dc[i];
                if(nr>=R||nr<0||nc>=C||nc<0||visited[1][nr][nc]) continue;

                if(map[nr][nc]=='.'){
                    map[nr][nc] = '*';
                    visited[1][nr][nc] = true;
                    water.add(new int[]{nr, nc});
                }
            }
        }
        //for(char[] i : map) System.out.println(Arrays.toString(i));
    }
}