import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int left=0, right=n-1, sum=0;
		for (; left<n-1; left+=2) {
			if(arr[left]<1  && arr[left+1]<1)
				sum += arr[left] * arr[left+1];
			else break;
		}

		for (; right>0; right-=2) {
			if(arr[right]>1 && arr[right-1]>1)
				sum += arr[right] * arr[right-1];
			else break;
		}

		for (;  left<=right; left++) {
			sum += arr[left];
		}
		br.close();
		System.out.print(sum);
	}
}