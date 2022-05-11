import java.io.*;
import java.util.*;

class Main {
	static int n, result = Integer.MAX_VALUE;
	static char[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		calMinValue(0);
		br.close();
		System.out.print(result);
	}

	static void calMinValue(int idx){
		if(idx == n){
			int cnt = 0;
			for(int i = 0; i<n; i++){
				int tCnt = 0;
				for(int j=0; j<n; j++){
					if(arr[i][j] == 'T'){  //행 체크
						tCnt++;
					}
				}
				cnt += Math.min(tCnt, n-tCnt);  //뒤집은것과 안뒤집은것 중 최소값
			}
			result = Math.min(cnt, result);
			return;
		}
		calMinValue(idx+1);
		for(int i = 0 ; i < n; i ++){  //열 변경
			arr[i][idx] = arr[i][idx] == 'H'?'T':'H';
		}
		calMinValue(idx+1);
	}
}