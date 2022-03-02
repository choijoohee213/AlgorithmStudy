//https://www.acmicpc.net/problem/13460
//Solved : 22/03/01   64%
import java.util.*;
import java.io.*;

class Main{
    static class MT{
        int time;
        int rx, ry, bx, by;

        public MT(int time, int rx, int ry, int bx, int by) {
            this.time = time;
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }

    static char[][] map;
    static int N, M, rst=-1;
    static int[] R = new int[2];
    static int[] B =  new int[2];
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("res/beakjoon/q13460.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(map[i][j] == 'R'){
                    R[0] = i;
                    R[1] = j;
                }else if(map[i][j] == 'B'){
                    B[0] = i;
                    B[1] = j;
                }
            }
        }

        BFS();

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void BFS(){
        Queue<MT> q = new ArrayDeque<>();
        q.add(new MT(0, R[0], R[1], B[0], B[1]));
        loop:while(!q.isEmpty()){
            MT now = q.poll();
            if(now.time>=10){       //move함수에서  객체 생성하며 시간 +1하므로 9까지만
                rst=-1;
                break;
            }
            for(int i=0; i<4; i++){
                MT next = move(now, i);
                if(next.bx==-1) continue;       //파랑이 빠졌다면 무조건 실패이므로 나머지 큐 확인
                if(next.rx==-1){                //파랑이 빠지지 않았을테니
                    rst = next.time;
                    break loop;
                }
                q.add(next);
            }
        }
    }
    static MT move(MT m, int d){
        MT mt = new MT(m.time+1, m.rx, m.ry, m.bx, m.by);   //시간 +1하여 추가
        for(int t=0; t<2; t++){                //2번씩 움직이게하면 막혀서 못 움직이는것도 움직여지므로 2번씩 이동
            while(true){
                if(mt.rx==-1||map[mt.rx+dr[d]][mt.ry+dc[d]]=='O'){      //출구로 빨강이 나가면 위치 (-1,-1)선언
                    mt.rx = -1;
                    mt.ry = -1;
                    break;
                }
                if(map[mt.rx+dr[d]][mt.ry+dc[d]]=='#' ||                    //벽으로 둘러 쌓여있으므로 배열 밖 체크 x
                        (mt.rx+dr[d]==mt.bx)&&(mt.ry+dc[d]==mt.by))  break; //파랑이랑 위치 같아지면 이동 취소
                mt.rx += dr[d];
                mt.ry += dc[d];

            }
            while(true){
                if(mt.bx==-1||map[mt.bx+dr[d]][mt.by+dc[d]]=='O'){      //출구로 파랑이 나가면 위치 (-1,-1)선언
                    mt.bx = -1;
                    mt.by = -1;
                    break;
                }
                if(map[mt.bx+dr[d]][mt.by+dc[d]]=='#' ||                    //벽으로 둘러 쌓여있으므로 배열 밖 체크 x
                        (mt.rx==mt.bx+dr[d])&&(mt.ry==mt.by+dc[d]))  break; //빨강과 위치 동일해지면 이동 취소
                mt.bx += dr[d];
                mt.by += dc[d];
            }
        }
        return mt;
    }
}