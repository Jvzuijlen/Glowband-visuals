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
    public double NWlatitude;
    public double NWlongitude;
    public double SElatitude;
    public double SElongitude;
    public int width;
    public int height;
    
    public Crowd(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
}
