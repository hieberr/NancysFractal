package floatingpointdev.nancysfractal;
import processing.core.*;

public class ViewPort extends DisplayableObject{
  ViewPort(int x, int y, int viewWidth, int viewHeight, PApplet displayContext) {
    super(displayContext);
    setScale(viewWidth/2,-viewHeight/2);
    moveTo(viewWidth/2+x,viewHeight/2+y);
    processingContext.strokeWeight(.01f);
  } 
}
