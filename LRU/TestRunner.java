//package warmup;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import static junit.framework.TestCase.assertEquals;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    /**
     * Test Double linked LRU
     */
    @Test(timeout = 1000)
    public void testSimpleLRU() {
        LRULinkedList lruLinkedList1 = new LRULinkedList(1);
        lruLinkedList1.add(1, 2);
        assertEquals(lruLinkedList1.getNode(1).value, 2);
        lruLinkedList1.add(2, 3);
        assertEquals( null,lruLinkedList1.getNode(1).value);

    }

    /**
     * Test example from the instructions
     */
    @Test(timeout = 1000)
    public void testExample() {
        LRUConstantTime cache = new LRUConstantTime(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(cache.get(1), 1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        assertEquals(cache.get(2), -1);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assertEquals(cache.get(1), -1);       // returns -1 (not found)
        assertEquals(cache.get(3), 3);       // returns 3
        assertEquals(cache.get(4), 4);       // returns 4

    }

    public static void main(String[] args) {
    Result result = JUnitCore.runClasses(TestRunner.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }





}
