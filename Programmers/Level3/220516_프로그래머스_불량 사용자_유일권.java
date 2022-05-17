//Solved :

import java.util.*;

class Solution02{
    public static void main(String[] args){
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }
    static boolean[] chk;
    static Set<String> set;
    static String[] badUsers;
    public static int solution(String[] user_id, String[] banned_id) {
        chk = new boolean[user_id.length];
        badUsers = new String[banned_id.length];
        set = new HashSet<>();
        dfs(user_id, banned_id, 0);
        return set.size();
    }
    public static void dfs(String[] user_id, String[] banned_id, int idx){
        if(idx == banned_id.length){
            String[] tmp = badUsers.clone();
            Arrays.sort(tmp);
            set.add(Arrays.toString(tmp));
            return;
        }
        String user, ban = banned_id[idx];
        for(int i=0; i<user_id.length; i++){
            if(chk[i]) continue;
            user = user_id[i];
            if(user.length() != ban.length()) continue;
            boolean flag = true;
            for(int j=0; j<user_id[i].length(); j++){
                if(ban.charAt(j) == '*') continue;
                if(ban.charAt(j) != user.charAt(j)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                chk[i] = true;
                badUsers[idx] = user;
                dfs(user_id, banned_id, idx+1);
                chk[i] = false;
            }
        }
    }
}