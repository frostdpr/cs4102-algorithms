public class LRULinkedList {
    public Node head;
    public Node tail;
    public int capacity =0;
    public int size =0;

    /**
     * Constructor creates a double linked list with a single value.
     */
    public LRULinkedList(int capacity){
        this.capacity = capacity;
    }

    /**
     * Add a node to the linked list.
     * @param key
     * @param value
     * @return new node
     */
    public Node add(int key, int value) {
        Node node = new Node(key, value);
        if(capacity == size){
            deleteTail();
        }
        if (size == 0){
            node.next = null;
            node.prev = null;
            head = node;
            tail = node;
        }
        else{
            head.prev = node;
            node.next = head;
            node.prev = null;
            head = node;
        } 
        size++;
        return head;
    }

    /**
     * Remove the last item in the linked list.
     */
    public void deleteTail(){
        if(size <= 0) return;
        if(size > 1) tail.prev.next = null;
        if(size == 1) head = null; 
        tail = tail.prev;
        size --; 
    }

    /**
     * Get the node that corresponds to the passed in key.
     * @param key
     * @return node that matches key
     */
    public Node getNode(int key){
        Node cur = head;
        while(cur != null){
            if (cur.key == key) break;
            cur = cur.next;
        }
        moveNodeToHead(cur);
        return cur;
    }

    /**
     * Move the passed in node to the head position.
     * @param node
     */
    public void moveNodeToHead(Node node){
        if(node == head || head == tail || node == null) return;
        node.prev.next = node.next;
        if(node != tail) node.next.prev = node.prev;
        else tail = node.prev;
        node.next = head;
        head.prev = node;
        node.prev = null;
        head = node;
    }
    public void printlist(boolean direction){
        Node cur = direction ? head:tail;

        while(cur != null){
            System.out.println("Key: " +  cur.key + " Value: " + cur.value);
            cur = direction ? cur.next : cur.prev ;
        }
        if(head != null){
            System.out.println("Head: " + head.key + " Tail: " + tail.key);
        }
        else{
            System.out.println("empty list");
        }
    }

    public static void main(String[] args) {

    }

}
