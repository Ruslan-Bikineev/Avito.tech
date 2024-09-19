package helper;

import org.testng.Assert;

import java.util.LinkedList;

public class AssertHelper {


    /**
     * This method compares two linked lists.
     * First, it checks that the lists have the same size.
     * Then it checks that the elements of the lists are equal.
     *
     * @param expected is expected list
     * @param actual   is actual list
     */
    public static void checkEqualsTwoLinkedLists(LinkedList expected, LinkedList actual) {
        Assert.assertEquals(actual.size(), expected.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue(expected.get(i).equals(actual.get(i)));
        }
    }
}
