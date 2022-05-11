package beakjoon.d220224.q1062;
//https://www.acmicpc.net/problem/1062
//Solved : 22/02/24

import java.util.*;
import java.io.*;

public class Main {
    static int N, K, rst=0;
    static boolean[][] words;
    static boolean[] learn;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K<5){            //a, c, i, n, t는 필수 이므로 5개 미만이면 검사할 필요가 없음
            bw.write(Integer.toString(rst));    //추후 입력되는 단어들 안받아도 정답
            bw.close();
            br.close();
            System.exit(0);
        }
        words = new boolean[N][26];
        learn = new boolean[26];

        learn[0] = true;            //a, c, i, n, t는 필수 이므로 이거는 무조건 사용
        learn['c' - 'a'] = true;
        learn['i' - 'a'] = true;
        learn['n' - 'a'] = true;
        learn['t' - 'a'] = true;

        for(int i=0; i<N; i++){
            String t = br.readLine();
            for(int j=0; j<t.length(); j++){
                words[i][t.charAt(j)-'a'] = true;
            }
        }

        dfs(1, 5);     //a를 무조건 쓰므로 idx는 1로 시작
                                // a,c,i,n,t는 필수라 이미 사용됬으므로 5
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void dfs(int idx, int used){
        if(used == K){
            int w = 0;
            loop:for(int i=0; i<N; i++){
                for(int j=0; j<26; j++){
                    if(!learn[j]&&words[i][j]) continue loop;   //사용된 알파벳인데 배운 알파벳이 아님 다음 단어
                }
                w++;                //다 배운 알파벳이므로 결과+1
            }
            rst = rst > w ? rst : w;    //최대값 대입 후 종료
            return;
        }
        for(int i=idx; i<26; i++){
            if(i=='c'-'a'||i=='i'-'a'||i=='n'-'a'||i=='t'-'a') continue;    //필수알파벳은 스킵
            learn[i] = true;
            dfs(i+1, used+1);
            learn[i] = false;
        }
    }
}
