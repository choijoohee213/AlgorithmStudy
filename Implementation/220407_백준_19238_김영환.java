package com.younghwani.a220407;
/*
6 3 15
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
2 2 5 6
5 4 1 6
4 2 3 5
>>>14
 */
import java.io.*;
import java.util.*;
public class Main_bj_19238_스타트택시 {
    static int N, M, fuel, map[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1};
    static Person driver;
    static List<Person> passengers = new ArrayList<>();
    static PriorityQueue<Person> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken()); // 연료양
        // 지도 입력
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 운전자 정보
        st = new StringTokenizer(br.readLine(), " ");
        driver = new Person(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        // 승객 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fromR = Integer.parseInt(st.nextToken())-1;
            int fromC = Integer.parseInt(st.nextToken())-1;
            int toR = Integer.parseInt(st.nextToken())-1;
            int toC = Integer.parseInt(st.nextToken())-1;
            passengers.add(new Person(fromR, fromC, toR, toC));
        }
        // 이동 및 결과 출력
        System.out.println(move());
        br.close();
    }

    static int move() {
        while(M-->0) {
            // 모든 승객의 거리를 다 구해서 정보를 업데이트해준다.
            pq = new PriorityQueue<>();
            for(Person p: passengers) {
                p.dist = getDist(driver.r, driver.c, p.r, p.c);
                pq.offer(p);
            }
            // 택시와의 최단 거리인 승객을 찾는다.
            Person cp = pq.poll();
            int dist = cp.dist;
            passengers.remove(cp); // 최단거리인 승객 리스트에서 삭제

            // 승객의 위치로 이동하는 경우
            if(dist > fuel || dist == Integer.MAX_VALUE) return -1;
            fuel -= dist;

            // 찾은 승객 이동 (driver를 이동시킨다)
            dist = getDist(cp.r, cp.c, cp.toR, cp.toC);
            if(dist > fuel || dist == Integer.MAX_VALUE) return -1;
            driver.r = cp.toR;
            driver.c = cp.toC;
            fuel -= dist;

            // 이동 완료했으면 fuel += 최단이동거리*2;
            fuel += dist * 2;
        }
        return fuel;
    }
    // 출발 위치에서 목적지 사이의 최단 거리
    static int getDist(int sr, int sc, int er, int ec) {
        ArrayDeque<Person> q = new ArrayDeque<>();
        boolean[][] V = new boolean[N][N];
        q.offer(new Person(sr, sc, 0));
        V[sr][sc] = true;
        while (!q.isEmpty()) {
            Person cur = q.poll();
            if(cur.r==er && cur.c==ec) return cur.dist; // 목적지 도착 시 거리 반환
            for (int d = 0; d < 4; d++) {
                int ni=cur.r+di[d], nj=cur.c+dj[d];
                if(ni<0||ni>=N||nj<0||nj>=N||map[ni][nj]==1||V[ni][nj]) continue;
                V[ni][nj] = true;
                q.offer(new Person(ni, nj, cur.dist + 1));
            }
        }
        return Integer.MAX_VALUE; // 목적지 못가면 패널티 부여
    }

    static class Person implements Comparable<Person> {
        int r, c, toR, toC, dist=0;
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public Person(int r, int c, int dist) {
            this(r, c);
            this.dist = dist;
        }
        public Person(int r, int c, int toR, int toC) {
            this(r, c);
            this.toR = toR;
            this.toC = toC;
        }
        @Override
        public int compareTo(Person o) {
            if(this.dist==o.dist) {
                if(this.r ==o.r) return Integer.compare(this.c, o.c);
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.dist, o.dist);
        }
    }
}

// 깔끔한 코드를 위해 운전자, 승객, 승객과 운전자의 이동거리 클래스를 하나로 합쳤다.
// 아무리 생각해봐도 로직엔 문제 없다고 보여, 시간을 많이 잡아 먹을 것 같은 리스트 정렬 로직을 우선순위 큐 활용 로직으로 변경 -> 성공
/* 시간 초과가 난다. 효율적인 방향으로 수정이 필요하다.
public class Main_bj_19238_스타트택시 {
    static int N, M, fuel, map[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1};
    static Driver driver;
    static List<Passenger> passengers = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken()); // 연료양
        // 지도 입력
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 운전자 정보
        st = new StringTokenizer(br.readLine(), " ");
        driver = new Driver(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        // 승객 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fromR = Integer.parseInt(st.nextToken())-1;
            int fromC = Integer.parseInt(st.nextToken())-1;
            int toR = Integer.parseInt(st.nextToken())-1;
            int toC = Integer.parseInt(st.nextToken())-1;
            passengers.add(new Passenger(fromR, fromC, toR, toC));
        }
        // 이동 및 결과 출력
        System.out.println(move());
        br.close();
    }

    static int move() {
        while(M-->0) {
            // 모든 승객의 거리를 다 구해서 정보를 업데이트해준다.
            for(Passenger p: passengers) {
                p.dist = getDist(driver.r, driver.c, p.fromR, p.fromC);
            }
            // dist -> r -> c 순으로 정렬해준다.
            Collections.sort(passengers);
            // 택시와의 최단 거리인 승객을 찾는다.
            Passenger cp = passengers.get(0); // current passenger
            int dist = cp.dist;
            passengers.remove(cp); // 최단거리인 승객 리스트에서 삭제

            // 승객의 위치로 이동하는 경우
            if(dist > fuel || dist == Integer.MAX_VALUE) return -1;
            fuel -= dist;

            // 찾은 승객 이동 (driver를 이동시킨다)
            dist = getDist(cp.fromR, cp.fromC, cp.toR, cp.toC);
            if(dist > fuel || dist == Integer.MAX_VALUE) return -1;
            driver.r = cp.toR;
            driver.c = cp.toC;
            fuel -= dist;

            // 이동 완료했으면 fuel += 최단이동거리*2;
            fuel += dist * 2;
        }
        return fuel;
    }
    // 출발 위치에서 목적지 사이의 최단 거리
    static int getDist(int sr, int sc, int er, int ec) {
        ArrayDeque<Distance> q = new ArrayDeque<>();
        boolean[][] V = new boolean[N][N];
        q.offer(new Distance(sr, sc, 0));
        V[sr][sc] = true;
        while (!q.isEmpty()) {
            Distance cur = q.poll();
            if(cur.r==er && cur.c==ec) return cur.dist; // 목적지 도착 시 거리 반환
            for (int d = 0; d < 4; d++) {
                int ni=cur.r+di[d], nj=cur.c+dj[d];
                if(ni<0||ni>=N||nj<0||nj>=N||map[ni][nj]==1||V[ni][nj]) continue;
                V[ni][nj] = true;
                q.offer(new Distance(ni, nj, cur.dist + 1));
            }
        }
        return Integer.MAX_VALUE; // 목적지 못가면 패널티 부여
    }

    static class Driver {
        int r, c;
        public Driver(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Passenger implements Comparable<Passenger> {
        int fromR, fromC, toR, toC, dist=0;
        public Passenger(int fromR, int fromC, int toR, int toC) {
            this.fromR = fromR;
            this.fromC = fromC;
            this.toR = toR;
            this.toC = toC;
        }
        @Override
        public int compareTo(Passenger o) {
            if(this.dist==o.dist) {
                if(this.fromR==o.fromR) return Integer.compare(this.fromC, o.fromC);
                return Integer.compare(this.fromR, o.fromR);
            }
            return Integer.compare(this.dist, o.dist);
        }
    }
    static class Distance {
        int r, c, dist;
        public Distance(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
 */