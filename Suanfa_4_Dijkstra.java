package ceshi;

public class Suanfa_4_Dijkstra {
	private static int M = 10000; // ��·��ͨ

	public static void main(String[] args) {
		int[][] weight2 = { { 0, 10, M, 30, 100 }, { M, 0, 50, M, M },
				{ M, M, 0, M, 10 }, { M, M, 20, 0, 60 }, { M, M, M, M, 0 } };

		int start = 0;
		int[] shortPath = dijkstra(weight2, start);

		for (int i = 0; i < shortPath.length; i++)
			System.out.println("��" + start + "������" + i + "����̾���Ϊ��"
					+ shortPath[i]);
	}

	public static int[] dijkstra(int[][] weight, int start) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
		// ����һ��int[] ���飬��ʾ��start���������·������
		int n = weight.length;// �������
		int[] shortPath = new int[n];// ����start��������������·��
		String[] path = new String[n];// ����start�������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++)
			path[i] = new String(start + "-->" + i);
		int[] visited = new int[n];// ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����

		// ��ʼ������һ�������Ѿ����
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count < n; count++) {// Ҫ����n-1������
			int k = -1;// ѡ��һ�������ʼ����start�����δ��Ƕ���
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}

			// ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
			shortPath[k] = dmin;
			visited[k] = 1;

			// ��kΪ�м�㣬������start��δ���ʸ���ľ���
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0
						&& weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
		}
		System.out.println("=====================================");
		return shortPath;
	}
}
