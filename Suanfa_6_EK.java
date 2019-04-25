package ceshi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Suanfa_6_EK {

	static int maxData = Integer.MAX_VALUE;
	static int arraysize = 199;
	static int capacity[][] = null;//��¼�������������
	static int flow[] = null;//��Ǵ�Դ�㵽��ǰ�ڵ�ʵ�ʻ�ʣ������������
	static int pre[] = null;//���������·���ϵ�ǰ�ڵ��ǰ��,ͬʱ��Ǹýڵ��Ƿ��ڶ�����
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
		System.out.println("����������� ");
		int edge = scan.nextInt();
		System.out.println("������Դ��ͻ�㣺 ");
		n = scan.nextInt();//Դ��
		m = scan.nextInt();//���
		System.out.println("������ߺ�Ȩ�أ� ");
		for(int i=0;i<edge;i++){
			start = scan.nextInt();//���
			end = scan.nextInt();//�յ�
			ci = scan.nextInt();//Ȩ��
			capacity[start][end] = ci;
		}
		System.out.println("��������");
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
		int increasement = 0;//�ֲ������
		int sumflow = 0;//�����
		while((increasement = BFS(start,end))!=-1){
			int k = end;//����ǰ��Ѱ��·��
			System.out.println(end);
			while(k!=start){
				int last = pre[k];
				capacity[last][k] -= increasement;//�ı�����ߵ�����
				capacity[k][last] += increasement;//�ı䷴��ߵ�����
				k = last;
				System.out.println(k);
			}
			sumflow += increasement;
			System.out.println("increasement "+increasement);
		}
		return sumflow;
	}
	
	static int BFS(int start,int end){
		//�����������������
		while(!queue.isEmpty())//�������
			queue.poll();
		for(int i=0;i<m+1;++i){
			pre[i] = -1; //��ʼ��ǰ���ڵ�
		}
		pre[start] = 0;//��ʼ��ǰ���ڵ�
		flow[start] = maxData;//��ʼ�����ֵ 
		queue.add(start);//�����
		while(!queue.isEmpty()){
			int index = queue.poll();//��������
			if(index == end)//�ҵ�����·��
				break;
			for(int i=0;i<m+1;++i){
				if(i != start && capacity[index][i] > 0 && pre[i] == -1){
					pre[i] = index;//��¼ǰ��
					//�ؼ����������ҵ�����
					flow[i] = Math.min(capacity[index][i], flow[index]);
					queue.add(i);
				}
			}
		}
		if(pre[end] == -1)//����ͼ�в��ٴ�������·��
			return -1;
		else
			return flow[end];
	}
}
