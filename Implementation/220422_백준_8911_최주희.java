import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String cmd = br.readLine();
			int x = 0, y = 0, dir = 0;
			int minX = 0, maxX = 0;
			int minY = 0, maxY = 0;

			for (int i = 0; i < cmd.length(); i++) {
				char c = cmd.charAt(i);
				if(c == 'F') {
					x += dx[dir];
					y += dy[dir];
					minX = Math.min(minX, x);
					maxX = Math.max(maxX, x);
					minY = Math.min(minY, y);
					maxY = Math.max(maxY, y);
				} else if(c == 'B') {
					x += dx[(dir+2)%4];
					y += dy[(dir+2)%4];
					minX = Math.min(minX, x);
					maxX = Math.max(maxX, x);
					minY = Math.min(minY, y);
					maxY = Math.max(maxY, y);
				} else if(c == 'L') dir = (dir+3) % 4;
 				else dir = (dir+1) % 4;
			}
			System.out.println(Math.abs(minX - maxX) * Math.abs(minY - maxY));
		}
		br.close();
	}
}