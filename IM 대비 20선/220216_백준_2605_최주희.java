import java.io.*;
import java.util.*;

public class 줄세우기_baekjoon2605 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> list = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(list.isEmpty()) list.add(i);
			else list.add(list.size()-x, i);
		}
		for(int a : list) {
			System.out.print(a + " ");
		}
	}
}
