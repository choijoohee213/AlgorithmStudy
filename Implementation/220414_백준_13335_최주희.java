import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] weight = new int[n+1];
		for (int i = 1; i <= n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		int curWeight = weight[1], idx = 2, time = 1;
		Queue<int[]> bridge = new ArrayDeque<>();
		bridge.offer(new int[]{weight[1], time});

		while(!bridge.isEmpty()) {
			time++;
			if(bridge.peek()[1] + w == time) {
				curWeight -= bridge.peek()[0];
				bridge.poll();
			}
			if(idx<=n && curWeight + weight[idx] <= l){
				bridge.offer(new int[]{weight[idx], time});
				curWeight += weight[idx++];
			}
		}
		br.close();
		System.out.println(time);
	}
}