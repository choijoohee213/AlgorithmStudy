package d220213.q2605;
//https://www.acmicpc.net/problem/
//solved : 22/02/13

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Integer> line = new ArrayList<>(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            line.add(line.size() - a, i + 1);
        }
        for (int i = 0; i < N; i++) {
            sb.append(line.get(i)).append(" ");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
