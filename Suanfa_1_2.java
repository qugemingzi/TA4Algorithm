package ceshi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Suanfa_1_2 {

	static int g[][];// 邻接矩阵
	static String col[];// 判断当前列是否遍历过,WHITE,GRAY,BLACK
	static Map<String, Integer> map;
	static int index = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();// 点数
		// int m = scan.nextInt();//边数
		g = new int[n][n];
		col = new String[n];
		// 初始化
		for (int i = 0; i < n; i++) {
			col[i] = "WHITE";
			for (int j = 0; j < n; j++) {
				g[i][j] = 0;
			}
		}
		String a, b;
		map = new HashMap<>();
		while (scan.hasNext()) {
			a = scan.next();
			b = scan.next();
			if (a.equals("-1") && b.equals("-1")) {
				break;
			}
			add(a, b);

		}
		boolean flag = false;
		for (int i = 0; i < n; i++) {
			if (col[i] == "WHITE" && !bfs(i, n)) {
				flag = true;
				break;
			}
		}
		if (flag)
			System.out.println("No!");
		else
			System.out.println("Yes!");
		scan.close();

	}

	static boolean bfs(int s, int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		col[s] = "GRAY";
		while (!queue.isEmpty()) {
			int front = queue.poll();
			for (int i = 0; i < n; i++) {
				if (g[front][i] == 1 && col[front] == col[i]) {
					return false;
				}else if (g[front][i] == 1 && col[i] == "WHITE") {
					queue.add(i);
					col[i] = "GRAY";
				}
			}
			col[front] = "BLACK";
		}
		return true;
	}

	static void add(String a, String b) {
		if (!map.containsKey(a)) {
			map.put(a, index++);
		}
		if (!map.containsKey(b)) {
			map.put(b, index++);
		}
		g[map.get(a)][map.get(b)] = 1;
		g[map.get(b)][map.get(a)] = 1;
	}

}
