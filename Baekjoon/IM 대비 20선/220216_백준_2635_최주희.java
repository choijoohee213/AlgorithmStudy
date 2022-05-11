import java.io.*;
import java.util.*;

public class 수이어가기_baekjoon2635 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), cnt = 0;
		List<Integer> result = new ArrayList<>();

		for(int i=n/2; i<=n; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(n);
			list.add(i);

			while(true) {
				int a = list.get(list.size()-2);
				int b = list.get(list.size()-1);
				if(a-b < 0) break;
				list.add(a-b);
			}
			if(cnt<list.size()) {
				cnt = list.size();
				result = list;
			}
		}
		System.out.println(cnt);
		for(int x : result) {
			System.out.print(x + " ");
		}
	}
}
