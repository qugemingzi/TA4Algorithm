package ceshi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Suanfa_2 {

	static int n;
	static int g[][];// �ڽӾ���
	static String col[];// �жϵ�ǰ���Ƿ������,WHITE,GRAY,BLACK
	static Map<String, Integer> map;
	static Map<Integer, String> map_;
	static int index = 0;
	static int cyclenum = 0;
	static boolean hascycle;
	static ArrayList<Integer> cycle;// ��Ȧ·��

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();// ����
		g = new int[n][n];
		col = new String[n];
		// ��ʼ��
		for (int i = 0; i < n; i++) {
			col[i] = "WHITE";
			for (int j = 0; j < n; j++) {
				g[i][j] = 0;
			}
		}
		String a, b;
		map = new HashMap<>();
		map_ = new HashMap<>();
		while (scan.hasNext()) {
			a = scan.next();
			b = scan.next();
			if (a.equals("-1") && b.equals("-1")) {
				break;
			}
			add(a, b);// �������ı�
		}
		cycle = new ArrayList<>();
		hascycle = false;
		dfs_findcycle(0);// dfs���������õ���Ȧ
		if (!hascycle) {
			System.out.println("No cycle!");
		}
		scan.close();

	}

	private static void dfs_findcycle(int i) {
		// System.out.println("dfs_findcycle:" + i);
		if (col[i] == "GRAY") {// �����
			int j, first;
			if ((first = j = cycle.indexOf(i)) != -1) {
				hascycle = true;
				System.out.print("Cycle" + (++cyclenum) + ": ");
				while (j < cycle.size()) {
					System.out.print(map_.get(cycle.get(j++)));
				}
				System.out.println(map_.get(cycle.get(first)));
				return;
			}
			return;
		}
		col[i] = "GRAY";
		cycle.add(i);
		// System.out.println("add:" + i);

		for (int k = 0; k < n; k++) {
			if (g[i][k] == 1) {
				dfs_findcycle(k);
			}
		}
		// System.out.println("remove:" + cycle.get(cycle.size() - 1));
		cycle.remove(cycle.size() - 1);
		col[i] = "BLACK";
	}

	static void add(String a, String b) {
		if (!map.containsKey(a)) {
			map_.put(index, a);
			map.put(a, index++);
		}
		if (!map.containsKey(b)) {
			map_.put(index, b);
			map.put(b, index++);
		}
		g[map.get(a)][map.get(b)] = 1;
		// g[map.get(b)][map.get(a)] = 1;
	}

	static void clear() {
		for (int i = 0; i < n; i++) {
			col[i] = "WHITE";
		}
	}

}
