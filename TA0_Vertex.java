package ceshi;

import java.util.ArrayList;

public class TA0_Vertex {
	// 无向图点类
	private String name = null;
    private int d = 0;
    private int f = 0;
    private int dfn = 0;
	private int low = 0;
    private String color = null;
    private TA0_Vertex pi = null;
    private ArrayList<TA0_Vertex> edge = new ArrayList<TA0_Vertex>();

    public TA0_Vertex(String name){
        this.name = name;
    }

    public void createEdge(TA0_Vertex v){
        edge.add(v);
        v.edge.add(this);
        System.out.println("顶点" + this.name + "新建边" + v.name);
    }

    public void printEdge(){
    	System.out.print("顶点 " + this.name + "的邻接链表为 ");
        for(TA0_Vertex v: this.edge) {
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

	public TA0_Vertex getPi() {
		return pi;
	}

	public void setPi(TA0_Vertex pi) {
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
	
	public int getDfn() {
		return dfn;
	}

	public void setDfn(int dfn) {
		this.dfn = dfn;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}
	
	public ArrayList<TA0_Vertex> getEdge() {
		return edge;
	}
}
