import java.io.*;
import java.util.*;

class Main {
	static int n,m, result = Integer.MAX_VALUE;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		char[][] arr = new char[n][m];
		List<Integer> pos = new ArrayList<>();
		for(int i=0; i<n; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 'o') {
					pos.add(i);
					pos.add(j);
				}
			}
		}
		dfs(0, pos.get(0), pos.get(1), pos.get(2), pos.get(3), arr);
		br.close();
		System.out.print(result>10?-1:result);
	}

	static void dfs(int cnt, int x1, int y1, int x2, int y2, char[][] arr) {
		if(cnt>10) return;
		for(int i=0; i<4; i++) {
			int nx1 = x1 + dx[i];
			int ny1 = y1 + dy[i];
			int nx2 = x2 + dx[i];
			int ny2 = y2 + dy[i];

			if((nx1<0 || nx1>=n || ny1<0 || ny1>=m) && (nx2<0 || nx2>=n || ny2<0 || ny2>=m)) //동전이 두개 다 떨어진다면 넘어가기
				continue;
			boolean flag1 = true, flag2 = true;
			if(nx1<0 || nx1>=n || ny1<0 || ny1>=m) flag1 = false;  //동전1이 떨어진다면
			else if(arr[nx1][ny1] == '#') {
				nx1 = x1;
				ny1 = y1;
			}

			if(nx2<0 || nx2>=n || ny2<0 || ny2>=m) flag2 = false;  //동전2가 떨어진다면
			else if(arr[nx2][ny2] == '#') {
				nx2 = x2;
				ny2 = y2;
			}

			if(flag1 && flag2 && arr[nx1][ny1] == '#' && arr[nx2][ny2] == '#') continue;
			if(!flag1 || !flag2) {  //동전이 하나라도 떨어진다면
				result = Math.min(cnt+1, result);  //최소값 갱신
				return;
			}
			dfs(cnt+1, nx1, ny1, nx2, ny2, arr);
		}
	}
}