//https://www.acmicpc.net/problem/11967
//Solved : 22/05/10

import java.io.*;
import java.util.*;

class Main {
    static int [] dx = {0,0,1,-1},dy = {1,-1,0,0};
    static boolean[][] visited, isPossible;
    static ArrayList<Node>[][] map;
    static int n,m;
    static int ans =0;
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new ArrayList[n+1][n+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            ArrayList<Node>list = map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
            list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        bfs();
        System.out.println(ans);
    }

    public static void bfs() {
        Queue<Node> q= new LinkedList<>();
        visited = new boolean[n+1][n+1];
        isPossible = new boolean[n+1][n+1];
        ans++;
        ArrayList<Node>waiting = new ArrayList<>();
        visited[1][1]= true;
        isPossible[1][1] =true;
        q.add(new Node(1,1));
        while(!q.isEmpty()) {
            Node a = q.poll();

            for(Node light : map[a.x][a.y]) {
                if(!isPossible[light.x][light.y]) {
                    isPossible[light.x][light.y] = true;
                    ans++;
                }
            }

            for(int i=0; i<4; i++) {
                int nx = a.x+dx[i];
                int ny = a.y+dy[i];

                if(isRange(nx,ny) && !visited[nx][ny]) {
                    if(isPossible[nx][ny]) {
                        q.add(new Node(nx,ny));
                        visited[nx][ny] = true;
                    }
                    else {
                        waiting.add(new Node(nx,ny));
                    }
                }
            }
            int size = waiting.size();

            for (Node poll : waiting) {
                if (isPossible[poll.x][poll.y] && !visited[poll.x][poll.y]) {
                    q.add(new Node(poll.x, poll.y));
                    visited[poll.x][poll.y] = true;
                }
            }
        }

    }
    public static boolean isRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= n && y <= n;
    }
}
class Node{
    int x,y;
    Node(int x, int y){
        this.x=x;
        this.y=y;
    }
}