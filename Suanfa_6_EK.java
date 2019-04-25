package ceshi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Suanfa_6_EK {

	static int maxData = Integer.MAX_VALUE;
	static int arraysize = 199;
	static int capacity[][] = null;//记录残留网络的容量
	static int flow[] = null;//标记从源点到当前节点实际还剩多少流量可用
	static int pre[] = null;//标记在这条路径上当前节点的前驱,同时标记该节点是否在队列中
	static int edge,n,m;
	static Queue<Integer> queue = null;
	
	public static void main(String[] args) {
		capacity = new int [arraysize][arraysize];
		flow = new int[arraysize];
		pre = new int[arraysize];
		queue = new LinkedList<Integer>();
		
		initialize();
		Scanner scan = new Scanner(System.in);
		int start,end,ci;
		System.out.println("请输入边数： ");
		int edge = scan.nextInt();
		System.out.println("请输入源点和汇点： ");
		n = scan.nextInt();//源点
		m = scan.nextInt();//汇点
		System.out.println("请输入边和权重： ");
		for(int i=0;i<edge;i++){
			start = scan.nextInt();//起点
			end = scan.nextInt();//终点
			ci = scan.nextInt();//权重
			capacity[start][end] = ci;
		}
		System.out.println("结束啦！");
		scan.close();
		System.out.println(maxFlow(n,m));
	}

	static void initialize(){
		for(int i=0;i<arraysize;i++){
			for(int j=0;j<arraysize;j++){
				capacity[i][j] = 0;
			}
			flow[i] = 0;
		}
	}
	
	static int maxFlow(int start,int end){
		int increasement = 0;//局部最大流
		int sumflow = 0;//最大流
		while((increasement = BFS(start,end))!=-1){
			int k = end;//利用前驱寻找路径
			System.out.println(end);
			while(k!=start){
				int last = pre[k];
				capacity[last][k] -= increasement;//改变正向边的容量
				capacity[k][last] += increasement;//改变反向边的容量
				k = last;
				System.out.println(k);
			}
			sumflow += increasement;
			System.out.println("increasement "+increasement);
		}
		return sumflow;
	}
	
	static int BFS(int start,int end){
		//广度优先搜索找增广
		while(!queue.isEmpty())//队列清空
			queue.poll();
		for(int i=0;i<m+1;++i){
			pre[i] = -1; //初始化前驱节点
		}
		pre[start] = 0;//初始化前驱节点
		flow[start] = maxData;//初始化最大值 
		queue.add(start);//入队列
		while(!queue.isEmpty()){
			int index = queue.poll();//弹出队列
			if(index == end)//找到增广路径
				break;
			for(int i=0;i<m+1;++i){
				if(i != start && capacity[index][i] > 0 && pre[i] == -1){
					pre[i] = index;//记录前驱
					//关键：迭代的找到增量
					flow[i] = Math.min(capacity[index][i], flow[index]);
					queue.add(i);
				}
			}
		}
		if(pre[end] == -1)//残留图中不再存在增广路径
			return -1;
		else
			return flow[end];
	}
}
