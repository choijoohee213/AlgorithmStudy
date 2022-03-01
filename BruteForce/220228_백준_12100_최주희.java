import java.io.*;
import java.util.*;

class Main {
	static int n, result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		move(0, arr);
		br.close();
		System.out.print(result);
	}

	static void move(int cnt, int[][] map) {
		if(cnt == 5) {
			return;
		}

		for(int i=0; i<4; i++) {
			int[][] arr = new int[n][n];
			for(int j=0; j<n; j++) {
				System.arraycopy(map[j], 0, arr[j], 0, n);
			}
			//좌로 밀 때, 맨 왼쪽열부터 검사
			if(i == 0) {
				for (int j = 0; j < n; j++) {
					Stack<Integer> st = new Stack<>();
					boolean[] visited = new boolean[n];
					for (int k = 0; k < n; k++) {
						if(arr[j][k] == 0) continue;
						int pushItem = arr[j][k];
						if(!st.isEmpty()) {
							if(!visited[st.size()] && st.peek() == arr[j][k]) {
								pushItem = st.pop() * 2;
								visited[st.size()+1] = true;
							}
						}
						if(pushItem !=0) st.push(pushItem);
					}
					int size = st.size()-1;
					for(int k=size; k>=0; k--) {
						arr[j][k] = st.pop();
						result = Math.max(result, arr[j][k]);
					}
					for(int k=size+1; k<n; k++) {
						arr[j][k] = 0;
					}
				}
			}

			//우로 밀 때, 맨 오른쪽열부터 검사
			else if(i == 1) {
				for (int j = 0; j < n; j++) {
					Stack<Integer> st = new Stack<>();
					boolean[] visited = new boolean[n];
					for (int k = n - 1; k >= 0; k--) {
						if(arr[j][k] == 0) continue;
						int pushItem = arr[j][k];
						if(!st.isEmpty()) {
							if(!visited[st.size()] && st.peek() == arr[j][k]) {
								pushItem = st.pop() * 2;
								visited[st.size() + 1] = true;
							}
						}
						if(pushItem !=0) st.push(pushItem);
					}
					int size = st.size();
					for(int k=n-size; k<n; k++) {
						arr[j][k] = st.pop();
						result = Math.max(result, arr[j][k]);
					}
					for(int k=n-size-1; k>=0; k--) {
						arr[j][k] = 0;
					}
				}
			}

			//아래로 밀 때, 맨 아래행부터 검사
			else if(i == 2) {
				for (int j = 0; j < n; j++) {
					Stack<Integer> st = new Stack<>();
					boolean[] visited = new boolean[n];
					for (int k = n - 1; k >= 0; k--) {
						if(arr[k][j] == 0) continue;
						int pushItem = arr[k][j];
						if(!st.isEmpty()) {
							if(!visited[st.size()] && st.peek() == arr[k][j]) {
								pushItem = st.pop() * 2;
								visited[st.size() + 1] = true;
							}
						}
						if(pushItem !=0) st.push(pushItem);
					}
					int size = st.size();
					for(int k=n-size; k<n; k++) {
						arr[k][j] = st.pop();
						result = Math.max(result, arr[k][j]);
					}
					for(int k=n-size-1; k>=0; k--) {
						arr[k][j] = 0;
					}
				}
			}

			//위로 밀 때, 맨 윗행부터 검사
			else {
				for (int j = 0; j < n; j++) {
					Stack<Integer> st = new Stack<>();
					boolean[] visited = new boolean[n];
					for (int k = 0; k < n; k++) {
						if(arr[k][j] == 0) continue;
						int pushItem = arr[k][j];
						if(!st.isEmpty()) {
							if(!visited[st.size()] && st.peek() == arr[k][j]) {
								pushItem = st.pop() * 2;
								visited[st.size() + 1] = true;
							}
						}
						if(pushItem !=0) st.push(pushItem);
					}
					int size = st.size()-1;
					for(int k=size; k>=0; k--) {
						arr[k][j] = st.pop();
						result = Math.max(result, arr[k][j]);
					}
					for(int k=size+1; k<n; k++) {
						arr[k][j] = 0;
					}
				}
			}
			move(cnt+1, arr);
		}
	}
}