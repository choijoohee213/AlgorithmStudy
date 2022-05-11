import java.io.*;
import java.util.*;

public class Main_bj_1931_회의실배정 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]==o2[1]?o1[0]-o2[0]:o1[1]-o2[1];
            }
        });

        int cnt=0, prev=0;
        for (int i = 0; i < N; i++) {
            if(prev<=arr[i][0]) {
                prev=arr[i][1];
                cnt++;
            }
        }
        System.out.print(cnt);
        br.close();
    }
}
