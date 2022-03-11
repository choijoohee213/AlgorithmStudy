import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		loop : while(T-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == b) {
				sb.append(0).append('\n');
				continue;
			}

			Queue<Integer> q = new ArrayDeque<>();
			int[] d = new int[10000];
			q.offer(a);
			d[a] = 0;

			while(!q.isEmpty()){
				int x = q.poll();
				String s = String.valueOf(x);

				for(int i=0; i<4; i++){
					for(int j=48; j<=57; j++){  //0~9 아스키코드
						if(s.charAt(i) == j) continue;
						char c = (char)j;
						String temp = s.substring(0,i) + c + s.substring(i+1);  //i번째를 c로 바꿔주기

						int nx = Integer.parseInt(temp);
						if(nx == b) {
							sb.append(d[x] + 1).append('\n');
							continue loop;
						}
						if(nx >= 1000 && d[nx]==0 && isPrimeNum(nx)){ //소수이면
							q.offer(nx);
							d[nx] = d[x] + 1;
						}
					}
				}
			}
			sb.append("IMPOSSIBLE").append('\n');
		}
		br.close();
		System.out.print(sb);
	}

	private static boolean isPrimeNum(int x) {
		int sqrt = (int)Math.sqrt(x);
		for(int i=2; i<=sqrt; i++) {
			if(x % i == 0) return false;
		}
		return true;
	}
}