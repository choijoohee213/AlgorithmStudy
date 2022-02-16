package com.younghwani.a220212;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
테두리로만 이동한다.
인접한 변은 한가지 방법으로 이동 가능하다(최소 거리)
평행한 변은 두가지 route를 계산하고, 비교하여 최소 거리를 사용한다.
 */
public class Main_bj_2564_경비원 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2564.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(br.readLine());
        int[][] arr = new int[T][2];
        for (int i = 0; i < T; i++) arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.deepToString(arr));
        st = new StringTokenizer(br.readLine(), " ");
        int direction = Integer.parseInt(st.nextToken());
        int location = Integer.parseInt(st.nextToken());
        //System.out.println("direction : " + direction + ", location : " + location);

        int sum = 0;
        for (int tc = 0; tc < T; tc++) {
            //System.out.println("상점" + tc + "방향 : " + arr[tc][0] + ", 위치 : " + arr[tc][1]);
            int sd = arr[tc][0]; // 상점의 방향
            int sl = arr[tc][1]; // 상점의 위치
            if (direction == sd) sum += Math.abs(location - sl); // 같은 방향이면 위치 차이만 구함
            else if(direction==1) {
                if (sd == 2) sum += Math.min(location + H + sl, (W - location) + H + (W - sl));
                else if (sd == 3) sum += location + sl;
                else if (sd == 4) sum += (W - location) + sl;
            } else if(direction==2) {
                if (sd == 1) sum += Math.min(location + H + sl, (W - location) + H + (W - sl));
                else if (sd == 3) sum += location + (H - sl);
                else if (sd == 4) sum += (W - location) + (H - sl);
            } else if (direction == 3) {
                if (sd == 1) sum += location + sl;
                else if (sd == 2) sum += (H - location) + sl;
                else if (sd == 4) sum += Math.min(location + W + sl, (H - location) + W + (H - sl));
            } else if (direction == 4) {
                if (sd == 1) sum += location + (W - sl);
                else if (sd == 2) sum += (H - location) + (W - sl);
                else if (sd == 3) sum += Math.min(location + W + sl, (H - location) + W + (H - sl));
            }
        }
        System.out.println(sum);
        br.close();
    }
}
