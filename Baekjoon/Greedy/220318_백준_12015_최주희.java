import java.io.*;
import java.util.*;

class Main {
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), result = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		list.add(0);
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(x > list.get(list.size()-1)) {
				list.add(x);
				result++;
			} else {  //lower_bound : 삽입되는 x보다 크거나 같은 첫번쨰 원소 위치 반환 (이분탐색)
				list.set(lowerBound(x), x);
			}
		}
		System.out.print(result);
	}

	static int lowerBound(int target) {
		int begin = 0;
		int end = list.size();

		while(begin < end) {
			int mid = (begin + end) / 2;
			if(list.get(mid) >= target) {
				end = mid;
			}
			else {
				begin = mid + 1;
			}
		}
		return end;
	}
}