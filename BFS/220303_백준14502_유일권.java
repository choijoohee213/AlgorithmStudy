//https://www.acmicpc.net/problem/14502
//Solved : 22/03/03

import java.util.*;
import java.io.*;

class Main{
    static int[][] lab;
    static Map<Integer, int[]> virus = new HashMap<>();
    static int vir_num=1, N, M, rst = Integer.MIN_VALUE;
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    static int safe=-3;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                lab[i][j] = Integer.parseInt(st.nextToken());
                if(lab[i][j]==2) virus.put(vir_num++, new int[]{i,j});      //바이러스를 입력받으면 좌표를 맵에 추가
                else if(lab[i][j]==0) safe++;                               //빈칸이면 갯수 추가
            }
        }
        if(safe==0) rst = 0;                        //빈칸이 딱 3개면 벽새우고나면 자리 없으므로 0
        else make_map(0, 0, 0);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void make_map(int idx, int r, int c){
        if(idx==3){                 //벽 3개 채우면 확인
            chk_safe();
            return;
        }
        if(r==N) return;            //마지막 행이 되면 함수 종료
        if(c==M){                   //마지막 열이 되면 다음 행 검사, 종료
            make_map(idx, r+1, 0);
            return;
        }
        if(lab[r][c]==0){               //빈공간이면 벽새우고 확인, 벽 지우고 확인
            lab[r][c]=1;
            make_map(idx+1, r, c+1);
            lab[r][c]=0;
            make_map(idx, r, c+1);
        }else{                          //아니면 다음칸
            make_map(idx, r, c+1);
        }
    }

    static void chk_safe(){
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> que = new ArrayDeque<>();
        for(int i=1; i<vir_num; i++){       //바이러스 위치들 큐에 추가
            int[] arr = virus.get(i);
            que.add(arr);
        }
        //int safe_num = safe + vir_num -1;             //총 빈칸수에 이미 바이러스로 빠진 수만큼 추가
        while(!que.isEmpty()){
            int[] p = que.poll();
            //safe_num--;                               //바이러스 검사때마다 -1씩
            for(int i=0; i<4; i++){
                //배열 밖으로 나가거나, 빈공간 X, 방문한경우 스킵
                if(p[0]+dr[i]<0||p[0]+dr[i]>=N||p[1]+dc[i]<0||p[1]+dc[i]>=M||lab[p[0]+dr[i]][p[1]+dc[i]]!=0||visited[p[0]+dr[i]][p[1]+dc[i]]) continue;
                visited[p[0]+dr[i]][p[1]+dc[i]] = true;                     //방문체크, 큐에 추가
                que.add(new int[]{p[0]+dr[i], p[1]+dc[i]});
            }
        }
        int v = 0;
        for(boolean[] i : visited) for(boolean j : i) if(j) v++;        //방문한 경우 바이러스에 감염된것이므로
        rst = rst > safe-v ? rst : safe-v;                              //총 빈공간에서 감염된만큼만 빼준다.
        //rst = rst > safe_num ? rst : safe_num;
    }
}