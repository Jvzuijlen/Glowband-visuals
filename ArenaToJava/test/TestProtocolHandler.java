/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import arenatojava.Crowd;
import arenatojava.ProtocolHandler;
import java.awt.Color;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jvzui
 */
public class TestProtocolHandler {
    
    public TestProtocolHandler() {
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
        byte[] data = protocolHandler.createHeader(crowd);
        System.out.println(Arrays.toString(data));
    }
    
    @Test
    public void Pixels()
    {
        Crowd crowd = new Crowd(2, 2);
        
        ProtocolHandler protocolHandler = new ProtocolHandler();
        
        Color[][] imgData = new Color[2][2];
        imgData[0][0] = Color.BLACK;
        imgData[0][1] = Color.BLUE;
        imgData[1][0] = Color.RED;
        imgData[1][1] = Color.GREEN;
        
        //byte[] data = protocolHandler.createPixels(imgData, crowd);
        //for (int i = 0; i < data.length; i++)
        {
            //System.out.println((int)(data[i] & 0xFF)); 
            
        }
    }
}
