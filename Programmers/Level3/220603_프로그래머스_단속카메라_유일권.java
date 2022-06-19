import java.util.*;

class Solution_Cam {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}));
    }
    public static int solution(int[][] routes) {
        int answer = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[1]==y[1] ? x[0]-y[0] : x[1]-y[1]);
        //시작점 기준으로는 안되고 끝지점으로는 됨 이유는 모름..
        pq.addAll(Arrays.asList(routes));
        int[] now = pq.poll();
        int fin = now[1];
        while (!pq.isEmpty()){
            now = pq.poll();
            if(fin<now[0]){
                answer++;
                fin = now[1];
            }
        }

        return answer;
    }
}
