import java.util.ArrayList;
import java.util.Arrays;
public class AppleSort {


    /**
     * Implement this method that takes in an arraylist of Apples and sorts them into one giant array of Apples.
     * You are free to implement any helper methods you need.
     * @param apples
     * @return
     */
    public static Comparable[] sort(ArrayList<Apple[]> apples){
        Comparable[] lastList = new Comparable[1];
        int i = 0;
        while(apples.size() > 1){
            apples.set(0,merge(apples.get(0), apples.get(1)));
            apples.remove(1);
        }
        lastList = apples.get(0);

        return lastList;
    }
    private static Apple[] merge(Apple[] a, Apple[] b){
        Apple[] ret = new Apple[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < a.length && j< b.length){
            if(less(b[j], a[i])) ret[k++] = b[j++];
            else ret[k++] = a[i++];
        }
        while(i < a.length) ret[k++] = a[i++];
        while(j < b.length) ret[k++] = b[j++];
        return ret;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    public static void main(String[] args) {
        Apple[] basket0 = {new Apple(1,1), new Apple(2,1), new Apple(2,1)};
        Apple[] basket1 = {new Apple(1,10), new Apple(2,10)};
        ArrayList<Apple[]> list = new ArrayList();
        list.add(basket0);
        list.add(basket1);
        Comparable[] apples =  AppleSort.sort(list);
 
    }

}

