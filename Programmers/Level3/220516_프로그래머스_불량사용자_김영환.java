package com.younghwani.a220516;
/*
글자 순서 같아야 함.
글자 길이 같아야 함.
만족하는 경우의 수 출력.
regex? 구글링 필요
 */
import java.util.*;
import java.util.regex.Pattern;

public class Solution_pro_불량사용자 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
        // 2 2 3
    }

    static Set<List<String>> set;
    static List<List<String>> banned;
    static int solution(String[] user_id, String[] banned_id) {
        banned = new ArrayList<>(); // 불량사용자로 지정할 키워드 별 사용자 리스트
        for (int i = 0; i < banned_id.length; i++) {
            String P = banned_id[i].replace('*', '.'); // regex 패턴
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < user_id.length; j++) {
                if (Pattern.matches(P, user_id[j])) tmp.add(user_id[j]); // 불량과 일치하면 리스트에 추가
            }
            banned.add(tmp);
        }
        set = new HashSet<>(); // 최종 경우
        dfs(0, new ArrayList<>());
        return set.size();
    }
    // 패턴에 만족하는 불량사용자의 리스트의 조합을 구하고, 중복 제거 위해 set에 담는다.
    static void dfs(int depth, List<String> list) {
        if (depth == banned.size()) {
            Collections.sort(list); // 정렬을 통해 중복 경우 없앰
            set.add(list);
            return;
        }
        for (String uid : banned.get(depth)) {
            if(!list.contains(uid)) {
                list.add(uid);
                dfs(depth + 1, list);
                list.remove(uid);
            }
        }
    }
}
