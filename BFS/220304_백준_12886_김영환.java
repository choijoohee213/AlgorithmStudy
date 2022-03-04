import java.io.*;
import java.util.*;
public class Main_bj_12886_돌그룹 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] V = new boolean[1501][1501];
        V[a][b]=true;
        q.offer(new int[]{a, b, c});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0]==cur[1]&&cur[1]==cur[2]) {
                System.out.println(1);
                return;
            }
            // a, b 비교
            int x=0, y=0;
            if(cur[0]<cur[1]) {
                x=cur[0]+cur[0];
                y=cur[1]-cur[0];
                if(!V[x][y]) {
                    q.offer(new int[]{x, y, cur[2]});
                    V[x][y]=true;
                }
            } else if(cur[0]>cur[1]) {
                x=cur[1]+cur[1];
                y=cur[0]-cur[1];
                if(!V[y][x]) {
                    q.offer(new int[]{y, x, cur[2]});
                    V[y][x]=true;
                }
            }
            // b, c 비교
            if(cur[1]<cur[2]) {
                x=cur[1]+cur[1];
                y=cur[2]-cur[1];
                if(!V[x][y]) {
                    q.offer(new int[]{cur[0], x, y});
                    V[x][y]=true;
                }
            } else if(cur[1]>cur[2]) {
                x=cur[2]+cur[2];
                y=cur[1]-cur[2];
                if(!V[y][x]) {
                    q.offer(new int[]{cur[0], y, x});
                    V[y][x]=true;
                }
            }
            // a, c 비교
            if(cur[0]<cur[2]) {
                x=cur[0]+cur[0];
                y=cur[2]-cur[0];
                if(!V[x][y]) {
                    q.offer(new int[]{x, cur[1], y});
                    V[x][y]=true;
                }
            } else if(cur[0]>cur[2]) {
                x=cur[2]+cur[2];
                y=cur[0]-cur[2];
                if(!V[y][x]) {
                    q.offer(new int[]{y, cur[1], x});
                    V[y][x]=true;
                }
            }
        }
        System.out.println(0);
        br.close();
    }
}
