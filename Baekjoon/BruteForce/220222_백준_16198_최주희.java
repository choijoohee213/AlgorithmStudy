import java.io.*;
import java.util.*;

class Main {
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<n; i++) list.add(Integer.valueOf(st.nextToken()));
		getMaxEnergy(0, list);

		br.close();
		System.out.print(result);
	}

	static void getMaxEnergy(int sum, List<Integer> list) {
		result = Math.max(result, sum);
		for(int i=0; i<list.size(); i++) {
			if(i == 0 || i == list.size()-1) continue;  //첫번째와 마지막 구슬 생략
			List<Integer> newList = new ArrayList<>(list);
			newList.remove(i);  //x번째 구슬을 제거한 새로운 리스트
			getMaxEnergy(sum + list.get(i-1) * list.get(i+1), newList);
		}
	}
}