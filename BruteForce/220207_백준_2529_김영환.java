package com.younghwani.a220207;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] visited = new boolean[10];
	static char[] inputs;
	static List<String> res = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2529.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new char[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) inputs[i] = st.nextToken().charAt(0);
		
		dfs("", 0);
		Collections.sort(res);
		System.out.println(res.get(res.size()-1));
		System.out.println(res.get(0));
		br.close();
	}
	
	static void dfs(String num, int depth) {
		if(depth == N+1) {
			res.add(num);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if(visited[i]) continue;
			if(depth==0 || compare(Character.getNumericValue(num.charAt(depth-1)), i, inputs[depth-1])) {
				visited[i] = true;
				dfs(num+i, depth+1);
				visited[i] = false;
			}
		}
	}
	
	static boolean compare(int a, int b, char c) {
		if(c=='<') return a<b;
		else return a>b;
	}
}
