/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import arenatojava.Crowd;
import arenatojava.ProtocolHandler;
import arenatojava.SerialHandler;
import java.awt.Color;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joep
 */
public class TestSerialHandler {
    
    public TestSerialHandler() {
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
    
    @Test public void TestSerialLib() throws InterruptedException
    {
        SerialHandler main = new SerialHandler("COM12", 250000);
        if(main.initialize())
        {      
            byte[] data = new byte[4];
            data[0] = (byte)200;
            data[1] = (byte)(201 & 0xFF);
            data[2] = (byte)400;
            data[3] = (byte)(10 & 0xFF);

            for(;;)
            {
                main.writeData(data);
                Thread.sleep(100);
            }
        }
    }
}
