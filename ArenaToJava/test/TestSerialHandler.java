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
    
    /*
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
    */
    
    @Test public void TestSerialLibHeader() throws InterruptedException
    {
        SerialHandler main = new SerialHandler("COM4", 38400);
        if(main.initialize())
        {    
            Crowd crowd = new Crowd(400, 300);
            crowd.setNW(1.23456789, 2.23456789);
            crowd.setSE(3.23456789, 4.23456789);

            ProtocolHandler protocolHandler = new ProtocolHandler();
            byte[] header = protocolHandler.createHeader(crowd);
            for (int i = 0; i < header.length; i++)
            {
                System.out.println((int)(header[i] & 0xFF)); 
            }

            for(;;)
            {
                main.writeData(header);
                Thread.sleep(50); //25
            }
        }
    }
    
    /*
    @Test public void TestSerialLibHeaderPixels() throws InterruptedException
    {
        SerialHandler main = new SerialHandler("COM13", 38400);
        if(main.initialize())
        {
            Crowd crowd = new Crowd(2, 2);

            crowd.setNW(1.23456789, 2.23456789);
            crowd.setSE(3.23456789, 4.23456789);

            ProtocolHandler protocolHandler = new ProtocolHandler();

            Color[][] imgData = new Color[2][2];
            imgData[0][0] = Color.BLACK;
            imgData[0][1] = Color.BLUE;
            imgData[1][0] = Color.RED;
            imgData[1][1] = Color.GREEN;

            byte[] header = protocolHandler.createHeader(crowd);
            byte[] data = protocolHandler.createPixels(imgData, crowd);

            byte[] finalData = protocolHandler.combineHeaderPixels(header, data);

            for (int i = 0; i < finalData.length; i++)
            {
                System.out.println((int)(finalData[i] & 0xFF)); 
            }

            for(;;)
            {
                main.writeData(header);
                Thread.sleep(50); //25
            }
        }
        
    }
    */
}
