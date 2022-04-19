import java.io.*;
import java.util.*;

class Ball {
	public int num, color, score;

	public Ball(int num, int color, int score) {
		this.num = num;
		this.color = color;
		this.score = score;
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Ball[] balls = new Ball[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			balls[i] = new Ball(i,c,s);
		}
//15, 10, 8, 3 36 21
		Arrays.sort(balls, (Comparator.comparingInt(o -> o.score)));
		int[] result = new int[n];
		int[] color = new int[n+1];
		int idx = 0, sum = 0;

		for (int i = 0; i < n; i++) {
			while(balls[idx].score < balls[i].score) {
				sum += balls[idx].score;
				color[balls[idx].color] += balls[idx].score;
				idx++;
			}
			result[balls[i].num] = sum - color[balls[i].color];
		}

		for (int i = 0; i < n; i++) {
			sb.append(result[i]).append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}