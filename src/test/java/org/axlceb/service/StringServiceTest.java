package org.axlceb.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Cases:
 * Task 1: ""
 * Task 1: "1"
 * Task 1: "1,2"
 * Task 2: "1,1 1,1 1"
 * Task 3: "1\n2,3"
 * Task 4: "//;\n1;2"
 * Task 4: "1,1 1,1\n1"
 * Task 5: "0,-1,2,3,-4,5"
 * Task 6: "2,1001"
 * Task 7: "//***\n1***2***3"
 */
public class StringServiceTest {

    private StringService stringService = new StringServiceImp();

    @Test
    public void addTestCase1() {
        assertEquals(0, stringService.add(""));
        assertEquals(1, stringService.add("1"));
        assertEquals(3, stringService.add("1,2"));
    }

    @Test
    public void addTestCase2() {
        assertEquals(5, stringService.add("1,1 1,1 1"));
    }

    @Test
    public void addTestCase3() {
        assertEquals(6, stringService.add("1\n2,3"));
    }

    @Test
    public void addTestCase4() {
        assertEquals(3, stringService.add("//;\n1;2"));
        assertEquals(5, stringService.add("1,1 1,1\n1"));
    }

    @Test
    public void addTestCase5() {
        assertEquals(0, stringService.add("0,-1,2,3,-4,5"));
    }

    @Test
    public void addTestCase6() {
        assertEquals(2, stringService.add("2,1001"));
    }

    @Test
    public void addTestCase7() {
        assertEquals(6, stringService.add("//***\n1***2***3"));
    }
}
