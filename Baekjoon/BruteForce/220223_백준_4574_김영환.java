package com.younghwani.a220223;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
스도쿠 규칙을 따른다.
각 행, 열에는 1부터 9까지 숫자가 하나씩
3*3 그리드에 1부터 9까지 숫자가 하니씩
타일은 두 숫자의 조합으로 이뤄져있음.
 */
public class Main_bj_4574_스도미노쿠 {
    static int[] di = {0, 1}; //우,하
    static int[] dj = {1, 0};
    static int[][] pan; // 스도쿠판
    static boolean[][] tile; // 도미노 타일(도미노 사용 여부 체크)

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_4574.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int cnt=1;
        int N; // 채워져있는 도미노의 개수
        while ((N=Integer.parseInt(br.readLine()))!=0) { // 0입력 시 중단
            sb.append("Puzzle ").append(cnt++).append("\n");
            // 스도쿠 판 채우기(입력)
            pan = new int[9][9];
            tile = new boolean[10][10];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int[] tmp = new int[2];
                for (int j = 0; j < 2; j++) {
                    int num = Integer.parseInt(st.nextToken()); // 주어진 값
                    String rc = st.nextToken(); // A3 같은 형태로 주어진 입력 구분
                    pan[rc.charAt(0)-'A'][rc.charAt(1)-'1'] = num; // 스토쿠 판에 번호 채우기
                    tmp[j] = num;
                }
                tile[tmp[0]][tmp[1]]=true; tile[tmp[1]][tmp[0]] = true; // 사용한 도미노 번호 체크
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 9; i++) {
                String rc = st.nextToken();
                pan[rc.charAt(0)-'A'][rc.charAt(1)-'1'] = i+1;
            }
            dfs(0); // 스도쿠 맞추기(도미노 유의-도미노 중복 안됨)
            for(int[] pa: pan) { for(int p: pa) sb.append(p); sb.append("\n"); } // 스도쿠 결과 sb에 담기
        }
        System.out.println(sb);
        br.close();
    }

    // 스도쿠 맞추기
    static boolean dfs(int depth) {
        if(depth==81) return true;
        int r = depth/9, c = depth%9; // 도미노 타일 배치를 시작할 위치
        if (pan[r][c]!=0) return dfs(depth+1); // 현 위치에 이미 값 있으면 다음으로
        else {
            for (int i = 0; i < 2; i++) {
                int ni = r + di[i], nj = c + dj[i];
                if(ni<0||nj<0||ni>=9||nj>=9||pan[ni][nj]!=0) continue;
                for (int h = 1; h <= 9; h++) { // 위나 왼쪽 도미노 숫자
                    for (int k = 1; k <= 9; k++) { // 아래나 오른쪽 도미노 숫자
                        if(h==k||tile[h][k]) continue; // 도미노가 중복조합이거나 이미 사용한 것이면 안됨.
                        if(!isOk(r, c, h) || !isOk(ni, nj, k)) continue; // 스도쿠 규칙 불만족하면 안됨.
                        tile[h][k]=true; tile[k][h]=true; // 사용한 도미노 타일 체크
                        pan[r][c] = h; pan[ni][nj] = k;
                        if(dfs(depth+1)) return true; // 현위치에 도미노 타일을 뒀을 때, 모두 성공한 경우라면
                        tile[h][k] = false; tile[k][h] = false;
                        pan[r][c] = 0; pan[ni][nj] = 0; // 현위치에 도미노 타일을 뒀을 때, 스도쿠 실패
                    }
                }
            }
        }
        return false;
    }
    // 숫자의 중복 체크
    static boolean isOk(int row, int col, int val) {
        // 열 방향에 대해
        for (int i = 0; i < 9; i++) {
            if (val == pan[i][col]) return false;
        }
        // 행 방향에 대해
        for (int i = 0; i < 9; i++) {
            if (val == pan[row][i]) return false;
        }
        // 3*3 박스에 대해
        int r = row/3 * 3; // 지역적 공간의 first row
        int c = col/3 * 3; // 지역적 공간의 first col
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pan[r+i][c+j]==val) return false;
            }
        }
        return true; // 모든 case에서 중복 없으면 true 출력
    }
}
