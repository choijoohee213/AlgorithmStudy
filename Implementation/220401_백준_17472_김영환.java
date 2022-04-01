package com.younghwani.a220401;

import java.io.*;
import java.util.*;

public class Main_bj_17472_다리만들기2 {
	static class Block {
		int x, y;
		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int N, M, map[][], res, P[], di[]={-1,0,1,0}, dj[]={0,1,0,-1}; // U,R,B,L
	static boolean[][] V;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		V = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// Find island's group
		int no = 1; // island group no
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1 && !V[i][j]) { // land, non-visited
					// BFS - grouping island
					ArrayDeque<Block> q = new ArrayDeque<>();
					V[i][j]=true;
					q.offer(new Block(i, j));
					map[i][j]=no;
					while(!q.isEmpty()) {
						Block cur = q.poll();
						for (int idx = 0; idx < 4; idx++) {
							int ni=cur.x+di[idx], nj=cur.y+dj[idx];
							if(ni<0||ni>=N||nj<0||nj>=M||map[ni][nj]==0||V[ni][nj]) continue; // outOfRange, isWater, visited
							V[ni][nj]=true;
							q.offer(new Block(ni, nj));
							map[ni][nj]=no;
						}
					}
					no++;
				}
			}
		}
		// Connect Bridge
		V = new boolean[N][M]; // new reference
		List<Edge> bridges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]!=0&&!V[i][j]) { // land, non-visited
					// BFS - connecting bridge between the islands
					ArrayDeque<Block> q = new ArrayDeque<>();
					V[i][j]=true;
					q.offer(new Block(i, j));
					while(!q.isEmpty()) {
						Block cur = q.poll();
						for (int idx = 0; idx < 4; idx++) {
							int ni=cur.x+di[idx], nj=cur.y+dj[idx];
							if(ni<0||ni>=N||nj<0||nj>=M||V[ni][nj]) continue; // outOfRange, visited
							if(map[ni][nj]==map[i][j]) { // located on the same island, just move
								V[ni][nj]=true;
								q.offer(new Block(ni, nj));
								continue;
							}
							if(map[ni][nj]==0) { // isWater, connecting
								// bridge -> same direction
								int len=1;
								while(true) {
									ni+=di[idx]; nj+=dj[idx];
									if(ni<0||ni>=N||nj<0||nj>=M||map[ni][nj]==map[i][j]) break; // outOfRange, sameIsland
									if(map[ni][nj]!=0&&map[ni][nj]!=map[i][j]) { // arriving on another island
										if(len<2) break; // larger than or equal to 2
										bridges.add(new Edge(map[i][j], map[ni][nj], len));
										break;
									}
									len++;
								}
							}
						}
					}
				}
			}
		}
		// Kruskal
		Collections.sort(bridges);
		int bCnt=0;
		P = new int[no];
		for (int i = 0; i < no; i++) P[i]=i; // make set
		for (int i = 0; i < bridges.size(); i++) {
			Edge cur = bridges.get(i);
			int sRoot=findSet(cur.from), eRoot=findSet(cur.to);
			if(sRoot!=eRoot) {
				unionSet(sRoot, eRoot);
				res += cur.weight;
				bCnt++;
			}
		}
		// All islands must be connected.(only one root)
		int iCnt=0, iRoot=P[1];
		for (int i = 1; i < no; i++) if(P[i]==iRoot) iCnt++;
		if(bCnt!=iCnt-1) res=-1;
		// The island is connected by a single bridge.(bCnt=no-1)
		if(bCnt != no-2) res=-1;
		System.out.print(res);
		br.close();
	}
	static int findSet(int s) {
		if (P[s]==s) return s;
		int root = findSet(P[s]);
		P[s]=root;
		for (int i = 0; i < P.length; i++) if(P[i]==s) P[i]=root; // Change the parent of children with parent s to root
		return root;
	}
	static void unionSet(int s, int e) {
		s = findSet(s);
		e = findSet(e);
		if(s!=e) P[e]=s;
	}
}
