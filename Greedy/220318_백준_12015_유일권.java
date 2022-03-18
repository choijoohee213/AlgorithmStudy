//https://www.acmicpc.net/problem/12015
//Solved : 22/03/18

import java.util.*;
import java.io.*;
//이게 왜 그리디일까요?? 설명해주실분 ~~~~
class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>(N+1);      //값 저장용 리스트
        list.add(0);                                                //비교해야하니 0 추가
        int size = 0;
        int t;
        for(int i=0; i<N; i++){
            t = Integer.parseInt(st.nextToken());                   //입력 받아서
            if(list.get(size)<t){                                   //맨 끝값보다 크면 끝에 추가 후 크기 증가
                list.add(t);
                size++;
            }else{                                                  //아니면 이분 탐색으로 값 찾아서 거기에 값 갱신
                int left=1;
                int right=size;
                while(left<right){
                    int mid = (left+right)/2;
                    if(list.get(mid) < t) left = mid+1;
                    else right = mid;
                }
                list.set(right, t);
            }
        }

        bw.write(Integer.toString(size));
        bw.close();
        br.close();
    }
}