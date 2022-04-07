import java.io.*;
import java.util.*;

/**
 * 1. 1~k번 말을 순서대로 이동(각 말은 이동방향을 가지고 있음)
 * 2. 말이 4개이상 쌓이면 게임 종료
 * 3. 흰색 칸으로 이동 -> 이미 말이 있으면 그 위에 다 쌓음 (여러개는 모두 같이 이동)
 * 4. 빨간색 칸으로 이동 -> 여러개가 같이 이동하는데 쌓여있는 순서를 반대로 바꿔서 올려놓자
 * 5. 파란색 칸으로 이동 -> 말의 이동방향을 반대로 하고 한칸 이동 (바꾼 후 이동하려는 칸이 파란색일 경우 가만히 있음)
 * 6. 체스판 벗어나는 경우 파란색칸과 동일하게
 */

class Point {
	public int color;
	public List<Integer> horses = new ArrayList<>();
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};
		int[] reverseDir = {1,0,3,2};
		int[][] horseInfo = new int[k+1][3];
		Point[][] points = new Point[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				points[i][j] = new Point();
				points[i][j].color = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			horseInfo[i][0] = Integer.parseInt(st.nextToken());
			horseInfo[i][1] = Integer.parseInt(st.nextToken());
			horseInfo[i][2] = Integer.parseInt(st.nextToken())-1;
			points[horseInfo[i][0]][horseInfo[i][1]].horses.add(i);
		}
		br.close();

		for (int turn = 1; turn <= 1000; turn++) {
			for (int i = 1; i <= k; i++) {
				int x = horseInfo[i][0], y = horseInfo[i][1], dir = horseInfo[i][2];

				int nx = x + dx[dir];
				int ny = y + dy[dir];

				//이동하려는 말 위에 올려져있는 말들 리스트에 넣기
				List<Integer> horsesToMove = new ArrayList<>();
				int idx = -1;
				for (int j=0; j<points[x][y].horses.size(); j++) {
					Integer horse = points[x][y].horses.get(j);
					if(idx == -1 && horse == i) {
						idx = j;
						horsesToMove.add(horse);
					}
					else if(idx != -1){
						horsesToMove.add(horse);
					}
				}

				//체스판을 벗어나는 경우 or 파란색 -> 반대 방향으로 한칸
				if(nx<1 || nx>n || ny<1 || ny>n || points[nx][ny].color == 2) {
					horseInfo[i][2] = reverseDir[dir];
					dir = horseInfo[i][2];
					nx = x + dx[dir];
					ny = y + dy[dir];
				}

				//다시 체스판을 벗어나는 경우 or 파란색 -> 가만히 있는다
				if(nx<1 || nx>n || ny<1 || ny>n || points[nx][ny].color == 2) continue;

				//흰색 or 빨간색 : 현재 칸 -> 다음 칸으로 말들 이동
				if(points[nx][ny].color == 1) //빨간색이면 뒷순서부터
					Collections.reverse(horsesToMove);
				for (Integer horse : horsesToMove) {
					horseInfo[horse][0] = nx;
					horseInfo[horse][1] = ny;
					points[nx][ny].horses.add(horse);
					if(points[nx][ny].horses.size() >= 4) {  //4개 이상 말이 쌓이면 게임 종료
						System.out.print(turn);
						return;
					}
				}
				//현재 칸->다음 칸 이동한 말들 현재 칸 리스트에서 제거
				if (points[x][y].horses.size() > idx) {
					points[x][y].horses.subList(idx, points[x][y].horses.size()).clear();
				}
			}
		}
		System.out.print(-1);
	}
}