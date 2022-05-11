import java.io.*;
import java.util.*;
public class Main_bj_9019_DSLR {
    static class Command {
        int n;
        ArrayList<String> list;
        public Command(int n, ArrayList<String> list) {
            this.n=n;
            this.list=list;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            Queue<Command> q = new ArrayDeque<>();
            boolean[] V = new boolean[10000];
            V[from] = true;
            Command cmd = new Command(from, new ArrayList<>());
            q.offer(cmd);
            while (!q.isEmpty()) {
                Command ccmd = q.poll();
                int cur = ccmd.n;
                ArrayList<String> cLst = ccmd.list;
                if(cur==to) {
                    cLst.forEach(s -> sb.append(s));
                    sb.append("\n");
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    ArrayList<String> tmp = (ArrayList<String>) cLst.clone();
                    int n = 0;
                    if(i==0) { // D
                        n = (cur*2) % 10000;
                        tmp.add("D");
                    } else if(i==1) { // S
                        n = (cur==0) ? 9999: cur-1;
                        tmp.add("S");
                    } else if(i==2) {
                        n = cur%1000*10 + cur/1000;
                        tmp.add("L");
                    } else if(i==3) {
                        n = cur%10*1000 + cur/10;
                        tmp.add("R");
                    }
                    if(V[n]) continue;
                    q.offer(new Command(n, tmp));
                    V[n]=true;
                }
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
