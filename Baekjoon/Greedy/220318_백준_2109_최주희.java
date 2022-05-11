import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = i;
		}
		List<int[]> list = new ArrayList<>();
		int sum = 0;
		boolean[] visited = new boolean[n];
		for (int i = 1; i <= n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[j][1]>=i && !visited[arr[j][2]]) {
					list.add(arr[j]);
					visited[arr[j][2]] = true;
				}
			}
		}
		list.sort((int[] a, int[] b) -> b[0]-a[0]);
		for (int i = n; i >= 1; i--) {
			for(int[] x : list) {
				if(x[1] >= i) {  //p값이 큰것부터 n개 뽑는다
					sum += x[0];
					list.remove(x);
					break;
				}
			}
		}
		br.close();
		System.out.print(sum);
	}
}