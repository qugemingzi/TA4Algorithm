package ceshi;

import java.util.Scanner; 
import java.util.ArrayList;
  
public class TA3_MST_kruskal {  
    private static int MAX = 100;  
    private ArrayList<Edge> edge = new ArrayList<Edge>();//����ͼ�ı�  
    private ArrayList<Edge> target = new ArrayList<Edge>();//Ŀ��ߣ���С������  
    private int[] equivalence = new int[MAX];//��־���ڵļ���  
    private static double INFINITY = 99999999.99;//���������  
    private double mincost = 0.0;//��С�ɱ�  
    private int n;//������  

    public static void main(String args[]){  
    	TA3_MST_kruskal sp = new TA3_MST_kruskal();  
        sp.init();  
        sp.kruskal();  
        sp.print();  
    }  
    //��ʼ��  
    public void init(){  
        Scanner scan = new Scanner(System.in);  
        int p,q;  
        double w;  
          
        System.out.println("spanning tree begin!Input the node number:");  
        n = scan.nextInt();  
        System.out.println("Input the graph(-1,-1,-1 to exit)");  
          
        while(true){  
            p = scan.nextInt();  
            q = scan.nextInt();  
            w = scan.nextDouble();  
            if(p < 0 || q < 0 || w < 0){  
                break;  
            }  
            Edge e = new Edge();  
            e.start = p;  
            e.end = q;  
            e.cost = w;  
            edge.add(e);  
        }  
          
        mincost = 0.0;  
          
        for (int i = 1; i <= n; ++i){  
        	equivalence[i] = i;  
        }  
        scan.close();
    }  
    //���Ϻϲ�  
    public void union(int j, int k){  
        for(int i = 1; i <= n; ++i){  
            if(equivalence[i] == j){  
            	equivalence[i] = k;  
            }  
        }  
    }  
    //Kruskal�㷨����  
    public void kruskal(){  
        //��ʣ�µ�n-2����  
        int i = 0;  
        while(i < n-1 && edge.size() > 0){  
            //ÿ��ȡһ��С�ߣ���ɾ��  
            double min = INFINITY;  
            Edge tmp = null;  
            for(int j = 0; j < edge.size(); ++j){  
                Edge tt = edge.get(j);  
                if(tt.cost < min){  
                    min = tt.cost;  
                    tmp = tt;  
                }  
            }  
            int jj = equivalence[tmp.start];  
            int kk = equivalence[tmp.end];  
            //ȥ�������жϵ�ǰ�����ߵ������˵��Ƿ�����ͬһ����
            if(jj != kk){  
                ++i;  
                target.add(tmp);  
                mincost += tmp.cost;  
                union(jj,kk);  
            }  
            edge.remove(tmp);  
        }  
        if(i != n-1){  
            System.out.println("no spanning tree");  
        }  
    }  
    //��ӡ���  
    public void print(){  
        double sum = 0;
        for(int i = 0; i < target.size(); ++i){  
            Edge e = target.get(i);  
            System.out.println("the " + (i+1) + "th edge:" + e.start + "---" + e.end + "   cost:" + e.cost);  
            sum += e.cost;
        }  
        System.out.println("the MST cost:"+sum);
    }  
}  
  
class Edge  
{  
    public int start;//ʼ��  
    public int end;//�ձ�  
    public double cost;//Ȩ��  
}  