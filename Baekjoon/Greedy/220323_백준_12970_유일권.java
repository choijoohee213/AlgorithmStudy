//https://www.acmicpc.net/problem/12970
//Solved : 22/03/22

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean chk = false;                //정답 여부 확인용 변수

        if(K==0){                           //만약 K가 0이면 걍 다 A로 채움
            chk = true;
            for(int i=0; i<N; i++){
                sb.append('A');
            }
        }
        else {
            for (int i = N; i >= 0; i--) {  //a가 i개면 b는 N-i개이므로 그때 최대 값은 i*(N-i)이므로 가능한 경우 찾기
                if (i * (N - i) < K) continue;  //최대 값보다 작으면 불가능 하므로 스킵
                chk = true;
                int a = i-(i*(N-i) - K);        //몇번째에 B를 하나 넣어야 하는지
                int b = N-i;                    //B의 총 갯수
                for(int j=1; j<=i; j++){
                    sb.append('A');
                    if(j==a){
                        sb.append('B');
                        b--;
                    }
                }
                for(int j=1; j<=b; j++){
                    sb.append('B');
                }
                break;
            }
            if(!chk) sb.append("-1");           //결과를 못냈음 -1 출력
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}