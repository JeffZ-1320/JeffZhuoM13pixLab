/*
Program Name:PictureTester
Author: Jeff Zhuo
Date: 3/22/2021
Program description:
  Make changes to methods according to assignment 9. 
What I learned from this program:
  Use and manipulate codes written by others. 
What difficulties did I have and how I solved them:
I found that the original background to be too small. Therefore, I added my own background that is 1920x1080.
Look for method comments with *MODIFIED* meaning that I had edited the code. 
  
*/ 

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /**
   * Test the keepOnlyBlue
   */
  public static void testKeepOnlyBlue(){
    Picture myPic = new Picture("images/myPic.jpg");
    myPic.keepOnlyBlue();
    myPic.explore();
  }
  
  /**
   * Method to test negate()
   */
  public static void testNegate(){
    Picture myPic = new Picture("images/myPic.jpg");
    myPic.negate();
    myPic.explore();
  }

  /**
   * Method to test grayScale()
   */
  public static void testGrayscale(){
    Picture myPic = new Picture("images/myPic.jpg");
    myPic.Grayscale();
    myPic.explore();
  }

  /**
   * test the fixUnderwater method EXTRA CREDIT
   */
  public static void testFixUnderwater(){
    Picture water = new Picture("images/water.jpg");
    water.fixUnderwater();
    water.explore();
  }

  /**
   * test the MirrorVerticalRightToLeft method
   */
  public static void testMirrorVerticalRightToLeft(){
    Picture myPic = new Picture("images/myPic.jpg");
    myPic.mirrorVerticalRightToLeft();
    myPic.explore();
  }

  /**
   * test the mirrorHorizontal method
   */
  public static void testMirrorHorizontal(){
    Picture myPic = new Picture("images/myPic.jpg");
    myPic.mirrorHorizontal();
    myPic.explore();
  }

  /**
   * test the mirrorHorizontalBotToTop method
   */
  public static void testMirrorHorizontalBotToTop(){
    Picture myPic = new Picture("images/myPic.jpg");
    myPic.mirrorHorizontalBotToTop();
    myPic.explore();
  }

  /**
   * test the mirrorDiagonal method EXTRA CREDIT
   */
  public static void testMirrorDiagonal(){
    Picture myPic = new Picture("images/beach.jpg");
    myPic.mirrorDiagonal();
    myPic.explore();
  }

  /**
   * test the mirrorArms method
   */
  public static void testMirrorArms(){
    Picture snowman = new Picture("images/snowman.jpg");
    snowman.mirrorArms();
    snowman.explore();
  }

  /**
   * test the mirrorGull method
   */
  public static void testMirrorGull(){
    Picture seagull = new Picture("images/seagull.jpg");
    seagull.mirrorGull();
    seagull.explore();
  }


  /** Method to test the collage method */
  public static void testCollage2()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage2();
    canvas.explore();
  }

  /** Method to test the myCollage method; I added my own white background*/
  public static void testMyCollage()
  {
    Picture canvas = new Picture("images/1920x1080.jpg");
    canvas.myCollage();
    canvas.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    // testZeroBlue();
    // testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    // testNegate();
    // testGrayscale();
    // testFixUnderwater();
    // testMirrorVertical();
    // testMirrorTemple();
    // testMirrorArms();
    // testMirrorGull();
    // testMirrorDiagonal();
    // testCollage();
    //testCopy();
    // testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);

    // testMirrorVerticalRightToLeft();
    // testMirrorHorizontal();
    // testMirrorHorizontalBotToTop();
    // testCollage2();
    testMyCollage();

  }
}