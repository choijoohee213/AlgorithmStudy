import java.io.*;
import java.util.*;

class Main {
	static int n,k;
	static char[] result;
	static boolean flag = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		result = new char[n];

		if(k == 0) {
			flag = true;
			for (int i = 0; i < n; i++) {
				result[i] = 'A';
			}
		} else {
			getAnswer();
		}
		StringBuilder sb = new StringBuilder();
		if(flag) {
			for (int i = 0; i < n; i++) {
				sb.append(result[i]);
			}
			System.out.print(sb);
		} else {
			System.out.print(-1);
		}
		br.close();
	}

	static void getAnswer() {
		int b = n;
		int a = 0;

		while(a <= n) {
			//A,B 쌍을 만들 수 있는 최대 개수는 a*b개
			if(a * b < k) {
				a++;
				b--;
				continue;
			}
			Arrays.fill(result, 'B');

			//a*b가 k를 넘어갈수도 있음-> 앞에서부터 a-1까지만 A로 채우기
			for (int i = 0; i < a - 1; i++) {
				result[i] = 'A';
			}

			int res = (a-1) * b; //이제 만들수 있는 쌍 개수는 (a-1)*b개
			int remain = k - res;  //맨 오른쪽에서 A를 두고 왼쪽으로 한칸씩 이동할때마다 쌍의 개수가하나씩 늘어남
			result[(n-1) - remain] = 'A'; //나머지를 오른쪽에서 왼쪽방향으로 remain만큼 떨어진곳을 A로 변경
			flag = true;
			break;
		}
	}
}