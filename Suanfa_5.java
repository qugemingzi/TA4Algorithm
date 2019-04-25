package ceshi;

public class Suanfa_5 {

	static int[] p = { 0, 1, 5, 2, 4, 5, 9, 5, 10, 5, 6, 6, 7, 8, 7, 11, 6, 6,
			7, 11, 10, 7, 12, 8, 8, 8, 6, 5, 13, 14, 9, 8, 9, 3, 3, 11, 2, 9,
			13, 4, 6, 4, 4, 8, 9, 6, 15, 5, 7, 9, 3 };
	static int[] w = { 0, 2, 3, 2, 5, 4, 4, 6, 8, 5, 7, 7, 4, 6, 9, 12, 4, 4,
			3, 5, 5, 3, 6, 9, 5, 9, 12, 3, 7, 12, 11, 11, 3, 4, 5, 3, 12, 2, 6,
			6, 4, 9, 15, 12, 4, 8, 31, 1, 12, 3, 4 };
	static int length;// 工件个数
	static int[] f;// 子集生产总消耗
	static int[] g;// 每一个批次的最开始索引号
	static int[] h;// 累计消耗时间
	static int []p1;
	static int []w1;

	public static void main(String[] args) {
		sort_p(p,w);
		length = w1.length;
		f = new int[length];
		g = new int[length];
		h = new int[length];
		for (int i = 0; i < length; i++) {
			f[i] = h[i] = 0;
			g[i] = 1;
		}
		comp_f(p1,w1);
		print(f);
		print(g);
		print(h);
		System.out.println(f[f.length-1]);
	}

	static void sort_p(int []p,int []w) {
		int temp_p,temp_w;
		for(int i=0;i<p.length-1;i++){
			for(int j=i+1;j<p.length;j++){
				if(p[j]<p[i]){
					temp_p=p[i];
					p[i]=p[j];
					p[j]=temp_p;
					temp_w=w[i];
					w[i]=w[j];
					w[j]=temp_w;
				}else if(p[j]==p[i]&&w[j]<w[i]){
					temp_w=w[i];
					w[i]=w[j];
					w[j]=temp_w;
				}
			}
		}
		p1 = new int[16];
		for (int i = 1; i <= 15; i++) {
			p1[i] = i;
		}
		w1 = new int[16];
		int sum = 0;
		w1[1] = 2;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j <= 50; j++) {
				if (p[j] == i + 1)
					sum = sum + w[j];
			}
			w1[i + 1] = sum;
			sum = 0;
		}
		print(p1);print(w1);
		
	}
	
	static void print(int []arr) {
		for(int temp:arr){
			System.out.print(temp+" ");
		}
		System.out.println();
	}
	
	static void sum(int []arr) {
		int result=0;
		for(int temp:arr){
			result+=temp;
		}
		System.out.println(p[p.length-1]*result);
	}

	static void comp_f(int []p,int []w) {
		for(int n=1;n<p.length;n++){
			int t1 = f[n - 1] + w[n] * (h[n - 1] + p[n]);
			int t=0;
			for(int j=g[n-1];j<=n;j++){
				t+=w[j];
			}
			int t2 = f[g[n - 1] - 1] + t * (h[g[n - 1] - 1] + p[n]);
			if(t1<t2){
				f[n]=t1;
				g[n]=n;
				h[n]=h[n-1]+p[n];
			}else{
				f[n]=t2;
				g[n]=g[n-1];
				h[n]=h[g[n-1]-1]+p[n];
			}
		}
	}
}
