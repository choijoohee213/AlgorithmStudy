//https://www.acmicpc.net/problem/21610
//Solved : 22/0418

import java.util.*;
import java.io.*;

class Main{
    static int[][] dir = new int[][]{{0,-1},{-1,-1},{-1,0},{-1,1},
            {0,1},{1,1},{1,0},{1,-1}};
    static int[] dr = {0,-1,-1,-1,0,1,1,1};

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int sum=0;

        int[][]map = new int[n][n];
        boolean[][] chk;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {n-1,0});
        que.add(new int[] {n-1,1});
        que.add(new int[] {n-2,0});
        que.add(new int[] {n-2,1});

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            //Set<int[]> set = new HashSet<>();
            chk=new boolean[n][n];      //구름이 있다 사라진칸 체크
            //구름 이동
            int L = que.size();
            for (int j = 0; j < L; j++) {
                int[] now = que.poll();
                int newX = now[0]+dir[d][0]*s;
                int newY = now[1]+dir[d][1]*s;
                newX = ((newX%n)+n)%n;
                newY = ((newY%n)+n)%n;
                //1증가
                map[newX][newY]++;
                que.add(new int[] {newX,newY});
            }

            //이동 후 물양 증가
            for (int j = 0; j < L; j++) {
                int[] now = que.poll();
                if(now[0]-1>=0 && now[1]-1>=0 && map[now[0]-1][now[1]-1]>0) {
                    map[now[0]][now[1]]++;
                }if(now[0]-1>=0 && now[1]+1<n && map[now[0]-1][now[1]+1]>0) {
                    map[now[0]][now[1]]++;
                }if(now[0]+1<n && now[1]-1>=0 && map[now[0]+1][now[1]-1]>0) {
                    map[now[0]][now[1]]++;
                }if(now[0]+1<n && now[1]+1<n && map[now[0]+1][now[1]+1]>0) {
                    map[now[0]][now[1]]++;
                }
                chk[now[0]][now[1]]=true;
                //set.add(new int[]{now[0], now[1]});       //배열은 equals안댐
            }

            //신규 구름 생성
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(!chk[j][k] && map[j][k]>=2) {
                        map[j][k]-=2;
                        que.add(new int[] {j,k});
                    }
                }
            }
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                sum+=map[x][y];
            }
        }
        bw.write(Integer.toString(sum));
        bw.close();
        br.close();
    }
}