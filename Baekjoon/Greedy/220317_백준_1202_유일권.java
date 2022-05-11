//https://www.acmicpc.net/problem/1202
//Solved : 21/03/17

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<int[]> jewel = new ArrayList<>(N);
        PriorityQueue<Integer> bag = new PriorityQueue<>();
        PriorityQueue<Integer> small = new PriorityQueue<>((x,y) -> y-x);
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            jewel.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        Collections.sort(jewel, (x,y)-> x[0] != y[0] ? x[0] - y[0] : y[1] - x[1]);

        for(int i=0; i<K; i++){
            bag.add(Integer.parseInt(br.readLine()));
        }

        long rst=0;
        int idx = 0;
        while(!bag.isEmpty()){
            int bag_size = bag.poll();
            while(idx < jewel.size() && jewel.get(idx)[0] <= bag_size){
                small.add(jewel.get(idx++)[1]);
            }
            if(!small.isEmpty()) rst += small.poll();
        }
        bw.write(Long.toString(rst));
        bw.close();
        br.close();
    }
}