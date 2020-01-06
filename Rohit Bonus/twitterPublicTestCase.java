import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class twitterPublicTestCase {

    public void testBasicFindKPeople() {
        twitterMap example = new twitterMap(7);

        example.addFollower(0, 1);
        example.addFollower(0, 2);
        example.addFollower(1, 3);
        example.addFollower(4, 1);
        example.addFollower(6, 4);
        example.addFollower(4, 6);

        example.addFollower(6, 0);
        example.addFollower(5, 2);
        example.addFollower(5, 6);
        example.addFollower(6, 5);

        List<Integer> ans = example.findKPeople();

        List<Integer> ansReal = new ArrayList<Integer>();
        ansReal.add(4);
        ansReal.add(5);
        ansReal.add(6);

        assertEquals(ansReal, ans);
    }



    @Test(timeout=10)
    public void callTests() {
        testBasicFindKPeople();

    }
    public static void main(String[] args) {
              Result result = JUnitCore.runClasses(twitterPublicTestCase.class);
              System.out.println("Ran: " + result.getRunCount() + " Failed: " +  result.getFailureCount());
              for (Failure failure : result.getFailures()) {
                 System.out.println("Failed on: " + failure.toString());
              }
                
              System.out.println(result.wasSuccessful());
    }

}