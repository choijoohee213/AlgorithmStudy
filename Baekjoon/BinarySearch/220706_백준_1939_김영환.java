package com.younghwani.a220707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_bj_1939_중량제한 {
    static class Bridge {
        int to, weight;

        public Bridge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Bridge>[] bridges = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) bridges[i] = new ArrayList<>();

        int s = Integer.MAX_VALUE, e = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            bridges[A].add(new Bridge(B, C));
            bridges[B].add(new Bridge(A, C));
            s = Math.min(s, C);
            e = Math.max(e, C);
        }

        st = new StringTokenizer(br.readLine(), " ");
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        int res = -1;
        while (s <= e) {
            int m = (s + e) / 2;
            if (isOk(m, N, from, to, bridges)) {
                s = m + 1;
                res = m;
            } else {
                e = m - 1;
            }
        }

        System.out.print(res);
        br.close();
    }

    static boolean isOk(int weight, int N, int from, int to, List<Bridge>[] bridges) {
        Queue<Bridge> q = new ArrayDeque<>();
        boolean[] V = new boolean[N + 1];
        q.offer(new Bridge(from, 0));

        while (!q.isEmpty()) {
            Bridge cur = q.poll();

            if (cur.to == to) return true;

            for (Bridge next : bridges[cur.to]) {
                if (next.weight >= weight && !V[next.to]) {
                    q.offer(next);
                    V[next.to] = true;
                }
            }
        }

        return false;
    }
}
