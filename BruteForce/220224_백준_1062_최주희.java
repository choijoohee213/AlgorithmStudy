import java.io.*;
import java.util.*;

class Main {
	static int n,k,result = 0;
	static String[] words;
	static boolean[] visited = new boolean['z'+1];
	static Character[] alphabet;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if(k < 5) {
			System.out.println(0);
			return;
		}
		//이 5개는 무조건 들어감
		visited['a'] = true;
		visited['n'] = true;
		visited['t'] = true;
		visited['i'] = true;
		visited['c'] = true;
		words = new String[n];
		Set<Character> set = new HashSet<>();
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=4; j<s.length()-4; j++) {
				char c = s.charAt(j);
				if(!visited[c]) set.add(c);
			}
			words[i] = s.substring(4, s.length()-4);
		}
		alphabet = set.toArray(new Character[0]); //알파벳 목록
		k = Math.min(k-5, alphabet.length);
		recursive(0, 0);
		br.close();
		System.out.print(result);
	}

	static void recursive(int idx, int cnt) {
		if(cnt == k) {
			int sum = 0;
			for(int i=0; i<n; i++) {
				boolean flag = true;
				for(int j=0; j<words[i].length(); j++) {
					if(!visited[words[i].charAt(j)]) { //알파벳이 없다는 거니까 읽을 수 없음
						flag = false;
						break;
					}
				}
				if(flag) sum++;
			}
			result = Math.max(sum, result);
		}

		for(int i=idx; i< alphabet.length; i++) {
			char c = alphabet[i];
			if(visited[c]) continue;
			visited[c] = true;
			recursive(i+1, cnt+1);
			visited[c] = false;
		}
	}
}