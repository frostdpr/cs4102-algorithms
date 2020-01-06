import java.util.*;

public class twitterMap {
    private int numberOfPeople;
    private int follows;
    private LinkedList<Integer> adj[];
    private LinkedList<Integer> adj_T[];
    private List<Integer> answer;
    /**
     * The constructor of the twitter map
     * Takes in the number of people and creates a corresponding adjency matrix.
     * @param numberOfPeople
     */
    twitterMap(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        adj = new LinkedList[numberOfPeople];
        adj_T = new LinkedList[numberOfPeople];
        this.follows = 0;
        for (int i = 0; i< numberOfPeople; ++i){
            adj[i] = new LinkedList();
            adj_T[i] = new LinkedList();
        }
    }

    /**
     * This function adds the personBeingFollowed to the list of people the follower is following. It does this by going
     * to the twitterScrapeResults adjacency matrix of the follower and adds the integer representation of
     * personBeingFollowed to that matrix.
     * @param follower
     * @param personBeingFollowed
     */
    public void addFollower(int follower, int personBeingFollowed) {
        adj[follower].add(personBeingFollowed);
    }

    /**
     * This returns a list of the integers that represent the people required to tweet to everyone.
     * TODO: Implement this method using whatever strategy you want. Runtime should be O(V + E).
     * TODO: Have fun and good luck!
     */
    public  List<Integer> findKPeople() {
        answer = new ArrayList<Integer>();

        Stack stack = new Stack(); 
  
        boolean visited[] = new boolean[numberOfPeople];
  

        for (int i = 0; i < numberOfPeople; i++) 
            if (!visited[i]) 
                fillOrder(i, visited, stack); 
  
        transpose(); 
  
        for (int i = 0; i < numberOfPeople; i++) 
            visited[i] = false; 
  
        while (!stack.empty()){ 
            int v = (int)stack.pop(); 
  
            if (!visited[v]) { 
                DFSUtil(v, visited); 
                Collections.sort(answer);
                return answer;
            } 
        } 

        return answer;
    }
    public void DFSUtil(int v, boolean visited[]){ 
        visited[v] = true; 
        answer.add(v);
        int n; 

        Iterator<Integer> i = adj_T[v].iterator(); 
        while (i.hasNext()){ 
            n = i.next(); 
            if (!visited[n]) 
                DFSUtil(n,visited); 
        } 
    } 
  
    public void transpose(){ 
        for (int v = 0; v < numberOfPeople; v++) {
            for (int w : adj[v]) {
                adj_T[w].add(v);
            }
        }
    } 
  
    public void fillOrder(int v, boolean visited[], Stack stack) { 
        visited[v] = true; 
  
        Iterator<Integer> i = adj[v].iterator(); 
        while (i.hasNext()){ 
            int n = i.next(); 
            if(!visited[n]) 
                fillOrder(n, visited, stack); 
        } 
  
        stack.push(new Integer(v)); 
    }
    /**
     * added to compile
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("Welcome to Rohit's bonus problem!");
    }

}


