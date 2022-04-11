import java.io.*;
import java.util.*;

class Fireball {
	public int r,c,w,s,d;
	boolean flag;

	public Fireball(int r, int c, int w, int s, int d, boolean flag) {
		this.r = r;
		this.c = c;
		this.w = w;
		this.s = s;
		this.d = d;
		this.flag = flag;
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
		ArrayList<Fireball>[][] fireballs = new ArrayList[n][n];
		boolean flag = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fireballs[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Fireball fireball = new Fireball(r,c,w,s,d,true);
			fireballs[r][c].add(fireball);
		}

		while(k-- > 0) {
			//파이어볼 이동
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int l = 0; l < fireballs[i][j].size(); l++) {
						Fireball f = fireballs[i][j].get(l);
						if (f.flag == flag) continue;  //저번턴 것인지 이번턴 것인지 구분

						int nr = f.r + dx[f.d] * (f.s % n);
						int nc = f.c + dy[f.d] * (f.s % n);
						if (nr > 0) nr %= n;
						if (nc > 0) nc %= n;
						if (nr < 0) nr = n - Math.abs(nr);
						if (nc < 0) nc = n - Math.abs(nc);

						f.r = nr;
						f.c = nc;
						f.flag = flag;
						fireballs[nr][nc].add(f);
						fireballs[i][j].remove(l--);
					}
				}
			}

			//파이어볼 합치기
			for (int i = 0; i < n; i++) {
				for (int j=0; j < n; j++) {
					int size = fireballs[i][j].size();
					if(size < 2) continue;

					int mSum = 0, sSum = 0, oddCnt = 0, evenCnt = 0;
					for (int l = 0; l < size; l++) {
						Fireball f = fireballs[i][j].get(l);
						mSum += f.w;
						sSum += f.s;
						if(f.d % 2 != 0) oddCnt++;
						else evenCnt++;
					}
					fireballs[i][j].clear();
					mSum /= 5;
					sSum /= size;
					if(mSum == 0) continue;
					int dir = 1;
					if(oddCnt == 0 || evenCnt == 0) dir = 0;
					for (; dir <= 7; dir+=2) {
						fireballs[i][j].add(new Fireball(i, j, mSum, sSum, dir, flag));
					}
				}
			}
			flag = !flag;
		}

		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (Fireball f : fireballs[i][j]) {
					sum += f.w;
				}
			}
		}
		br.close();
		System.out.print(sum);
	}
}