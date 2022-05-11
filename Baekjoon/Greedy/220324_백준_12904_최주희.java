import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder s = new StringBuilder(br.readLine());
		StringBuilder t = new StringBuilder(br.readLine());
		while(s.length()<t.length()) {
			if(t.charAt(t.length()-1) == 'A') {
				t.deleteCharAt(t.length() - 1);
			}
			else {
				t.deleteCharAt(t.length()-1);
				t.reverse();
			}
			if(t.toString().equals(s.toString())) {
				System.out.print(1);
				return;
			}
		}
		br.close();
		System.out.print(0);
	}
}