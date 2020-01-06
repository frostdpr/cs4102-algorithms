import java.util.*;

public class DDRRobot {

    public Digraph G = null;
    private Digraph G_r = null;
    private int[] id;
    private boolean[] marked;
    private int[] count;
    private int dim;
    private int num_squares;
    private int max_id;
    private int ident;
    /**
     * Initalizes the DDRobot Board
     * @param squares
     */
    public DDRRobot(int squares){
        G = new Digraph(squares);
        G_r = G.reverse();
        num_squares = squares;
        dim = (int)Math.sqrt(squares);
    }

    /**
     * key:
     * → 1
     * ↑ 2
     * ← 3
     * ↓ 4
     * @param square  based o the square number
     * @param arrow based on tile arrow number
     */

    public void addTile(int square, int arrow){
        int square_to=0; 
        //System.out.println(arrow);
        switch(arrow){
            case 1:
                square_to = square++;
                if( square_to % dim != 0) {G.addEdge(square, square_to); }
                break;
            case 2:
                square_to = square - dim;
                if(square_to >= 0) G.addEdge(square, square_to);
                break;
            case 3:
                square_to = square --;
                if(square_to % dim != 2 ) G.addEdge(square, square_to);
                break;
            case 4:
                square_to = square + dim;
                if(square_to < num_squares) G.addEdge(square, square_to);
                break;
            default:
                System.out.println("Ya FUCKED up");

        }
        
    }

    /**
     * Retuns the list of Tiles
     */
    public ArrayList<Integer> getPlayOptions(){
        DFS dfs_r = new DFS(G_r);
        marked = new boolean[G.V()];
        count = new int[G.V()];
        id = new int[G.V()];
        max_id = -1;
        ArrayList <Integer> ret = new ArrayList<Integer>();
        Stack <Integer> reverse = dfs_r.reversePostorder();
        while(!reverse.empty()){
            int v = reverse.pop();
            if(!marked[v]){
                dfs(G,v);
                ident++;
            }
        }
        for(int i = 0; i < id.length; i++){
            //System.out.println(i + ":" + id[i]);
            if(id[i] == max_id){
                
                ret.add(i);
            }
        }
        for(int i: ret) System.out.println(i);

        return ret;
    }
    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v] = ident;
        for(int w : G.adj(v)){
            if(!marked[w]) dfs(G,w);
            count[ident]++;
            if(count[ident] > max_id) max_id = ident;
        }
    }
    public static void main(String[] args) {

    }

}

class DFS{
    private boolean[] marked;
    private Stack<Integer> postorder;

    public DFS(Digraph G){
        marked = new boolean[G.V()];
        postorder =  new Stack<Integer>();
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]) dfs(G,v);
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true; 
        for(int w : G.adj(v))
            if(!marked[w]) dfs(G,w);
        postorder.push(v);
    }
    public Stack<Integer> reversePostorder(){
        return  postorder;
    }

}