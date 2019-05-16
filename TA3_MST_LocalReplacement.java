package ceshi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TA3_MST_LocalReplacement {
    private ArrayList<lEdge> edge = new ArrayList<lEdge>();//整个图的边  
    private ArrayList<lVertex> ver = new ArrayList<lVertex>();//整个图的点
    private ArrayList<lEdge> target = new ArrayList<lEdge>();//目标边，最小生成树  
    private int[] equivalence;//标志所在的集合  
    private int n;//结点个数
    private static lVertex key = null;
    private static lVertex end = null;

	public static void main(String[] args) {
		TA3_MST_LocalReplacement lr = new TA3_MST_LocalReplacement();  
        lr.init();  
        lr.local();  
        lr.print();  
	}
	
	//初始化  
    public void init(){
    	n = 9;
        System.out.println("结点数目：" + n);
        System.out.println("添加边成图");
        
        /*
         * 9个点，14条边
         * start: end: cost
         * 1 2 4, 1 8 8, 2 3 8, 2 8 11, 3 4 7, 3 6 4, 3 9 2
         * 4 5 9, 4 6 14, 5 6 10, 6 7 2, 7 8 1, 7 9 6, 8 9 7
         */
        lVertex v1 = new lVertex();v1.name = 1;v1.vertex.add(v1);ver.add(v1);
        lVertex v2 = new lVertex();v2.name = 2;v2.vertex.add(v2);ver.add(v2);
        lVertex v3 = new lVertex();v3.name = 3;v3.vertex.add(v3);ver.add(v3);
        lVertex v4 = new lVertex();v4.name = 4;v4.vertex.add(v4);ver.add(v4);
        lVertex v5 = new lVertex();v5.name = 5;v5.vertex.add(v5);ver.add(v5);
        lVertex v6 = new lVertex();v6.name = 6;v6.vertex.add(v6);ver.add(v6);
        lVertex v7 = new lVertex();v7.name = 7;v7.vertex.add(v7);ver.add(v7);
        lVertex v8 = new lVertex();v8.name = 8;v8.vertex.add(v8);ver.add(v8);
        lVertex v9 = new lVertex();v9.name = 9;v9.vertex.add(v9);ver.add(v9);
        lEdge e1 = new lEdge();  
        e1.start = v1;e1.end = v2;  
        e1.cost = 4;edge.add(e1);
        lEdge e2 = new lEdge();  
        e2.start = v1;e2.end = v8;  
        e2.cost = 8;edge.add(e2);
        lEdge e3 = new lEdge();  
        e3.start = v2;e3.end = v3;  
        e3.cost = 8;edge.add(e3);
        lEdge e4 = new lEdge();  
        e4.start = v2;e4.end = v8;  
        e4.cost = 11;edge.add(e4);
        lEdge e5 = new lEdge();  
        e5.start = v3;e5.end = v4;  
        e5.cost = 7;edge.add(e5);
        lEdge e6 = new lEdge();  
        e6.start = v3;e6.end = v6;  
        e6.cost = 4;edge.add(e6);
        lEdge e7 = new lEdge();  
        e7.start = v3;e7.end = v9;  
        e7.cost = 2;edge.add(e7);
        lEdge e8 = new lEdge();  
        e8.start = v4;e8.end = v5;  
        e8.cost = 9;edge.add(e8);
        lEdge e9 = new lEdge();  
        e9.start = v4;e9.end = v6;  
        e9.cost = 14;edge.add(e9);
        lEdge e10 = new lEdge();  
        e10.start = v5;e10.end = v6;  
        e10.cost = 10;edge.add(e10);
        lEdge e11 = new lEdge();  
        e11.start = v6;e11.end = v7;  
        e11.cost = 2;edge.add(e11);
        lEdge e12 = new lEdge();  
        e12.start = v7;e12.end = v8;  
        e12.cost = 1;edge.add(e12);
        lEdge e13 = new lEdge();  
        e13.start = v7;e13.end = v9;  
        e13.cost = 6;edge.add(e13);
        lEdge e14 = new lEdge();  
        e14.start = v8;e14.end = v9;  
        e14.cost = 7;edge.add(e14);
        
        equivalence = new int [n + 1];
        for (int i = 1; i <= n; ++i){  
        	equivalence[i] = i;  
        }
    }
    
    //集合合并  
    public void union(lEdge v, int j, int k){
    	int jj = equivalence[j];
    	int kk = equivalence[k];
        for(int i = 1; i <= n; ++i){  
            if(equivalence[i] == kk){  
            	equivalence[i] = jj;
            }  
        }
        
        lVertex boss = null;
        lVertex bro = null;
        for(lVertex lv: ver){
        	if(lv.name == jj)
        		boss = lv;
        	if(lv.name == kk)
        		bro = lv;
        }
        v.start.createEdge(v.end);
        boss.vertex.addAll(bro.vertex);
        for(lVertex lv: boss.vertex){
        	System.out.print(lv.name + "---");
        	for(lVertex lvv: lv.edge){
        		System.out.print(lvv.name + " ");
        	}
        	System.out.println();
        }
    }
    
    //集合合并  
    public void union(lEdge v, int j){
    	int jj = equivalence[j];
        
        lVertex boss = null;
        for(lVertex lv: ver){
        	if(lv.name == jj)
        		boss = lv;
        }
        v.start.createEdge(v.end);
        for(lVertex lv: boss.vertex){
        	System.out.print(lv.name + "---");
        	for(lVertex lvv: lv.edge){
        		System.out.print(lvv.name + " ");
        	}
        	System.out.println();
        }
    }
    
    //删除环中权重最大的边
    public void delete(lEdge v, int j){
    	int jj = equivalence[j];
        
        lVertex boss = null;
        for(lVertex lv: ver){
        	if(lv.name == jj)
        		boss = lv;
        }
        dfs(boss.vertex);
        double maxWeightedEdge = 0;
        lVertex startV = null;
        lVertex endV = null;
        double w = getWeighted(end, key);
        if(w == 0)
        	System.out.println("find edge error!");
        maxWeightedEdge = w;
        startV = end;
        endV = key;
        lVertex s = startV;
        lVertex t = endV;
        while(s != key){
        	t = s;
        	s = s.getPi();
        	w = getWeighted(s, t);
        	if(w > maxWeightedEdge){
        		maxWeightedEdge = w;
        		startV = s;
            	endV = t;
        	}
        }
        
        int n1 = startV.name;
        int n2 = endV.name;
        if(n1 > n2){
    		int tmp = n1;
    		n1 = n2;
    		n2 =tmp;
    	}
        for(lEdge e: target){
        	if(e.start.name == n1 && e.end.name == n2 && e.cost == maxWeightedEdge){
        		System.out.println("删除边 " + n1 + "---" + n2 + "    cost: " + maxWeightedEdge);
        		target.remove(e);
        		break;
        	}
        }
        
        Iterator<lVertex> its = startV.getEdge().iterator();
    	while(its.hasNext()){
        	lVertex lv = its.next();
        	if(lv == endV){
        		its.remove();
        		break;
        	}
    	}
    	Iterator<lVertex> itt = endV.getEdge().iterator();
    	while(itt.hasNext()){
        	lVertex lv = itt.next();
        	if(lv == startV){
        		itt.remove();
        		break;
        	}
    	}
    }
    
    private double getWeighted(lVertex end, lVertex key) {
    	int name1 = end.name;
    	int name2 = key.name;
    	if(name1 > name2){
    		int tmp = name1;
    		name1 = name2;
    		name2 =tmp;
    	}
    	
    	for(lEdge edge: target){
    		if(edge.start.name == name1 && edge.end.name == name2){
    			return edge.cost;
    		}
    	}
		return 0;
	}

	public static void dfs(List<lVertex> list){
    	for(lVertex u: list){
    		u.setColor("WHITE");
    		u.setPi(null);
    	}
    	for(lVertex u: list){
    		if(u.getColor() == "WHITE"){
    			dfs_visit(u);
    		}
    	}
    }
    
    public static void dfs_visit(lVertex u){
    	u.setColor("GRAY");
    	Iterator<lVertex> it = u.getEdge().iterator();
    	while(it.hasNext()){
        	lVertex v = it.next();
        	if(v.getColor() == "WHITE"){
    			v.setPi(u);
    			dfs_visit(v);
    		}
        	if(v.getColor() == "GRAY" && !u.getPi().equals(v)){
        		key = v;
        		end = u;
        	}
    	}
    	u.setColor("BLACK");
    }
    
    //Local Replacement算法主体  
    public void local(){  
        //找剩下的n-2条边  
        while(edge.size() > 0){  
            //每次随机取一边，并删除   
            lEdge tmp = edge.get(0);
            int j = equivalence[tmp.start.name];  
            int k = equivalence[tmp.end.name];
            System.out.println("j为" + j + " k为" + k);
            //去掉环，判断当前这条边的两个端点是否属于同一棵树
            if(j != k){
            	//不是同一棵树
                target.add(tmp); 
                union(tmp, j, k);  
            }else{
            	//是同一棵树
            	target.add(tmp);
            	union(tmp, j);
            	//找环删边
            	delete(tmp, j);
            }
            edge.remove(tmp);  
        }
    }
    
    //打印结果  
    public void print(){
    	System.out.println("剩余邻接链表为");
    	lVertex boss = null;
        for(lVertex lv: ver){
        	if(lv.name == 1)
        		boss = lv;
        }
        for(lVertex lv: boss.vertex){
        	System.out.print(lv.name + "---");
        	for(lVertex lvv: lv.edge){
        		System.out.print(lvv.name + " ");
        	}
        	System.out.println();
        }
        double sum = 0;
        for(int i = 0; i < target.size(); ++i){  
            lEdge e = target.get(i);  
            System.out.println((i+1) + "th edge:" + e.start.name + "---" + e.end.name + "   cost:" + e.cost);  
            sum += e.cost;
        }  
        System.out.println("MST cost:"+sum);
    }  

}

class lVertex  
{  
    public int name;
    public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public String color = null;
    public lVertex pi = null;
    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public lVertex getPi() {
		return pi;
	}

	public void setPi(lVertex pi) {
		this.pi = pi;
	}

	public ArrayList<lVertex> edge = new ArrayList<lVertex>();
    public ArrayList<lVertex> getEdge() {
		return edge;
	}

	public void setEdge(ArrayList<lVertex> edge) {
		this.edge = edge;
	}

	public ArrayList<lVertex> vertex = new ArrayList<lVertex>();
    
    public void createEdge(lVertex v){
        edge.add(v);
        v.edge.add(this);
        System.out.println("顶点" + this.name + "新建边" + v.name);
    }
}

class lEdge  
{  
    public lVertex start;//始边  
    public lVertex end;//终边  
    public double cost;//权重  
}  
