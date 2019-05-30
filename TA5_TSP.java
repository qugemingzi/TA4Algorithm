import java.util.ArrayList;
import java.util.Scanner;

public class TA5_TSP {
    private float edge[][];
    private float city[][];
    private static ArrayList<Integer> U;

    private void input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入城市的数量:");
        int num = scan.nextInt();
        city = new float[num][2];
        edge = new float[num][num];
        U = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.println("请输入第" + (i + 1) + "个城市的位置:");
            city[i][0] = scan.nextFloat();
            city[i][1] = scan.nextFloat();
            if(i > 0)
                U.add(i);
        }
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                edge[i][j] = (float) Math.sqrt(Math.pow(
                        city[i][0] - city[j][0], 2)
                        + Math.pow(city[i][1] - city[j][1], 2));
            }
        }
        scan.close();
    }

    private float tsp(int s, ArrayList<Integer> X, int t){
        float min = 999;
        if(X.size() == 0){
            min = edge[s][t];
        }else{
            for(int i = 0; i < X.size(); i++){
                int v = X.get(i);
                X.remove(i);
                float temp = tsp(s, X, v) + edge[v][t];
                if(temp < min) {
                    min = temp;
                }
                X.add(i, v);
            }
        }

        return min;
    }

    public static void main(String args[]){
        TA5_TSP t = new TA5_TSP();
        t.input();
        float cost = t.tsp(0, U, 0);
        System.out.println(cost);
    }
}
