/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import arenatojava.Crowd;
import arenatojava.ProtocolHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joep
 */
public class LatLngtoInt {
    
    public LatLngtoInt() {
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

    @Test
    public void doubleToInt()
    {
        Crowd crowd = new Crowd(100, 100);
        crowd.setNW(1.23456789, 1.23456789);
        System.out.println(crowd.getNwLat());
    }
    
    @Test
    public void Header()
    {
        Crowd crowd = new Crowd(100, 200);
        crowd.setNW(1.23456789, 2.23456789);
        crowd.setSE(3.23456789, 4.23456789);
        
        ProtocolHandler protocolHandler = new ProtocolHandler();
        byte[] data = new byte[25];
        protocolHandler.createHeader(data, crowd);
        System.out.println(data);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
