//https://www.acmicpc.net/problem/1744
//Solved : 22/03/22

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int rst = 0;
        int Zero = 0;
        PriorityQueue<Integer> big = new PriorityQueue<>((x,y) -> y-x);
        PriorityQueue<Integer> small = new PriorityQueue<>();

        for(int i=0; i<N; i++){
            int n = Integer.parseInt(br.readLine());
            if(n==0) Zero++;
            else if(n>0) big.add(n);
            else small.add(n);
        }
        while(small.size()>1){
            int n1 = small.poll();
            int n2 = small.poll();
            rst += n1*n2;
        }
        while(big.size()>1){
            int n1 = big.poll();
            int n2 = big.poll();
            if(n1==1||n2==1) rst += n1+n2;
            else rst += n1*n2;
        }

        if(!small.isEmpty()&&Zero==0) rst += small.poll();
        if(!big.isEmpty())  rst += big.poll();

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}

//class Main{
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int N = Integer.parseInt(br.readLine());
//        int rst = 0;
//        int Zero = 0;
//        List<Integer> big = new ArrayList<>(N);
//        List<Integer> small = new ArrayList<>(N);
//
//        for(int i=0; i<N; i++){
//            int n = Integer.parseInt(br.readLine());
//            if(n==0) Zero++;
//            else if(n>0) big.add(n);
//            else small.add(n);
//        }
//        big.sort((x, y) -> y - x);
//        Collections.sort(small);
//        while(small.size()>1){
//            int n1 = small.get(0);
//            int n2 = small.get(1);
//            rst += n1*n2;
//            small.remove(0);
//            small.remove(0);
//            System.out.println("small : "+rst);
//        }
//        while(big.size()>1){
//            int n1 = big.get(0);
//            int n2 = big.get(1);
//            if(n1==1||n2==1) rst += n1+n2;
//            else rst += n1*n2;
//
//            big.remove(0);
//            big.remove(0);
//            System.out.println(big.size()>0?big.get(0):"Null");
//            System.out.println("big : "+rst);
//        }
//
//        if(small.size()>0&&Zero==0) rst += small.get(0);
//        System.out.println("small left : "+rst);
//        if(big.size()>0)  rst += big.get(0);
//        System.out.println("big left : "+rst);
//
//        bw.write(Integer.toString(rst));
//        bw.close();
//        br.close();
//    }
//}