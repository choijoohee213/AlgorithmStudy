import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_bj_14395_4연산 {
    static class NS {
        long num;
        String str;
        NS(long num, String str) {
            this.num = num;
            this.str = str;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] in = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        if(in[0]==in[1]) { System.out.print(0); return; }
        Queue<NS> q = new ArrayDeque<>();
        Set<Long> V = new HashSet<>();
        q.offer(new NS(in[0], ""));
        V.add(in[0]);
        while (!q.isEmpty()) {
            long s = q.peek().num;
            String str = q.poll().str;
            if(s==in[1]) { System.out.print(str); return; }

            if(s*s<=in[1]&&!V.contains(s*s)) {
                q.offer(new NS(s*s, str+"*"));
                V.add(s * s);
            }

            if(s+s<=in[1]&&!V.contains(s+s)) {
                q.offer(new NS(s+s, str+"+"));
                V.add(s + s);
            }

            if(s!=0&&!V.contains(s/s)) {
                q.offer(new NS(s/s, "/"));
                V.add(s / s);
            }
        }
        System.out.print(-1);
        br.close();
    }
}
