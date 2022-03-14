import java.io.*;
import java.util.*;

class cmd {
	public long num;
	public String result;

	public cmd(long num, String result) {
		this.num = num;
		this.result = result;
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		char[] operation = {'*','+','-','/'};
		long s = Integer.parseInt(st.nextToken());
		long t = Integer.parseInt(st.nextToken());
		br.close();
		if(s == t) {
			System.out.print(0);
			return;
		}
		Queue<cmd> q = new ArrayDeque<>();
		Set<Long> visited = new HashSet<>();
		q.offer(new cmd(s,""));
		visited.add(s);

		while(!q.isEmpty()) {
			cmd x = q.poll();
			for(int i=0; i<4; i++) {
				long nx = x.num;
				if(i==0) nx *= nx;
				else if(i == 1) nx += nx;
				else if(i == 2) nx -= nx;
				else if(nx != 0) nx /= nx;
				if(nx > t || visited.contains(nx)) continue;
				if(nx == t) {
					System.out.print(x.result + operation[i]);
					return;
				}
				q.offer(new cmd(nx, x.result + operation[i]));
				visited.add(nx);
			}
		}
		System.out.print(-1);
	}
}