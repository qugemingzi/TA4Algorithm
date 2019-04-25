package ceshi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Suanfa_4_1_Dijkstra {
	private static int M = 10000; // 此路不通
	private static String fileStr = "D:\\文档\\My java\\dijkstra输入文件.txt";
	static Map<String, Integer> map;
	static Map<Integer, String> map_;
	static int index = 0;
	static int g[][];// 邻接矩阵
	static int start=0;
	
	static int [] matrix(String fileStr){
		map = new HashMap<>();
		map_ = new HashMap<>();
		File file = new File(fileStr);//读文件
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//构造一个BufferedReader类来读取文件
		String s = null;
		try {
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
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
						System.out.println("路径输入错误！");
						return null;
					}start = map.get(arrroute[0]);
					break;
				}else if(s.contains("*") && s.contains("Edge")){
					s = br.readLine();
					if(s.length() != 6){
						System.out.println("边输入错误！");
						return null;
					}
					add(s.charAt(0)+"" , s.charAt(3)+"" , Integer.parseInt(s.charAt(5)+""));
				}else{
					if(s.length() != 6){
						System.out.println("边输入错误！");
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
			System.out.println("从" + map_.get(start) + "出发到" + map_.get(i) + "的最短距离为："
					+ shortPath[i]);
	}

	public static int[] dijkstra(int[][] weight, int start) {
		// 接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
		// 返回一个int[] 数组，表示从start到它的最短路径长度
		int n = weight.length;// 顶点个数
		int[] shortPath = new int[n];// 保存start到其他各点的最短路径
		String[] path = new String[n];// 保存start到其他各点最短路径的字符串表示
		for (int i = 0; i < n; i++)
			path[i] = new String(map_.get(start) + "-->" + map_.get(i));
		int[] visited = new int[n];// 标记当前该顶点的最短路径是否已经求出,1表示已求出

		// 初始化，第一个顶点已经求出
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count < n; count++) {// 要加入n-1个顶点
			int k = -1;// 选出一个距离初始顶点start最近的未标记顶点
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}

			// 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
			shortPath[k] = dmin;
			visited[k] = 1;

			// 以k为中间点，修正从start到未访问各点的距离
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0
						&& weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + map_.get(i);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println("从" + map_.get(start) + "出发到" + map_.get(i) + "的最短路径为：" + path[i]);
		}
		System.out.println("=====================================");
		return shortPath;
	}
}
