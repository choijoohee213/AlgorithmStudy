import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Map<Character, Integer> number = new HashMap<>();
		number.put('I', 1);
		number.put('A', 4);  //IV
		number.put('V', 5);
		number.put('B', 9);  //IX
		number.put('X', 10);
		number.put('E', 40);  //XL
		number.put('L', 50);
		number.put('F', 90);  //XC
		number.put('C', 100);
		number.put('G', 400);  //CD
		number.put('D', 500);
		number.put('H', 900);  //CM
		number.put('M', 1000);

		String[] word = {br.readLine(), br.readLine()};
		for (int i = 0; i < 2; i++) {
			word[i] = word[i].replaceAll("IV", "A");
			word[i] = word[i].replaceAll("IX", "B");
			word[i] = word[i].replaceAll("XL", "E");
			word[i] = word[i].replaceAll("XC", "F");
			word[i] = word[i].replaceAll("CD", "G");
			word[i] = word[i].replaceAll("CM", "H");
		}
		int sum = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < word[i].length(); j++) {
				sum += number.get(word[i].charAt(j));
			}
		}
		System.out.println(sum);

		while (sum >= 1000) {
			sb.append('M');
			sum -= 1000;
		}
		if (sum >= 900) {
			sb.append("CM");
			sum -= 900;
		}
		if (sum >= 500) {
			sb.append('D');
			sum -= 500;
		}
		if (sum >= 400) {
			sb.append("CD");
			sum -= 400;
		}
		while (sum >= 100) {
			sb.append('C');
			sum -= 100;
		}
		if (sum >= 90) {
			sb.append("XC");
			sum -= 90;
		}
		if (sum >= 50) {
			sb.append('L');
			sum -= 50;
		}
		if (sum >= 40) {
			sb.append("XL");
			sum -= 40;
		}
		while (sum >= 10) {
			sb.append('X');
			sum -= 10;
		}
		if (sum >= 9) {
			sb.append("IX");
			sum -= 9;
		}
		if (sum >= 5) {
			sb.append('V');
			sum -= 5;
		}
		if (sum >= 4) {
			sb.append("IV");
			sum -= 4;
		}
		while (sum >= 1) {
			sb.append('I');
			sum -= 1;
		}

		br.close();
		System.out.print(sb);
	}
}