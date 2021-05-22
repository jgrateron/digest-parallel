package com.fresco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

    public void testAppMd5() throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
    	String fileName = "sample_document.pdf";
    	File file = new File(fileName);
        assertTrue( "5032a16766eecf178c515fd1b8e7a546  sample_document.pdf".equals(new CalcularDigest("md5", file, fileName).obtenerDigest().toString()));  
    }

    public void testAppSha() throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
    	String fileName = "sample_document.pdf";
    	File file = new File(fileName);
        assertTrue( "2fb8fc1a92a484f0b00c5cfad899cfe1d927b1af  sample_document.pdf".equals(new CalcularDigest("sha", file, fileName).obtenerDigest().toString()));   
    }

    public void testAppSha256() throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
    	String fileName = "sample_document.pdf";
    	File file = new File(fileName);
        assertTrue( "4e49c5e567cc6404b40a4dff71335080ff16708d8267fe01d8be9709ff8ca29c  sample_document.pdf".equals(new CalcularDigest("sha256", file, fileName).obtenerDigest().toString()));   
    }
    
    public void testAppSha384() throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
    	String fileName = "sample_document.pdf";
    	File file = new File(fileName);
        assertTrue( "829cd179343c2127b410319a167d8a6199dccd4ceeeed4ed0c6a23548eb03db59f9a9ad6417c1fae7fc7ad43ba55f427  sample_document.pdf".equals(new CalcularDigest("sha384", file, fileName).obtenerDigest().toString()));   
    }
    
    public void testAppSha512() throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
    	String fileName = "sample_document.pdf";
    	File file = new File(fileName);
        assertTrue( "85a2347b6dbbf4b48f86a95cea151622e04795ebd60484515230ee2d41329480187730c2832278301ec636033c070611c8ec72dd3c4758c8e727f3912e71de69  sample_document.pdf".equals(new CalcularDigest("sha512", file, fileName).obtenerDigest().toString()));   
    }
    
}
