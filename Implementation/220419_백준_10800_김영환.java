package com.younghwani.a220419;

import java.io.*;
import java.util.*;
/*
각 플레이어는 자기 공보다 크기가 작고 색이 다른 공을 사로잡는 것을 목표로 한다. 잡은 공의 크기만큼을 점수로 얻는다.
다른 공을 사로잡은 이후에도 본인의 공의 색, 크기를 불변.
 */
public class Main_bj_10800_컬러볼 {
    public static class Ball {
        int id, color, size;
        Ball(int id, int color, int size) {
            this.id = id;
            this.color = color;
            this.size = size;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        ArrayList<Ball> input = new ArrayList<>();

        int[] players = new int[200001];
        int[] colors = new int[200001];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            input.add(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); //id,color,size
        }

        Collections.sort(input, (o1, o2) -> o1.size==o2.size?o1.color-o2.color:o1.size-o2.size); // sort

        int sum = 0;
        for (int i = 0, j = 0; i < N; i++) {
            Ball a = input.get(i); // 본인 공
            Ball b = input.get(j); // 잡을 공
            while (b.size < a.size) { // 잡을 공의 크기가 더 작아야만 한다.
                sum += b.size; // 다른 공을 잡을 수 있는 경우에 대해서, 일단 잡은 공의 크기를 다 더해준다.
                colors[b.color] += b.size; // b의 색상에 따른 크기를 누적합한다.
                b = input.get(++j); // 잡을 수 있는 모든 공의 합을 구하기 위해 다음으로 이동
            }
            players[a.id] = sum - colors[a.color]; // 더 이상 잡을 수 있는 공이 없다면, 누적합에서 같은 색인 경우의 크기를 제외한 값 사용.
        }

        for (int i = 0; i < N; i++) sb.append(players[i]).append("\n");
        System.out.print(sb.toString());
        br.close();
    }
}
