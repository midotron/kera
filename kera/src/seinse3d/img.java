/*
 * written bu ahmed hossam
 */
package seinse3d;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;


/**
 *
 * @author mido tron
 */
public class img {
    




public  int[][] image;
void img(BufferedImage h){
      BufferedImage Image=h ;//= ImageIO.read(package.class.getResource("image.jpg"));
      image = convert(Image);
     }


   private int[][] convert(BufferedImage image) {

      final byte[] p = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      final boolean hasAlphaChannel = image.getAlphaRaster()!= null;
int argb,rgb;
      int[][] result = new int[height][width];
      if (hasAlphaChannel) {
         for (int pixel = 0, row = 0, col = 0; pixel < p.length; pixel +=4) {
            argb = 0;
            argb += (int) p[pixel]<<24;    // alpha
            argb += (int) p[pixel+1];      // blue
            argb += (int) p[pixel+2]<<8;   // green
            argb += (int) p[pixel+3]<<16;  // red
            result[row][col] = argb;
            col++;
            if (col == width) {
               col = 0;
               row++;
            }}
      } else {
         for (int pixel = 0, row = 0, col = 0; pixel < p.length; pixel += 3) {
            rgb = 0;
            rgb +=(int) p[pixel];         // blue                                                                                                   //rgb +=-16777216;              // 255 alpha
            rgb +=(int) p[pixel+1]<< 8;   // green
            rgb +=(int) p[pixel+2]<<16;   // red
            result[row][col] = rgb;
            col++;
            if (col == width) {
               col = 0;
               row++;
            }}}
      return result;
   }   
}
/* another code
public static int[][] convertToArray(BufferedImage image)
{

    if (image == null || image.getWidth() == 0 || image.getHeight() == 0)
        return null;

    // This returns bytes of data starting from the top left of the bitmap
    // image and goes down.
    // Top to bottom. Left to right.
    final byte[] pixels = ((DataBufferByte) image.getRaster()
            .getDataBuffer()).getData();

    final int width = image.getWidth();
    final int height = image.getHeight();

    int[][] result = new int[height][width];

    boolean done = false;
    boolean alreadyWentToNextByte = false;
    int byteIndex = 0;
    int row = 0;
    int col = 0;
    int numBits = 0;
    byte currentByte = pixels[byteIndex];
    while (!done)
    {
        alreadyWentToNextByte = false;

        result[row][col] = (currentByte & 0x80) >> 7;
        currentByte = (byte) (((int) currentByte) << 1);
        numBits++;

        if ((row == height - 1) && (col == width - 1))
        {
            done = true;
        }
        else
        {
            col++;

            if (numBits == 8)
            {
                currentByte = pixels[++byteIndex];
                numBits = 0;
                alreadyWentToNextByte = true;
            }

            if (col == width)
            {
                row++;
                col = 0;

                if (!alreadyWentToNextByte)
                {
                    currentByte = pixels[++byteIndex];
                    numBits = 0;
                }
            }
        }
    }

    return result;
}*/