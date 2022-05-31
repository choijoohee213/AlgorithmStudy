import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			String s = br.readLine();
			if(s.equals("end")) break;
			char[][] board = new char[3][3];
			int cntX = 0, cntO = 0;
			for (int i = 0; i < 9; i++) {
				board[i/3][i%3] = s.charAt(i);
				if(board[i/3][i%3] == 'X') cntX++;
				else if(board[i/3][i%3] == 'O') cntO++;
			}
			if(cntX + cntO == 9) {
				if(cntX != cntO + 1 || isBingo(board, 'O')) {
					sb.append("invalid\n");
				}
				else sb.append("valid\n");
			} else {
				if(cntX == cntO) {   //O 빙고로 끝났어야함
					if(!isBingo(board, 'X') && isBingo(board, 'O')) {
						sb.append("valid\n");
					} else {
						sb.append("invalid\n");
					}
				} else if(cntX == cntO + 1) {  //X 빙고로 끝났어야함
					if(isBingo(board, 'X') && !isBingo(board, 'O')) {
						sb.append("valid\n");
					} else {
						sb.append("invalid\n");
					}
				} else {
					sb.append("invalid\n");
				}
			}
		}
		br.close();
		System.out.print(sb);
	}

	static boolean isBingo(char[][] board, char c) {
		// 가로
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == c && board[i][1] == c && board[i][2] == c) {
				return true;
			}
		}

		// 세로
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == c && board[1][i] == c && board[2][i] == c) {
				return true;
			}
		}

		// 대각선
		return (board[0][0] == c && board[1][1] == c && board[2][2] == c) ||
			(board[0][2] == c && board[1][1] == c && board[2][0] == c);
	}
}