import java.io.*;
import java.util.StringTokenizer;

class Main_1790 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long finalNum = 0;          //~자리수에서 ~번째 수를 나타내는 변수
        long numlength = 1;         //자리수 나타내는 변수
        long numCnt = 9;            //~자리수의 총 숫자 길이를 나타내는 변수

        while (k > numlength * numCnt) {
            k -= (numlength * numCnt);
            finalNum += numCnt;

            numlength++;
            numCnt *=10;
        }


        finalNum = (finalNum+1)+ (k-1)/numlength;
        if(finalNum > N) {
            System.out.println(-1);
        }else {
            int index = (int) ((k-1)%numlength);
            System.out.println(String.valueOf(finalNum).charAt(index));
        }
    }

}