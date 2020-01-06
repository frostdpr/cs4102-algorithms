import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
public class FriendCircles {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i
    private int n;
    /**
     *
     * @param M the adjacency matrix of friends
     * @return number of friend circles
     */
    public int findCircleNum(int[][] M) {
        n = M.length;
        parent = new int[n];
        rank = new byte[n];
        int count = 0;
        for(int i = 0; i < n; i++){
            rank[i] = 1;
            parent[i] = i;
        }
        for(int i = 0; i < n; i ++){
            for(int j = 0; j <= i; j++){
                if(M[i][j] == 1){
                    union(i,j);
                }
            }
        }
        for(int i = 0; i < n; i++){
            if(parent[i] == i) count++;
        }
        return count;
    }


    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        if(!(0 <= p && p < n)) throw new IllegalArgumentException();
        while(p != parent[p]){
            parent[p]=parent[parent[p]];
            p = parent[p];}
        return p;
    }


    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        // TODO: implement this method
        if(!(0 <= p && p < n && 0 <= q && q < n)) throw new IllegalArgumentException();
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        if(!(0 <= p && p < n && 0 <= q && q < n)) throw new IllegalArgumentException();
        int i = find(p);
        int j = find(q);
        if(rank[i] < rank[j]){parent[i] = j; rank[j] += rank[i];}
        else {parent[j] = i; rank[i] += rank[j];}
        //parent[i] = j;
    }

    public static void main(String[] args) {

    }   

}
