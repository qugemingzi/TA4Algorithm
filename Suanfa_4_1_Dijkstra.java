package ceshi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Suanfa_4_1_Dijkstra {
	private static int M = 10000; // ��·��ͨ
	private static String fileStr = "D:\\�ĵ�\\My java\\dijkstra�����ļ�.txt";
	static Map<String, Integer> map;
	static Map<Integer, String> map_;
	static int index = 0;
	static int g[][];// �ڽӾ���
	static int start=0;
	
	static int [] matrix(String fileStr){
		map = new HashMap<>();
		map_ = new HashMap<>();
		File file = new File(fileStr);//���ļ�
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//����һ��BufferedReader������ȡ�ļ�
		String s = null;
		try {
			while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
				if(s.contains("*") && s.contains("Node")){
					s = br.readLine();
					String []arrnode = s.split(" ");
					for(int i=0;i<arrnode.length;i++){
						add(arrnode[i]);
					}
					g = new int [arrnode.length][arrnode.length];
					for(int i=0;i<arrnode.length;i++){
						for(int j=0;j<arrnode.length;j++){
							g[i][j] = M;
						}
					}
				}else if(s.contains("*") && s.contains("Route")){
					s = br.readLine();
					String []arrroute = s.split(" ");
					if(arrroute.length != 2){
						System.out.println("·���������");
						return null;
					}start = map.get(arrroute[0]);
					break;
				}else if(s.contains("*") && s.contains("Edge")){
					s = br.readLine();
					if(s.length() != 6){
						System.out.println("���������");
						return null;
					}
					add(s.charAt(0)+"" , s.charAt(3)+"" , Integer.parseInt(s.charAt(5)+""));
				}else{
					if(s.length() != 6){
						System.out.println("���������");
						return null;
					}
					add(s.charAt(0)+"" , s.charAt(3)+"" , Integer.parseInt(s.charAt(5)+""));
				}
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	static void add(String a){
		if (!map.containsKey(a)) {
			map_.put(index, a);
			map.put(a, index++);
		}
	}
	
	static void add(String a, String b , int height) {
		g[map.get(a)][map.get(b)] = height;
	}
	
	public static void main(String[] args) {
		
		matrix(fileStr);
		
		int[] shortPath = dijkstra(g, start);

		for (int i = 0; i < shortPath.length; i++)
			System.out.println("��" + map_.get(start) + "������" + map_.get(i) + "����̾���Ϊ��"
					+ shortPath[i]);
	}

	public static int[] dijkstra(int[][] weight, int start) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
		// ����һ��int[] ���飬��ʾ��start���������·������
		int n = weight.length;// �������
		int[] shortPath = new int[n];// ����start��������������·��
		String[] path = new String[n];// ����start�������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++)
			path[i] = new String(map_.get(start) + "-->" + map_.get(i));
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
					path[i] = path[k] + "-->" + map_.get(i);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println("��" + map_.get(start) + "������" + map_.get(i) + "�����·��Ϊ��" + path[i]);
		}
		System.out.println("=====================================");
		return shortPath;
	}
}
