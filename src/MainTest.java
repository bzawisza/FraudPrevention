import java.io.*;

import static org.junit.Assert.assertTrue;

public class MainTest {

    ByteArrayOutputStream baos;
    PrintStream old;

    @org.junit.Before
    public void setUp() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        old = System.out;
        System.setOut(ps);

    }

    private String getOutput() {
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

//    Original Test Case from problem
    @org.junit.Test
    public void test() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test1.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,2"));
    }

//    Original Test Case from problem that was failing because the numbers weren't sorted
    @org.junit.Test
         public void test2() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test2.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("4,6,7,10,12,13,18,19,23,24"));
    }

//    Good input of same email and same credit cards
    @org.junit.Test
    public void test3() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test3.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals(""));
    }

//    Same email and address, but different credit cards
    @org.junit.Test
    public void test4() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test4.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,3"));
    }

//    Different email, but same credit cards
    @org.junit.Test
    public void test5() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test5.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals(""));
    }

//    Different emails and same credit cards
    @org.junit.Test
    public void test6() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test6.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals(""));
    }

//    A bunch of extra periods in the email, different addresses, and credit cards
    @org.junit.Test
    public void test7() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test7.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,3"));
    }

//    Emails are the same if plus sign and periods removed
    @org.junit.Test
    public void test8() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test8.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,3"));
    }

//    Emails are different even if plus sign and periods removed. St and NY were expanded.
    @org.junit.Test
    public void test9() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test9.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,3"));
    }

//    Emails are different, but street is the same. Cases are randomizes
    @org.junit.Test
         public void test10() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test10.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,3"));
    }

//    A bunch of good orders, but one bad order.
    @org.junit.Test
    public void test11() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test11.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,2,3,4,5,7"));
    }

//    Emails are the same (cases randomized)
    @org.junit.Test
    public void test12() throws Exception {
        InputStream inputStream = new FileInputStream(new File("tests/test12.txt"));
        Main.checkForFraud(inputStream);
        String output = getOutput();
        System.out.println(output);
        assertTrue(output.equals("1,3"));
    }

}