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
    public int width;
    public int height;
    
    public Crowd(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public int[] getLatLonData()
    {
        int[] data = new int[4];
        
        data[0] = this.nwLatitude;
        data[1] = this.nwLongitude;
        data[2] = this.seLatitude;
        data[3] = this.seLongitude;
        return data;
    }
    
    public void setNW(double lat, double lon)
    {
        nwLatitude = (int)(lat * 100000);
        nwLongitude = (int)(lon * 100000);
    }
    
    public void setSE(double lat, double lon)
    {
        seLatitude = (int)(lat * 100000);
        seLongitude = (int)(lon * 100000);
    }
    
    public int getNwLat()
    {
        return this.nwLatitude;
    }
    
    public int getNwLon()
    {
        return this.nwLongitude;
    }
        
    public int getSeLat()
    {
        return this.seLatitude;
    }
            
    public int getSeLon()
    {
        return this.seLongitude;
    }
}
