import java.util.*;
import java.io.*;

class Main {
	static char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	static char[] sign;
	static boolean[] visited;
	static int k;
	static String result_min = "9999999999", result_max = "0";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sign = new char[k];
		visited = new boolean[10];

		for (int i = 0; i < k; i++) {
			sign[i] = st.nextToken().charAt(0);
		}
		permutation(0, "");
		System.out.println(result_max);
		System.out.println(result_min);
	}

	static void permutation(int cnt, String str) {
		if (!check(str))
			return;
		if (cnt == k+1) {
			if (Long.parseLong(result_min) > Long.parseLong(str)) {
				result_min = str;
			}
			if (Long.parseLong(result_max) < Long.parseLong(str)) {
				result_max = str;
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			permutation(cnt + 1, str + nums[i]);
			visited[i] = false;
		}
	}

	static boolean check(String str) {
		if (str.length() <= 1)
			return true;
		boolean flag = true;
		for (int i = 0; i < str.length() - 1; i++) {
			if ((sign[i] == '<' && (str.charAt(i) - '0') > (str.charAt(i + 1) - '0'))
				|| (sign[i] == '>' && (str.charAt(i) - '0') < (str.charAt(i + 1) - '0'))) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}