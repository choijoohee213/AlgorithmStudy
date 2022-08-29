import java.io.*;
import java.util.*;

class Solution {
	static int n, minTime;
	static int[][] arr, dist;
	static List<Integer> people;
	static int[] stairs, peopleToStair;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			minTime = Integer.MAX_VALUE;
			people = new ArrayList<>();
			stairs = new int[2];
			int idx = 0;

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] > 1) {
						stairs[idx++] = i * n + j;
					} else if(arr[i][j] == 1){
						people.add(i * n + j);
					}
				}
			}
			peopleToStair = new int[people.size()];
			dist = new int[people.size()][2];

			//각 사람들과 각 계단 사이의 거리 구하기
			for (int i = 0; i < people.size(); i++) {
				for (int j = 0; j < 2; j++) {
					int peopleX = people.get(i) / n;
					int peopleY = people.get(i) % n;
					int stairX = stairs[j] / n;
					int stairY = stairs[j] % n;
					dist[i][j] = Math.abs(peopleX - stairX) + Math.abs(peopleY - stairY);
				}
			}
			recursive(0, 0);
			recursive(0, 1);
			sb.append("#" + t + " " + minTime + "\n");
		}
		System.out.print(sb);
	}

	private static void recursive(int cnt, int stairIdx) {
		if(cnt == people.size()) {
			calculate();
			return;
		}
		peopleToStair[cnt] = stairIdx;
		recursive(cnt+1, 0);
		recursive(cnt+1, 1);
	}

	private static void calculate() {
		int[] distArr = new int[people.size()];
		int[] downCnt = new int[2];

		for (int i = 0; i < people.size(); i++) {
			distArr[i] = dist[i][peopleToStair[i]];
		}

		int time = 0;
		boolean[] finished = new boolean[people.size()];
		while (time++ < minTime) {
			List<int[]> wait = new ArrayList<>();
			for (int i = 0; i < people.size(); i++) {
				if (finished[i])
					continue;
				if (distArr[i] == 0) {  //계단 내려갈 준비 완료
					wait.add(new int[]{i, peopleToStair[i]});
				}
				if (--distArr[i] < (-1) * arr[stairs[peopleToStair[i]] / n][stairs[peopleToStair[i]] % n]) {
					//계단을 모두 내려갔으면
					finished[i] = true;
					downCnt[peopleToStair[i]]--;
				}
			}

			for (int[] wp : wait) {
				if(downCnt[wp[1]] < 3) {
					downCnt[wp[1]]++;
					distArr[wp[0]]--;
				}
			}

			boolean flag = true;
			for (int i = 0; i < people.size(); i++) {
				if (!finished[i]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				minTime = time;
			}
		}
	}
}