package beakjoon.before.q2304;
//https://www.acmicpc.net/problem/2304
//22/02/06

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Storage> storages = new ArrayList<>(N);
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            storages.add(new Storage(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(storages);

        int rst = 0;
        int now = 0;
        while(now<N){
            int next = -1;
            for(int i=1; i<N-now; i++){
                if(storages.get(now).getLength() < storages.get(now+i).getLength()){
                    next = now+i;
                    break;
                }
            }
            //System.out.println("while "+now+" "+next);
            if(next>0){
                rst += storages.get(now).getLength()*(storages.get(next).getLoc()-storages.get(now).getLoc());
                now = next;
            }else{
                if(now==N-1){
                    rst += storages.get(now).getLength();
                    break;
                }else{
                    int max = 0;
                    for(int i=1; i<N-now; i++){
                        if(max <= storages.get(now+i).getLength()){
                            next = now+i;
                            max = storages.get(now+i).getLength();
                        }
                    }
                    rst += storages.get(now).getLength();
                    rst += max*(storages.get(next).getLoc()-storages.get(now).getLoc()-1);
                    now = next;
                }
            }
            //System.out.println(rst+" "+now+" "+next);
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}

class Storage implements Comparable<Storage>{
    int loc;
    int length;
    Storage(int loc, int length){
        this.length = length;
        this.loc = loc;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    @Override
    public int compareTo(Storage o) {
        return this.loc - o.loc;
    }
    @Override
    public String toString(){
        return loc+" "+length;
    }
}