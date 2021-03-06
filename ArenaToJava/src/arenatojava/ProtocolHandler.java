/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;
import java.awt.Color;

/**
 *
 * @author Joep
 */
public class ProtocolHandler
{
    private byte startByte;
    private byte stopByte;
    private int headerSize = 22;
    
    private SerialHandler serialHandler;
    
    public ProtocolHandler()
    {
        startByte = (byte)100;
        stopByte = (byte)200;
    }
    
    public boolean OpenConnection(String comName, int baudRate)
    {
        if(serialHandler != null)
        {
            serialHandler.close();
        }
        serialHandler = new SerialHandler(comName, baudRate);
        
        return serialHandler.initialize();
    }
    
    public boolean SendData(Color[][] imgData, Crowd crowd)
    {
        int pixelSize = crowd.getHeight() * crowd.getWidth() * 11;
        int headerSize = 22;
        byte[] sendData = new byte[pixelSize + headerSize];
        
        int index = 0;
        
        //createHeader(sendData, crowd);
        
        try
        {
            for (int x = 0; x < crowd.getWidth(); x++)
            {
                for (int y = 0; y < crowd.getHeight(); y++)
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
    
    public byte[][] createPixels(Color[][] imgData, Crowd crowd)
    {
        int pixelSize = 7;
        byte[][] pixelData = new byte[crowd.getWidth() * crowd.getHeight()][pixelSize];
        
        int index = 0;
        for (int x = 0; x < crowd.getWidth(); x++)
        {
            for (int y = 0; y < crowd.getHeight(); y++)
            {
                int pixelIndex = 0;
                int w = x;
                pixelData[index][pixelIndex++] = (byte)(w >> 8);       //Convert int to 2 byte array
                pixelData[index][pixelIndex++] = (byte)(w /*>> 0*/);

                int h = y;
                pixelData[index][pixelIndex++] = (byte)(h >> 8);
                pixelData[index][pixelIndex++] = (byte)(h /*>> 0*/);   //Convert int to 2 byte array
                
                pixelData[index][pixelIndex++] = (byte)imgData[x][y].getRed();
                pixelData[index][pixelIndex++] = (byte)imgData[x][y].getGreen();
                pixelData[index++][pixelIndex++] = (byte)imgData[x][y].getBlue();
            }
        }
        return pixelData;
    }
    
    public byte[] createHeader(Crowd crowd)
    {
        byte[] header = new byte[headerSize];
        
        int index = 0;
        header[index++] = startByte;
        
        int[] crowdData = crowd.getLatLonData();
        for(int i = 0; i < 4; i++)
        {
            int value = crowdData[i];
            for(int y = 0; y < 4; y++)
            {
                header[index++] = (toBytes(value)[y]);
            }
        }
        
        int w = crowd.getWidth();
        header[index++] = (byte)(w >> 8);       //Convert int to 2 byte array
        header[index++] = (byte)(w /*>> 0*/);
        
        int h = crowd.getHeight();
        header[index++] = (byte)(h >> 8);
        header[index++] = (byte)(h /*>> 0*/);   //Convert int to 2 byte array
        
        header[index] = stopByte;
        return header;
    }
    
    private byte[] toBytes(int i) //Convert int to 4 byte array
    {
        byte[] result = new byte[4];
        result[0] = (byte) (i >> 24);
        result[1] = (byte) (i >> 16);
        result[2] = (byte) (i >> 8);
        result[3] = (byte) (i /*>> 0*/);
        return result;
    }
    
    public byte[] combineHeaderPixels(byte[] header, byte[] data)
    {
        byte[] finalData = new byte[header.length + data.length];
        System.arraycopy(header, 0, finalData, 0, header.length);
        System.arraycopy(data, 0, finalData, header.length, data.length);
        return finalData;
    }
}
