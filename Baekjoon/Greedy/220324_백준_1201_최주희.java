import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if(n < m+k-1 || m*k < n) {
			System.out.println(-1);
			return;
		}
		int[] group = new int[m+1];
		group[1] = k;  //첫번째 그룹의 크기
		n -= k;

		if(m-1 > 0) {
			int div = n / (m-1); //나머지 그룹의 기본 크기
			int remain = n % (m-1);  //균등하게 나누어지지 않을 경우 나머지 그룹에 재분배하기

			for (int i = 2; i <=m; i++) {
				if(remain-- > 0) {
					group[i] = div + 1;
				}
				else group[i] = div;
			}
		}

		StringBuilder sb = new StringBuilder();
		int end = 0;
		for (int i = 1; i <= m; i++) {
			end += group[i];
			for (int j = end; j > end-group[i]; j--) {
				sb.append(j).append(" ");
			}
		}
		br.close();
		System.out.print(sb);
	}
}