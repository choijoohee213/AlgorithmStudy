import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution  st = new Solution();
        String rst = st.solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"});
        System.out.println(rst);
    }
}
//class Solution {
//    public String solution(int n, int k, String[] cmd) {
//        char[] array = new char[n];
//        List<Integer> now_point = new ArrayList<>();
//        StringTokenizer st;
//        String cmdN;
//        Stack<int[]> del_recent = new Stack<>();
//        int left_row = n-1;
//        for(int i=0; i<array.length; i++){
//            array[i] = 'X';
//            now_point.add(i);
//        }
//        for (String s : cmd) {
//            st = new StringTokenizer(s);
//            cmdN = st.nextToken();
//            switch (cmdN) {
//                case "U":                                           //올라가기
//                    k = k - Integer.parseInt(st.nextToken());
//                    if (k < 0) {
//                        k = 0;
//                    }
//                    break;
//                case "D":                                           //내려가기
//                    k = k + Integer.parseInt(st.nextToken());
//                    if (k > left_row) {
//                        k = left_row;
//                    }
//                    break;
//                case "C":                                           //삭제
//                    del_recent.add(new int[]{k, now_point.get(k)});
//                    now_point.remove(k);
//                    left_row--;
//                    if (k > left_row) {
//                        k = left_row;
//                    }
//                    break;
//                default:                                            //복구
//                    if (del_recent.size() > 0) {
//                        int[] recover = del_recent.pop();
//                        now_point.add(recover[0], recover[1]);
//                        if(recover[0] <= k) k++;
//                        left_row++;
//                    }
//                    break;
//            }
//        }
//
//        for (int temp : now_point) {
//            array[temp] = 'O';
//        }
//        StringBuilder sb = new StringBuilder();
//        for(char x : array){
//            sb.append(x);
//        }
//        return sb.toString();
//    }
//}
class Solution {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> remove_order = new Stack<>();
        int table_size = n;
        for(int i=0; i<cmd.length; i++) {
            char c = cmd[i].charAt(0);
            if(c=='D')
                k+=Integer.parseInt(cmd[i].substring(2));
            else if(c=='U')
                k-=Integer.parseInt(cmd[i].substring(2));
            else if(c=='C') {
                remove_order.add(k);
                table_size--;
                if(k==table_size)
                    k--;
            }
            else if(c=='Z') {
                if(remove_order.pop()<=k)
                    k++;
                table_size++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<table_size; i++)
            builder.append("O");
        while(!remove_order.isEmpty())
            builder.insert(remove_order.pop(), "X");
        return builder.toString();
    }
}