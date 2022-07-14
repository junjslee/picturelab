import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    public void grayscale() {
        Pixel [][] pixel = this.getPixels2D();
        for (Pixel[] rowArray : pixel) {
            for (Pixel pixelObj : rowArray) {
                int average = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen()) / 3;
                pixelObj.setBlue(average);
                pixelObj.setGreen(average);
                pixelObj.setRed(average);
            }
        }
    }

    public  void negate() {
        Pixel [][] pixel = this.getPixels2D();
        for (Pixel[] rowArray : pixel) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setBlue(255 - pixelObj.getBlue());
                pixelObj.setRed(255 - pixelObj.getRed());             
            }
        }
    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void keepOnlyBlue() {
        Pixel[][] pixel = this.getPixels2D();
        for (Pixel[] rowArray : pixel) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void fixUnderWater() {
        Pixel[][] pixel = this.getPixels2D();
        for (Pixel[] rowArray : pixel) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(pixelObj.getRed()+110);
                pixelObj.setGreen(pixelObj.getGreen()-15);
                pixelObj.setBlue(pixelObj.getBlue()+10);
                if (pixelObj.getBlue() > 170) {
                    pixelObj.setBlue(pixelObj.getBlue()+10);
                }
                else {
                    pixelObj.setBlue(pixelObj.getBlue()-10);
                }
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }
    
    public void mirrorVerticalLeftToRight()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal() {
        Pixel[][] pixel = this.getPixels2D();
        Pixel upPixel = null;
        Pixel downPixel = null;
        int width = pixel[0].length;
        for (int row = 0; row < pixel.length; row++)
        {
            for (int col = 0; col < width; col++)
            {
                upPixel = pixel[row][col];
                downPixel = pixel[pixel.length - 1 - row][col];
                downPixel.setColor(upPixel.getColor());
            }
        } 
    }

    public void mirrorDiagonal() {
        Pixel[][] pixel = this.getPixels2D();
        Pixel upPixel = null;
        Pixel downPixel = null;
        int width = pixel[0].length;
        int a = 0;
        int b = 0;
        for (int row = 0; row < pixel.length; row++)
        {
            for (int col = 0; col < row; col++)
            {
                upPixel = pixel[row][col];
                downPixel =pixel[col][row];
                downPixel.setColor(upPixel.getColor());
                upPixel.setColor(downPixel.getColor());
                b++;
            }
        } 
    }

    public void mirrorHorizontalBotToTop() {
        Pixel[][] pixel = this.getPixels2D();
        Pixel upPixel = null;
        Pixel downPixel = null;
        int width = pixel[0].length;
        for (int row = 0; row < pixel.length; row++)
        {
            for (int col = 0; col < width; col++)
            {
                upPixel = pixel[row][col];
                downPixel = pixel[pixel.length - 1 - row][col];
                upPixel.setColor(downPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorArms() 
    {
        int mirrorPoint = 220;
        Pixel upPixel = null;
        Pixel downPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        // loop through the rows
        for (int row = 155; row < mirrorPoint; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 100; col < 163; col++)
            {
                upPixel = pixels[row][col];      
                downPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                downPixel.setColor(upPixel.getColor());
            }
        }
        for (int row = 155; row < mirrorPoint; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 243; col < 310; col++)
            {
                upPixel = pixels[row][col];      
                downPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                downPixel.setColor(upPixel.getColor());
            }
        }
    }

    public void mirrorGull()
    {
        int mirrorPt = 351;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixel = this.getPixels2D();
        // loop through the rows
        for (int row = 228; row < 330; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 226; col < mirrorPt; col++)
            {
                
                
                leftPixel = pixel[row][col];      
                rightPixel = pixel[row]                       
                [mirrorPt - col + mirrorPt];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
            count++;
        }
        System.out.println(count);
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;  
        fromRow < fromPixels.length && toRow < toPixels.length;  
        fromRow++, toRow++) {  
            for (int fromCol = 0, toCol = startCol;  
            fromCol < fromPixels[0].length && toCol < toPixels[0].length;
            fromCol++, toCol++) {  
                fromPixel = fromPixels[fromRow][fromCol];  
                toPixel = toPixels[toRow][toCol];  
                toPixel.setColor(fromPixel.getColor());  
            }  
        }   
    }  

    public void copy2nd(Picture fromPic, int startRow, int startCol, int a, int b)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
        fromRow < a && toRow < a;
        fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < b && toCol < b;
            fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVerticalLeftToRight();
        this.write("collage.jpg");
    }

    public void myCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,200,0);
        this.copy(flower1, 100,0);
        this.mirrorVerticalLeftToRight();
        //this.copy2nd(flower1, 20, 30, 10, 50);
        //this.write("mycollage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    public void edgeDetection2(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixel = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixel.length; row++)
        {
            for (int col = 0; 
            col < pixel[0].length-1; col++)
            {
                leftPixel = pixel[row][col];
                rightPixel = pixel[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
        Pixel upPixel = null;
        Pixel downPixel = null;
        Color downColor = null;
        for (int roww = 0;roww < pixel[0].length-1; roww++)
        {
            for (int coll = 0; 
            coll < pixel.length; coll++)
            {
                upPixel = pixel[coll][roww];
                downPixel = pixel[coll][roww+1];
                downColor = downPixel.getColor();
                if (upPixel.colorDistance(downColor) > 
                edgeDist)
                    upPixel.setColor(Color.BLACK);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
