import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()), result = Integer.MAX_VALUE;
		int total = 0;
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				total += arr[i][j];
			}
		}

		//x,y,d1,d2 선정
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				for (int d1 = 1; d1 < n; d1++) {
					for (int d2 = 1; d2 < n; d2++) {
						if(x+d1+d2 >= n) continue;
						if(y-d1<0 || y+d2 >= n) continue;
						int[] people = new int[6];
						boolean[][] edge = new boolean[n][n];
						int sum = total;

						//경계선 설정
						for (int i = 0; i <= d1; i++) {
							edge[x + i][y - i] = true;
							edge[x + d2 + i][y + d2 - i] = true;
						}
						for (int i = 0; i <= d2; i++) {
							edge[x + i][y + i] = true;
							edge[x + d1 + i][y - d1 + i] = true;
						}

						//1~5번 선거구 나누기
						for (int k = 0; k < x+d1; k++) {
							for (int l = 0; l <= y; l++) {
								if(edge[k][l]) break;
								people[1] += arr[k][l];
								sum -= arr[k][l];
							}
						}
						for (int k = 0; k <= x+d2; k++) {
							for (int l = n-1; l > y; l--) {
								if(edge[k][l]) break;
								people[2] += arr[k][l];
								sum -= arr[k][l];
							}
						}
						for (int k = x+d1; k < n; k++) {
							for (int l = 0; l < y-d1+d2; l++) {
								if(edge[k][l]) break;
								people[3] += arr[k][l];
								sum -= arr[k][l];
							}
						}
						for (int k = x+d2+1; k < n; k++) {
							for (int l = n-1; l >= y-d1+d2; l--) {
								if(edge[k][l]) break;
								people[4] += arr[k][l];
								sum -= arr[k][l];
							}
						}
						//5번 선거구
						people[5] = sum;

						int maxValue = 0, minValue = Integer.MAX_VALUE;
						for (int k = 1; k <= 5; k++) {
							maxValue = Math.max(people[k], maxValue);
							minValue = Math.min(people[k], minValue);
						}
						result = Math.min(maxValue - minValue, result);
					}
				}
			}
		}
		br.close();
		System.out.print(result);
	}
}