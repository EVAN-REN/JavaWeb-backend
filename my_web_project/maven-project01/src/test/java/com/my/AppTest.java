package com.my;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    @org.junit.Test
    public void test1(){
        System.out.println("hello test1");
    }

    @org.junit.Test
    public void test2(){
        System.out.println("hello test2");
    }

    @org.junit.Test
    public void test3(){
        System.out.println("hello test3");
    }
}
