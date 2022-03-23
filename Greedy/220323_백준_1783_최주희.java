import java.io.*;
import java.util.*;

//https://songwonseok.github.io/algorithm/BOJ-1783/
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		if(n==1) System.out.print(1);
		else if(n==2){
			if(((m+1)/2)>=4) System.out.print(4);
			else System.out.print((m+1)/2);
		}
		else{
			if(m>=7) System.out.print(m-2);
			else System.out.print(Math.min(m,4));
		}
		br.close();
	}
}