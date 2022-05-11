import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine()), x, y;
		int[][] arr = new int[k][2];

		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		int result = 0;
		for(int i=0; i<k; i++) {
			if(arr[i][0] == 1) {
				if(x == 1) {
					result += Math.abs(y - arr[i][1]);
				}
				else if(x == 2) {
					result += Math.min(y+m+arr[i][1], n-y+m+n-arr[i][1]);
				}
				else if(x == 3) {
					result += y + arr[i][1];
				}
				else if(x == 4) {
					result += n-arr[i][1] + y;
				}
			}
			else if(arr[i][0] == 2) {
				if(x == 1) {
					result += Math.min(y+m+arr[i][1], n-y+m+n-arr[i][1]);
				}
				else if(x == 2) {
					result += Math.abs(y - arr[i][1]);
				}
				else if(x == 3) {
					result += m-y+arr[i][1];
				}
				else if(x == 4) {
					result += n-y+m-arr[i][1];
				}
			}
			else if(arr[i][0] == 3) {
				if(x == 1) {
					result += y+arr[i][1];
				}
				else if(x == 2) {
					result += y+m-arr[i][1];
				}
				else if(x == 3) {
					result += Math.abs(y-arr[i][1]);
				}
				else if(x == 4) {
					result += Math.min(y+arr[i][1]+n, m-y+m-arr[i][1]+n);
				}
			}
			else if(arr[i][0] == 4) {
				if(x == 1) {
					result += n-y+arr[i][1];
				}
				else if(x == 2) {
					result += n-y+m-arr[i][1];
				}
				else if(x == 3) {
					result += Math.min(y+arr[i][1]+n, m-y+m-arr[i][1]+n);
				}
				else if(x == 4) {
					result += Math.abs(y-arr[i][1]);
				}

			}
		}
		System.out.print(result);
	}
}