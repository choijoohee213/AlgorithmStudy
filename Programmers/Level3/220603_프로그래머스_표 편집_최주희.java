// import java.util.*;
//
// class Solution {
// 	public String solution(int n, int k, String[] cmd) {
// 		StringBuilder answer = new StringBuilder();
// 		Stack<int[]> deleted = new Stack<>();
// 		List<Integer> table = new LinkedList<>();
// 		for (int i = 0; i < n; i++) {
// 			table.add(i);
// 			answer.append('X');
// 		}
//
// 		for (String s : cmd) {
// 			char c = s.charAt(0);
// 			if(c == 'U') {
// 				int num = Integer.parseInt(s.split(" ")[1]);
// 				k -= num;
// 			} else if(c == 'D') {
// 				int num = Integer.parseInt(s.split(" ")[1]);
// 				k += num;
// 			} else if(c == 'C') {
// 				deleted.add(new int[]{k, table.get(k)});
// 				table.remove(k);
// 				if(k >= table.size())
// 					k = table.size() - 1;
// 			} else if(c == 'Z') {
// 				int[] info = deleted.pop();
// 				table.add(info[0], info[1]);
// 				if(info[0] <= k) k++;
// 			}
// 		}
//
// 		for (Integer idx : table) {
// 			answer.setCharAt(idx, 'O');
// 		}
// 		return answer.toString();
// 	}
// }

import java.util.*;

class Solution {
	public String solution(int n, int k, String[] cmd) {
		StringBuilder answer = new StringBuilder();
		Stack<Integer> deleted = new Stack<>();
		int size = n;

		for (String s : cmd) {
			char c = s.charAt(0);
			if(c == 'U') {
				k -= Integer.parseInt(s.split(" ")[1]);
			} else if(c == 'D') {
				k += Integer.parseInt(s.split(" ")[1]);
			} else if(c == 'C') {
				deleted.add(k);
				size--;
				if(k == size) k--;
			} else if(c == 'Z') {
				int idx = deleted.pop();
				if(idx <= k) k++;
				size++;
			}
		}

		for (int i = 0; i < size; i++) {
			answer.append('O');
		}
		while(!deleted.isEmpty()) {
			answer.insert(deleted.pop().intValue(), 'X');
		}
		return answer.toString();
	}
}