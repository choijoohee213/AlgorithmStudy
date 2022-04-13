import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String str = br.readLine();

			int start = 0, end = str.length()-1;
			boolean palindrome = true;

			while(start < end) {
				if(str.charAt(start++) != str.charAt(end--)) {
					palindrome = false;
					break;
				}
			}
			if(palindrome) {
				sb.append(0).append('\n');
				continue;
			}

			//다른 알파벳 두개를 각각 없애고 다시 팰린드롬 확인해보기
			int j = start - 1;
			int s = 0, e = str.length()-1;
			palindrome = true;
			while(s < e) {
				if(s == j) s++;
				if(e == j) e--;
				if(str.charAt(s++) != str.charAt(e--)) {
					palindrome = false;
					break;
				}
			}
			if(palindrome) {
				sb.append(1).append('\n');
				continue;
			}

			j = end + 1;
			s = 0;
			e = str.length()-1;
			palindrome = true;
			while(s < e) {
				if(s == j) s++;
				if(e == j) e--;
				if(str.charAt(s++) != str.charAt(e--)) {
					palindrome = false;
					break;
				}
			}
			if(palindrome) {
				sb.append(1).append('\n');
				continue;
			}
			sb.append(2).append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}