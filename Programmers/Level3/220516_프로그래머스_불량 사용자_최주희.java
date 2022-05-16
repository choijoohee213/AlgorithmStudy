import java.util.*;

class Solution {
	int ans = 0;
	Set<Set<String>> result = new HashSet<>();
	public int solution(String[] user_id, String[] banned_id) {
		permutation(0, new HashSet<>(), user_id, banned_id, new boolean[user_id.length]);
		return ans;
	}

	private void permutation(int cnt, HashSet<String> selected, String[] user_id, String[] banned_id, boolean[] visited) {
		if(cnt == banned_id.length) {
			if(!result.contains(selected)) {
				ans++;
				result.add(new HashSet<>(selected));
			}
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			if(visited[i] || !isSame(user_id[i], banned_id[cnt])) continue;
			visited[i] = true;
			selected.add(user_id[i]);
			permutation(cnt+1, selected, user_id, banned_id, visited);
			visited[i] = false;
			selected.remove(user_id[i]);
		}
	}

	private boolean isSame(String userId, String bannedId) {
		if(userId.length() != bannedId.length()) return false;
		for (int idx = 0; idx < bannedId.length(); idx++) {
			if(bannedId.charAt(idx) == '*') continue;
			if (bannedId.charAt(idx) != userId.charAt(idx)) return false;
		}
		return true;
	}
}