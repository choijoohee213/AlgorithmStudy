import java.util.*;
import java.io.*;

public class Main {
	static int N, min;
	static int[][] inputs;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		inputs = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; st.hasMoreTokens(); j++) {
				inputs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(min);
		br.close();
	}
	
	static void dfs(int n, int depth) {
		if(depth==N/2) {
			int usedSum = 0; // 스타트팀
			int unusedSum = 0; // 링크팀
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					if(visited[i] && visited[j]) usedSum += inputs[i][j] + inputs[j][i];
					else if(!visited[i] && !visited[j]) unusedSum += inputs[i][j] + inputs[j][i];
				}
			}
			int diff;
			if((diff = Math.abs(usedSum-unusedSum)) == 0) { // 0보다 작은 값 없으므로 출력 후 시스템종료
				System.out.println(diff);
				System.exit(0);
			}
			min = Math.min(min, diff);
			return;
		}
		for (int i = n; i < N; i++) {
			if(visited[i] || depth==i) continue;
			visited[i] = true;
			dfs(i+1, depth+1);
			visited[i] = false;
		}
	}
}