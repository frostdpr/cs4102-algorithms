import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUConstantTime obj = new LRUConstantTime(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LRUConstantTime {

    public HashMap<Integer, Node> hmap;
    public LRULinkedList LRULinkedList;
    public LRUConstantTime(int capacity) {
        this.hmap = new HashMap<Integer, Node>();
        this.LRULinkedList = new LRULinkedList(capacity);
    }

    /**
     * Returns the value for the key in data structure.
     * If the value is not in data return -1
     * @param key
     * @return value
     */
    public int get(int key) {
        Node get = hmap.get(key);
        if (get == null) return -1;
        else{
            LRULinkedList.moveNodeToHead(get);
            return get.value;
        }
    }

    /**
     * Put a key value pair in the cache. It value already exist
     * Override the value otherwise add the key value pair.
     * @param key
     * @param value
     */
    public void put(int key, int value) {   
        if(hmap.get(key) == null){
            if(LRULinkedList.capacity == LRULinkedList.size){
                hmap.remove(LRULinkedList.tail.key);
            }
            hmap.put(key,LRULinkedList.add(key, value));
        } 
        else{
            hmap.get(key).value = value;
            LRULinkedList.moveNodeToHead(hmap.get(key));

        } 
    }

    public static void main(String[] args) {

        
    }

}
 