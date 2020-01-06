import java.util.Random;
public class NthPlaceLoser {


    public static Student NthPlaceLoser(Student[] aList, int N) {
        int hi = aList.length - 1; 
        int lo = 0;
        //StdRandom.shuffle(aList);
        while(hi > lo){
            int pivot = partition(aList, lo,hi);
            if (pivot < N) lo = pivot + 1;
            else if ( pivot > N) hi = pivot - 1;
            else return aList[N];

        }

        return aList[N];
    }

    public static int partition(Student[] a,int lo , int hi){
        int i = lo, j = hi+1;
        while(true){
            while (less(a[++i], a[lo])) 
                if (i == hi) break;

            while(less(a[lo], a[--j]))
                if(j==lo) break;
            
            if (i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;

    }

    public static void shuffle(Student[] a){
        int N = a.length;
        Random rand = new Random();
        for(int i = 0; i < N; i++){
            int r = rand.nextInt(i+1);
            exch(a,i,r);
        }
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    /**
     * This method returns whether a student is less than another student in respect
     * to how your compareTo method is defined
     * @param v First Student
     * @param w Second Student
     * @return if v (first student) is less than w (second student)
     */
    private static boolean less(Student v, Student w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) > 0;
    }

    /**
     * This method exchanges a[i] and a[j] in an array
     * @param a array in which you want to swap
     * @param i index of first item
     * @param j index of second item
     */
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String[] args) {

    }

}