package ceshi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Suanfa_7 {

	static String filePath = "C:\\Users\\asus\\Desktop\\算法实验\\splicing_graphs输入文件例子\\splicing_graphs\\splicing_graph0.rg";
	static String filePathArr = "C:\\Users\\asus\\Desktop\\算法实验\\splicing_graphs输入文件例子\\splicing_graphs\\splicing_graph";
	static String fileNew = "C:\\Users\\asus\\Desktop\\算法实验\\splicing_graphs输入文件例子\\splicing_graphs\\splicing_graph0.txt";
	static double maxData = Double.MAX_VALUE;
	static int arraysize = 199;
	static double capacity[][] = null;// 记录残留网络的容量
	static double flow[] = null;// 标记从源点到当前节点实际还剩多少流量可用
	static int pre[] = null;// 标记在这条路径上当前节点的前驱,同时标记该节点是否在队列中
	static int n, m;
	static Queue<Integer> queue = null;
	static Map<Node, Integer> mapNodeInt = new HashMap<>();
	static Map<Integer, Node> mapIntNode = new HashMap<>();
	static int BIGindex = 0;

	public static void main(String[] args) {
		initialize();
		for(int i=0;i<2;i++){
			readrg(filePathArr+i+".rg");
			System.out.println(maxFlow(n, m, fileNew,i));
		}
	}

	static void readrg(String filePath) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int id = 0;
				int length = 0;
				double cov = 0.00;
				String sequence = "";
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.contains("Topological")) {
						lineTxt = bufferedReader.readLine();
						String[] arrtempnode = lineTxt.split(",");
						n = Integer.parseInt(arrtempnode[0]);
						m = Integer
								.parseInt(arrtempnode[arrtempnode.length - 1]);
						System.out.println("start " + arrtempnode[0] + ",end "
								+ arrtempnode[arrtempnode.length - 1]);
					} else if (lineTxt.contains("Nodes")) {

					} else if (lineTxt.contains("node")) {
						String[] arrtemp = lineTxt.split(" ");
						String[] arrtempid = arrtemp[3].split("	");
						id = Integer.parseInt(arrtempid[0]);
						System.out.println("id " + id);
						String[] arrtemplength = arrtemp[5].split("	");
						length = Integer.parseInt(arrtemplength[0]);
						System.out.println("length " + length);
						String[] arrtempcov = arrtemp[7].split("	");
						cov = Double.parseDouble(arrtempcov[0]);
						System.out.println("cov " + cov);
						sequence = bufferedReader.readLine();
						System.out.println("sequence " + sequence);
						createNode(id, id, length, cov, sequence);
					} else if (lineTxt.contains("Edges")) {

					} else if (lineTxt.contains("->")) {
						String[] arrtemp = lineTxt.split(";");
						String[] arrtempedge = arrtemp[0].split(",");
						for (int i = 0; i < arrtempedge.length; i++) {
							String[] arrtemprb = arrtempedge[i].split("->");
							String[] arrtempcbqz = arrtemprb[1].split(":");
							System.out.println("rb " + arrtemprb[0] + ",cb "
									+ arrtempcbqz[0] + ",qz " + arrtempcbqz[1]);
							int start = Integer.parseInt(arrtemprb[0]);
							int end = Integer.parseInt(arrtempcbqz[0]);
							double ci = Double.parseDouble(arrtempcbqz[1]);
							System.out.println("start " + start + ",end " + end
									+ ",ci " + ci);
							writeedge(start, end, ci);
						}
					}
					// System.out.println("***"+lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	static void createNode(int numberofid, int id, int length, double cov,
			String sequence) {
		Node nodetemp = mapIntNode.get(numberofid);
		nodetemp = new Node(id, length, cov, sequence);
		mapIntNode.put(numberofid, nodetemp);
		mapNodeInt.put(nodetemp, numberofid);
	}

	static void initialize() {
		capacity = new double[arraysize][arraysize];
		flow = new double[arraysize];
		pre = new int[arraysize];
		queue = new LinkedList<Integer>();
		for (int i = 0; i < arraysize; i++) {
			for (int j = 0; j < arraysize; j++) {
				capacity[i][j] = 0.0;
			}
			flow[i] = 0.0;
		}
		Node node0 = null;
		Node node1 = null;
		Node node2 = null;
		Node node3 = null;
		Node node4 = null;
		Node node5 = null;
		Node node6 = null;
		Node node7 = null;
		Node node8 = null;
		Node node9 = null;
		Node node10 = null;
		Node node11 = null;
		Node node12 = null;
		Node node13 = null;
		Node node14 = null;
		Node node15 = null;
		Node node16 = null;
		Node node17 = null;
		Node node18 = null;
		Node node19 = null;
		mapNodeInt.put(node0, 0);
		mapNodeInt.put(node1, 1);
		mapNodeInt.put(node2, 2);
		mapNodeInt.put(node3, 3);
		mapNodeInt.put(node4, 4);
		mapNodeInt.put(node5, 5);
		mapNodeInt.put(node6, 6);
		mapNodeInt.put(node7, 7);
		mapNodeInt.put(node8, 8);
		mapNodeInt.put(node9, 9);
		mapNodeInt.put(node10, 10);
		mapNodeInt.put(node11, 11);
		mapNodeInt.put(node12, 12);
		mapNodeInt.put(node13, 13);
		mapNodeInt.put(node14, 14);
		mapNodeInt.put(node15, 15);
		mapNodeInt.put(node16, 16);
		mapNodeInt.put(node17, 17);
		mapNodeInt.put(node18, 18);
		mapNodeInt.put(node19, 19);
		mapIntNode.put(0, node0);
		mapIntNode.put(1, node1);
		mapIntNode.put(2, node2);
		mapIntNode.put(3, node3);
		mapIntNode.put(4, node4);
		mapIntNode.put(5, node5);
		mapIntNode.put(6, node6);
		mapIntNode.put(7, node7);
		mapIntNode.put(8, node8);
		mapIntNode.put(9, node9);
		mapIntNode.put(10, node10);
		mapIntNode.put(11, node11);
		mapIntNode.put(12, node12);
		mapIntNode.put(13, node13);
		mapIntNode.put(14, node14);
		mapIntNode.put(15, node15);
		mapIntNode.put(16, node16);
		mapIntNode.put(17, node17);
		mapIntNode.put(18, node18);
		mapIntNode.put(19, node19);
	}

	static double maxFlow(int start, int end, String fileNew,int i) {
		double increasement = 0;// 局部最大流
		double sumflow = 0;// 最大流
		Queue<Integer> transcript = null;
		int index = 0;
		while ((increasement = BFS(start, end)) != -1.00) {
			transcript = new LinkedList<Integer>();
			int k = end;// 利用前驱寻找路径
			transcript.add(k);
			while (k != start) {
				int last = pre[k];
				capacity[last][k] -= increasement;// 改变正向边的容量
				capacity[k][last] += increasement;// 改变反向边的容量
				k = last;
				transcript.add(k);
			}
			sumflow += increasement;

			// 写文件
			try {
				BufferedWriter bufferedwriter = new BufferedWriter(
						new FileWriter(new File(fileNew), true));
				int length = 0;
				double cov = 0.0;
				String sequence = "";
				String result;
				int temp;
				while (!transcript.isEmpty()) {
					temp = transcript.poll();
					System.out.print(temp);
					Node nodetemp = mapIntNode.get(temp);
					System.out.println("id " + nodetemp.getId() + "length "
							+ nodetemp.getLength() + "cov " + nodetemp.getCov()
							+ "sequence " + nodetemp.getSequence());
					length += nodetemp.getLength();
					cov += nodetemp.getCov();
					sequence = nodetemp.getSequence() + sequence;
				}
				System.out.print(">trans"+(++BIGindex)+"_sg"+ i+"_"+ (++index) + " ");
				System.out.println("len = " + length + " cov = " + cov
						+ " sequence = ");
				System.out.println(sequence);
				result = ">trans" +BIGindex+"_sg"+i+"_"+ index + " " + "len = " + length
						+ " cov = " + cov + " sequence = ";
				System.out.println("increasement "+increasement);
				bufferedwriter.write(result);
				bufferedwriter.write("\r\n");
				bufferedwriter.write(sequence);
				bufferedwriter.write("\r\n");
				bufferedwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sumflow;
	}

	static double BFS(int start, int end) {
		// 广度优先搜索找增广
		while (!queue.isEmpty())
			// 队列清空
			queue.poll();
		for (int i = 0; i < m + 1; ++i) {
			pre[i] = -1; // 初始化前驱节点
		}
		pre[start] = 0;// 初始化前驱节点
		flow[start] = maxData;// 初始化最大值
		queue.add(start);// 入队列
		while (!queue.isEmpty()) {
			int index = queue.poll();// 弹出队列
			if (index == end)// 找到增广路径
				break;
			for (int i = 0; i < m + 1; ++i) {
				if (i != start && capacity[index][i] > 0 && pre[i] == -1) {
					pre[i] = index;// 记录前驱
					// 关键：迭代的找到增量
					flow[i] = Math.min(capacity[index][i], flow[index]);
					queue.add(i);
				}
			}
		}
		if (pre[end] == -1)// 残留图中不再存在增广路径
			return -1;
		else
			return flow[end];
	}

	static void writeedge(int start, int end, double ci) {
		capacity[start][end] = ci;
	}

	public static class Node {
		int id = 0;
		int length = 0;
		double cov = 0.00;
		String sequence = "";

		Node(int id, int length, double cov, String sequence) {
			this.id = id;
			this.length = length;
			this.cov = cov;
			this.sequence = sequence;
		}

		int getId() {
			return id;
		}

		int getLength() {
			return length;
		}

		double getCov() {
			return cov;
		}

		String getSequence() {
			return sequence;
		}
	}
}
