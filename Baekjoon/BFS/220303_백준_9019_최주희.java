import java.io.*;
import java.util.*;

class Cmd {
	public int x;
	public String result;

	public Cmd(int x, String result) {
		this.x = x;
		this.result = result;
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Queue<Cmd> q = new ArrayDeque<>();
			Set<Integer> set = new HashSet<>();
			q.offer(new Cmd(a, ""));
			set.add(a);

			while(!q.isEmpty()) {
				Cmd cmd = q.poll();

				for(int i=0; i<4; i++) {
					int nx;
					String nresult = cmd.result;
					if(i == 0) {
						nx = (cmd.x * 2) % 10000;
						nresult += 'D';
					}
					else if(i == 1) {
						if(cmd.x == 0) nx = 9999;
						else nx = (cmd.x-1);
						nresult += 'S';
					}
					else if(i == 2) {
						nx = (cmd.x % 1000) * 10 + (cmd.x / 1000);
						nresult += 'L';
					}
					else {
						nx = (cmd.x % 10) * 1000 + (cmd.x / 10);
						nresult += 'R';
					}
					if(set.contains(nx)) continue;
					if(nx == b) {
						sb.append(nresult).append('\n');
						q.clear();
						break;
					}
					q.offer(new Cmd(nx, nresult));
					set.add(nx);
				}
			}
		}
		br.close();
		System.out.print(sb);
	}
}