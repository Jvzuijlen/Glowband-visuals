/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

import arduino.Arduino;
import java.awt.Color;

/**
 *
 * @author Joep
 */
public class ProtocolHandler
{
    byte startByte;
    byte stopByte;
    
    public ProtocolHandler()
    {
        startByte = (byte)(100 & 0xFF);
        stopByte = (byte)(200 & 0xFF);
    }
    
    public boolean OpenConnection(String comName)
    {
        Arduino arduino = new Arduino("COM4", 9600);

        try
        {
            arduino.openConnection(); 
            byte x = 100;
            arduino.serialWrite((char)x);
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean SendData(Color[][] imgData, Crowd crowd)
    {
        int pixelSize = crowd.height * crowd.height * 11;
        int headerSize = 22;
        byte[] sendData = new byte[pixelSize + headerSize];
        
        createHeader(sendData, crowd);
        
        try
        {
            for (int x = 0; x < crowd.width; x++)
            {
                for (int y = 0; y < crowd.height; y++)
                {
                        /*pstmt.setInt(2, x);
                        pstmt.setInt(3, y);

                        pstmt.setInt(4, imgData[x][y].getRed());
                        pstmt.setInt(5, imgData[x][y].getGreen());
                        pstmt.setInt(6, imgData[x][y].getBlue());

                        pstmt.setInt(7, x);
                        pstmt.setInt(8, y);
                        pstmt.setInt(9, imgData[x][y].getRed());
                        pstmt.setInt(10, imgData[x][y].getGreen());
                        pstmt.setInt(11, imgData[x][y].getBlue());
                        pstmt.executeUpdate();*/
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public byte[] createHeader(byte[] data, Crowd crowd)
    {
        int index = 0;
        data[index++] = (byte)(startByte & 0xFF);
        
        for(int i = 0; i < 4; i++)
        {
            data[index++] = (byte)(toBytes(crowd.getNwLat())[i] & 0xFF);
        }
        
        for(int i = 0; i < 4; i++)
        {
            data[index++] = (byte)(toBytes(crowd.getNwLon())[i] & 0xFF);
        }
        
        for(int i = 0; i < 4; i++)
        {
            data[index++] = (byte)(toBytes(crowd.getSeLat())[i] & 0xFF);
        }
        
        for(int i = 0; i < 4; i++)
        {
            data[index++] = (byte)(toBytes(crowd.getSeLon())[i] & 0xFF);
        }
        
        int w = crowd.width;
        data[index++] = (byte) ((w >> 24) & 0xFF);
        data[index++] = (byte) ((w >> 16) & 0xFF);
        
        int h = crowd.height;
        data[index++] = (byte) ((h >> 24) & 0xFF);
        data[index++] = (byte) ((h >> 16) & 0xFF);
        
        data[index] = (byte)(stopByte & 0xFF);
        return data;
    }
    
    private byte[] toBytes(int i)
    {
        byte[] result = new byte[4];
        result[0] = (byte) (i >> 24);
        result[1] = (byte) (i >> 16);
        result[2] = (byte) (i >> 8);
        result[3] = (byte) (i /*>> 0*/);
        return result;
    }
}
