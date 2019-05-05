package ceshi;

import java.util.ArrayList;

public class TA0_DVertex {
	// 有向图点类
	private String name = null;
    private int d = 0;
    private int f = 0;
    private String color = null;
    private TA0_DVertex pi = null;
    private ArrayList<TA0_DVertex> edge = new ArrayList<TA0_DVertex>();

    public TA0_DVertex(String name){
        this.name = name;
    }

    public void createEdge(TA0_DVertex v){
        edge.add(v);
        System.out.println("顶点" + this.name + "新建边" + v.name);
    }

    public void printEdge(){
    	System.out.print("顶点 " + this.name + "的邻接链表为 ");
        for(TA0_DVertex v: this.edge) {
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

	public TA0_DVertex getPi() {
		return pi;
	}

	public void setPi(TA0_DVertex pi) {
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
	
	public ArrayList<TA0_DVertex> getEdge() {
		return edge;
	}
}
