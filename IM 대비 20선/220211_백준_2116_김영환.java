package com.younghwani.a220206;

import java.util.*;
import java.io.*;

public class Main_bj_2116_주사위쌓기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2116.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][6];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 6; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		// System.out.println(Arrays.deepToString(arr));
		
		// 1번 주사위의 6면에 대한 반복
		// 6면에 대한 반복을 통해 6개의 기둥 case 만들기
		// 기둥으로 만든 위 아래 면을 제외한 4면을 이용해 max 값 구하기 - 각 주사위에서 4면 중 최대값 뽑아서 더함
		int max = 0;
		for (int i = 0; i < 6; i++) {
			int upSide = arr[0][up(i)];
			int tmpMax = maxArr(arr[0], i);
			for (int j = 1; j < N; j++) {
				for (int h = 0; h < 6; h++) {
					if(arr[j][h]==upSide) {
						upSide = arr[j][up(h)];
						tmpMax += maxArr(arr[j], h);
						break;
					}
				}
			}
			if(max<tmpMax) max = tmpMax;
		}
		System.out.println(max);
		sc.close();
	}
	
	static int maxArr(int[] arr, int idx) {
		int max = 0;
		int[] id = new int[4];
		
		switch(idx) {
		case 0:
		case 5:
			id = new int[] {1,2,3,4};
			break;
		case 1:
		case 3:
			id = new int[] {0,2,4,5};
			break;
		case 2:
		case 4:
			id = new int[] {0,1,3,5};
			break;
		}
		// 인덱스가 id에 해당하는 배열 arr 요소 중에서 max 값 구하기
		for (int i : id) {
			if(arr[i]>max) max = arr[i];
		}
		
		return max;
	}
	
	static int up(int idx) {
		int res=0;
		switch(idx) {
		case 0:
			res=5;
			break;
		case 1:
			res=3;
			break;
		case 2:
			res=4;
			break;
		case 3:
			res=1;
			break;
		case 4:
			res=2;
			break;
		case 5:
			res=0;
			break;
		}
		return res;
	}
}
