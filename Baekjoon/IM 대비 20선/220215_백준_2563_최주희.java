import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), r, c, cnt = 0;
		boolean[][] map = new boolean[100][100];

		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			for(int j=r; j<r+10; j++) {
				for(int k=c; k<c+10; k++)
					map[j][k] = true;
			}
		}

		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]) cnt++;
			}
		}
		System.out.print(cnt);
	}
}