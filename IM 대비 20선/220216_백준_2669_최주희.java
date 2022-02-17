import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] arr = new boolean[101][101];

		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for(int r=x1; r<x2; r++) {
				for(int c=y1; c<y2; c++)
					arr[r][c] = true;
			}
		}

		int cnt = 0;
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++)
				if(arr[i][j]) cnt++;
		}
		System.out.print(cnt);
	}
}