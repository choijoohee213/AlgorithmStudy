//https://www.acmicpc.net/problem/9019
//Solved : 22/03/03

import java.util.*;
import java.io.*;

class Main{
    static class DSLR{              //현재 숫자, 여태 움직인 string
        int num;
        String str;

        public DSLR(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }
    static Set<Integer> used;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            used = new HashSet<>();
            sb.append(BFS(A, B)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static String BFS(int A, int B){
        Queue<DSLR> que = new ArrayDeque<>();
        StringBuilder sb;
        que.add(new DSLR(A, ""));
        int next;
        while(!que.isEmpty()){
            DSLR now = que.poll();

            sb = new StringBuilder(now.str);
            sb.append('D');
            next = (now.num*2)%10000;
            if(next == B) return sb.toString();
            else if(!used.contains(next)){
                used.add(next);
                que.add(new DSLR(next, sb.toString()));
            }

            sb = new StringBuilder(now.str);
            sb.append('S');
            if(now.num == 0) next = 9999;
            else next = now.num-1;
            if(next == B) return sb.toString();
            else if(!used.contains(next)){
                used.add(next);
                que.add(new DSLR(next, sb.toString()));
            }

            sb = new StringBuilder(now.str);
            sb.append('L');
            next = now.num%1000*10+now.num/1000;
            if(next == B) return sb.toString();
            else if(!used.contains(next)){
                used.add(next);
                que.add(new DSLR(next, sb.toString()));
            }

            sb = new StringBuilder(now.str);
            sb.append('R');
            next = now.num/10+now.num%10*1000;
            if(next == B) return sb.toString();
            else if(!used.contains(next)){
                used.add(next);
                que.add(new DSLR(next, sb.toString()));
            }
        }

        return "";
    }
}