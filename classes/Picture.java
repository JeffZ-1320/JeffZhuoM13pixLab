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
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
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
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("images/collage.jpg");
  }
  
  
  /** Method to show large changes in color *MODIFIED*
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color bottomColor = null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        bottomPixel = pixels[row+1][col];
        rightColor = rightPixel.getColor();
        bottomColor = bottomPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist || leftPixel.colorDistance(bottomColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /**
   * Set red and green values to 0; blue remain unchanged.
   */
  public void keepOnlyBlue(){
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }

  /**
   * Negate the RGB values
   */
  public void negate(){
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }

  /**
   * Turn RGB values into shades of gray
   */
  public void Grayscale(){
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int average = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
      }
    }
  }

  /**
   * By increasing the contrast of the image, it would be easier to spot the fishes. 
   */
  public void fixUnderwater(){
    Pixel[][] pixels = this.getPixels2D();
		long total = 0;
		int num = 0;
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				int ave = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen()) / 3;
				total += ave;
				num++;
			}
		}
		int ave = (int) (total /= num);
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setBlue(4 * (pixelObj.getBlue() - ave));
				pixelObj.setRed(4 * (pixelObj.getRed() - ave));
				pixelObj.setGreen(4 * (pixelObj.getGreen() - ave));
			}
		}
  }

  /**
   * Mirror a picture from right to left vertically
   */
  public void mirrorVerticalRightToLeft(){
    Pixel[][] pixels = this.getPixels2D();
    Pixel rightPixel = null;
    Pixel leftPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = width / 2; col < width; col++)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }

  /**
   * Mirrors the image horizontally
   */
  public void mirrorHorizontal(){
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < pixels.length / 2; row++)
    {
      for (int col = 0 / 2; col < pixels[row].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }

  /**
   * Mirror the image horizontally from bottom to top
   */
  public void mirrorHorizontalBotToTop(){
    Pixel[][] pixels = this.getPixels2D();
    Pixel bottomPixel = null;
    Pixel topPixel = null;
    int height = pixels.length;
    for (int row = pixels.length / 2; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        bottomPixel = pixels[row][col];
        topPixel = pixels[height - 1 - row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    } 
  }

  /**
   * Diagonally mirror the image
   */
  public void mirrorDiagonal(){
    Pixel[][] pixels = this.getPixels2D();
    Pixel keptPixel = null;
    Pixel copiedPixel = null;
    int limit = 0;
    if (pixels.length < pixels[0].length) {
      limit = pixels.length;
    }else{
      limit = pixels[0].length;
    }
    for (int row = 0; row < limit; row++)
    {
      for (int col = 0 ; col < limit; col++)
      {
        copiedPixel = pixels[row][col];
        keptPixel = pixels[col][row];
        copiedPixel.setColor(keptPixel.getColor());
      }
    } 
  }

  /**
   * Mirror the arms of the snowman
   */
  public void mirrorArms(){
    Pixel armPixel = null;
    Pixel subArmPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 160; row < 191; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 104; col < 170; col++)
      {
        
        armPixel = pixels[row][col];      
        subArmPixel = pixels[row + 75][col-5];
        subArmPixel.setColor(armPixel.getColor());
      }
    }
    for (int row = 172; row < 196; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 238; col < 294; col++)
      {
        
        armPixel = pixels[row][col];      
        subArmPixel = pixels[row + 75][col + 5];
        subArmPixel.setColor(armPixel.getColor());
      }
    }
  }

  /**
   * mirror the seagull so the image now has 2 seagulls
   */
  public void mirrorGull(){
    Pixel armPixel = null;
    Pixel subArmPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 235; row < 322; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 238; col < 344; col++)
      {
        
        armPixel = pixels[row][col];      
        subArmPixel = pixels[row + 5][col + 110];
        subArmPixel.setColor(armPixel.getColor());
      }
    }
  }

  /** Method to create a collage of several pictures *MODIFIED* */
  public void createCollage2()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy2(flower1,0,0,50,50);
    this.copy2(flower2,100,0,50,50);
    this.copy2(flower1,200,0,50,50);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy2(flowerNoBlue,300,0,50,50);
    this.copy2(flower1,400,0,50,50);
    this.copy2(flower2,500,0,50,50);
    this.mirrorVertical();
    this.write("images/collage.jpg");
  }

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture *MODIFIED*
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    * @param endRow the end row to copy from
    * @param endCol the end col to copy from
    */
    public void copy2(Picture fromPic, int startRow, int startCol, int endRow, int endCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
    fromRow < endRow &&
    toRow < toPixels.length; 
    fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
      fromCol < endCol &&
      toCol < toPixels[0].length;  
      fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  public void myCollage(){
    Picture myPic = new Picture("images/myPic.jpg");
    Picture myPicNoBlue = new Picture(myPic);
    myPicNoBlue.zeroBlue();
    this.copy(myPicNoBlue,0,0);
    Picture myPicMirror = new Picture(myPic);
    myPicMirror.mirrorVertical();
    this.copy(myPicMirror,300,0);
    Picture myPicNegate = new Picture(myPic);
    myPicNegate.negate();
    this.copy(myPicNegate,600,0);
    this.mirrorVertical();
    this.write("images/myCollage.jpg");
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/myPic.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
