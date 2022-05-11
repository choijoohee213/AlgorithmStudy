package com.younghwani.a220302;

import java.io.*;
import java.util.*;
// 사다리를 만나지 않는 경우는 주사위(1~6)만큼 이동한다.
// 사다리 만나면 이동한다.
// 그냥 뱀 만나면 continue로 넘긴다.(틀림) -> 이건 안됨. 뱀 만나서 뒤로 갔다가, 지름길 타는 경우도 고려해야될 듯
public class Main_bj_16928_뱀과사다리게임 {
    static int N, M;
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map.put(k, v);
        }
        bfs();
        br.close();
    }
    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] V = new boolean[101];
        V[1]=true;
        q.offer(new int[]{0, 1}); // cnt, idx
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 1; i <= 6; i++) { // 주사위 수만큼
                int nIdx = cur[1]+i;
                if(nIdx==100) {System.out.println(cur[0]+1); return;}
                if(nIdx<0||nIdx>100||V[nIdx]) continue;
                if (map.containsKey(nIdx)) { // 사다리 or 뱀
                    nIdx = map.get(nIdx);
                    if(V[nIdx]) continue;
                    V[nIdx] = true;
                    q.offer(new int[]{cur[0]+1, nIdx});
                    continue;
                }
                V[nIdx] = true;
                q.offer(new int[]{cur[0]+1, nIdx});
            }
        }
    }
}
