import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] num = new int[10];
		int sum = 0;
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			num[s.charAt(i)-'0']++;
			sum += s.charAt(i)-'0';
		}
		br.close();
		if(sum % 3 == 0 && num[0]!=0) {
			for (int i = 9; i >=0 ; i--) {
				for (int j = 0; j < num[i]; j++) {
					System.out.print(i);  //큰거부터 출력
				}
			}
			return;
		}
		System.out.print(-1);
	}
}