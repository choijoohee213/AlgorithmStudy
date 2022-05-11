//https://www.acmicpc.net/problem/16946
//Solved :

import java.util.*;
import java.io.*;

class Main{
    static int N,M, num=1;
    static int[][] map;
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    static Map<Integer, Integer> size = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        String s;
        for(int i=0; i<N; i++){
            s = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = s.charAt(j) - '2';      //사이즈랑 햇갈리지 않게 시작을 더 뺴주었다(빈칸 -2, 벽 -1)
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == -2) BFS(i,j);       //빈공간이면 bfs돌면서 다 하나로 묶음
            }
        }
        Set<Integer> set = new HashSet<>();         //둘러쌓인경우 중복해서 더해지게 되므로 확인용 set
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == -1){                //벽인경우
                    set.clear();                    //셋 한번 비워주고
                    int n = 1;
                    for(int k=0; k<4; k++){         //4방향 다 돌며 값 확인
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr<0||nr>=N||nc<0||nc>=M||map[nr][nc]==-1||set.contains(map[nr][nc])) continue;
                        set.add(map[nr][nc]);
                        n += size.get(map[nr][nc]);
                    }
                    sb.append(n%10);                //확인한 값들의 나머지만 출력
                }else{
                    sb.append(0);                   //벽 아님 걍 0
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void BFS(int r, int c){
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{r,c});
        map[r][c] = num;                        //몇번째 모임인지 확인
        int sum = 1;
        while(!que.isEmpty()){
            int[] arr = que.poll();
            for(int i=0; i<4; i++){
                int nr = arr[0] + dr[i];
                int nc = arr[1] + dc[i];
                if(nr<0||nr>=N||nc<0||nc>=M||map[nr][nc]!=-2) continue;
                sum++;
                map[nr][nc] = num;              //모임 번호로 숫자 채우기
                que.add(new int[]{nr, nc});
            }
        }
        size.put(num++, sum);                   //맵에 숫자 추가 후 다음 모임 숫자 증가
    }
}