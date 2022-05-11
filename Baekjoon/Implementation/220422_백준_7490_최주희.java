import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			recursive(2, n, new StringBuilder().append(1));
			System.out.println();
		}
		br.close();
	}

	static void recursive(int num, int n, StringBuilder sb) {
		if(num == n+1) {
			int sum = 0, prev = 1, multi = 1;
			for (int i = 1; i < sb.length(); i++) {
				char c = sb.charAt(i);
				if(c == '+') {
					sum += prev * multi;
					multi = 1;
				} else if(c == '-') {
					sum += prev * multi;
					multi = -1;
				} else if(c == ' '){
					prev = prev * 10 + (sb.charAt(i+1) - '0');
					i++;
				} else {
					prev = c - '0';
				}
			}
			sum += prev * multi;
			if(sum == 0) System.out.println(sb);
			return;
		}
		recursive(num+1, n, new StringBuilder(sb).append(' ').append(num));
		recursive(num+1, n, new StringBuilder(sb).append('+').append(num));
		recursive(num+1, n, new StringBuilder(sb).append('-').append(num));
	}
}