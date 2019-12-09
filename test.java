import java.util.ArrayList;
import java.util.Iterator;

public class test {

    public static class Animal{
        public void move(){
            System.out.println("动物可以移动");
        }
    }

    public static class Dog extends Animal{
        public void move(){
            System.out.println("狗可以移动");
        }
        public void bark(){
            System.out.println("狗可以吠叫");
        }
    }

    public static class Cat extends Animal{
        public void move(){
            System.out.println("猫可以移动");
        }
        public void bark(){
            System.out.println("猫可以吠叫");
        }
    }

    private static void print(ArrayList<Integer> X){
        Iterator<Integer> it = X.iterator();
        while(it.hasNext()){
            int x = it.next();
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String args[]){
        ArrayList<Integer> U = new ArrayList<Integer>();
        U.add(1);U.add(3);U.add(2);
//        for(int i = 0; i < U.size(); i++){
//            System.out.println("U");
//            print(U);
//            ArrayList<Integer> V = (ArrayList<Integer>)U.clone();
//            V.remove(i);
//            System.out.println("V");
//            print(V);
//        }
        for(int i = 0; i < U.size(); i++){
            System.out.println("U");
            print(U);
            int temp = U.get(i);
            U.remove(i);
            System.out.println("U");
            print(U);
            U.add(i,temp);
            System.out.println("U");
            print(U);
        }

        int []v = {32, 16, 8, 4, 2};
        int []c = {1, 2, 3, 4, 5};
        int []h = {5, 4, 3, 2, 1};

        double []per = new double[5];
        for(int i = 0; i < per.length; i++)
            per[i] = (double) h[i]/v[i];

        for(int i = 0; i < per.length-1; i++){
            for(int j = i; j < per.length; j++){
                if((per[i] < per[j]) || ((per[i] == per[j]) && (v[i] < v[j]))){
                    double dtemp;
                    int itemp;
                    dtemp = per[i]; per[i] = per[j]; per[j] = dtemp;
                    itemp = v[i]; v[i] = v[j]; v[j] = itemp;
                    itemp = c[i]; c[i] = c[j]; c[j] = itemp;
                    itemp = h[i]; h[i] = h[j]; h[j] = itemp;
                }
            }
        }
        for(int i = 0; i < per.length; i++){
            System.out.println(per[i] + "\t" + v[i] + "\t" + c[i] + "\t" + h[i]);
        }System.out.println();

        Animal a = new Animal();
        Animal b = new Dog();
        Dog dog = new Dog();
        a.move();
        b.move();
        ((Dog) b).bark();
        dog.bark();
    }
}
