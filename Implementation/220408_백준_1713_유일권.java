//https://www.acmicpc.net/problem/1713
//Solved : 22/04/08

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = 0;
        int now = 0;
        int idx = 0;
        int total = Integer.parseInt(br.readLine());
        int[] student = new int[total];
        int[] recommend = new int[total];
        int[] time = new int[total];

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            now = Integer.parseInt(st.nextToken());
            idx = 0;
            for (int j = 0; j < total; j++) {
                if (student[j] == 0 || student[j] == now) {// 사진틀이 비어있거나, 등록된 경우 인덱스 찾고 탐색 끝
                    idx = j;
                    break;
                }
                if (recommend[idx] > recommend[j] || (recommend[idx] == recommend[j] && time[idx] > time[j])) {
                    // 추천 횟수가 더 적거나, 횟수가 같고 시간이 더 빠른 경우
                    idx = j;
                }
            }
            if (student[idx] != now) {
                student[idx] = now;
                recommend[idx] = 0;
                time[idx] = i;
            }
            recommend[idx]++;
        }
        Arrays.sort(student);
        for (int i : student) {
            if (i != 0){
                bw.write(Integer.toString(i));
                bw.write(" ");
            }
        }
        bw.close();
        br.close();
    }
}

