import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st2.nextToken());
			int[][] arr = new int[2][5];

			for(int j=0; j<a; j++) arr[0][Integer.parseInt(st1.nextToken())]++;
			for(int j=0; j<b; j++) arr[1][Integer.parseInt(st2.nextToken())]++;

			for(int j=4; j>=1; j--) {
				if(arr[0][j] == arr[1][j]) {
					if(j == 1) sb.append("D");
					continue;
				}
				if(arr[0][j] > arr[1][j]) sb.append("A");
				else if(arr[0][j] < arr[1][j]) sb.append("B");
				break;
			}
			sb.append("\n");
		}
		br.close();
		System.out.print(sb);
	}
}