//https://www.acmicpc.net/problem/
//Solved : 22/05/14

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str1 = br.readLine();
        String str2 = br.readLine();
        int sum = StringToNumber(str1) + StringToNumber(str2);
        sb.append(sum).append("\n").append(NumberToString(sum));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String NumberToString(int num) {
        StringBuilder s = new StringBuilder();
        while (num >= 1000) {
            s.append("M");
            num -= 1000;
        }
        if (num >= 900) {
            s.append("CM");
            num -= 900;
        }
        if (num >= 500) {
            s.append("D");
            num -= 500;
        }
        if (num >= 400) {
            s.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            s.append("C");
            num -= 100;
        }
        if (num >= 90) {
            s.append("XC");
            num -= 90;
        }
        if (num >= 50) {
            s.append("L");
            num -= 50;
        }
        if (num >= 40) {
            s.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            s.append("X");
            num -= 10;
        }
        if (num >= 9) {
            s.append("IX");
            num -= 9;
        }
        if (num >= 5) {
            s.append("V");
            num -= 5;
        }
        if (num >= 4) {
            s.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            s.append("I");
            num -= 1;
        }
        return s.toString();
    }

    private static int StringToNumber(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char C = str.charAt(i);
            if (i + 1 < str.length()) {
                String temp = String.valueOf(C) + String.valueOf(str.charAt(i + 1));
                switch (temp) {
                    case "CM" -> sum += 900;
                    case "CD" -> sum += 400;
                    case "XC" -> sum += 90;
                    case "XL" -> sum += 40;
                    case "IX" -> sum += 9;
                    case "IV" -> sum += 4;
                    default -> {
                        sum += charToNumber(C);
                        i--;
                    }
                }
                i++;
            } else sum += charToNumber(C);
        }
        return sum;
    }

    private static int charToNumber(char C) {
        return switch (C) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }
}