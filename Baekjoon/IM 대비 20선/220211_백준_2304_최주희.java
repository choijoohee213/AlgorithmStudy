import java.util.*;
import java.io.*;

class Pillar implements Comparable<Pillar>{
	int l;
	int h;

	public Pillar(int l, int h) {
		this.l = l;
		this.h = h;
	}

	@Override
	public int compareTo(Pillar o) {
		if(l > o.l) return 1;
		else return -1;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<Pillar> st_left = new Stack<>();
		Stack<Pillar> st_right = new Stack<>();
		List<Pillar> list = new ArrayList<>();

		for(int i=0; i<n; i++) {
			StringTokenizer stoken = new StringTokenizer(br.readLine(), " ");
			list.add(new Pillar(Integer.parseInt(stoken.nextToken()), Integer.parseInt(stoken.nextToken())));
		}
		Collections.sort(list);

		int maxHeight = 0;
		for(int i=0; i<n; i++) {
			if(maxHeight < list.get(i).h) {
				st_left.push(list.get(i));
				maxHeight = list.get(i).h;
			}
		}

		maxHeight = 0;
		for(int i=n-1; i>=0; i--) {
			if(maxHeight < list.get(i).h) {
				st_right.push(list.get(i));
				maxHeight = list.get(i).h;
			}
		}

		int sum = (st_right.peek().l - st_left.peek().l + 1) * st_right.peek().h;
		int prevLeft = st_left.pop().l;
		while(!st_left.isEmpty()) {
			sum += (prevLeft - st_left.peek().l) * st_left.peek().h;
			prevLeft = st_left.pop().l;
		}
		int prevRight = st_right.pop().l;
		while(!st_right.isEmpty()) {
			sum += (st_right.peek().l - prevRight) * st_right.peek().h;
			prevRight = st_right.pop().l;
		}
		System.out.println(sum);
	}
}