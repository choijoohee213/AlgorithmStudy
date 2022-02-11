import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] switches = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for(int i=1; i<=n; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}

		int studentCnt = Integer.parseInt(br.readLine()), gender, num;
		for(int i=0; i<studentCnt; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			gender = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());

			if(gender == 1) {  //남자
				for(int j=num; j<=n; j+=num) {
					switches[j] = Math.abs(switches[j] - 1);
				}
			}
			else if(gender == 2) {  //여자
				switches[num] = Math.abs(switches[num] - 1);
				int j = 1;
				while(num-j >=1 && num+j <= n) {
					if(switches[num-j] != switches[num+j]) break;
					switches[num-j] = Math.abs(switches[num-j] - 1);
					switches[num+j] = Math.abs(switches[num+j] - 1);
					j++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i++ < n) {
			sb.append(switches[i]);
			if(i % 20 == 0) sb.append("\n");
			else sb.append(" ");
		}
		System.out.println(sb);
	}
}