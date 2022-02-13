package d220213.q2628;
//https://www.acmicpc.net/problem/2628
//solved : 22/02/13
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<Integer> queX = new PriorityQueue<>();
        Queue<Integer> queY = new PriorityQueue<>();
        int rst = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x =  Integer.parseInt(st.nextToken());     //가로
        int y =  Integer.parseInt(st.nextToken());     //세로

        int N = Integer.parseInt(br.readLine());
        queX.add(0);
        queY.add(0);
        queX.add(x);
        queY.add(y);

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int way = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            if(way==1) queX.add(point);
            else queY.add(point);
        }

        x = 0;
        y = 0;
        while(queX.size()>1){
            int now = queX.poll();
            x = Math.max(x, queX.peek()-now);
        }
        while(queY.size()>1){
            int now = queY.poll();
            y = Math.max(y, queY.peek()-now);
        }
        rst = x*y;

        bw.write(Integer.toString(rst));
        br.close();
        bw.close();
    }
}
