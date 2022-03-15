//https://www.acmicpc.net/problem/14395
//Solved : 22/03/14

import java.util.*;
import java.io.*;

class Main{
    static class Node{
        long s;
        String str;

        public Node(long s, String str) {
            this.s = s;
            this.str = str;
        }
    }
    static int S,T;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        if(S==T) bw.write("0");
        else bw.write(BFS());
        bw.close();
        br.close();
    }
    static String BFS(){
        Queue<Node> que = new ArrayDeque<>();
        Set<Long> set = new HashSet<>();
        set.add((long)S);
        que.add(new Node(S, ""));
        while(!que.isEmpty()){
            Node n = que.poll();
            long num = n.s;

            long next = num*num;
            if(next==T) return n.str+"*";
            if(!set.contains(next)){
                set.add(next);
                que.add(new Node(next, n.str+"*"));
            }

            next = num+num;
            if(next==T) return n.str+"+";
            if(!set.contains(next)){
                set.add(next);
                que.add(new Node(next, n.str+"+"));
            }

            next = 0;
            if(next==T) return n.str+"-";
            if(!set.contains(next)){
                set.add(next);
                que.add(new Node(next, n.str+"-"));
            }

            if(num!=0) {
                next = 1;
                if (next == T) return n.str + "/";
                if (!set.contains(next)) {
                    set.add(next);
                    que.add(new Node(next, n.str + "/"));
                }
            }
        }
        return "-1";
    }
}