import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), result = 0;
		int[][] dice = new int[n][6];
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>() {{
			put(0, 5); put(5,0);
			put(1, 3); put(3,1);
			put(2, 4); put(4,2);
		}};

		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<6; j++)
				dice[i][j] = Integer.parseInt(st.nextToken());
		}

		for(int k=0; k<6; k++) {
			int top = dice[0][k], bottom = dice[0][hashMap.get(k)];
			int sum = getMaxSide(top, bottom);
			for(int i=1; i<n; i++) {
				for(int j=0; j<6; j++) {
					if(dice[i][j] == top) {
						bottom = dice[i][j];
						top = dice[i][hashMap.get(j)];
						break;
					}
				}
				sum += getMaxSide(top, bottom);
			}
			result = Math.max(result, sum);
		}
		System.out.print(result);
	}

	static int getMaxSide(int top, int bottom) {
		if(top + bottom == 11) return 4;
		if(top == 6 || bottom == 6) return 5;
		else return 6;
	}
}