package beakjoon.d220218.q14696;
//https://www.acmicpc.net/problem/14696
//Solved : 22/02/18

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for(int round=0; round<N; round++){
            int[] A = new int[4];               //A배열
            int[] B = new int[4];               //B배열
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            for(int i=0; i<l; i++){             //숫자만큼 인덱스값 더해주기
                A[Integer.parseInt(st.nextToken())-1]++;
            }
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            for(int i=0; i<l; i++){             //A와 동일
                B[Integer.parseInt(st.nextToken())-1]++;
            }
            if(A[3]==B[3]) {                    //값 비교해서 결과 출력
                if(A[2]==B[2]){                 //더 간결하게 가능했을까...
                    if(A[1]==B[1]){
                        if(A[0]==B[0]){
                            sb.append("D\n");
                        }else if(A[0]>B[0]) sb.append("A\n");
                        else sb.append("B\n");
                    }else if(A[1]>B[1]) sb.append("A\n");
                    else sb.append("B\n");
                }else if(A[2]>B[2]) sb.append("A\n");
                else sb.append("B\n");
            }else if(A[3]>B[3]) sb.append("A\n");
            else sb.append("B\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
