//https://www.acmicpc.net/problem/1963
//Solved : 22/03/13

import java.util.*;
import java.io.*;

class Main {
    static int[] d = {1000, 100, 10, 1};
    static boolean[] isNotPrime = new boolean[10000];
    static boolean[] discovered = new boolean[10000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T= Integer.parseInt(br.readLine());

        findPrimeNum();

        for(int tc=0; tc<T; tc++){// T가 양수일때 진행
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(A==B){
                sb.append(0).append("\n");
                continue;
            }

            int ret = bfs(A,B);

            sb.append(ret).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(int A, int B){

        discovered = new boolean[10000];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(A);
        discovered[A] = true;

        int step = -1;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int i = 0; i <size ; i++) {
                int u = queue.poll();
                // 종료조건(내가 찾던 값을 찾으면, 지금까지 진행한 step변수 리턴
                if(u==B){
                    return step;
                }
                // u->자리수 분할
                int[] cihper = new int[4];
                for (int j = 0; j <4 ; j++) {
                    cihper[j] = u/d[j];
                    u %= d[j];
                }
                // 탐색
                for (int j = 0; j <4 ; j++) {
                    for (int k = 0; k <10 ; k++) {
                        cihper[j] = cihper[j] + 1 > 9 ? 0 : cihper[j]+1;
                        int nextNum = 0;
                        for (int l = 0; l <4 ; l++) {
                            // d배열은 각 자리수를 표현해주기 위해 만든 배열이다.
                            nextNum += cihper[l]*d[l];
                        }
                        // 만든 자리수가 1000미만, 이미 체크했던것인지, 소수인지 확인하기.
                        if (nextNum < 1000||discovered[nextNum]||isNotPrime[nextNum]) continue;

                        // 모든 조건이 맞으면 queue에 넣고, discovered 배열에도 true표시
                        queue.add(nextNum);
                        discovered[nextNum] = true;
                    }
                }

            }
        }
        return -1;
    }


    private static void findPrimeNum(){
        // 소수인지 판별
        for (int i = 2; i <10000 ; i++) {
            if(isNotPrime[i]) continue;
            for (int j = 2; j*i <10000 ; j++) {
                isNotPrime[i*j] = true;
            }
        }
    }
}