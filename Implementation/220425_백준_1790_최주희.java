import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long tmp_k = k, len = 1, num_cnt = 9, res = 0;

		//k가 어느 범위에 속하는 지 구하기
		while(tmp_k > num_cnt * len)
		{
			tmp_k -= num_cnt * len;
			res += num_cnt;
			num_cnt *= 10;
			len++;
		}

		//res는 현재까지 지나쳐온 자릿수의 위치를 지니고 있으며, tmp_k는 그 위치에서 더 가야하는 잔여 카운트값
		//자릿수를 맞춰 res+1, 앞에서 한칸 이동했으니 tmp_k-1, tmp_k-1/len 을 더해주면 실제 포함한 숫자
		res = (res+1) + ((tmp_k-1) / len);

		if(res > N) System.out.print(-1);
		else System.out.print(String.valueOf(res).charAt((int)((tmp_k-1) % len)));
	}
}