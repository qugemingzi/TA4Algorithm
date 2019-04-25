package ceshi;

import java.util.Scanner;

//3.2主函数使用旅行商问题类
public class Suanfa_3_TSP {
	public static void main(String[] args) {
		approximation approx = new approximation();
		approx.approxTSP();// 生成最小生成树
		approx.PreTra();// 前序遍历
	}
}

class approximation {
	private float edge[][], cost;
	private float city[][];
	private int start[], next[];
	private int TSP[], sit;

	public approximation() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入城市的数量:");
		int num = scan.nextInt();
		city = new float[num][2];
		edge = new float[num][num];
		for (int i = 0; i < num; i++) {
			System.out.println("请输入第" + (i + 1) + "个城市的位置:");
			city[i][0] = scan.nextFloat();
			city[i][1] = scan.nextFloat();
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				edge[i][j] = (float) Math.sqrt(Math.pow(
						city[i][0] - city[j][0], 2)
						+ Math.pow(city[i][1] - city[j][1], 2));
			}
		}
		scan.close();
	}

	void approxTSP() {
		int n = city.length;
		float c[][] = edge;
		float[] lowcost = new float[n];
		int[] clostest = new int[n];
		boolean[] s = new boolean[n];
		start = new int[n];
		next = new int[n];
		s[0] = true;// 初始化，计算未遍历城市到已遍历集合的距离
		for (int i = 1; i < n; i++) {
			lowcost[i] = c[0][i];
			clostest[i] = 0;
			s[i] = false;
		}
		for (int i = 0; i < n - 1; i++) {
			float min = Float.MAX_VALUE;
			int j = 0;
			for (int k = 1; k < n; k++) {// 查找距离已遍历集合最近的城市
				if ((lowcost[k] < min) && (!s[k])) {
					min = lowcost[k];
					j = k;
				}
			}
			// System.out.println(clostest[j]+",  "+j); //输出并保存
			start[i] = clostest[j];
			next[i] = j;
			s[j] = true;
			for (int k = 1; k < n; k++) {// 重新计算未遍历城市到已遍历集合的距离
				if (c[j][k] < lowcost[k] && (!s[k])) {
					lowcost[k] = c[j][k];
					clostest[k] = j;
				}
			}
		}
	}

	void PreTra() {
		int n = city.length;
		TSP = new int[n];
		sit = 0;
		PreTraverse(0);
		System.out.println("旅行路径为：");// 输出并计算旅行代价
		cost = edge[TSP[n - 1]][0];
		for (int i = 0; i < n; i++) {
			System.out.print(TSP[i] + " ");
			if (i < n - 1) {
				cost = cost + edge[TSP[i]][TSP[i + 1]];
			}
		}
		System.out.print("\n旅行总代价为" + cost + "\n");
	}

	private void PreTraverse(int a) {// 前序遍历最小生成树的递归代码
		int n = city.length;
		TSP[sit] = a;
		sit++;
		for (int i = 0; i < n - 1; i++) {
			if (start[i] == a) {
				PreTraverse(next[i]);
			}
		}
	}
}
