import java.io.*;
import java.util.*;

class Main {
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9][9];
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursive(0, 0);
		br.close();
	}

	static void recursive(int x, int y) {
		if(y == 9) {
			recursive(x+1, 0);  //다음 행
			return;
		}
		if(x == 9) { //모든 행 돌았으면 출력
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
			System.exit(0);
		}
		if(arr[x][y] == 0) {  //칸이 비어있다면
			for(int i=1; i<=9; i++) {
				if(check(x,y,i)) {  //i를 현재 칸에 넣을 수 있는지 검사
					arr[x][y] = i;
					recursive(x,y+1);
					arr[x][y] = 0;
				}
			}
			return;
		}
		recursive(x, y+1);
	}

	static boolean check(int x, int y, int num) {
		for(int i=0; i<9; i++) {
			if(arr[x][i] == num || arr[i][y] == num) return false;  //행,열 체크
		}
		//3*3 체크
		int sx = x / 3 * 3;
		int sy = y / 3 * 3;
		for(int i = sx; i<sx+3; i++) {
			for(int j=sy; j<sy+3; j++) {
				if(arr[i][j] == num) return false;
			}
		}
		return true;
	}
}