//https://www.acmicpc.net/problem/7490
//Solved : 22/04/22

import java.util.*;
import java.io.*;

class Main{
    static List<int[]> rst;
    static int[] arr;
    static char[] op = {' ', '+', '-'};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            int n = Integer.parseInt(br.readLine());
            rst = new ArrayList<>();
            arr = new int[n-1];

            DFS(n, 0);
            if(rst.isEmpty()) sb.append("\n");
            else {
                for (int[] i : rst) {
                    sb.append('1');
                    for (int j = 0; j < i.length; j++) {
                        sb.append(op[i[j]]).append(j + 2);
                    }
                    sb.append("\n");
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void DFS(int n, int idx){
        if(idx==n-1){
            int sum = calc(n);
            if(sum==0){
                int[] tmp = arr.clone();
                rst.add(tmp);
            }
            return;
        }
        for(int i=0; i<3; i++){
            arr[idx] = i;
            DFS(n, idx+1);
        }
    }
    static int calc(int n){
        int sum = 1;
        int before = 1;
        int oper = 1;
        for(int i=0; i<arr.length; i++){
            int cur = i+2;
            if(arr[i]==0){
                if(oper==1){
                    sum -= before;
                    before = before*10 + cur;
                    sum += before;
                }else{
                    sum += before;
                    before = before*10 + cur;
                    sum -= before;
                }
            }else{
                if(arr[i]==1) {
                    sum += cur;
                    before = cur;
                    oper = 1;
                }else{
                    sum -= cur;
                    before = cur;
                    oper = 2;
                }
            }
        }
        return sum;
    }
}