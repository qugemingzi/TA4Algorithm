import java.util.ArrayList;
import java.util.Scanner;

public class negcircle {
    private static int W[][];
    private static int Pi[][];
    private static int n;
    private static final int INFINITY = 999;

    public static void input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入节点数：");
        n = scan.nextInt();
        System.out.println("请输入边数：");
        int m = scan.nextInt();
        W = new int[n + 1][n + 1];
        Pi = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    W[i][j] = 0;
                else
                    W[i][j] = INFINITY;
                Pi[i][j] = 0;
            }
        }
        System.out.println("请依次输入边（起始点 结束点 权重）");
        while(m > 0){
            int u = scan.nextInt();
            int v = scan.nextInt();
            int cost = scan.nextInt();
            W[u][v] = cost;
            Pi[u][v] = u;
            m--;
        }
        System.out.println("k = 0");
        print();
    }

    public static void print(){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j<= n; j++) {
                System.out.print(W[i][j] + "\t");
            }
            for(int j = 1; j<= n; j++) {
                System.out.print(Pi[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void floyd(){
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(W[i][j] > W[i][k] + W[k][j]){
                        W[i][j] = W[i][k] + W[k][j];
                        Pi[i][j] = Pi[k][j];
                    }
                }
            }
            System.out.println("k = " + k);
            print();
        }
    }

    public static void findCircle(){
        int v = 0;
        ArrayList<Integer> path = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            if(W[i][i] < 0){
                v = i;
                break;
            }
        }
        if(v > 0){
            // find negative circle
            path.add(0, v);
            while(!path.contains(Pi[v][v])){
                path.add(0, Pi[v][v]);
                v = Pi[v][v];
            }
            System.out.print("发现负环：" + Pi[v][v] + "->");
            for(int i = 0; i < path.size(); i++){
                if(path.get(i) == Pi[v][v]) {
                    System.out.println(path.get(i));
                    break;
                }else
                    System.out.print(path.get(i) + "->");
            }
        }else{
            System.out.println("未发现负环！");
        }
    }

    public static void main(String args[]){
        input();
        floyd();
        findCircle();
    }
    /*
5 10
1 2 3
1 3 8
1 5 -4
2 1 -2
2 4 1
2 5 7
3 2 4
4 1 2
4 3 -5
5 4 6
     */
}
