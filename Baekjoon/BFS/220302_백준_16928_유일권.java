//https://www.acmicpc.net/problem/16928
//Solved : 22/03/02

import java.util.*;
import java.io.*;

class Main{
    static Map<Integer, Integer> lad;
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        lad = new HashMap<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int s,f;
        for(int i=0; i<N+M; i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            f = Integer.parseInt(st.nextToken());
            lad.put(s, f);
        }

        int rst = BFS();

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static int BFS(){
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{1,0});
        while(!que.isEmpty()){
            int[] now = que.poll();
            if(visited[now[0]]) continue;
            visited[now[0]] = true;
            for(int i=1; i<=6; i++){
                int next;
                if(lad.get(now[0]+i)!=null) next = lad.get(now[0]+i);
                else  next = now[0]+i;

                if(next==100){
                    return now[1]+1;
                }
                if(next>100) break;
                que.add(new int[]{next, now[1] + 1});
            }
        }
        return 0;
    }
}