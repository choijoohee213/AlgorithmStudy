import java.io.*;
import java.util.*;

/*
1. 상어가 (0,0)의 물고기 먹고 그 방향으로 시작
<반복>
2. 번호가 작은 물고기부터 순서대로 이동 (번호는 1~16)
3. 물고기는 한칸 이동(빈칸이나 다른 물고기가 있는칸으로, 상어가 있거나 공간의 경계 넘으면 못감)
	- 물고기가 다른 물고기가 있는 칸으로 갈때는 둘이 위치를 swap
	- 물고기가 이동을 못할 경우에는 이동할 수 있을때까지 45도 반시계로 회전 ->그래도 못하면 이동 안함
4. 물고기 이동이 다 끝나면 상어 이동 (방향의 칸으로, 한번에 여러칸 이동 가능)
 	- 상어가 물고기 칸으로 이동하면 그곳의 물고기 먹고 그 방향을 가지게됨
	- 물고기가 없는 칸으로 이동 불가 - 이동할 수 있는 칸이 없으면 공간에서 벗어나 집에감
7.

 */

class Fish {
	public int num, dir;

	public Fish(int num, int dir) {
		this.num = num;
		this.dir = dir;
	}
}

class Main {
	static int result;
	static int[] dx = {-1,-1,0,1,1,1,0,-1}, dy = {0,-1,-1,-1,0,1,1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] arr = new Fish[4][4];
		int[] fishPos = new int[17]; //없으면 -1
		fishPos[0] = -1;
		int sharkX = 0, sharkY = 0, sharkDir, sum = 0;

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				arr[i][j] = new Fish(num, dir);
				fishPos[num] = i*4+j;
			}
		}
		//상어가 0,0부터 출발
		sharkDir = arr[0][0].dir;
		fishPos[arr[0][0].num] = -1;
		sum += arr[0][0].num;
		arr[0][0] = null;
		result = sum;

		dfs(arr, fishPos, sharkX, sharkY, sharkDir, sum);

		br.close();
		System.out.print(result);
	}

	static void dfs(Fish[][] arr, int[] fishPos, int sharkX, int sharkY, int sharkDir, int sum) {
		//번호순으로 물고기 이동
		for (int i = 1; i <= 16; i++) {
			if(fishPos[i] == -1) continue;
			int x = fishPos[i] / 4, y = fishPos[i] % 4;
			Fish fish = new Fish(arr[x][y].num, arr[x][y].dir);  //새롭게 생성하지 않으면 나중에 바뀐 값에 영향을 받는다.

			for (int j = 0; j < 8; j++) {
				int nx = x + dx[fish.dir];
				int ny = y + dy[fish.dir];

				//못움직일 경우 45도 반시계 회전
				if(nx<0 || nx>=4 || ny<0 || ny>=4 || (nx == sharkX && ny == sharkY)) {
					fish.dir = (fish.dir + 1) % 8;
					continue;
				}
				//물고기가 있으면 위치 swap
				Fish tmp = arr[nx][ny];
				arr[nx][ny] = fish;
				arr[x][y] = tmp;
				fishPos[fish.num] = nx*4+ny;
				if(tmp != null)
					fishPos[tmp.num] = x*4+y;
				break;
			}
		}

		//상어 이동
		for (int i = 1; i <= 3; i++) {
			int nx = sharkX + dx[sharkDir] * i;
			int ny = sharkY + dy[sharkDir] * i;

			if(nx<0 || nx>=4 || ny<0 || ny>=4 || arr[nx][ny] == null) continue;
			//배열 새롭게 생성
			Fish[][] map = new Fish[4][4];
			for (int j = 0; j < 4; j++) {
				System.arraycopy(arr[j], 0, map[j], 0, 4);
			}
			int[] pos = fishPos.clone();
			pos[arr[nx][ny].num] = -1;  //물고기 먹음
			map[nx][ny] = null;
			dfs(map, pos, nx, ny, arr[nx][ny].dir, sum + arr[nx][ny].num);
		}
		result = Math.max(result, sum);
	}
}