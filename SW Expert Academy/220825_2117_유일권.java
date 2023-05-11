//solved : 22/

import java.util.*;
import java.io.*;

class Solution {
	static int[][] map;
	static int N, M, houses, best;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            best = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						houses++;
				}
			}
            // System.out.println(calc(4,3));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int rst = calc(i, j);
                    best = best > rst ? best : rst;
				}
			}
            sb.append("#").append(tc).append(" ").append(best).append("\n");
		}

		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	static int calc(int r, int c) {
		int h = 0;
		int d = 1;
		int budget;
		while (true) {
            int time = 0;
			budget = d * d + (d - 1) * (d - 1);
			if (budget > houses * M)
				break;
			for (int i = -d + 1; i < d; i++) {
				for (int j = -d + 1 + Math.abs(i); j < d - Math.abs(i); j++) {
					int nr = r + i;
					int nc = c + j;
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					if (map[nr][nc] == 1){
                        time++;
                    }
				}
			}
            if(budget <= M * time){
                h = h > time ? h : time;
            }
			d++;
		}
		return h;
	}
}