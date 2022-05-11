//https://www.acmicpc.net/problem/2109
//Solved : 22/03/18

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> que = new PriorityQueue<>((x,y) -> x[0] == y[0] ? y[1] - x[1] : y[0] - x[0]);  //가격 내림차순 같으면 날짜 내림차순
        Set<Integer> chk = new HashSet<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            que.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        long rst = 0;
        while(!que.isEmpty()){
            int[] n = que.poll();
            if(!chk.contains(n[1])){                //만약 그 강연날짜에 가능하면 그냥 추가
                chk.add(n[1]);
                rst += n[0];
            }else {
                int time = n[1]-1;                  //그렇지 않으면 하루씩 앞으로 떙기며 가능 한 날짜 있는지 확인
                while(time>0){
                    if(!chk.contains(time)){        //있으면 바로 추가후 while문 탈출
                        chk.add(time);
                        rst += n[0];
                        break;
                    }
                    time--;
                }
            }
        }

        bw.write(Long.toString(rst));
        bw.close();
        br.close();
    }
}