package beak11047;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());           //동전 갯수
        int k = Integer.parseInt(st.nextToken());           //만들 돈가격
        int[] prices = new int[n];                          //돈 가격
        int rst = 0;                                        //결과
        for(int i=0; i<n; i++){
            prices[i] = Integer.parseInt(br.readLine());
        }
        while(k>0){
            for(int i=n-1; i>=0; i--){
                if(prices[i]<=k){
                    k=k-prices[i];
                    rst++;
                    //System.out.println(k);
                    break;
                }
            }
        }
        System.out.println(rst);
    }
}
