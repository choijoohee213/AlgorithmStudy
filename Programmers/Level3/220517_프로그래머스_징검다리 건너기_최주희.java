class Solution {
	public int solution(int[] stones, int k) {
		int answer = 0, start = 1, end = 200000000, mid;

		while(start <= end) {
			mid = (start + end) / 2;
			if(checkCross(stones, k, mid)) {
				start = mid + 1;
				answer = Math.max(answer, mid);
			} else {
				end = mid - 1;
			}
		}
		return answer;
	}

	private boolean checkCross(int[] stones, int k, int cnt) {
		int skip = 0;
		for (int stone : stones) {
			if(stone - cnt < 0) {
				skip++;
			} else skip = 0;
			if(skip >= k) return false;
		}
		return true;
	}
}