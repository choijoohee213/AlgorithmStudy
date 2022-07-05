package com.younghwani.a220705;

import java.util.*;

public class Main_bj_1072_게임 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long X = sc.nextInt();
        long Y = sc.nextInt();

        int Z = (int) (Y * 100 / X);

        if (Z >= 99) {
            System.out.print(-1);
            return;
        }

        long s=0, e=2000000000;

        while (s <= e) {
            long m = (s + e) / 2;
            long zz = (Y + m) * 100 / (X + m);
            if (zz > Z) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        System.out.print(e+1);

        sc.close();
    }
}
