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

    @org.junit.After
    public void destroy() {
    }

    @org.junit.Test
    public void test() throws Exception {
        InputStream inputStream = new FileInputStream(new File("test1.txt"));
        Main.checkForFraud(inputStream);
        System.out.flush();
        System.setOut(old);
        String output = baos.toString();
        System.out.println(output);
        assertTrue(output.equals("1,2"));
    }

    @org.junit.Test
    public void test2() throws Exception {
        InputStream inputStream = new FileInputStream(new File("test2.txt"));
        Main.checkForFraud(inputStream);
        System.out.flush();
        System.setOut(old);
        String output = baos.toString();
        System.out.println(output);
        assertTrue(output.equals("4,6,7,10,12,13,18,19,23,24"));
    }

}