import java.util.*;
import java.io.*;


class Main {
    static class Node {
        int number, score, start, next;
        public Node(int number, int score, int next) {
            this.number = number;
            this.score = score;
            this.next = next;
            start = -1;
        }
        public Node(int number, int score, int start, int next) {
            this.number = number;
            this.score = score;
            this.start = start;
            this.next = next;
        }
    }

    static int[] diceNums = new int[10];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            diceNums[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, new int[] {0,0,0,0});

        System.out.println(MAX);

    } // main

    static int MAX = 0;
    private static void solve(int turn, int score, int[] markers) {
        if(turn == 10) {
            if(MAX < score) MAX = score;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(markers[i] >= 32) continue;

            int dice = diceNums[turn];
            int nextNum;
            if(nodes[markers[i]].start > 0) {
                nextNum = nodes[markers[i]].start;
            } else {
                nextNum = nodes[markers[i]].next;
            }
            if(nextNum >= 32) {
                int[] copy = new int[4];
                for (int k = 0; k < 4; k++) {
                    copy[k] = markers[k];
                }
                copy[i] = nextNum;
                solve(turn+1, score, copy);
                continue;
            }
            dice--;
            boolean isEnd = false;
            while(dice > 0) {
                nextNum = nodes[nextNum].next;
                if(nextNum >= 32) {
                    isEnd = true;
                    int[] copy = new int[4];
                    for (int k = 0; k < 4; k++) {
                        copy[k] = markers[k];
                    }
                    copy[i] = nextNum;
                    solve(turn+1, score, copy);
                    break;
                }
                dice--;
            }
            if(isEnd) continue;

            boolean isSuccess = true;
            for (int j = 0; j < 4; j++) {
                if(i == j) continue;
                if(markers[j] == nextNum) {
                    isSuccess = false;
                    break;
                }
            }
            if(isSuccess) {
                int[] copy = new int[4];
                for (int k = 0; k < 4; k++) {
                    copy[k] = markers[k];
                }
                copy[i] = nextNum;
                solve(turn+1, score+nodes[nextNum].score, copy);
            }
        } // i

        // 10 턴 까지 다 못가고 모든 말이 도착하는 경우 때문에 추가
        if(MAX < score) MAX = score;

    } // solve

    static Node[] nodes = {
            new Node(0,0,1),
            new Node(1,2,2),
            new Node(2,4,3),
            new Node(3,6,4),
            new Node(4,8,5),
            new Node(5,10,6,10),
            new Node(6,13,7),
            new Node(7,16,8),
            new Node(8,19,9),
            new Node(9,25,29),
            new Node(10,12,11),
            new Node(11,14,12),
            new Node(12,16,13),
            new Node(13,18,14),
            new Node(14,20,15,17),
            new Node(15,22,16),
            new Node(16,24,9),
            new Node(17,22,18),
            new Node(18,24,19),
            new Node(19,26,20),
            new Node(20,28,21),
            new Node(21,30,22,25),
            new Node(22,28,23),
            new Node(23,27,24),
            new Node(24,26,9),
            new Node(25,32,26),
            new Node(26,34,27),
            new Node(27,36,28),
            new Node(28,38,31),
            new Node(29,30,30),
            new Node(30,35,31),
            new Node(31,40,32)
    };
}
