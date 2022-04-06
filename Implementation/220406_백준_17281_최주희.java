import java.io.*;
import java.util.*;

/*
1. 9명이 한팀
2. 경기가 시작하기 전까지 타순 정해야함
3. 9번 타자까지 공 쳤는데 3아웃이 아니라면 다시 1번 타자
4. 타순은 이닝이 변경되어도 유지되어야함
5. 타자가 공을 치면 안타,2루타,3루타,홈런,아웃 중 하나

- 아인타팀의 4번타자는 1번선수
 */

class Main {
	static int n, result = 0;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1][10];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] order = new int[10];
		order[4] = 1;
		permutation(1, new boolean[10], order);
		br.close();
		System.out.print(result);
	}

	static void permutation(int cnt, boolean[] visited, int[] order) {
		if(cnt == 10) {
			play(order);
		}
		if(cnt == 4) {
			permutation(cnt+1, visited, order);
			return;
		}

		for (int i = 2; i <= 9; i++) {
			if(visited[i]) continue;
			order[cnt] = i;
			visited[i] = true;
			permutation(cnt+1, visited, order);
			visited[i] = false;
		}
	}

	static void play(int[] order) {
		int score = 0, j = 0;

		for (int i = 1; i <= n; i++) {
			j = j == 9? 1: j + 1;
			int[] runner = new int[3];
			int out = 0;
			while(true) {
				int player = order[j];
				int x = arr[i][player];
				if(x == 1) {  //안타
					if(runner[2] != 0) score++;
					runner[2] = runner[1];
					runner[1] = runner[0];
					runner[0] = player;
				} else if(x == 2) {  //2루타
					if(runner[2] != 0) score++;
					if(runner[1] != 0) score++;
					runner[2] = runner[0];
					runner[1] = player;
					runner[0] = 0;
				} else if(x == 3) {  //3루타
					for (int k = 0; k < 3; k++) {
						if(runner[k] != 0) {
							score++;
							runner[k] = 0;
						}
					}
					runner[2] = player;
				} else if(x == 4) {  //홈런
					for (int k = 0; k < 3; k++) {
						if(runner[k] != 0) {
							score++;
							runner[k] = 0;
						}
					}
					score++;
				} else if(x == 0) {
					out++;
					if(out == 3) {
						break;
					}
				}
				j = j == 9? 1: j + 1;
			}
		}
		result = Math.max(result, score);
	}
}