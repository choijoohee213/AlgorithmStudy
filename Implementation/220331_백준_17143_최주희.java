import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int sum = 0;
		int[] dx = {-1,1,0,0}, dy = {0,0,1,-1};
		int[] changeDir = {1,0,3,2};
		int[][][] arr = new int[c+1][r+1][c+1];
		Map<Integer, int[]> shark = new LinkedHashMap<>();

		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			shark.put(i, new int[]{a,b,s,d,z});  //x,y,속력,이동방향,크기
			arr[0][a][b] = i;
		}

		for(int i=1; i<=c; i++) {  //1.오른쪽으로 한칸 이동
			if(shark.size() == 0) break;
			for(int j=1; j<=r; j++) {  //2.같은열의 상어중 가장가까운 상어 잡기
				if(arr[i-1][j][i] == 0) continue;
				sum += shark.get(arr[i-1][j][i])[4];
				shark.remove(arr[i-1][j][i]);
				arr[i-1][j][i] = 0;
				break;
			}

			//3.상어 이동
			List<Integer> list = new ArrayList<>();
			for(Integer key : shark.keySet()) {
				int[] info = shark.get(key);
				//속도만큼 이동
				int nx = info[0] + dx[info[3]] * info[2];
				int ny = info[1] + dy[info[3]] * info[2];

				while(nx<1 || nx>r || ny<1 || ny>c) {  //경계밖에나감 -> 방향 바꿔
					info[3] = changeDir[info[3]];
					if(nx<1) nx = 1 + dx[info[3]] * (1 - nx);
					else if(nx>r) nx = r + dx[info[3]] * (nx - r);
					else if(ny<1) ny = 1 + dy[info[3]] * (1 - ny);
					else if(ny>c) ny = c + dy[info[3]] * (ny - c);
				}
				//원래있던 상어가 먼저 움직였던 상어일 경우
				if(arr[i][nx][ny] != 0) {
					if(shark.get(arr[i][nx][ny])[4] > info[4]) {  //있던 상어가 더 클경우 잡아먹힘
						list.add(key);
						continue;
					}
					else {  //잡아먹음
						list.add(arr[i][nx][ny]);
					}
				}
				arr[i][nx][ny] = key;
				info[0] = nx;
				info[1] = ny;
				shark.put(key, info);
			}
			for (Integer key : list) shark.remove(new Integer(key));
		}
		System.out.print(sum);
	}
}

/* 상어 상태 값에 죽었나 안죽었나 저장
import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int sum = 0, size = m;
		int[] dx = {-1,1,0,0}, dy = {0,0,1,-1};
		int[] changeDir = {1,0,3,2};
		int[][][] arr = new int[c+1][r+1][c+1];
		Map<Integer, int[]> shark = new LinkedHashMap<>();

		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			shark.put(i, new int[]{a,b,s,d,z,1});  //x,y,속력,이동방향,크기
			arr[0][a][b] = i;
		}

		for(int i=1; i<=c; i++) {  //1.오른쪽으로 한칸 이동
			if(size == 0) break;
			for(int j=1; j<=r; j++) {  //2.같은열의 상어중 가장가까운 상어 잡기
				if(arr[i-1][j][i] == 0 || shark.get(arr[i-1][j][i])[5] == 0) continue;
				sum += shark.get(arr[i-1][j][i])[4];
				shark.get(arr[i-1][j][i])[5] = 0;
				size--;
				break;
			}

			//3.상어 이동
			for(Integer key : shark.keySet()) {
				int[] info = shark.get(key);
				//상어가 잡아먹히지 않았고 속력이 있어야함
				if(info[5] == 0) continue;

				//속도만큼 이동
				int nx = info[0] + dx[info[3]] * info[2];
				int ny = info[1] + dy[info[3]] * info[2];

				while(nx<1 || nx>r || ny<1 || ny>c) {  //경계밖에나감 -> 방향 바꿔
					info[3] = changeDir[info[3]];
					if(nx<1) nx = 1 + dx[info[3]] * (1 - nx);
					else if(nx>r) nx = r + dx[info[3]] * (nx - r);
					else if(ny<1) ny = 1 + dy[info[3]] * (1 - ny);
					else if(ny>c) ny = c + dy[info[3]] * (ny - c);
				}
				//원래있던 상어가 먼저 움직였던 상어일 경우
				if(arr[i][nx][ny] != 0) {
					if(shark.get(arr[i][nx][ny])[4] > info[4]) {  //있던 상어가 더 클경우 잡아먹힘
						size--;
						info[5] = 0;
						continue;
					}
					else {  //잡아먹음
						shark.get(arr[i][nx][ny])[5] = 0;
						size--;
					}
				}
				arr[i][nx][ny] = key;
				info[0] = nx;
				info[1] = ny;
				shark.put(key, info);
			}
		}
		System.out.print(sum);
	}
}

 */