import java.io.*;
import java.util.*;

class Count implements Comparable<Count>{
	public int num, cnt;
	public Count(int num) {
		this.num = num;
		cnt = 0;
	}

	@Override
	public int compareTo(Count c) {  //등장 횟수 오름차순 , 수 오름차순
		if(c == null) return -1;
		if(cnt == c.cnt) {
			return (num - c.num)<0? -1 : 1;
		}
		return (cnt - c.cnt)<0? -1 : 1;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[101][101];
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		br.close();

		int rCnt = 3, cCnt = 3;
		for(int time=0; time<=100; time++) {
			if(arr[r-1][c-1] == k) {
				System.out.print(time);
				return;
			}
			//행과 열의 크기가 100을 넘어가면 나머지는 버린다.
			if(rCnt>101) rCnt = 101;
			if(cCnt>101) cCnt = 101;

			int maxCnt = 0;
			if(rCnt >= cCnt) {  //r연산
				for(int i=0; i<rCnt; i++) {
					Map<Integer, Count> count = new HashMap<>();
					//가장 큰 행을 구하기 위함 - 수의 종류 * 2가 행의 개수
					Set<Integer> numSet = new TreeSet<>(Comparator.reverseOrder());

					for(int j=0; j<cCnt; j++) {
						if(arr[i][j] == 0) continue;
						if(!count.containsKey(arr[i][j]))
							count.put(arr[i][j], new Count(arr[i][j]));
						count.get(arr[i][j]).cnt++;  //등장 횟수 증가
						numSet.add(arr[i][j]);
					}
					int idx = 0;
					List<Map.Entry<Integer, Count>> list = new ArrayList<>(count.entrySet());
					list.sort(Map.Entry.comparingByValue());  //Count를 기준으로 정렬
					for (Map.Entry<Integer, Count> e : list) {
						arr[i][idx++] = e.getValue().num;
						arr[i][idx++] = e.getValue().cnt;
					}
					for(int j=idx; j<=100; j++) {  //나머지 뒤에는 0으로 채우기
						arr[i][j] = 0;
					}
					maxCnt = Math.max(maxCnt, numSet.size() * 2);
				}
				cCnt = maxCnt;  //가장 큰행이 다음의 열의 개수가됨
			}

			else {  //c 연산
				for(int i=0; i<cCnt; i++) {
					Map<Integer, Count> count = new HashMap<>();
					Set<Integer> numSet = new TreeSet<>(Comparator.reverseOrder());

					for(int j=0; j<rCnt; j++) {
						if(arr[j][i] == 0) continue;
						if(!count.containsKey(arr[j][i]))
							count.put(arr[j][i], new Count(arr[j][i]));
						count.get(arr[j][i]).cnt++;
						numSet.add(arr[j][i]);
					}
					int idx = 0;
					List<Map.Entry<Integer, Count>> list = new ArrayList<>(count.entrySet());
					list.sort(Map.Entry.comparingByValue());
					for (Map.Entry<Integer, Count> e : list) {
						arr[idx++][i] = e.getValue().num;
						arr[idx++][i] = e.getValue().cnt;
					}
					for(int j=idx; j<=100; j++) {
						arr[j][i] = 0;
					}
					maxCnt = Math.max(maxCnt, numSet.size() * 2);
				}
				rCnt = maxCnt;  //가장 큰 열이 다음의 행이 됨
			}

		}
		System.out.print(-1);
	}
}