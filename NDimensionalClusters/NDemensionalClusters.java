import java.util.*;

public class NDemensionalClusters {
	ArrayList<Point> points = new ArrayList<Point>();
   
    /**
     * Adds a point to the dataSet being considered
     * @param point
     */
    public void add(Point point) {
    	points.add(point);
    }

    /**
     * The Total sum on the edges in a cluster.
     * @param clusters the number of clusters to consider
     * @return sum
     */
    public double getSum(int clusters) {
    	PriorityQueue<Edge> pq = new PriorityQueue<Edge>(20, Collections.reverseOrder()); 
    	Queue<Edge> mst = new LinkedList<Edge>();  // edges in MST
    	double weight = 0;                        // weight of MST
    	int numClusters = points.size();
    	WeightedQuickUnionUF uf = new WeightedQuickUnionUF(numClusters);
    	for(Point i: points){
    		for(Point j: points){
    			if(i != j){
    				Edge toAdd = new Edge(points.indexOf(i), points.indexOf(j), i.distance(j));
    				pq.add(toAdd);
    			}
    		}
    	}
    	//System.out.println("Cluster Size = " + clusters);
    	while (pq.peek() != null && mst.size() < points.size() - 1) {
    		if(numClusters == clusters){
            	//System.out.println(weight + " " + numClusters);
            	break;
            }
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.add(e);  // add edge e to mst
                weight += e.weight;
                //System.out.println(weight + " " + numClusters);
                numClusters--;
            }
        }
        return weight;
    }

    public static void main(String[] args) {

    }
}

class Edge implements Comparable<Edge>{
	private final int v, w;
	public final double weight;

	public Edge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public int either(){
		return v;
	}

	public int other(int vertex){
		if(vertex == v) return w;
		else return v;
	}

	public int compareTo(Edge that){
	    if(this.weight < that.weight) return -1;
	    else if (this.weight > that.weight) return +1;
	    else return  0;
	}
}

class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }
  
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}
