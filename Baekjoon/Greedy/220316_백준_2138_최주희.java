import java.io.*;
import java.util.*;
import java.util.stream.Stream;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int cnt1=0, cnt2=0;
		char[] tmp = br.readLine().toCharArray();
		int[] a1 = new int[n];
		int[] a2 = new int[n];
		int[] b = new int[n];
		for(int i=0; i<n; i++) {
			a1[i] = tmp[i] - '0';
			a2[i] = tmp[i] - '0';
		}
		tmp = br.readLine().toCharArray();
		for(int i=0; i<n; i++) {
			b[i] = tmp[i] - '0';
		}

		//a2는 0번을 누른상태로 시작
		a2[0] = Math.abs(a2[0]-1);
		a2[1] = Math.abs(a2[1]-1);
		cnt2++;

		for (int i = 1; i < n; i++) {
			if (a1[i-1] != b[i-1]) {  //i-1을 결정하는건 i
				cnt1++;
				for (int j = i - 1; j <= i + 1; j++) {
					if(j<n) {
						a1[j] = Math.abs(a1[j]-1);
					}
				}
			}
			if (a2[i-1] != b[i-1]) {
				cnt2++;
				for (int j = i - 1; j <= i + 1; j++) {
					if(j<n) {
						a2[j] = Math.abs(a2[j]-1);
					}
				}
			}
		}

		int result = Integer.MAX_VALUE;
		boolean flag1=true, flag2=true;
		for (int i = 0; i < n; i++) {
			if(a1[i] != b[i]) flag1 = false;
			if(a2[i] != b[i]) flag2 = false;
		}
		if(flag1) result = cnt1;
		if(flag2) result = Math.min(result, cnt2);

		br.close();
		System.out.print(result==Integer.MAX_VALUE? -1 : result);
	}
}