import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = 0;
		
		while(true) {
			int tmp = n + x;
			int cnt = 0;

			while(tmp > 0) {  //1의 개수가 물병의 개수
				if(tmp % 2 == 1) cnt++;
				tmp /= 2;
			}
			if(cnt <= k) break;
			x++;
		}

		br.close();
		System.out.print(x);
	}
}