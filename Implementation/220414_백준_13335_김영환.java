package com.younghwani.a220414;

import java.io.*;
import java.util.*;

public class Main_bj_13335_트럭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        // 트럭 정보 입력
        ArrayDeque<Integer> truck = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) truck.offer(Integer.parseInt(st.nextToken()));

        // 최종 결과(이동 시간), 다리 위 트럭 중량, 다리 위 통행 정보 초기화
        int res = 0, weight = 0; // weight : 현재 다리 위의 트럭 무게
        ArrayDeque<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < W; i++) bridge.offer(0);

        // 트럭 이동
        while (!bridge.isEmpty()) {
            int cur = bridge.poll();
            weight -= cur;
            if(!truck.isEmpty()) { // 이동할 수 있는 트럭 없으면 더 이상 다리 큐에 값 추가 안 함.
                if(truck.peek() + weight > L) { // 다리 제한 중량 초과
                    bridge.offer(0);
                } else { // 트럭 지나갈 수 있는 상태
                    weight += truck.peek();
                    bridge.offer(truck.poll());
                }
            }
            res++;
        }

        // 이동 시간 출력
        System.out.print(res);
        br.close();
    }
}
