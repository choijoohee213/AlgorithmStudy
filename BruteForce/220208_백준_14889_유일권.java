package beakjoon.q14889;
//https://www.acmicpc.net/problem/14889
//Solved : 22/02/08

import java.util.*;
import java.io.*;

public class Main {
    static int[][] S;
    static int Min = Integer.MAX_VALUE;
    static boolean[] team;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int Num = Integer.parseInt(br.readLine());
        S = new int[Num][Num];
        team = new boolean[Num];

        StringTokenizer st;
        for(int i=0; i<Num; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<Num; j++){
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        mk_team(Num, 0, 0);
        bw.write(Integer.toString(Min));
        bw.close();
        br.close();
    }

    static void mk_team(int max, int checked, int now){
        //System.out.println("mk_team : "+max+" "+checked+" "+now);
        if(max/2 == checked){
            //System.out.println("team : "+Arrays.toString(team));
            int t1=0, t2=0;
            for(int i=0; i<S.length; i++){
                if(team[i]){
                    for(int j=0; j<S.length; j++){
                        if(team[j]) {
                            t1 += S[i][j];
                        }
                    }
                }else{
                    for(int j=0; j<S.length; j++){
                        if(!team[j]) {
                            t2 += S[i][j];
                        }
                    }
                }
            }
            //System.out.println("Checking : "+ Min + " "+t1+" "+ t2);
            Min = Math.min(Min, Math.abs(t1 - t2));
            return;
        }
        if(max/2-checked > max-now){
            //System.out.println("mk_team chk2 : "+max+" "+checked+" "+now);
            return;
        }
        team[now] = true;
        mk_team(max, checked+1, now+1);
        team[now] = false;
        mk_team(max, checked, now+1);
    }
}
