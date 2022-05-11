//https://www.acmicpc.net/problem/10610
//Solved : 22/03/22

import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        long total=0;                       //전체 수 합쳐서 3으로 나눴을때 나머지가 0이여야 한다.
        int[] arr = new int[10];
        for(int i=0; i<str.length(); i++){
            int now = str.charAt(i)-'0';
            arr[now]++;
            total += now;
        }

        if(total%3==0 && arr[0]!=0){
            int now = 9;
            while(now>=0){
                if(arr[now]==0) now--;
                else{
                    arr[now]--;
                    sb.append(now);
                }
            }
        }else{
            sb.append("-1");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}