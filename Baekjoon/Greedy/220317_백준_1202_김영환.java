package com.younghwani.a220317;

import java.io.*;
import java.util.*;

public class Main_bj_1202_보석도둑 {
    static class Jewel {
        int weight, cost;
        public Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(jewels, (o1, o2) -> o1.weight-o2.weight); // 무게 순 natural order

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);

        long res=0;
        PriorityQueue<Jewel> costs = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost); // 비용 순 reverse order
        for (int i = 0, idx = 0; i < K; i++) {
            while (idx<N && jewels.get(idx).weight<=bags[i]) {
                Jewel jewel = jewels.get(idx++);
                costs.offer(new Jewel(jewel.weight, jewel.cost));
            }
            if(!costs.isEmpty()) res += costs.poll().cost;
        }

        System.out.println(res);
        br.close();
    }
}

/*
//시간 초과 난다.
import java.io.*;
import java.util.*;
public class Main {
    static class Jewel implements Comparable<Jewel> {
        int weight, cost;
        public Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(cost, o.cost)==0 ? -Integer.compare(weight, o.weight) : -Integer.compare(cost, o.cost);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(jewels);
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);
        int res=0;
        loop:for (int i = 0; i < K; i++) {
            for (int j = 0; j < jewels.size(); j++) {
                Jewel jewel = jewels.get(j);
                if(jewel.weight <= bags[i]) {
                    res += jewel.cost;
                    jewels.remove(j);
                    continue loop;
                }
            }
        }
        System.out.println(res);
        br.close();
    }
}
 */