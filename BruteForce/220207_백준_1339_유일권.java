package beakjoon.q1339;
//https://www.acmicpc.net/problem/1339
//Solved : 22/02/07

import java.io.*;

public class Main {

    static boolean[] chk = new boolean[10];         //사용한 숫자 체크
    static int[] alpabet = new int[]{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2};
                                                    //알파벳당 값을 저장할 배열(조금 지저분..)
    static char[][] words;                          //단어 배열
    static int alpabet_num;                         //알파벳 갯수
    static long MAX = Long.MIN_VALUE;               //값 비교

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine());
        words = new char[K][];
        for(int i=0; i<K; i++){
            words[i] = br.readLine().toCharArray();
            for(int j=0; j<words[i].length; j++){
                alpabet[words[i][j]-'A'] = -1;          //값의 유무를 -1로 확인
            }
        }
        //System.out.println(Arrays.toString(alpabet));
        alpabet_num = 0;
        for(int i=0; i<26; i++){
            if(alpabet[i]!=-2){
                alpabet_num++;                          //값의 총 갯수 확인
            }
        }
        //System.out.println(alpabet_num);
        mk_num(0,'A');

        bw.write(Long.toString(MAX));                   //출력
        bw.close();
        br.close();
    }
    //알파벳에 숫자를 정하는 함수
    static void mk_num(int num, char now){
        if(num==alpabet_num){               //알파벳 갯수와 동일하면 값 비교 후 종료
            chk_num();
            return;
        }
        int t=-1;
        for(int i=0; i<26-now+'A'; i++){    //더 설정해야하는 알파벳이 있는지 확인
            if(alpabet[now+i-'A']==-1){
                t = i;
                break;
            }
        }
        //System.out.print(t+" "+alpabet_num+" "+num+" "+now+" ");
        //System.out.println(Arrays.toString(alpabet));
        if(t!=-1) {                         //값을 정해야 하는 알파벳이 남았을 경우
            for (int i = 0; i < 10; i++) {  //0~9까지 안쓴것 골라서 써보고 다시 원상복귀
                if (!chk[i]) {
                    chk[i] = true;
                    alpabet[now+t-'A'] = i;
                    mk_num(num + 1, (char) (now + 1));
                    chk[i] = false;
                    alpabet[now+t-'A'] = -1;
                }
            }
        }
    }
    //숫자가 정해진 알파벳을 통해 총 합 계산
    static void chk_num(){
        long sum  = 0;
        for(int i=0; i< words.length; i++){
            long num_word= 0;
            for(int j=0; j<words[i].length; j++){
                num_word = (num_word*10) + alpabet[words[i][j]-'A'];
            }
            sum += num_word;
        }
        //System.out.print("chk sum : "+sum);
        //System.out.println(Arrays.toString(alpabet));
        MAX = Math.max(sum, MAX);
    }
}
