//https://www.acmicpc.net/problem/14442
//Solved : 22/03/07

import java.util.*;
import java.io.*;

class Main{
    static char[][] map;
    static int N, M, K;
    static int[] dr = {-1,0,1,0}, dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();

        if(N==1&&M==1) bw.write("1");               //1*1짜리 맵인 경우 답은 1
        else bw.write(Integer.toString(BFS()));

        bw.close();
        br.close();
    }

    static int BFS(){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][K+1];       //맵크기, 벽 깼을떄 안깼을떄 방문 비교
        q.add(new int[]{0,0,K,1});                          //시작점, 벽꺤횟수, 시작부터 1이므로
        visited[0][0][K] = true;                            //벽안깬 0,0 방문 체크
        while(!q.isEmpty()){
            int[] n = q.poll();
            for(int i=0; i<4; i++){
                int nr = n[0] + dr[i];
                int nc = n[1] + dc[i];

                if(nr==N-1 && nc==M-1) return n[3]+1;           //도착점 도착시 반환 후 종료
                if(nr>=N||nr<0||nc>=M||nc<0||visited[nr][nc][n[2]]) continue;          //배열 밖, 이미 이동했던 곳 스킵

                if(map[nr][nc] == '1'&& n[2]>0){               //벽 한번도 안깼을경우
                    visited[nr][nc][n[2]-1] = true;
                    q.add(new int[]{nr,nc,n[2]-1,n[3]+1});
                }
                else if(map[nr][nc] != '1'){
                    visited[nr][nc][n[2]] = true;
                    q.add(new int[]{nr,nc,n[2],n[3]+1});
                }
            }
        }
        return -1;
    }
}