// import java.util.*;
//
// class Solution {
// 	Set<Set<String>> result = new HashSet<>();
// 	public int solution(String[] user_id, String[] banned_id) {
// 		permutation(0, new HashSet<>(), user_id, banned_id, new boolean[user_id.length]);
// 		return result.size();
// 	}
//
// 	private void permutation(int cnt, HashSet<String> selected, String[] user_id, String[] banned_id, boolean[] visited) {
// 		if(cnt == banned_id.length) {
// 			if(!result.contains(selected)) {
// 				result.add(new HashSet<>(selected));
// 			}
// 			return;
// 		}
//
// 		for (int i = 0; i < user_id.length; i++) {
// 			if(visited[i] || !isSame(user_id[i], banned_id[cnt])) continue;
// 			visited[i] = true;
// 			selected.add(user_id[i]);
// 			permutation(cnt+1, selected, user_id, banned_id, visited);
// 			visited[i] = false;
// 			selected.remove(user_id[i]);
// 		}
// 	}
//
// 	private boolean isSame(String userId, String bannedId) {
// 		if(userId.length() != bannedId.length()) return false;
// 		for (int idx = 0; idx < bannedId.length(); idx++) {
// 			if(bannedId.charAt(idx) == '*') continue;
// 			if (bannedId.charAt(idx) != userId.charAt(idx)) return false;
// 		}
// 		return true;
// 	}
// }


import java.util.*;

class Solution {
	Set<Integer> result = new HashSet<>();
	public int solution(String[] user_id, String[] banned_id) {
		permutation(0, 0, user_id, banned_id, new boolean[user_id.length]);
		return result.size();
	}

	private void permutation(int cnt, int bits, String[] user_id, String[] banned_id, boolean[] visited) {
		if(cnt == banned_id.length) {
			result.add(bits);
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			if(visited[i] || !isSame(user_id[i], banned_id[cnt])) continue;
			visited[i] = true;
			permutation(cnt+1, bits | (1 << i), user_id, banned_id, visited);
			visited[i] = false;
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