import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n+2][m+1];
		int[] idx = new int[n+2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Set<Integer> erase = new HashSet<>();
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if(b == 0) c = m - c;  //시계라면 반시계방향으로 바꿔주기
			for (int j = a; j <= n; j+=a) {  //원판 번호 배수
				idx[j] = (idx[j] + c) % m;  //원판 회전
			}

			if(erase.size() < n*m) {  //원판에 수가 남아있으면
				boolean adj = false;  //인접 여부
				//인접하면서 수가 같은것 모두 찾아 지우기
				for (int x = 1; x <= n; x++) {
					for (int y = 0; y < m; y++) {
						int num = arr[x][(idx[x]+y)%m];
						if(erase.contains(x*m + (idx[x]+y)%m)) continue;
						boolean flag = false;
						if(num == arr[x-1][(idx[x-1]+y)%m]) {  //i-1,j 원판
							erase.add((x-1)*m+(idx[x-1]+y)%m);
							flag = true;
							adj = true;
						}
						if(num == arr[x+1][(idx[x+1]+y)%m]) {  //i+1,j 원판
							erase.add((x+1)*m + (idx[x+1]+y)%m);
							flag = true;
							adj = true;
						}
						if(num == arr[x][(idx[x]+y+1)%m]) {  //i,j+1 원판
							erase.add(x*m + (idx[x]+y+1)%m);
							flag = true;
							adj = true;
						}
						if(num == arr[x][(idx[x]+y+m-1)%m]) {  //i,j-1 원핀
							erase.add(x*m + (idx[x]+y+m-1)%m);
							flag = true;
							adj = true;
						}
						if(flag) {
							erase.add(x*m + (idx[x]+y)%m);  //자기 자신 지우기
						}
					}
				}
				for (Integer integer : erase) {
					arr[integer/m][integer%m] = 0;  //인접했던 수들 0으로 지워주기
				}
				if(!adj) {  //인접하면서 수가 같은 것이 없다면 평균 구해서 값 갱신
					float avg = 0;
					for (int k = 1; k <= n; k++) {
						for (int l = 0; l < m; l++) {
							avg += arr[k][l];
						}
					}
					avg /= n*m - erase.size();
					for (int k = 1; k <= n; k++) {
						for (int l = 0; l < m; l++) {
							if(arr[k][l] == 0) continue;
							if((float)arr[k][l] > avg) arr[k][l]--;
							else if((float)arr[k][l] < avg) arr[k][l]++;
						}
					}
				}
			}
		}
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				sum += arr[i][j];
			}
		}
		br.close();
		System.out.print(sum);
	}
}