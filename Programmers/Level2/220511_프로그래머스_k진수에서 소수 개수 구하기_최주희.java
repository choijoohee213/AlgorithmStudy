import java.util.*;

class Solution {
	Map<Long, Boolean> prime = new HashMap<>();  //소수인지 저장

	public int solution(int n, int k) {
		int answer = 0;
		long num = 0, size = 1;

		while (n > 0) {
			int x = n % k;
			if(num != 0 && x == 0) {
				if(isPrime(num)) answer++;  //소수인지 체크
				//다시 초기화
				num = 0;
				size = 1;
			} else if (x != 0) {
				num += size * x;
				size *= 10;
			}
			n /= k;
		}
		if(num != 0 && isPrime(num)) answer++;
		return answer;

	}

	boolean isPrime(long num) {
		if(num == 1) return false;
		if(prime.containsKey(num)) {
			return prime.get(num);
		}

		int sqrtNum = (int)Math.sqrt(num);
		for (int i = 2; i <= sqrtNum ; i++) {
			if(num % i == 0) {
				prime.put(num, false);
				return false;
			}
		}
		prime.put(num, true);
		return true;
	}
}