import java.io.*;
import java.util.*;

class Main {
	static int n, result = 0;
	static int[] queen;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		queen = new int[n];
		nqueen(0);
		br.close();
		System.out.print(result);
	}

	static void nqueen(int idx) {
		if(idx == n) {
			result++;
			return;
		}
		for(int i=0; i<n; i++) {
			queen[idx] = i;
			boolean flag = true;
			for(int j=0; j<idx; j++) {  //지금 idx행에 놓은 퀸이 영향 받는지 체크
				if(queen[idx] == queen[j] || Math.abs(idx-j) == Math.abs(queen[idx]-queen[j])) {
					flag = false;  //같은 행에 존재하거나 대각선에 놓여있을 경우
					break;
				}
			}
			if(flag) nqueen(idx+1);
		}
	}
}