/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joep
 */
public class ImageHandler
{
    private Rectangle screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds();

    public ImageHandler()
    {
        
    }
    
    public Image takeScreenShot()
    {
        try
        {
            BufferedImage capture = new Robot().createScreenCapture(screenRect);

            return capture;
        }
        catch (AWTException ex)
        {
            Logger.getLogger(ImageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
