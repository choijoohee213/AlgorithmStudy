package d220213.q2628;
//https://www.acmicpc.net/problem/2628
//solved : 22/02/13
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<Integer> queX = new PriorityQueue<>();            //가로 세로 PQ선언
        Queue<Integer> queY = new PriorityQueue<>();            //잘린 지점을 저장
        int rst = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x =  Integer.parseInt(st.nextToken());     //가로
        int y =  Integer.parseInt(st.nextToken());     //세로

        int N = Integer.parseInt(br.readLine());
        queX.add(0);                                    //시작점, 끝점 저장
        queY.add(0);
        queX.add(x);
        queY.add(y);

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int way = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            if(way==1) queX.add(point);                 //1이면 가로에 저장
            else queY.add(point);                       //2이면 세로에 저장
        }

        x = 0;          //변수 재활용...^-^
        y = 0;
        while(queX.size()>1){                           //PQ빠져나오며 다음값과의 차이가 잴 큰것들 확인
            int now = queX.poll();                      //단 peek하기 위하여 사이즈는 1보다 커야함
            x = Math.max(x, queX.peek()-now);
        }
        while(queY.size()>1){
            int now = queY.poll();
            y = Math.max(y, queY.peek()-now);
        }
        rst = x*y;                                      //가로*세로 = 넓이

        bw.write(Integer.toString(rst));
        br.close();
        bw.close();
    }
}
