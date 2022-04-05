import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()) * 2;
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		boolean[] robot = new boolean[n/2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int level = 1, cnt = 0;
		while(true) {
			int tmp = arr[n-1];
			for (int i = n-1; i > 0 ; i--) {  //컨베이어 벨트 회전
				arr[i] = arr[i-1];
			}
			arr[0] = tmp;

			for (int i = n/2-1; i > 0 ; i--) {  //로봇도 컨베이어 벨트와 움직인다
				robot[i] = robot[i-1];
			}
			robot[0] = false;
			robot[n/2-1] = false;

			for (int i = n/2-1; i > 0; i--) {  //가장 먼저 올라간 로봇부터
				if(robot[i-1] && !robot[i] && arr[i] > 0) {  //로봇 이동
					robot[i] = true;
					robot[i-1] = false;
					arr[i]--;
				}
			}

			if(arr[0] > 0) {  //로봇 추가
				arr[0]--;
				robot[0] = true;
			}

			cnt = 0;
			for (int i = 0; i < n; i++) {
				if(arr[i] == 0) {
					cnt++;
				}
			}
			if(cnt >= k) {
				br.close();
				System.out.print(level);
				return;
			}
			level++;
		}
	}
}