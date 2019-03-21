/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theironyard.kyru.testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kylerudy
 */
public class HelloWorldTest {
    
    public HelloWorldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of multiplication method, of class HelloWorld.
     */
    @Test
    public void testMultiplication() {
        assertEquals(30, HelloWorld.multiplication(5, 6));
        assertEquals(-30, HelloWorld.multiplication(5, -6));
        assertEquals(30, HelloWorld.multiplication(-5, -6));
    }
    
}
