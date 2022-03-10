//https://www.acmicpc.net/problem/16933
//Solved : 22/03/07

import java.util.*;
import java.io.*;

class Main{                 //mem : 697828KB time : 4404ms
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
        boolean[][][][] visited = new boolean[N][M][K+1][2];        //맵크기, 벽 깼을떄 안깼을떄 방문 비교
        q.add(new int[]{0,0,K,1});                                  //시작점, 벽꺤횟수, 시작부터 1이므로
        visited[0][0][K][1] = true;                                 //벽안깬 0,0 방문 체크
        while(!q.isEmpty()){
            int[] n = q.poll();
            //System.out.println(Arrays.toString(n));
            for(int i=0; i<4; i++){
                int nr = n[0] + dr[i];
                int nc = n[1] + dc[i];

                if(nr==N-1 && nc==M-1) return n[3]+1;
                if(nr>=N||nr<0||nc>=M||nc<0||visited[nr][nc][n[2]][n[3]%2]) continue;          //배열 밖, 이미 이동했던 곳 스킵

                if(map[nr][nc] == '1'&& n[2]>0 && n[3]%2==1){                   //벽 한번도 안깼을경우, 낮(움직인 횟수가 홀수면 낮)
                    visited[nr][nc][n[2]][n[3]%2] = true;                       //방문처리하고 벽 파괴
                    q.add(new int[]{nr,nc,n[2]-1,n[3]+1});
                } else if(map[nr][nc] == '1' && n[2]>0){                        //벽 한번도 안깼을경우, 밤(움직인 횟수가 짝수면 밤)
                    visited[nr][nc][n[2]][n[3]%2] = true;                       //밤 위치 체크(벽부수려고 대기중~)
                    q.add(new int[]{n[0],n[1],n[2],n[3]+1});                    //그자리 그대로 추가
                    //q.add(new int[]{nr,nc,n[2]-1,n[3]+2});                    //낮까지 기다린다 생각하고 2씩 더하면 더 높은 수가 방문 처리가 되어서인지 0%에서 틀린다.
                } else if(map[nr][nc] != '1'){
                    visited[nr][nc][n[2]][n[3]%2] = true;                       //벽 아닌 경우 이동
                    q.add(new int[]{nr,nc,n[2],n[3]+1});
                }
            }
        }
        return -1;
    }
}
/*
import java.io.*;
import java.util.*;

class Main{                 //mem : 340900KB time : 3676ms
    static class Move implements Comparable<Move>{              //클래스 선언
        int r,c,k,t;

        public Move(int r, int c, int k, int t) {
            this.r = r;     //가로
            this.c = c;     //세로
            this.k = k;     //벽 남은수
            this.t = t;     //이동 수
        }
        @Override
        public int compareTo(Move o) {              //PQ 사용하므로 거리 -> 벽 남은 갯수 순 으로 비교
            if(this.t==o.t) return this.k - o.k;
            else return this.t - o.t;
        }
    }
    static int N,M,K;
    static char[][] map;
    static int[] dr = {-1,0,1,0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }

        if(N==M&&N==1) bw.write("1");
        else bw.write(Integer.toString(BFS()));

        bw.close();
        br.close();
    }

    static int BFS(){
        PriorityQueue<Move> q = new PriorityQueue<>();                  //2씩 움직이는 경우도 있으므로 PQ사용
        int rst = Integer.MAX_VALUE;
        boolean[][][] visited = new boolean[N][M][K+1];         //맵크기, 벽 깼을떄 안깼을떄 방문 비교(낮밤 상관없이 2씩 움직이므로)
        q.add(new Move(0,0, K,1));                              //시작점, 벽꺤횟수, 시작부터 1이므로
        visited[0][0][K] = true;                                //벽안깬 0,0 방문 체크
        while(!q.isEmpty()){
            Move n = q.poll();
            if(n.r==N-1 && n.c==M-1) rst = rst < n.t ? rst : n.t;           //도착점 도착시 반환 후 종료
            for(int i=0; i<4; i++){
                int nr = n.r + dr[i];
                int nc = n.c + dc[i];

                if(nr>=N||nr<0||nc>=M||nc<0||visited[nr][nc][n.k]) continue;          //배열 밖, 이미 이동했던 곳 스킵

                if(map[nr][nc] == '1' && n.k>0){
                    visited[nr][nc][n.k] = true;
                    if(n.t%2==1) q.add(new Move(nr,nc, n.k-1,n.t+1));
                    else q.add(new Move(nr,nc, n.k-1,n.t+2));
                }
                else if(map[nr][nc] != '1'){
                    visited[nr][nc][n.k] = true;
                    q.add(new Move(nr,nc, n.k,n.t+1));
                }
            }
        }
        return rst==Integer.MAX_VALUE ? -1 : rst;
    }
}
/*
반례
10 20 10
00100010110010001010
10110101110101011101
01101010001010011011
11101000011101011110
01100111011111000011
11000000001111111101
01100010010101111001
00000100110010100101
10011010100100010110
11011001110000010100

answer : 30
 */
