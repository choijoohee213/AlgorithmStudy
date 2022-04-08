import java.io.*;
import java.util.*;

class Student implements Comparable<Student>{
	public int num, order, recommend;

	public Student(int num, int order) {
		this.num = num;
		this.order = order;
		this.recommend = 0;
	}

	@Override
	public int compareTo(Student o) {
		if(recommend == o.recommend) {
			return order - o.order;  //추천횟수가 같으면 게시된지 오래된 순
		}
		return recommend - o.recommend;
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		List<Student> photos = new ArrayList<>();
		Set<Integer> studentSet = new HashSet<>();  //게시되어있는 학생 번호

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!studentSet.contains(num)) {  //새로 사진 게시
				if(photos.size() == n) {  //사진틀 꽉차면 하나 제거
					photos.sort(Student::compareTo);
					studentSet.remove(photos.get(0).num);
					photos.remove(0);
				}
				studentSet.add(num);  //사진틀에 학생 추가
				photos.add(new Student(num, i));  //학생번호와 게시시점 인덱스 정보
			} else {
				for (Student student : photos) {
					if(student.num == num) {
						student.recommend++;
						break;
					}
				}
			}
		}
		photos.sort(Comparator.comparingInt(o -> o.num));  //최종 후보의 학생번호 증가순
		for (Student student : photos) {
			sb.append(student.num).append(' ');
		}
		br.close();
		System.out.print(sb);
	}
}