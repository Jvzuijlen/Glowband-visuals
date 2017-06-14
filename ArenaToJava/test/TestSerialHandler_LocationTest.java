/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import arenatojava.Crowd;
import arenatojava.ProtocolHandler;
import arenatojava.SQL;
import arenatojava.SerialHandler;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joep
 */
public class TestSerialHandler_LocationTest {
    
    public TestSerialHandler_LocationTest() {
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
    
    @Test public void TestSerialLibHeaderPixels() throws InterruptedException
    {
        SerialHandler main = new SerialHandler("COM17", 38400);
        if(main.initialize())
        {
            SQL sql = new SQL();
            
            Crowd crowd = sql.getCrowdInfo();

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
}
