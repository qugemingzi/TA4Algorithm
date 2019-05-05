package ceshi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TA2_Cut_Vertex {
	static int time;

	public static void main(String[] args) {
		// Ŀ�꣺����ͼ�еĸ��
		// �뷨��DFS�м���dfn(dֵ)��lowֵ
		// dfn��ʾ��DFS���ݵ�ǰ���£��ý�㱻������ʱ���Ⱥ�˳��Ҳ����ʱ�����˳�����
		// low��ʾ�ý�㲻ͨ�����׵�������������ͨ�Ķ���ʱ�������Сֵ����ʼֵΪdfn��
		System.out.println("�½��㼯��");
        TA0_Vertex V1 = new TA0_Vertex("V1");
        TA0_Vertex V2 = new TA0_Vertex("V2");
        TA0_Vertex V3 = new TA0_Vertex("V3");
        TA0_Vertex V4 = new TA0_Vertex("V4");
        TA0_Vertex V5 = new TA0_Vertex("V5");
        TA0_Vertex V6 = new TA0_Vertex("V6");
        TA0_Vertex V7 = new TA0_Vertex("V7");
        TA0_Vertex V8 = new TA0_Vertex("V8");
        System.out.println("�½��߼���");
        V1.createEdge(V2);V1.createEdge(V4);
        V2.createEdge(V3);V2.createEdge(V5);
        V3.createEdge(V5);
        V5.createEdge(V6);
        V6.createEdge(V7);
        V7.createEdge(V8);
        V8.createEdge(V5);
        List<TA0_Vertex> V = new ArrayList<TA0_Vertex>();
        V.add(V1);V.add(V2);V.add(V3);V.add(V4);
        V.add(V5);V.add(V6);V.add(V7);V.add(V8);
        printList(V);
        dfs(V);
        printDL(V);
        checkCutVertex(V);
	}

	public static void printList(List<TA0_Vertex> list){
    	System.out.println("��ӡ�ڽ�����");
    	for(TA0_Vertex v: list){
    		v.printEdge();
    	}
    }
	
	public static void printDL(List<TA0_Vertex> list){
    	System.out.println("��ӡdfn��low");
    	for(TA0_Vertex v: list){
    		System.out.println("���� " + v.getName() + "dfnֵΪ " + v.getDfn() + " lowֵΪ " + v.getLow());
    	}
    }
    
    public static void dfs(List<TA0_Vertex> list){
    	for(TA0_Vertex u: list){
    		u.setColor("WHITE");
    		u.setPi(null);
    	}
    	time = 0;
    	for(TA0_Vertex u: list){
    		if(u.getColor() == "WHITE"){
    			dfs_visit(u);
    		}
    	}
    }
    
    public static void dfs_visit(TA0_Vertex u){
    	u.setDfn(++time);
    	u.setLow(time);
    	u.setColor("GRAY");
    	Iterator<TA0_Vertex> it = u.getEdge().iterator();
    	while(it.hasNext()){
        	TA0_Vertex v = it.next();
        	if(v.getColor() == "WHITE"){
    			v.setPi(u);
    			dfs_visit(v);
    			u.setLow(Math.min(u.getLow(), v.getLow()));
    		}
        	if(v.getColor() == "GRAY" && !u.getPi().equals(v)){
        		u.setLow(Math.min(u.getLow(), v.getDfn()));
        	}
    	}
    	u.setColor("BLACK");
    }
	
	public static void checkCutVertex(List<TA0_Vertex> list){
		System.out.println("��ӡ���");
		for(TA0_Vertex u: list){
			for(TA0_Vertex v: u.getEdge()){
				if(v.getLow() >= u.getDfn()){
					System.out.println("����" + u.getName() + "Ϊ���");
					break;
				}
			}
		}
	}
}
