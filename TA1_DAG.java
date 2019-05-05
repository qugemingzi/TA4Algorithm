package ceshi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TA1_DAG {
	static int time;
	
    public static void main(String args[]){
    	// Ŀ�꣺������ͼɾ��ĳЩ�߳�Ϊ�����޻�ͼ
    	// �뷨��DFS�����ر�ɾ����������ʱ��ɫΪ����ɫ��
        System.out.println("�½��㼯��");
        TA0_DVertex V1 = new TA0_DVertex("V1");
        TA0_DVertex V2 = new TA0_DVertex("V2");
        TA0_DVertex V3 = new TA0_DVertex("V3");
        TA0_DVertex V4 = new TA0_DVertex("V4");
        TA0_DVertex V5 = new TA0_DVertex("V5");
        TA0_DVertex V6 = new TA0_DVertex("V6");
        TA0_DVertex V7 = new TA0_DVertex("V7");
        TA0_DVertex V8 = new TA0_DVertex("V8");
        System.out.println("�½��߼���");
        V1.createEdge(V2);V1.createEdge(V4);
        V2.createEdge(V3);V2.createEdge(V5);
        V3.createEdge(V4);V3.createEdge(V5);
        V5.createEdge(V6);
        V6.createEdge(V7);
        V7.createEdge(V8);
        V8.createEdge(V4);V8.createEdge(V5);
        List<TA0_DVertex> V = new ArrayList<TA0_DVertex>();
        V.add(V1);V.add(V2);V.add(V3);V.add(V4);
        V.add(V5);V.add(V6);V.add(V7);V.add(V8);
        printList(V);
        dfs(V);
        printDF(V);
        printList(V);
    }
    
    public static void printList(List<TA0_DVertex> list){
    	System.out.println("��ӡ�ڽ�����");
    	for(TA0_DVertex v: list){
    		v.printEdge();
    	}
    }
    
    public static void printDF(List<TA0_DVertex> list){
    	System.out.println("��ӡDF");
    	for(TA0_DVertex v: list){
    		System.out.println("���� " + v.getName() + "DFֵΪ " + v.getD() + " " + v.getF());
    	}
    }
    
    public static void dfs(List<TA0_DVertex> list){
    	for(TA0_DVertex u: list){
    		u.setColor("WHITE");
    		u.setPi(null);
    	}
    	time = 0;
    	for(TA0_DVertex u: list){
    		if(u.getColor() == "WHITE"){
    			dfs_visit(u);
    		}
    	}
    }
    
    public static void dfs_visit(TA0_DVertex u){
    	u.setD(++time);
    	u.setColor("GRAY");
    	Iterator<TA0_DVertex> it = u.getEdge().iterator();
    	while(it.hasNext()){
        	TA0_DVertex v = it.next();
        	if(v.getColor() == "WHITE"){
    			v.setPi(u);
    			dfs_visit(v);
    		}
        	if(v.getColor() == "GRAY"){
        		it.remove();
        		System.out.println("����" + u.getName() + "ɾ����" + v.getName());
        	}
    	}
    	u.setColor("BLACK");
    	u.setF(++time);
    }

}


