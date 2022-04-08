import java.io.*;
import java.util.*;

class Node {
	public int val;
	public boolean isEmpty, isFinish;
	public Node next, fastPath;

	public Node(int val) {
		this.val = val;
		this.isEmpty = true;
	}

	public Node addNext(int val) {
		Node next = new Node(val);
		this.next = next;
		return next;
	}

	//시작지점부터 돌면서 특정 노드 찾기 =
	static public Node getNode(Node start, int target) {
		Node tmp = start;
		while(true) {
			if(tmp == null)
				return null;
			if(tmp.val == target)
				return tmp;
			tmp = tmp.next;
		}
	}
}

class Main {
	static int result = 0;
	static Node start;
	static Node[] horse = new Node[5];
	static int[] dice = new int[10];
	static int[] order = new int[10];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		init();
		permutation(0);
		br.close();
		System.out.print(result);
	}

	static void permutation(int cnt) {
		if(cnt >= 10) {
			result = Math.max(result, play());
			return;
		}

		for (int i = 1; i <= 4; i++) {
			order[cnt] = i;
			permutation(cnt+1);
		}
	}

	static int play() {
		Arrays.fill(horse, start);  //시작 지점으로 이동
		int score = 0;

		for (int i = 0; i < 10; i++) {
			Node cur = horse[order[i]];
			cur.isEmpty = true;  //이동할 것이기 때문에 현재 칸 비우기

			for (int j = 0; j < dice[i]; j++) {
				if(j == 0 && cur.fastPath != null) {  //시작지점에 지름길이 있으면(파란색)
					cur = cur.fastPath;
				} else {
					cur = cur.next;
				}
			}
			horse[order[i]] = cur;  //위치 이동

			if(!cur.isEmpty && !cur.isFinish) {  //도착점이 아닌데 이미 말이 있으면 이동못함
				score = 0;
				break;
			} else {
				cur.isEmpty = false;
				score += cur.val;
			}
		}
		for (int i = 1; i <= 4; i++) {
			horse[i].isEmpty = true;
		}
		return score;
	}

	static void init() {
		start = new Node(0); // 시작위치와 점수

		Node temp = start;
		for(int i = 2 ; i <= 40 ; i += 2) {    // 바깥쪽 경로 설정
			temp = temp.addNext(i);
		}

		// 도착지점
		Node end = temp.addNext(0);
		end.isFinish = true;
		end.next = end; // 자기 자신 포인트 -> NPE 방지

		// 가운데 교차점(val = 25)
		Node crossroads = new Node(25);
		// 교차점(25) - 30 - 35 - 40
		temp = crossroads.addNext(30);
		temp = temp.addNext(35);
		temp.next = Node.getNode(start, 40);

		// 10 - 13 - 16 - 19 - 25(교차점)
		temp = Node.getNode(start, 10);
		temp = temp.fastPath = new Node(13);
		temp = temp.addNext(16);
		temp = temp.addNext(19);
		temp.next = crossroads;

		// 20 - 22 - 24 - 25(교차점)
		temp = Node.getNode(start, 20);
		temp = temp.fastPath = new Node(22);
		temp = temp.addNext(24);
		temp.next = crossroads;

		// 30 - 28 - 27 - 26 - 25(교차점)
		temp = Node.getNode(start, 30);
		temp = temp.fastPath = new Node(28);
		temp = temp.addNext(27);
		temp = temp.addNext(26);
		temp.next = crossroads;
	}
}