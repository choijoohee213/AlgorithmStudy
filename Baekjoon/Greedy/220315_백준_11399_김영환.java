import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main_bj_11399_ATM {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(P);
        int tmp=0, res=0;
        for (int i = 0; i < N; i++) {
            res += tmp+P[i];
            tmp += P[i];
        }
        System.out.print(res);
        br.close();
    }
}
