package ceshi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TA1_DAG {
	static int time;
	
    public static void main(String args[]){
        System.out.println("新建点集！");
        Vertex V1 = new Vertex("V1");
        Vertex V2 = new Vertex("V2");
        Vertex V3 = new Vertex("V3");
        Vertex V4 = new Vertex("V4");
        Vertex V5 = new Vertex("V5");
        Vertex V6 = new Vertex("V6");
        Vertex V7 = new Vertex("V7");
        Vertex V8 = new Vertex("V8");
        System.out.println("新建边集！");
        V1.createEdge(V2);V1.createEdge(V4);
        V2.createEdge(V3);V2.createEdge(V5);
        V3.createEdge(V4);V3.createEdge(V5);
        V5.createEdge(V6);
        V6.createEdge(V7);
        V7.createEdge(V8);
        V8.createEdge(V4);V8.createEdge(V5);
        List<Vertex> V = new ArrayList<Vertex>();
        V.add(V1);V.add(V2);V.add(V3);V.add(V4);
        V.add(V5);V.add(V6);V.add(V7);V.add(V8);
        printList(V);
        dfs(V);
        printDF(V);
        printList(V);
    }
    
    public static void printList(List<Vertex> list){
    	System.out.println("打印邻接链表");
    	for(Vertex v: list){
    		v.printEdge();
    	}
    }
    
    public static void printDF(List<Vertex> list){
    	System.out.println("打印DF");
    	for(Vertex v: list){
    		System.out.println("顶点 " + v.getName() + "DF值为 " + v.getD() + " " + v.getF());
    	}
    }
    
    public static void dfs(List<Vertex> list){
    	for(Vertex u: list){
    		u.setColor("WHITE");
    		u.setPi(null);
    	}
    	time = 0;
    	for(Vertex u: list){
    		if(u.getColor() == "WHITE"){
    			dfs_visit(u);
    		}
    	}
    }
    
    public static void dfs_visit(Vertex u){
    	u.setD(++time);
    	u.setColor("GRAY");
    	Iterator<Vertex> it = u.getEdge().iterator();
    	while(it.hasNext()){
        	Vertex v = it.next();
        	if(v.getColor() == "WHITE"){
    			v.setPi(u);
    			dfs_visit(v);
    		}
        	if(v.getColor() == "GRAY"){
        		it.remove();
        		System.out.println("顶点" + u.getName() + "删除边" + v.getName());
        	}
    	}
    	u.setColor("BLACK");
    	u.setF(++time);
    }

}

class Vertex{
    private String name = null;
    private int d = 0;
    private int f = 0;
    private String color = null;
    private Vertex pi = null;
    private ArrayList<Vertex> edge = new ArrayList<Vertex>();

    public Vertex(String name){
        this.name = name;
    }

    public void createEdge(Vertex v){
        edge.add(v);
        System.out.println("顶点" + this.name + "新建边" + v.name);
    }

    public void printEdge(){
    	System.out.print("顶点 " + this.name + "的邻接链表为 ");
        for(Vertex v: this.edge) {
            System.out.print(v.name + " ");
        }
        System.out.println();
    }
    
    public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Vertex getPi() {
		return pi;
	}

	public void setPi(Vertex pi) {
		this.pi = pi;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}
	
	public ArrayList<Vertex> getEdge() {
		return edge;
	}
}

