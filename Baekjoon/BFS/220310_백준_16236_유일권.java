//https://www.acmicpc.net/problem/16236
//Solved : 22/03/10

import java.util.*;
import java.io.*;

class Main {
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int[][] map;
    static int[] cur;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    cur = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }
        int size = 2;
        int eat = 0; // 먹은 물고기 수
        int move = 0; // 움직인 총 거리

        while (true) {
            PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) ->
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );                                                                  //y축 이 다르면, y축 기준으로, 같으면 x축 기준으로 정렬
            boolean[][] visit = new boolean[N][N];

            que.add(new int[]{cur[0], cur[1], 0}); // y좌표, x좌표, 이동한 거리
            visit[cur[0]][cur[1]] = true;

            boolean ck = false; // 상어가 먹이를 먹었는지 체크할 변수

            while (!que.isEmpty()) {
                cur = que.poll();

                if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) { // 먹이가 있으면서 상어의 사이즈보다 작다면?
                    map[cur[0]][cur[1]] = 0; // 물고기를 제거
                    eat++;
                    move += cur[2]; // 움직인 거리를 총 움직인 거리에 추가
                    ck = true; // 먹이 먹었다고 체크
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visit[ny][nx] || map[ny][nx] > size)
                        continue;

                    que.add(new int[]{ny, nx, cur[2] + 1});
                    visit[ny][nx] = true;
                }
            }

            if (!ck) // 큐가 비워질 때까지 먹이를 먹은적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
                break;

            if (size == eat) { // 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기를 증가
                size++;
                eat = 0;
            }
        }
        System.out.println(move);
    }
}

//class Main{
//    static int N, SIZE=2, EAT=0;
//    static int[][] map;
//    static int[] shark, dr={-1,0,1,0}, dc={0,-1,0,1};
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st;
//        N = Integer.parseInt(br.readLine());
//        map = new int[N][N];
//
//        for(int i=0; i<N; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j=0; j<N; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j] == 9){
//                    shark = new int[]{i,j};
//                    map[i][j] = 0;
//                }
//            }
//        }
//        int rst = 0;
//        while(true){
//            System.out.println(Arrays.toString(shark)+" "+rst+" "+SIZE+" "+EAT);
//            int next = BFS();
//            if(next==-1) break;
//            else{
//                rst+=next;
//            }
//        }
//        bw.write(Integer.toString(rst));
//        bw.close();
//        br.close();
//    }
//    static int BFS(){
//        Queue<int[]> q = new ArrayDeque<>();
//        q.add(new int[]{shark[0], shark[1], 0});
//        boolean[][] visited = new boolean[N][N];
//        visited[shark[0]][shark[1]] = true;
//        int rst = -1, num = 0, cnt = q.size();
//        Map<Integer, int[]> dest = new HashMap<>();
//        while(!q.isEmpty()){
//            int[] n  = q.poll();
//            cnt--;
//            //System.out.println(Arrays.toString(n));
//            for(int i=0; i<4; i++){
//                int nr = n[0]+dr[i];
//                int nc = n[1]+dc[i];
//
//                if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]||map[nr][nc]>SIZE) continue;
//
//                if(map[nr][nc]>0&&map[nr][nc]<SIZE){
//                    dest.put(num++, new int[]{nr,nc});
//                    rst = n[2]+1;
//                    visited[nr][nc] = true;
//                    //System.out.println(EAT+" "+SIZE);
//                }
//                else{
//                    visited[nr][nc] = true;
//                    q.add(new int[]{nr, nc, n[2]+1});
//                }
//            }
//            if(cnt==0){
//                if(num>0) break;
//                cnt = q.size();
//            }
//        }
//        if(num>0) {
//            int[] next = new int[]{N, N};
//            for (int i = 0; i < num; i++) {
//                int[] tmp = dest.get(i);
//                if (next[0] == tmp[0]) {
//                    if (next[1] < tmp[1]) {
//                        next[1] = tmp[1];
//                    }
//                } else if (next[0] > tmp[0]) {
//                    next[0] = tmp[0];
//                    next[1] = tmp[1];
//                }
//            }
//            map[next[0]][next[0]] = 0;
//            shark[0] = next[0];
//            shark[1] = next[1];
//            if (++EAT == SIZE) {
//                EAT = 0;
//                SIZE++;
//            }
//        }
//        return rst;
//    }
//}