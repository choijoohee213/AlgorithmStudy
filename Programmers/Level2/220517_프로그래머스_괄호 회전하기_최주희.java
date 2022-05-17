import java.util.*;

class Solution {
	public int solution(String s) {
		int answer = 0;

		String rotation = s;
		for (int t = 0; t < s.length(); t++) {
			Stack<Character> st = new Stack<>();
			for (int i = 0; i < rotation.length(); i++) {
				char c = rotation.charAt(i);

				if(!st.isEmpty()) {
					char top = st.peek();
					if((top == '(' && c == ')') || (top == '[' && c == ']') || (top == '{' && c == '}'))
						st.pop();
					else if((top == ')' && c == '(') || (top == ']' && c == '[') || (top == '}' && c == '{'))
						break;
					else if ((top != '(' && c == ')') || (top != '[' && c == ']') || (top != '{' && c == '}'))
						break;
					else st.add(c);
				} else st.add(c);
			}
			if(st.isEmpty()) answer++;
			rotation = rotation.substring(1) + rotation.charAt(0);
		}

		return answer;
	}
}