package floatingpointdev.nancysfractal;
import processing.core.PApplet;
import java.util.LinkedList;
import java.util.ListIterator;

public class DisplayableObject {
    //transformation variables that define the objects position.
    protected float transX,transY,transRotate,transScaleX,transScaleY;
    protected PApplet processingContext;   
    protected LinkedList<DisplayableObject> displayableChildren;
    protected boolean isDisplayable = true;
    
    DisplayableObject(PApplet processingContext){
      this.processingContext = processingContext;
      displayableChildren = new LinkedList<DisplayableObject>();
      transX = 0;
      transY = 0;
      transRotate = 0;
      transScaleX = 1;
      transScaleY = 1;  
    }

    void display(){
      if(isDisplayable){
        beginDisplay();
        displayChildren();
        this.draw();
        endDisplay();
      }
    }
    
    //override the draw function to make your displayableObject draw things.
    protected void draw(){
    }
   
    protected void beginDisplay(){
      processingContext.pushMatrix();
      processingContext.translate(transX,transY); 
      processingContext.scale(transScaleX,transScaleY);   
      processingContext.rotate(transRotate);
      processingContext.pushStyle();
    }
    
    protected void endDisplay(){
      processingContext.popMatrix();
      processingContext.popStyle();
    }
    
    void displayChildren(){
      ListIterator<DisplayableObject> iterator = displayableChildren.listIterator(0);
      while(iterator.hasNext()){
        iterator.next().display();
      }   
    }
    
    void addChild(DisplayableObject child){
      displayableChildren.add(child);
    }
    
    void removeChild(DisplayableObject child){
      displayableChildren.remove(child);
    }
    
    void moveTo(float x, float y){
      transX = x; transY = y;
    }
    
    //get the amount of rotation in radians.
    float getRotate(){
      return transRotate;
    }  
    
    //set the rotation in radians.
    void setRotate(float rotAmt){
      transRotate = rotAmt;
    }
    
    float getScaleX(){
      return transScaleX;
    }  
    
    float getScaleY(){
      return transScaleY;
    } 
    
    float getPosX(){
      return transX;
    }  
    
    float getPosY(){
      return transY;
    }   
    
    void setScale(float x, float y){
      transScaleX = x; transScaleY = y;
    } 
    
    boolean isDisplayable(){
      return this.isDisplayable();
    }
    
    void setDisplayable(boolean state){
      isDisplayable = state;    
    }
}
