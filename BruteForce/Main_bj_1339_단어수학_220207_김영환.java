package com.younghwani.a220207;

import java.util.*;
import java.io.*;
public class Main_bj_1339_단어수학_220207_김영환 {
	static int N, res = Integer.MIN_VALUE;
	static String[] inputs;
	static List<Character> alpha = new ArrayList<>();
	static int[] val;
	static boolean[] visited = new boolean[10];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1339.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		inputs = new String[N];
		for (int i = 0; i < N; i++) {
			inputs[i] = br.readLine();
			for (int j = 0; j < inputs[i].length(); j++) {
				if (!alpha.contains(inputs[i].charAt(j))) alpha.add(inputs[i].charAt(j));
			}
		}
		val = new int[alpha.size()];
		dfs(0);
		System.out.println(res);
		br.close();
	}

	private static void dfs(int depth) {
		if (depth == alpha.size()) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				int num = 0;
				for (int j = 0; j < inputs[i].length(); j++) {
					num *= 10;
					num += val[ alpha.indexOf(inputs[i].charAt(j)) ];
				}
				sum += num;
			}
			res = Math.max(res, sum);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			val[depth] = i;
			dfs(depth + 1);
			val[depth] = 0;
			visited[i] = false;
		}
	}
}

// 아무리 봐도 아래 코드가 왜 틀렸다고 하는지 모르겠다. 시간이 많을 때 차근차근 살펴봐야겠다.
/**
 * @author kyh
 * 자리수 높은 알파벳부터 9 부여
 * (각 알파벳에 부여한 숫자 * 각 알파벳의 빈도)의 총합
 */
/* 틀림
public class Main_bj_1339_단어수학 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1339.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] inputs = new String[N]; // 10^출현한 알파벳의 자리
		int[] alpha = new int[26];
		for (int i = 0; i < N; i++) inputs[i] = sc.next();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < inputs[i].length(); j++) {
				alpha[inputs[i].charAt(0)-'A'] += (int) Math.pow(10, inputs[i].length()-j-1);
			}
		}
		Arrays.sort(alpha);
		int sum = 0;
		int idx = alpha.length-1;
		for (int i = 9; i >= 0; i--) sum += alpha[idx--] * i;
		System.out.println(sum);
		sc.close();
	}
}
 */

/* 틀린 코드
public class Main_bj_1339_단어수학 {
	static int[][] alpha = new int[26][2]; // [각 알파벳의 출현 빈도수, 출현한 알파벳의 자리]

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_1339.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < c.length; j++) {
				alpha[c[j]-'A'][0] += Math.pow(10, c.length-j-1); // 각 알파벳의 출현 빈도수
				if(alpha[c[j]-'A'][1] < c.length-j) alpha[c[j]-'A'][1] = c.length-j; // 출현한 알파벳의 자리
			}
		}

		// alpha를 o2 기준으로 정렬 후, 9 * o1 하여 결과 출력하기
		Arrays.sort(alpha, (o1, o2) -> {
			if(o1[1]==o2[1]) {
				return Integer.compare(o1[0], o2[0]);
			} else {
				return Integer.compare(o1[1], o2[1]);
			}
		});

		int sum = 0;
		int idx = alpha.length-1;
		for (int i = 9; i >= 0; i--) {
			sum += alpha[idx--][0] * i;
		}

		System.out.print(sum);

		br.close();
	}
}
 */