import java.io.*;
import java.util.*;

class Main {
	static int[][] arr = new int[9][9];
	static List<Integer> zero = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = c[j] - '0';
				if(arr[i][j] == 0) {
					zero.add(i*9+j);
				}
			}
		}
		br.close();
		recursive(0);
	}

	static void recursive(int cnt) {
		if(cnt == zero.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		int x = zero.get(cnt) / 9;
		int y = zero.get(cnt) % 9;
		for (int k = 1; k <= 9; k++) {
			if(checkSuccess(x,y,k)) {
				arr[x][y] = k;
				recursive(cnt+1);
				arr[x][y] = 0;
			}
		}
	}

	static boolean checkSuccess(int x, int y, int num) {
		int startX = (x / 3) * 3, startY = (y / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(arr[startX + i][startY + j] == num) return false;
			}
		}

		for (int i = 0; i < 9; i++) {
			if(arr[i][y] == num || arr[x][i] == num) return false;
		}
		return true;
	}
}