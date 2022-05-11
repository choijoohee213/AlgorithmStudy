class Solution {
	int scoreDiff = 0;
	int[] result = new int[11];

	public int[] solution(int n, int[] info) {
		dfs(0, n, info, new int[11]);
		if(scoreDiff == 0) return new int[]{-1};
		return result;
	}

	private void dfs(int idx, int cnt, int[] apeach, int[] ryan) {
		if(idx == 11|| cnt == 0) {
			ryan[10] += cnt;
			int score1 = 0, score2 = 0;
			for (int i = 0; i <= 10; i++) {
				if (apeach[i] < ryan[i]) score2 += 10 - i;
				else if (apeach[i] > 0) score1 += 10 - i;
			}
			int diff = score2 - score1;
			if (scoreDiff < diff) {
				scoreDiff = diff;
				result = ryan.clone();
			} else if(scoreDiff == diff) {
				for(int i=10; i>=0; i--){
					if(result[i] > ryan[i]) break;
					else if(result[i] < ryan[i]){
						result = ryan.clone();
						break;
					}
				}
			}
			ryan[10] -= cnt;
			return;
		}

		//포함
		if(apeach[idx] + 1 <= cnt) {
			ryan[idx] = apeach[idx] + 1;
			dfs(idx+1, cnt-ryan[idx], apeach, ryan);
			ryan[idx] = 0;
		}
		dfs(idx+1, cnt, apeach, ryan);  //포함안함
	}
}