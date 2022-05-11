import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[5][5];
		boolean[][] visited = new boolean[5][5];
		HashMap<Integer, Integer> arrInfo = new HashMap<>();
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arrInfo.put(arr[i][j], i*5+j);  //해시맵에 숫자를 key로, 인덱스를 value로 저장
			}
		}

		int cnt = 0;
		boolean bingoSuccess = false;
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<5; j++) {
				int num = Integer.parseInt(st.nextToken());
				int pos = arrInfo.get(num);
				visited[pos/5][pos%5] = true;  //칸 체크

				if(!bingoSuccess && cnt++>=11) {  //빙고 체크
					int sum = 0;
					for(int x = 0; x<5; x++) {
						int r = 0, c = 0;
						for(int y = 0; y<5; y++) {
							if(visited[x][y]) r++; //가로 체크
							if(visited[y][x]) c++; //세로 체크
						}
						if(r == 5) sum++;
						if(c == 5) sum++;
					}
					//대각선 체크
					if(visited[0][0] && visited[1][1] && visited[2][2] && visited[3][3] && visited[4][4]) sum++;
					if(visited[0][4] && visited[1][3] && visited[2][2] && visited[3][1] && visited[4][0]) sum++;

					if(sum >= 3) bingoSuccess = true;  //빙고 세개이상이면 더이상 cnt를 증가시키지 않도록함.
				}
			}
		}
		System.out.print(cnt);
	}
}