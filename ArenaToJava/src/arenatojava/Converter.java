/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Joep
 */
public class Converter 
{
    public Converter()
    {
        
    }
    
    public Color[][] ImageToData(BufferedImage image)
    {
        Color colorArr[][] = new Color[image.getWidth()][image.getHeight()];
        
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                colorArr[x][y] = new Color(image.getData().getPixel(x, y, new int[3])[0],
                        image.getData().getPixel(x, y, new int[3])[1],
                        image.getData().getPixel(x, y, new int[3])[2]);
            }
        }
        return colorArr;
    }
    
    public Color[][] ImageToData(BufferedImage image, int width, int height) throws IOException
    {
        image = Resize(image, width, height);
        
        Color colorArr[][] = new Color[image.getWidth()][image.getHeight()];
        
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                
                colorArr[x][y] = new Color(image.getData().getPixel(x, y, new int[3])[0],
                        image.getData().getPixel(x, y, new int[3])[1],
                        image.getData().getPixel(x, y, new int[3])[2]);
            }
        }
        return colorArr;
    }
    
    public BufferedImage Resize(BufferedImage img, int newW, int newH) throws IOException
    {
        return Thumbnails.of(img).forceSize(newW, newH).asBufferedImage();
    }
    
    public Color[][] CombineImageWithCrowd(Crowd crowd, Image img)
    {
        try
        {
            BufferedImage newImg = Resize((BufferedImage) img, crowd.getWidth(), crowd.getHeight());
            Color colors[][] = ImageToData(newImg);
            return colors;
        }
        catch (IOException ex)
        {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
