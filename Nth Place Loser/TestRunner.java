import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.*;

public class TestRunner {

    @Test
    public void exampleTest() {
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            students[i] = new Student(i+1 ,Integer.toString(i));
        }
        assertEquals(NthPlaceLoser.NthPlaceLoser(students, 1 ).rank,  9);
        assertEquals(NthPlaceLoser.NthPlaceLoser(students, 3 ).rank,  7);
    }

    public static void main(String[] args) {
          Result result = JUnitCore.runClasses(TestRunner.class);
            
          for (Failure failure : result.getFailures()) {
             System.out.println(failure.toString());
          }
            
          System.out.println(result.wasSuccessful());
       }

}
