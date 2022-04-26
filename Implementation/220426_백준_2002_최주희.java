import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] startCars = new String[n];
		String[] endCars = new String[n];

		for (int i = 0; i < n; i++) startCars[i] = br.readLine();
		for (int i = 0; i < n; i++) endCars[i] = br.readLine();

		int sIdx = 0, eIdx = 0;
		Set<String> set = new HashSet<>();
		for (; sIdx<n; sIdx++) {
			if(set.contains(startCars[sIdx])) continue;
			for (; eIdx<n ; eIdx++) {
				if(startCars[sIdx].equals(endCars[eIdx])) {
					eIdx++;
					break;
				}
				set.add(endCars[eIdx]);
			}
		}
		br.close();
		System.out.print(set.size());
	}
}