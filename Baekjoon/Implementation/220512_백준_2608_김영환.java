package com.younghwani.a220512;
import java.io.*;
import java.util.*;
class Main_bj_2608_로마숫자 {
    static Map<Character, Integer> rome;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        rome = new HashMap<>();
        rome.put('M', 1000);
        rome.put('D', 500);
        rome.put('C', 100);
        rome.put('L', 50);
        rome.put('X', 10);
        rome.put('V', 5);
        rome.put('I', 1);

        char[] r1 = br.readLine().toCharArray();
        char[] r2 = br.readLine().toCharArray();

        int[] n1 = new int[r1.length];
        int[] n2 = new int[r2.length];

        for (int i = 0; i < r1.length; i++) n1[i]=rome.get(r1[i]);
        for (int i = 0; i < r2.length; i++) n2[i]+=rome.get(r2[i]);

        int sum = 0;

        for (int i = 0; i < n1.length; i++) {
            if(i+1<n1.length && n1[i]<n1[i+1]) {
                sum += n1[i+1]-n1[i];
                i++;
            } else {
                sum += n1[i];
            }
        }
        for (int i = 0; i < n2.length; i++) {
            if(i+1<n2.length && n2[i]<n2[i+1]) {
                sum += n2[i+1]-n2[i];
                i++;
            } else {
                sum += n2[i];
            }
        }

        System.out.println(sum);
        System.out.print(numChanger(sum));
        br.close();
    }
    static String numChanger(int n) {
        StringBuilder sb = new StringBuilder();
        while(n >= 1000){ sb.append("M");   n -= 1000; }
        if(n >= 900)    { sb.append("CM");  n -= 900; }
        if(n >= 500)    { sb.append("D");   n -= 500; }
        if(n >= 400)    { sb.append("CD");  n -= 400; }
        while(n >= 100) { sb.append("C");   n -= 100; }
        if(n >= 90)     { sb.append("XC");  n -= 90; }
        if(n >= 50)     { sb.append("L");   n -= 50; }
        if(n >= 40)     { sb.append("XL");  n -= 40; }
        while(n >= 10)  { sb.append("X");   n -= 10; }
        if(n >= 9)      { sb.append("IX");  n -= 9; }
        if(n >= 5)      { sb.append("V");   n -= 5; }
        if(n >= 4)      { sb.append("IV");  n -= 4; }
        while(n >= 1)   { sb.append("I");   n -= 1; }
        return sb.toString();
    }
}
