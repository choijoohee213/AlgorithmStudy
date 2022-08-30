//solved : 22/08/30

import java.util.*;
import java.io.*;

class Solution {
	static int N;
	static int[][] map;
	static List<int[]> people;
	static List<int[]> stair;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			people = new ArrayList<>();
			stair = new ArrayList<>();
			int best = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						people.add(new int[] {i, j});
					} else if (map[i][j] > 1) {
						stair.add(new int[] {i, j});
					}
				}
			}

			PriorityQueue<Integer> stair1 = new PriorityQueue<>();
			PriorityQueue<Integer> stair2 = new PriorityQueue<>();
			for (int i = 0; i < (int)Math.pow(2, people.size()); i++) {
			// int i = 32;
				stair1.clear();
				stair2.clear();
				for (int j = 0; j < people.size(); j++) {
					int[] point = people.get(j);
					if ((i & (1 << j)) == 0) {
						int[] way = stair.get(0);
						int move = Math.abs(way[0] - point[0]) + Math.abs(way[1] - point[1]);
						stair1.add(move);
					} else {
						int[] way = stair.get(1);
						int move = Math.abs(way[0] - point[0]) + Math.abs(way[1] - point[1]);
						stair2.add(move);
					}
				}
				// System.out.print("i : "+i+", st1.size: "+stair1.size()+", st2.size: "+stair2.size());
				int t1 = checkTime(stair1, map[stair.get(0)[0]][stair.get(0)[1]]);
				int t2 = checkTime(stair2, map[stair.get(1)[0]][stair.get(1)[1]]);
				// System.out.print(" "+t1+" "+t2);
				t1 = t1 > t2 ? t1 : t2;
				best = t1 < best ? t1 : best;
				// System.out.println(" "+best);
			}
			sb.append("#").append(tc).append(" ").append(best).append("\n");
		}

		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	static int checkTime(PriorityQueue<Integer> stair, int dist) {
		if (stair.isEmpty()) {
			return -1;
		}

		Queue<Integer> pq = new ArrayDeque<>();
		int next = stair.poll();
		pq.add(next);
		int time = next;
		Queue<Integer> waiting = new ArrayDeque<>();
		while (true) {
			while (!stair.isEmpty()) {
				next = stair.peek();
				if (next <= time) {
					stair.poll();
					if(pq.size() < 3) {
						pq.add(next);
					}else {
						waiting.add(next);
					}
				}else {
					break;
				}
			}
			while (!pq.isEmpty()) {
				int t = pq.peek();
				if (t + dist + 1 == time) {
					pq.poll();
					if(waiting.size()>0){
						int tmp = waiting.poll();
						if(tmp == time){
							pq.add(time);
						}else {
							pq.add(time-1);
						}
					}
				} else {
					break;
				}
				// System.out.println(pq.size()+" "+waiting);
			}
			if(pq.isEmpty() && stair.isEmpty()){
				break;
			}
			// System.out.println(pq.size() + " " + waiting);
			time++;
		}
		return time;
	}
}
/*
#1 9
#2 8
#3 9 *
#4 7
#5 8
#6 8
#7 11
#8 11
#9 18 *
#10 12

 */