import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer[] arr = new Integer[26];
		int n = Integer.parseInt(br.readLine());
		Arrays.fill(arr, 0);

		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<s.length(); j++) {
				arr[s.charAt(j)-'A'] += (int)Math.pow(10, s.length()-j-1);
			}
		}
		Arrays.sort(arr, Collections.reverseOrder());

		int sum = 0, num = 9;
		for(int i=0; i<10; i++) {
			sum += arr[i] * num--;
		}
		System.out.println(sum);
	}
}