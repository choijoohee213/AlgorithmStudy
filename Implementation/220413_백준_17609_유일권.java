//https://www.acmicpc.net/problem/17609
//Solved : 22/04/13

import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            String str = br.readLine();
            sb.append(chk(str)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    static int chk(String str){
        int idx = -1;
        for(int i=0; i<str.length()/2; i++){
            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                idx = i;
                break;
            }
        }
        if(idx==-1) return 0;
        if(str.length()==2) return 1;
        else if(str.length()==3){
            if(str.charAt(0)==str.charAt(1)||str.charAt(1)==str.charAt(2)) return 1;
        }else {
            int plus = 0;
            int minus = 0;
            boolean[] chk = new boolean[]{true, true};
            for (int i = 0; i < str.length() / 2 - 1 + str.length()%2; i++) {
                if (i == idx) {
                    plus++;
                }
                if (str.charAt(i + plus) != str.charAt(str.length() - 1 - i)) {
                    chk[0] = false;
                    break;
                }
            }

            for (int i = 0; i < str.length() / 2 - 1 + str.length()%2; i++) {
                if (i == idx) {
                    minus++;
                }
                if (str.charAt(i) != str.charAt(str.length() - 1 - i - minus)) {
                    chk[1] = false;
                    break;
                }
            }
            if (chk[0] || chk[1]) return 1;
        }
        return 2;
    }
}
/*
3
axaaxaa
abcddadca
aabcbcaa

 */