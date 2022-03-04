import java.io.*;
import java.util.*;
public class Main_bj_2206_벽부수고이동하기 {
    static int N, M, arr[][], min=Integer.MAX_VALUE, di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        int cnt0 = 0;
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                arr[i][j]=Integer.parseInt(String.valueOf(tmp[j]));
                if(arr[i][j]==0) cnt0++;
            }
        }
        if(cnt0<=N+M-2) {
            System.out.println(-1);
            System.exit(0);
        }
        bfs();
        System.out.println(min==Integer.MAX_VALUE? -1 : min);
        br.close();
    }
    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] V = new boolean[N][M][2];
        V[0][0][0]=true;
        q.offer(new int[]{0, 0, 1, 0}); // r, c, cnt, broken
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0]==N-1&&cur[1]==M-1) {
                min = Math.min(min, cur[2]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ni=cur[0]+di[i], nj=cur[1]+dj[i];
                if(ni<0||nj<0||ni>=N||nj>=M) continue;
                if(arr[ni][nj]==1 && cur[3]==0 && !V[ni][nj][1]) { // 벽을 만났고, 이전에 벽을 깬적 없고, 해당 벽을 방문한적도 없을 때
                    V[ni][nj][cur[3]]=true; // 벽 제거하고 방문한 효과
                    q.offer(new int[]{ni, nj, cur[2] + 1, 1});
                }
                else if(arr[ni][nj]==0 && !V[ni][nj][cur[3]]) { // 벽 만나지 않았고, 방문한 적 없을 때
                    V[ni][nj][cur[3]]=true; // 벽 제거했으면 cur[3]은 1 -> 방문처리
                    q.offer(new int[]{ni, nj, cur[2] + 1, cur[3]});
                }
            }
        }
    }
}
