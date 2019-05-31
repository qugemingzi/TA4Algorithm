package ceshi;

import java.util.Scanner;

public class TA5_TSP {
    private float edge[][];
    private float city[][];
    private float d[][];
    private int pi[][];

    private void input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("��������и���:");
        int num = scan.nextInt();
        city = new float[num][2];
        edge = new float[num][num];
        d = new float[num][1 << (num-1)];//1<<(n-1)��ʾ2��n-1�η���d[]Ϊ��̬�滮�洢�ĳ��о�������
        pi = new int[num][1 << (num-1)];//���·����һ����㣬Ϊ���·��
        for (int i = 0; i < num; i++) {
            System.out.println("��" + (i + 1) + "����������Ϊ:");
            city[i][0] = scan.nextFloat();
            city[i][1] = scan.nextFloat();
        }
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                edge[i][j] = (float) Math.sqrt(Math.pow(
                        city[i][0] - city[j][0], 2)
                        + Math.pow(city[i][1] - city[j][1], 2));
                edge[j][i] = edge[i][j];
            }
        }
        scan.close();
    }

    private void tsp(){
    	int n = edge.length;
    	float min = 999;
    	int next = -1;
        for(int i = 1; i < n; i++){//�����г��е���1�����е�·����ʼ��Ϊ���м�ľ���
        	d[i][0] = edge[i][0];
        	pi[i][0] = i;
        }
        
        //������ʱ�临�Ӷ�ΪO(n^2*2^(n-1))
        for(int j = 1; j < 1<<(n-1); j++){
        	for(int i = 1; i < n; i++){//j�ö����Ʊ�ʾ�ĳ��м���
        		if((1 << (i-1)&j) == 0){//i����j��ʾ�ĳ��м�����
        			min = 999;
        			next = -1;
        			for(int k = 1; k < n; k++){
        				if((1 << (k-1)&j) != 0){//k��ʾ�ĳ�����j��ʾ�ĳ��м�����
        					float temp = edge[i][k] + d[k][j-(1 << (k-1))];
        					if(temp < min){
        						min = temp;//����k����С�ľ���
        						next = k;
        					}
        				}
        			}
        		}
        		d[i][j] = min;
        		pi[i][j] = next;
        	}
        }
        min = 999;
        next = -1;
        for(int k = 1; k < n; k++){//���ϳ���1��ʼ���õ����·��
        	float temp = edge[0][k] + d[k][((1 << (n-1))-1) - (1 << (k-1))];
        	if(min > temp){
        		min = temp;
        		next = k;
        	}
        }
        d[0][(1 << (n-1))-1] = min;
        pi[0][(1 << (n-1))-1] = next;
        print();
    }
    
    private void print(){
    	int n = edge.length;
    	System.out.println("min tsp is " + d[0][(1 << (n-1))-1]);
//    	for(int i = 0; i < n; i++){
//    		for(int j = 0; j < (1 << (n-1)); j++){
//    			System.out.print(d[i][j] + "\t");
//    		}System.out.println();
//    	}
    	int v = 0;
    	int U = (1 << (n-1))-1;
    	System.out.print(v+1 + "=>");
    	while(U != 0){
    		System.out.print(pi[v][U]+1 + "=>");
    		v = pi[v][U];
    		U -= 1 << (v-1);
    	}
    	System.out.println(1);
//    	System.out.println("print pi");
//    	for(int i = 0; i < n; i++){
//    		for(int j = 0; j < (1 << (n-1)); j++){
//    			System.out.print(pi[i][j] + "\t");
//    		}System.out.println();
//    	}
    }

    public static void main(String args[]){
        TA5_TSP t = new TA5_TSP();
        t.input();
        t.tsp();
    }
    /*
     0 1
     1 3
     0 6
     4 0
     6 3
     4 5
     */
}
