import java.util.*;

public class BirdScooters {

    Node root;
    Node champion;
    double minDistance;
    /**
     * Constructor
     */
    public BirdScooters(){
        this.root = null;
    }

    /**
     * Adds a scooter store at the Node passed in
     * @param scooter - location of the scooter store
     * @return true if added
     */
    
    public boolean add (Node scooter){
        if(root == null){
            root = new Node(scooter.x,scooter.y, 0);
            return true;
        }
        return add(root, scooter, root.level);
    }
    
    private boolean add(Node current, Node scooter, int level){
        if (root.x == scooter.x && root.y == scooter.y) {return false;}
        if ( current.compare(scooter, current, level) > 0) {
            level++;
            if (current.right ==  null){
                current.right = new Node(scooter.x,scooter.y, level);
                return true;
            }
            return add(current.right, scooter, level);
        }
        else {
            level++;
            if (current.left == null){
                current.left = new Node(scooter.x,scooter.y, level);
                return true;
            }
            return add(current.left, scooter, level);
        }
    }
    /**
     * Find the closest scooter to the provided location
     * @param location
     * @return closest Node corresponding to the closest scooter store
     */
    public Node closestPoint(Node location){
        minDistance = Distance(location, root);
        champion = root;
        find(location, root);
        return champion;

    }

    public void find(Node location, Node current){
        if(current == null){
            return;
        }
        double dist = Distance(current, location);
        if(dist < minDistance){minDistance = dist; champion = current;}
        double val = current.level % 2 == 0 ? location.x : location.y;
        double curVal = current.level % 2 == 0 ? current.x : current.y;
        if(location.compare(current, location, current.level) > 0){
            find(location, current.left);
            if(val + minDistance >= curVal){find(location, current.right);}
        }
        else{
            find(location, current.right);
            if(val - minDistance <= curVal){find(location, current.left);}
        }
        //return;
        
    }


    /***************************************************************************
     *  Helper function for Distance Formula
     ***************************************************************************/

    private double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String args[]){

    }

}
