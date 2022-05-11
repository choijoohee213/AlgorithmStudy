import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] jewel = new int[n][2];
		int[] bag = new int[k];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i][0] = Integer.parseInt(st.nextToken());
			jewel[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < k; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(jewel, new Comparator<int[]>() {  //무게를 오름차순 정렬, 같으면 가치를 내림차순 정렬
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) {
					return b[1]-a[1];
				}
				return a[0] - b[0];
			}
		});
		Arrays.sort(bag);

		long sum = 0;
		Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); //내림차순
		int idx = 0;
		for(int i=0; i<k; i++) {
			while(idx<n && jewel[idx][0]<=bag[i]) { //현재 가방 무게보다 이하인 보석을 우선순위 큐에 넣는다
				pq.offer(jewel[idx++][1]);
			}
			if(!pq.isEmpty()) sum += pq.poll(); //제일 큰 가치
		}

		br.close();
		System.out.print(sum);
	}
}