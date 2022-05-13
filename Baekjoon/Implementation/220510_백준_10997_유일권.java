//https://www.acmicpc.net/problem/10997
//Solved : 22/05/10

import java.io.*;

class Main{
    static int N,r,c;
    static boolean[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        if(N==1){
            sb.append('*');
        }else {
            r=7 + 4*(N-2);
            c=5 + 4*(N-2);
            arr = new boolean[r][c];
            recur(N);
            loop:for(int i=0; i<r; i++){
                for(int j=0; j<c; j++){
                    if(arr[i][j]) {
                        sb.append("*");
                        if(i==1){
                            sb.append("\n");
                            continue loop;
                        }
                    }
                    else sb.append(" ");
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    static void recur(int idx){
        if(idx==2){
            for(int i=r/2-3; i<r/2+4; i++){
                for(int j=c/2-2; j<c/2+3; j++){
                    if(i==r/2-3 || i==r/2+3
                            || (i==r/2-2 && j==c/2-2)
                            || (i==r/2-1 && j!=c/2-1)
                            || (i==r/2 && j%2==0)
                            || (i==r/2+1 && j%2==0)
                            || (i==r/2+2 && (j==c/2-2 || j==c/2+2))){
                        arr[i][j] = true;
                    }
                }
            }
            return;
        }
        int minR = r/2-3 - 2*(idx-2);
        int maxR = r/2+3 + 2*(idx-2);
        int minC = c/2-2 - 2*(idx-2);
        int maxC = c/2+2 + 2*(idx-2);
        for(int i=minR; i<=maxR; i++){
            if(i==maxR || i==minR){
                for(int j=minC; j<=maxC; j++){
                    arr[i][j] = true;
                }
            }else if(i==minR+2){
                arr[i][minC] = true;
                arr[i][maxC-1] = true;
                arr[i][maxC] = true;
            }else if(i==minR+1){
                arr[i][minC] = true;
            }else{
                arr[i][minC] = true;
                arr[i][maxC] = true;
            }
        }
        recur(idx-1);
    }
}