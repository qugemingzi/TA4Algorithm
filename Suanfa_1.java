package ceshi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Suanfa_1 {

	static int g[][];//�ڽӾ���
	static int col[];//�жϵ�ǰ���Ƿ������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();//����
		int m = scan.nextInt();//����
		g = new int [n][n];
		col = new int[n];
		//��ʼ��
		for(int i=0;i<n;i++){
			col[i] = -1;
			for(int j=0;j<n;j++){
				g[i][j] = 0;
			}
		}
		for(int i=0;i<m;i++){
			int a = scan.nextInt();
			int b = scan.nextInt();
			g[a][b] = g[b][a] = 1;
		}
		boolean flag = false;
		for(int i=0;i<n;i++){
			if(col[i] == -1 && !bfs(i,n)){
				flag = true;
				break;
			}
		}
		if(flag) System.out.println("No!");
		else System.out.println("Yes!");
		scan.close();

	}
	
	static boolean bfs(int s , int n){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		col[s] = 1;
		while(!queue.isEmpty()){
			int front = queue.poll();
			for(int i=0;i<n;i++){
				if(g[front][i] == 1 && col[i] == -1){
					queue.add(i);
					col[i] = 1+col[front];
				}
				if(g[front][i] == 1 && col[front] == col[i]){
					return false;
				}
			}
		}
		return true;
	}

}
