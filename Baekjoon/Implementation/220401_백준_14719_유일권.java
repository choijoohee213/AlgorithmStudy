import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int rst=0;
        int left=0;
        int right=0;
        int[] map = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int N = Integer.parseInt(st.nextToken());
            map[i] = N;
        }

        // 인덱스마다 모이는 빗물 계산
        for (int i = 1; i < W - 1; i++) {       //1번째 기둥과 마지막 기둥의 위치는 제외
            left = 0;
            right = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(map[j], left);      //왼쪽에서 가장 높은 건물
            }
            for (int j = i + 1; j < W; j++) {
                right = Math.max(map[j], right);    //오른쪽에서 가장 높은 건물
            }
            if (map[i] < left && map[i] < right) {
                rst += Math.min(left, right) - map[i];  //둘중 더 낮은값 만큼 추가
            }
        }
        System.out.println(rst);
    }
}