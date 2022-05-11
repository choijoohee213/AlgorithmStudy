import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n*n+1][5];
		int[][] map = new int[n][n];

		for (int i = 1; i <= n*n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n*n; i++) {
			int num = arr[i][0];

			//좋아하는 학생 수, 빈칸 수, 인덱스
			Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) {
						return o1[2] - o2[2];
					}
					return o2[1] - o1[1];
				}
				return o2[0] - o1[0];
			});

			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(map[j][k] != 0) continue;
					int likeCnt = 0, blankCnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nx = j + dx[dir];
						int ny = k + dy[dir];

						if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
						if(map[nx][ny] != 0 && (map[nx][ny] == arr[i][1] || map[nx][ny] == arr[i][2]
							|| map[nx][ny] == arr[i][3] || map[nx][ny] == arr[i][4])) {  //인접 칸 중 좋아하는 학생이 있다면
							likeCnt++;
						}
						else if(map[nx][ny] == 0) { //인접 칸 중 빈칸
							blankCnt++;
						}
					}
					pq.offer(new int[]{likeCnt, blankCnt, j*n+k});
				}
			}
			int idx = pq.poll()[2];
			map[idx/n][idx%n] = num;
		}

		//학생 만족도 총합 구하기
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cnt = 0, idx = 0;
				for (int k = 1; k <= n*n; k++) {  //해당 번호 인덱스 구하기
					if(arr[k][0] == map[i][j]){
						idx = k;
						break;
					}
				}
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(map[nx][ny] == arr[idx][1] || map[nx][ny] == arr[idx][2]
						|| map[nx][ny] == arr[idx][3] || map[nx][ny] == arr[idx][4]) {  //인접 칸 중 좋아하는 학생이 있다면
						cnt++;
					}
				}
				sum += Math.pow(10, cnt) / 10;
			}
		}
		br.close();
		System.out.print(sum);
	}
}