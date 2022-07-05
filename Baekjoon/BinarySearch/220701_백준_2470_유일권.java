//https://www.acmicpc.net/problem/2470
//solved : 22/07/04
import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int max = N-1;
        int min = 0;
        int best = Math.abs(arr[max] + arr[min]);
        int[] rst = new int[]{arr[min], arr[max]};

        if(best != 0) {
            while (min < max){
                int sum = arr[max] + arr[min];
                if(sum == 0){
                    rst[0] = arr[min];
                    rst[1] = arr[max];
                    break;
                }else if(sum > 0){
                    if (best > Math.abs(sum)) {
                        best = Math.abs(sum);
                        rst[0] = arr[min];
                        rst[1] = arr[max];
                    }
                    max--;
                }else{
                    if (best > Math.abs(sum)) {
                        best = Math.abs(sum);
                        rst[0] = arr[min];
                        rst[1] = arr[max];
                    }
                    min++;
                }
            }
        }

//        if(diff != 0) {
//            while (min < max) {
//                int left = Integer.MAX_VALUE;
//                int right = Integer.MAX_VALUE;
//
//                if (max - min >= 1) {
//                    left = Math.abs(arr[max] + arr[min + 1]);
//                    if (left == 0) {
//                        min++;
//                        rst[0] = arr[min];
//                        rst[1] = arr[max];
//                        break;
//                    }
//                    right = Math.abs(arr[max - 1] + arr[min]);
//                    if (right == 0) {
//                        max--;
//                        rst[0] = arr[min];
//                        rst[1] = arr[max];
//                        break;
//                    }
//                }
//
//                if (right <= left){
//                    max--;
//                    if(right < diff){
//                        rst[0] = arr[min];
//                        rst[1] = arr[max];
//                    }
//                }else{
//                    min++;
//                    if(left < diff){
//                        rst[0] = arr[min];
//                        rst[1] = arr[max];
//                    }
//                }
//            }
//        }

        StringBuilder sb = new StringBuilder();
        sb.append(rst[0]).append(" ").append(rst[1]);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
/*
5
-100 -50 20 40 80

 */