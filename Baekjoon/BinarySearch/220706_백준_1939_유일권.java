//https://www.acmicpc.net/problem/
//Solved :

import java.util.*;
import java.io.*;

class Main{
    static List<int[]>[] bridges;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long max = 0;

        bridges = new List[N];
        for(int i=0; i<N; i++) bridges[i] = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            int t3 = Integer.parseInt(st.nextToken());
            max = max > t3 ? max : t3;
            bridges[t1-1].add(new int[]{t2-1, t3});
            bridges[t2-1].add(new int[]{t1-1, t3});
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken())-1;
        int D = Integer.parseInt(st.nextToken())-1;
        long min = 0;

        long mid;
        while (min <= max){
            mid = (min + max)/2;
            visited = new boolean[N];
            visited[S] = true;
            if(DFS(S, D, mid)){
                min = mid+1;
            }else{
                max = mid-1;
            }
        }

        bw.write(Long.toString(max));
        bw.close();
        br.close();
    }

    static boolean DFS(int S, int D, long mid){
        if(S==D){
            return true;
        }
        for(int[] i : bridges[S]){
            if(visited[i[0]] || i[1] < mid) continue;
            visited[i[0]] = true;
            if(DFS(i[0], D, mid)){
                return true;
            }
            //visited[i[0]] = false;
        }
        return false;
    }

//    static boolean BFS(int S, int D, long mid){
//        Queue<Move> que = new ArrayDeque<>();
//        boolean[] tmp = new boolean[bridges.length];
//        tmp[S] = true;
//        que.add(new Move(S, tmp));
//        while (!que.isEmpty()){
//            Move now = que.poll();
//            for(int[] i : bridges[now.node]){
//                if(now.visited[i[0]] || mid > i[1]) continue;
//                if(i[0] == D) return true;
//                tmp = now.visited.clone();
//                que.add(new Move(i[0], tmp));
//            }
//        }
//        return false;
//    }
//    static class Move{
//        int node;
//        boolean[] visited;
//        Move(int node, boolean[] visited){
//            this.node = node;
//            this.visited = visited;
//        }
//    }
}