import java.io.*;
import java.util.*;

class Land {
	int food;
	PriorityQueue<Integer> trees;

	public Land() {
		this.food = 5;
		trees = new PriorityQueue<>();
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int result = 0;
		int[][] winter = new int[n][n];
		Land[][] arr = new Land[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = new Land();
			}
		}
		int[] dx = {-1,-1,-1,0,0,1,1,1}, dy = {-1,0,1,-1,1,-1,0,1};

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				winter[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			arr[x][y].trees.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i <k; i++) {
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < n; l++) {
					PriorityQueue<Integer> tree = new PriorityQueue<>();
					Land land = arr[j][l];
					int add = 0;
					while(land.trees.size() > 0) {
						int age = land.trees.poll();
						if(land.food >= age) {
							//나이만큼 양분 먹고 나이 1증가
							land.food -= age;
							tree.add(age + 1);
						}
						else {  //양분 부족하면 죽는다 -> 여름에 양분으로 변함
							add += age / 2;
						}
					}
					land.trees = tree;
					land.food += add;
				}
			}

			//가을
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < n; l++) {
					Land land = arr[j][l];
					for (Integer age : land.trees) {
						if(age % 5 == 0) {
							for (int dir = 0; dir < 8; dir++) {
								int nx = j + dx[dir];
								int ny = l + dy[dir];
								if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
								arr[nx][ny].trees.add(1);
							}
						}
					}
				}
			}
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < n; l++) {
					arr[j][l].food += winter[j][l];
				}
			}

			result = 0;
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < n; l++) {
					result += arr[j][l].trees.size();
				}
			}
			if(result == 0) break;
		}
		br.close();
		System.out.println(result);
	}
}