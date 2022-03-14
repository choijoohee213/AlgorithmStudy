import java.io.*;
import java.util.stream.Stream;
public class Main_bj_11047_동전0 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] coin = new int[in[0]];
        for (int i = 0; i < in[0]; i++) coin[i] = Integer.parseInt(br.readLine());
        int cnt = 0, idx = in[0]-1;
        while (in[1]!=0) {
            cnt += in[1]/coin[idx];
            in[1]=in[1]%coin[idx];
            idx--;
        }
        System.out.print(cnt);
        br.close();
    }
}
