package floatingpointdev.nancysfractal;
import processing.core.*;

import java.awt.Point;

/**
 * Provides a 2 dimensional graph.
 * 
 * @author floatingpointdev
 * 
 */
public class Graph extends DisplayableObject {
  public PImage pixels;

  private Fractal fractal;// = new Fractal(processingContext, this);
  // public int width; //the
  // public int height;

  protected float xMin = -1, xMax = 1; // the minimum and maximum domain values.
  protected float yMin = -1, yMax = 1; // the minimum and maximum range values.
  protected boolean showAxes = false; // do we draw the x and y axes.

  Graph(PApplet processingContext, Fractal fractal) {
    super(processingContext);
    this.fractal = fractal;
    pixels = processingContext.createImage(processingContext.width,
        processingContext.height, processingContext.RGB);
  }

  protected void draw() {
    // if(showAxes){
    // this.processingContext.stroke(255);
    // //this.processingContext.strokeWeight(.01f);
    //
    // //draw the y axis.
    // processingContext.line(getScreenCoordinateX(0), -1,
    // getScreenCoordinateX(0), 1);
    // //processingContext.text("origin",0,0);
    // //draw the x axis.
    // processingContext.line(-1, getScreenCoordinateY(0), 1,
    // getScreenCoordinateY(0));
    // }

    // fill the image
    for (int x = 0; x < processingContext.width; ++x) {
      float pointX = ((float) x / (float) processingContext.width)* (xMax - xMin) + xMin;

      for (int y = 0; y < processingContext.height; ++y) {
        float pointY = ((float) y / (float) processingContext.height)* (yMax - yMin) + yMin;
        int[] color = fractal.calculatePoint(pointX, pointY);
        pixels.pixels[(int) ((y * pixels.width) + x)] = processingContext
            .color(color[0], color[1], color[2]);
      }
    }

    // display the image.
    pixels.updatePixels();
    processingContext.image(pixels, -1, -1, 2, 2);
  }

  public float getxMin() {
    return xMin;
  }

  public void setxMin(float xMin) {
    this.xMin = xMin;
  }

  public float getxMax() {
    return xMax;
  }

  public void setxMax(float xMax) {
    this.xMax = xMax;
  }

  public float getyMin() {
    return yMin;
  }

  public void setyMin(float yMin) {
    this.yMin = yMin;
  }

  public float getyMax() {
    return yMax;
  }

  public void setyMax(float yMax) {
    this.yMax = yMax;
  }

  public boolean isShowAxes() {
    return showAxes;
  }

  public void setShowAxes(boolean showAxes) {
    this.showAxes = showAxes;
  }

  // void panUpDownByRelativeAmount(float amount) pans up and down
  // on the graph by an amount relative to the current view.
  // an amount value of 0 will not change the graph at all.
  // an amount of 1 will pan the graph up by one full graph height.
  // an amount of -1 will pan the graph down by one full graph height.
  public void panUpDownByRelativeAmount(float amount) {
    float shiftAmt;
    shiftAmt = amount * (yMax - yMin);
    yMin += shiftAmt;
    yMax += shiftAmt;
  }

  // void panLeftRightByRelativeAmount(float amount) pans up and down
  // on the graph by an amount relative to the current view.
  // an amount value of 0 will not change the graph at all.
  // an amount of 1 will pan the graph up by one full graph height.
  // an amount of -1 will pan the graph down by one full graph height.
  public void panLeftRightByRelativeAmount(float amount) {
    float shiftAmt;
    shiftAmt = amount * (xMax - xMin);
    xMin += shiftAmt;
    xMax += shiftAmt;
  }

  public void zoomOnPointByRelativeAmount(float pointX, float pointY,
      float amount) {
    // if(getPixelWidth() > .00001f){
    // xMax = (1/amount * (xMax - pointX)) + pointX;
    // xMin = (1/amount * (xMin - pointX)) + pointX;
    // }
    //    
    // if(getPixelHeight() > .00001f){
    // yMax = (1/amount * (yMax - pointY)) + pointY;
    // yMin = (1/amount * (yMin - pointY)) + pointY;
    // }
    float panHorizontalAmount = (pointX - xMin) / (xMax - xMin) - .5f;
    float panVerticalAmount = (pointY - yMin) / (yMax - yMin) - .5f;

    panHorizontalAmount *= .05f;
    panVerticalAmount *= .05f;
    panLeftRightByRelativeAmount(panHorizontalAmount);
    panUpDownByRelativeAmount(panVerticalAmount);
    // zoomByRelativeAmount(amount);
  }
  
  public void zoomByRelativeAmount(float amount) {
    // zoomOnPointByRelativeAmount( (xMax-xMin)/2 + xMin , (yMax-yMin)/2 + yMin,
    // amount);

    float ogCenterX = (xMax - xMin) / 2 + xMin;
    float ogCenterY = (yMax - yMin) / 2 + yMin;

    // if(getPixelWidth() > .00000001f){
    xMax = (1 / amount * (xMax - ogCenterX)) + ogCenterX;
    xMin = (1 / amount * (xMin - ogCenterX)) + ogCenterX;
    // }

    // if(getPixelHeight() > .00000001f){
    yMax = (1 / amount * (yMax - ogCenterY)) + ogCenterY;
    yMin = (1 / amount * (yMin - ogCenterY)) + ogCenterY;
    // }
  }

  // returns the screen coordinates for a point on the graph that the
  // processing draw functions need.
  public PVector getScreenCoordinatesFromPoint(float x, float y) {
    return new PVector((x / (xMax - xMin)) - xMin, (y / (yMax - yMin)) - yMin);
  }
  public PVector getScreenCoordinatesFromPoint(PVector point) {
    return new PVector((point.x / (xMax - xMin)) - xMin,
        (point.y / (yMax - yMin)) - yMin);
  }
  
  public float getScreenCoordinateX(float x) {
    return ((x - xMin) / (xMax - xMin)) * 2 - 1;
  }
  
  public float getScreenCoordinateY(float y) {
    return ((y - yMin) / (yMax - yMin)) * 2 - 1;
  }

  public float getPixelWidth() {
    return (xMax - xMin) / (float) this.processingContext.width;
  }
  
  public float getPixelHeight() {
    return (yMax - yMin) / (float) this.processingContext.height;
  }

  public Point graphCoordinateFromScreen(Point p) {
    Point ret = new Point();
    // ret.x = p.x/(float)pixels.width * (xMax-xMin) + xMin;
    ret.y = 0;
    return p;
  }

  public void saveFrame(String fileName) {
    pixels.save(fileName);
  }

  /**
   * @return
   */
  public float getZoom() {
    float ret;
    ret = ((xMax - xMin))*2;
    return ret;
  }
}