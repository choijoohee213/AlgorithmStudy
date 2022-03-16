//https://www.acmicpc.net/problem/2138
//Solved : 22/03/16

import java.util.*;
import java.io.*;

class Main{
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int rst1=1, rst2=0;             //1번 이미 켰으니 1,0

        boolean[] m1 = new boolean[N];  //1번 킨거
        boolean[] m2 = new boolean[N];  //1번 안킨거
        boolean[] t = new boolean[N];
        String str = br.readLine();
        for(int i=0; i<N; i++){
            m1[i] = str.charAt(i)=='1';
            m2[i] = m1[i];
        }
        str = br.readLine();
        for(int i=0; i<N; i++) t[i] = str.charAt(i)=='1';

        m1[0] = !m1[0];
        m1[1] = !m1[1];

        for(int i=0; i<N-1; i++){
            if(m1[i] != t[i]){              //목표와 다르면
                if(i<N-2){                  //3칸 바꿀수 있으면 3칸 바꾸고
                    for(int j=0; j<3; j++) m1[i+j] = !m1[i+j];
                }else{                      //아니면 2칸만
                    for(int j=0; j<2; j++) m1[i+j] = !m1[i+j];
                }
                rst1++;                     //스위치 눌렀으니 +1
            }
            if(m2[i] != t[i]){              //마찬가지
                if(i<N-2){
                    for(int j=0; j<3; j++) m2[i+j] = !m2[i+j];
                }else{
                    for(int j=0; j<2; j++) m2[i+j] = !m2[i+j];
                }
                rst2++;
            }
        }
        if(m1[N-1] != t[N-1]) rst1 = INF;   //끝이 다르면 안되는것이므로 값 삭제
        if(m2[N-1] != t[N-1]) rst2 = INF;   //22

        if(rst1==INF && rst2==INF) bw.write("-1");                  //값 없음 -1
        else bw.write(Integer.toString(rst1 < rst2 ? rst1 : rst2));     //둘중 하나라도 있으면 출력

        bw.close();
        br.close();
    }
}