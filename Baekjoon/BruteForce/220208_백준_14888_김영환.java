package com.younghwani.a220208;

import java.util.*;
import java.io.*;
public class Main_bj_14888_연산자끼워넣기 {
	static int N, max, min;
	static int[] inputs;
	static int[] deli = new int[4];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14888.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		// 숫자 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(Arrays.toString(inputs));
		
		// 연산자 입력받기
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			deli[i] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(Arrays.toString(deli));
		
		dfs(inputs[0], 1); // 곱셈, 나눗셈이 있으니 depth 0이 아닌 1부터 시작함.(depth==0의 n값이 0이므로)
		System.out.println(max);
		System.out.println(min);
		
		br.close();
	}
	
	static void dfs(int n, int depth) {
		if(depth==N) {
			max = Math.max(max, n);
			min = Math.min(min, n);
			return;
		}
		for (int i = 0; i < deli.length; i++) {
			if(deli[i] > 0) {
				deli[i] -= 1;
				if(i==0) {			// +
					dfs(n + inputs[depth], depth+1);
				} else if(i==1) {	// -
					dfs(n - inputs[depth], depth+1);
				} else if(i==2) {	// *
					dfs(n * inputs[depth], depth+1);
				} else if(i==3) {	// /
					dfs(n / inputs[depth], depth+1);
				}
				deli[i] += 1;
			}
		}
	}
}
