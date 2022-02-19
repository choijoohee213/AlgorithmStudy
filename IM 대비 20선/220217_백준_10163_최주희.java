import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[1001][1001];
		int[] width = new int[n+1];

		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for(int j=x; j<x+r; j++) {
				for(int k=y; k<y+c; k++) {
					if (arr[j][k] != 0) {  //이미 색종이가 있다면
						width[arr[j][k]]--;  //있던 색종이의 총 넓이를 하나 감소
					}
					arr[j][k] = i;  //위에 색종이를 덮는다.
					width[i]++;  //덮은 색종이의 총 넓이를 하나 증가
				}
			}
		}

		for(int i=1; i<=n; i++) {
			sb.append(width[i]).append("\n");
		}
		br.close();
		System.out.print(sb);
	}
}