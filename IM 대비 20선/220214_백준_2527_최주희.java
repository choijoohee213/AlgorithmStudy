import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());

			if ((x1 == p2 && y1 == q2) || (x2 == p1 && y2 == q1)
				|| (x2 == p1 && q2 == y1) || (x1 == p2 && q1 == y2)) {  //점 : c
				System.out.println("c");
			}
			else if((p1<x2) || (x1>p2) || (y1>q2) || (q1<y2))
				System.out.println("d");
			else if(x2 == p1 || q2 == y1 || q1 == y2 || x1 == p2) {
				System.out.println("b");
			}
			else
				System.out.println("a");
		}
	}
}