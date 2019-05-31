package ceshi;

import java.util.Scanner;

public class TA5_TSP {
    private float edge[][];
    private float city[][];
    private float d[][];
    private int pi[][];

    private void input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入城市个数:");
        int num = scan.nextInt();
        city = new float[num][2];
        edge = new float[num][num];
        d = new float[num][1 << (num-1)];//1<<(n-1)表示2的n-1次方，d[]为动态规划存储的城市经过矩阵
        pi = new int[num][1 << (num-1)];//最短路径下一个结点，为输出路径
        for (int i = 0; i < num; i++) {
            System.out.println("第" + (i + 1) + "个城市坐标为:");
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
        for(int i = 1; i < n; i++){//将所有城市到第1个城市的路径初始化为两市间的距离
        	d[i][0] = edge[i][0];
        	pi[i][0] = i;
        }
        
        //很明显时间复杂度为O(n^2*2^(n-1))
        for(int j = 1; j < 1<<(n-1); j++){
        	for(int i = 1; i < n; i++){//j用二进制表示的城市集合
        		if((1 << (i-1)&j) == 0){//i不在j表示的城市集合中
        			min = 999;
        			next = -1;
        			for(int k = 1; k < n; k++){
        				if((1 << (k-1)&j) != 0){//k表示的城市在j表示的城市集合中
        					float temp = edge[i][k] + d[k][j-(1 << (k-1))];
        					if(temp < min){
        						min = temp;//所有k中最小的距离
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
        for(int k = 1; k < n; k++){//加上城市1开始，得到最短路径
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
