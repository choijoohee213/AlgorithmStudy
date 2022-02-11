package com.younghwani.a220206;

import java.util.*;
import java.io.*;

/**
 * @author kyh
 * 아홉난쟁이가 아니라 일곱난쟁이임.
 * 난쟁이 키의 합이 100이됨.
 */
public class Main_bj_2309_일곱난쟁이 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2309.txt"));
		Scanner sc = new Scanner(System.in);
		int[] mini = new int[9];
		int total = 0;
		for (int i = 0; i < mini.length; i++) {
			mini[i] = sc.nextInt();
			total += mini[i];
		}
		Arrays.sort(mini);
		loop: for (int i = 0; i < mini.length; i++) {
			for (int j = i+1; j < mini.length; j++) {
				if(total - mini[i] - mini[j] == 100) {
					for (int h = 0; h < mini.length; h++) {
						if(h!=i && h!=j) System.out.println(mini[h]);
					}
					break loop;
				}
			}
		}
		sc.close();
	}
}
