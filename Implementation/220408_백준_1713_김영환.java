package com.younghwani.a220408;

import java.io.*;
import java.util.*;
/*
추천량 순 정렬, 최신 순 정렬
3
9
2 1 4 3 5 6 2 7 2
>>>2 6 7
 */
public class Main_bj_1713_후보추천하기 {
    static class Student implements Comparable<Student> {
        int no, cnt, updated;
        public Student(int no, int cnt, int updated) {
            this.no = no;
            this.cnt = cnt;
            this.updated = updated;
        }
        @Override
        public int compareTo(Student o) {
            return this.cnt==o.cnt ?
                    Integer.compare(this.updated, o.updated) :
                    Integer.compare(this.cnt, o.cnt);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // 사진틀 개수
        int C = Integer.parseInt(br.readLine());
        List<Student> students = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            int num = Integer.parseInt(st.nextToken());
            boolean flag = false; // 리스트에 존재하는지 여부

            for (Student s : students) {
                if(s.no == num) {
                    s.cnt++;
                    flag=true;
                    break;
                }
            }

            if(!flag) { // 리스트에 없다면, 값을 비교해보고 추가 가능 여부 확인해 결정한다.
                if(students.size() < N) {
                    students.add(new Student(num, 1, i));
                } else {
                    Collections.sort(students);
                    students.remove(0);
                    students.add(new Student(num, 1, i));
                }
            }
        }

        Collections.sort(students, (o1, o2) -> o1.no-o2.no);
        for(Student s : students) {
            sb.append(s.no).append(" ");
        }
        System.out.print(sb.toString());
        br.close();
    }
}
