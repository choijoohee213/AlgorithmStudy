package beakjoon.d220218.q13300;
//https://www.acmicpc.net/problem/13300
//Solved : 22/02/18

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] student = new int[2][6];                    //성별, 학년 배열
        int N = Integer.parseInt(st.nextToken());
        int Max = Integer.parseInt(st.nextToken());
        int rst=0;                                          //결과

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken())-1;
            student[gender][grade]++;
            if(student[gender][grade]==Max){                //최대값과 동일하면 지워버리기~
                student[gender][grade]-= Max;
                rst++;
            }
        }
        for(int i=0; i<2; i++){
            for(int j=0; j<6; j++){
                if(student[i][j]>0){                        //남은 학생들이 있으면 방 하나더
                    rst++;
                }
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}
