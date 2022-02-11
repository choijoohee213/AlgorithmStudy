package com.younghwani.a220204;

import java.util.*;
import java.io.*;

/**
 * @author kyh
 * N : 스위치의 개수
 * status : 스위치의 상태
 * S : 학생의 수
 * gender : 학생의 성별
 * num : 받은 스위치의 번호
 * 남학생의 경우 자기가 받은 스위치 번호의 배수 번호를 모두 변경한다.
 * 여학생의 경우, 자기가 받은 번호 양옆의 번호가 동일하다면 스위치 상태가 다른 범위까지 상태를 변경한다. 동일하지 않다면 받은 스위치 번호만 변경한다. 대칭 파악.
 */
public class Main_bj_1244_스위치켜고끄기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1244.txt"));
		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); 										// 스위치 개수
        int status[] = new int[N]; 									// 스위치의 상태
        for(int i=0; i<N; i++) {
            status[i] = sc.nextInt();
        }
        int S = sc.nextInt(); 										// 학생의 수
        while(S > 0) {
            int gender = sc.nextInt(); 								// 학생의 성별
            int num = sc.nextInt(); 								// 받은 스위치의 번호
            if(gender == 1) { 										// 성별이 남자인 경우
                for(int i=num-1; i<N; i+=num) {
                	status[i] ^= 1;
                }
            }
            else if(gender == 2) { 									// 성별이 여자인 경우
                num = num-1; 										// 받은 스위치의 번호 -> switch's index.
                status[num] = (int) Math.pow((status[num]-1), 2);
                for(int i=1; i<N; i++) {
                    if((num-i) >=0 && (num+i)<N) {					// 범위(index) 확인
                        if(status[num-i] == status[num+i]) {		// 대칭인 경우
                            status[num-i] ^= 1;
                            status[num+i] ^= 1;
                        }
                        else break;
                    }
                }
            }
            S--;
        }
        // 출력 - 한 줄에 20개씩 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(status[i]+" ");
            if((i+1) %20 == 0) {
                sb.setLength(sb.length()-1);
                System.out.println(sb.toString());
                sb.delete(0, sb.length());
            }
            if(i == status.length-1 && (i+1)%20 != 0) {
                sb.setLength(sb.length()-1);
                System.out.print(sb.toString());
            }
        }
	}
}
