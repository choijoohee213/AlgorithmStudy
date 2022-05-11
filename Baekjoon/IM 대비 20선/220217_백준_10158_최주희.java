import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());

		//dir : 0은 +1,+1(오위) . 1은 -1,+1(왼위). 2는 -1,-1(왼아). 3은 +1,-1(오아)
		int[] dx = {1, -1, -1, 1}, dy = {1, 1, -1, -1};
		int curt = 0, dir = 0;

		if(w == h) t %= w+h;
		else if(w%2 == 0 && h%2 == 0) t %= w*h;
		else if(w%2 == 1 || h%2 == 1) t %= w*h*2;

		while(true) {
			int x;
			if(dir == 0) x = Math.min(Math.abs(w-p), Math.abs(h-q));
			else if(dir == 1) x = Math.min(p, Math.abs(h-q));
			else if(dir == 2) x = Math.min(p,q);
			else x = Math.min(Math.abs(w-p), q);
			p += (dx[dir] * x);
			q += (dy[dir] * x);
			curt += x;
			if (curt >= t) {
				p -= (curt - t) * dx[dir];
				q -= (curt - t) * dy[dir];
				break;
			}

			if(dir == 0) {
				if(p == w && q == h) {
					dir = 2;
				} else if(p == w && q != h){
					dir = 1;
				} else if(p != w && q == h) {
					dir = 3;
				}
			} else if(dir == 1) {
				if(p == 0 && q == h) {
					dir = 3;
				} else if(p != w && q == h) {
					dir = 2;
				} else if(p == 0 && q != h) {
					dir = 0;
				}
			} else if(dir == 2) {
				if(p == 0 && q == 0) {
					dir = 0;
				} else if(p != w && q == 0) {
					dir = 1;
				} else if(p == 0 && q != h) {
					dir = 3;
				}
			} else if(dir == 3) {
				if(p == w && q == 0) {
					dir = 1;
				} else if(p == w && q != h) {
					dir = 2;
				} else if(p != w && q == 0) {
					dir = 0;
				}
			}
		}
		br.close();
		System.out.print(p + " " + q);
	}
}