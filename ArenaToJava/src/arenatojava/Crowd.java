/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

/**
 *
 * @author Joep
 */
public class Crowd 
{
    private int nwLatitude;
    private int nwLongitude;
    private int seLatitude;
    private int seLongitude;
    private int width;
    private int height;
    
    public Crowd(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public synchronized int getWidth()
    {
        return this.width;
    }
    
    public synchronized int getHeight()
    {
        return this.height;
    }
    
    public synchronized int[] getLatLonData()
    {
        int[] data = new int[4];
        
        data[0] = this.nwLatitude;
        data[1] = this.nwLongitude;
        data[2] = this.seLatitude;
        data[3] = this.seLongitude;
        return data;
    }
    
    public synchronized void setNW(double lat, double lon)
    {
        nwLatitude = (int)(lat * 100000);
        nwLongitude = (int)(lon * 100000);
    }
    
    public synchronized void setSE(double lat, double lon)
    {
        seLatitude = (int)(lat * 100000);
        seLongitude = (int)(lon * 100000);
    }
    
    public synchronized int getNwLat()
    {
        return this.nwLatitude;
    }
    
    public synchronized int getNwLon()
    {
        return this.nwLongitude;
    }
        
    public synchronized int getSeLat()
    {
        return this.seLatitude;
    }
            
    public synchronized int getSeLon()
    {
        return this.seLongitude;
    }
}
