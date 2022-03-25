import java.io.*;
import java.util.*;

//톱니바퀴 A와 B의 맞닿은 톱니의 극이 다르면, B는 A의 회전방향과 반대방향으로 회전
//같으면 회전 안함, B는 A가 회전하지 않았다면 회전 안함
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[4][8];
		int[] idx = new int[4];  //12시 방향의 인덱스 저장. 시계 회전이면 -1, 반시계면 +1

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			int[][] value = new int[4][2]; //left, right값 저장
			for (int j = 0; j < 4; j++) {
				value[j][0] = arr[j][(idx[j]+6) % 8];
				value[j][1] = arr[j][(idx[j]+2) % 8];
			}

			int leftDir = dir, rightDir = dir;
			idx[num] = (idx[num] + dir * -1) % 8;
			if(idx[num] == -1) idx[num] = 7;

			for (int j = 1; j < 4; j++) {
				if(num + j < 4) {  //오른쪽 방향
					if(rightDir != 0 && value[num+j][0] != value[num+j-1][1]) { //다른 극이면 반대방향 회전
						rightDir *= -1;
						idx[num+j] = (idx[num+j] + rightDir * -1) % 8;
						if(idx[num+j] == -1) idx[num+j] = 7;
					} else rightDir = 0;
				}
				if(num - j >= 0) {  //왼쪽 방향
					if(leftDir != 0 && value[num-j][1] != value[num-j+1][0]) { //다른 극이면 반대방향 회전
						leftDir *= -1;
						idx[num-j] = (idx[num-j] + leftDir * -1) % 8;
						if(idx[num-j] == -1) idx[num-j] = 7;
					} else leftDir = 0;
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if(arr[i][idx[i]] == 1) sum += Math.pow(2, i);  //s극
		}
		br.close();
		System.out.print(sum);
	}
}