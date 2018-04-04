package floatingpointdev.nancysfractal;

import processing.core.*;
//import floatingpoint.toolkit.*;
public class NancysFractalApplet extends PApplet{
  private ViewPort viewPort;
  private Fractal fractal;
  private Graph graph;
  int frameNumber = 0; 
  boolean isSavingFrames = false;
  boolean isZoomingIn = false;
  boolean isZoomingOut = false;
  /**
   * @param args
   */
  
  //ParameterManager parameterManager;
  
  //static public void main(String args[]) {
	//  PApplet.main(new String[] { "NancysFractalApplet" });
	//}

  public void setup () {
    size (1024, 640);
    this.noStroke();
    this.frameRate(20);
    setupDisplayableObjects();
  }
  
  private void setupDisplayableObjects(){
    viewPort = new ViewPort(0,0,width,height,this);
    fractal = new Fractal(this);
    graph = new Graph(this, fractal);
    viewPort.addChild(graph);
    //graph.addChild(fractal);
  }
  
  
  public void draw () {
    background (50);
    //viewPort.increment();
    viewPort.display();
    //if(isSavingFrames){
    //  saveImage();
    //}
    /*if(isZoomingIn == true){
    	
    	float x = ((float)mouseX / (float)width * (graph.getxMax()-graph.getxMin())) + graph.getxMin();
    	float y = ((float)mouseY / (float)height * (graph.getyMax()-graph.getyMin())) + graph.getyMin();
    	graph.zoomOnPointByRelativeAmount(x,-y,1.005f);
    }
    if(isZoomingOut == true){
    	float x = ((float)mouseX / (float)width * (graph.getxMax()-graph.getxMin())) + graph.getxMin();
       	float y = ((float)mouseY / (float)height * (graph.getyMax()-graph.getyMin())) + graph.getyMin();
    	graph.zoomOnPointByRelativeAmount(-x,y,.999f);
    }*/
    if (mousePressed && (mouseButton == LEFT)) {
    	//float x = ((float)mouseX / (float)width /* * (graph.getxMax()-graph.getxMin())*/) + graph.getxMin();
    	//float y = ((float)mouseY / (float)height /* * (graph.getyMax()-graph.getyMin())*/) + graph.getyMin();
    	//graph.zoomOnPointByRelativeAmount(x,-y,1.01f);
        float panX = (float)mouseX / (float)width -.5f;
        float panY = (float)mouseY / (float)height -.5f;
        
    	graph.zoomByRelativeAmount(1.01f);
    	graph.panLeftRightByRelativeAmount(panX*.04f);
    	graph.panUpDownByRelativeAmount(-panY*.04f);
    	
    } else if (mousePressed && (mouseButton == RIGHT)) {
      float panX = (float)mouseX / (float)width -.5f;
      float panY = (float)mouseY / (float)height -.5f;
      
      graph.zoomByRelativeAmount(.99f);
      graph.panLeftRightByRelativeAmount(panX*.03f);
      graph.panUpDownByRelativeAmount(-panY*.03f);
    }
    if (keyPressed && (key == 'q')) {
    	fractal.modifyParam1(.001f * graph.getZoom());
    } 
    else if (keyPressed && (key == 'a')) {
    	fractal.modifyParam1(-.001f* graph.getZoom());
    }
    else if (keyPressed && (key == 'w')) {
    	fractal.modifyParam2(.008f* graph.getZoom());
    } 
    else if (keyPressed && (key == 's')) {
    	fractal.modifyParam2(-.008f* graph.getZoom());
    }
    else if (keyPressed && (key == 'u')) {
    	fractal.modifyColorRAmp(1.05f);
    } 
    else if (keyPressed && (key == 'j')) {
    	fractal.modifyColorRAmp(.95f);
    } 
    else if (keyPressed && (key == 'i')) {
    	fractal.modifyColorGAmp(1.05f);
    } 
    else if (keyPressed && (key == 'k')) {
    	fractal.modifyColorGAmp(.95f);
    }    
    else if (keyPressed && (key == 'o')) {
    	fractal.modifyColorBAmp(1.05f);
    } 
    else if (keyPressed && (key == 'l')) {
    	fractal.modifyColorBAmp(.95f);
    } 
    else if (keyPressed && (key == 'e')) {
    	fractal.modifySomeFunctParam(.01f);
    }    
    else if (keyPressed && (key == 'd')) {
    	fractal.modifySomeFunctParam(-.01f);
    } 
    else if (keyPressed && (key == 'r')) {
      fractal.modifyColorMod(5f);
    }    
    else if (keyPressed && (key == 'f')) {
      fractal.modifyColorMod(-5f);
    }    

    
    else if (keyPressed && (key == 'x')) {
    	fractal.modifyX(10f);
    }    
    else if (keyPressed && (key == 'z')) {
    	fractal.modifyX(-10f);
    }    
  }
  
  public void mousePressed() {
	  if(mouseButton == PApplet.LEFT){
		  isZoomingIn = true;
	  }
	  if(mouseButton == PApplet.RIGHT){
		  isZoomingOut = true;
	  }
  }

  public void mouseReleased(){
	  if(mouseButton == PApplet.LEFT){
		  isZoomingIn = false;
	  }
	  if(mouseButton == PApplet.RIGHT){
		  isZoomingOut = false;
	  }
  }

  public void keyPressed() {
   //the arrow keys
   if (key == CODED){
     if(keyCode == UP){
       graph.panUpDownByRelativeAmount(.05f);
     } else if(keyCode == DOWN){
       graph.panUpDownByRelativeAmount(-.05f);      
     } else if(keyCode == LEFT){
       graph.panLeftRightByRelativeAmount(-.05f);
     } else if(keyCode == RIGHT){
       graph.panLeftRightByRelativeAmount(.05f);
     }  
   }
    
   //if (key == ' '){
   //  graph.setShowAxes(true);
   //}
  }
  
  public void saveScreenshot(String filename){
	  
	  graph.saveFrame(filename);
  }
  void saveImage(){
    String fileName = "frames3/foo";
    if(frameNumber < 10){
      fileName+="0000";
    } else if(frameNumber<100){
      fileName+="000";
    } else if(frameNumber<1000){
      fileName+="00";
    } else if(frameNumber<10000){
      fileName+="0";
    } else if(frameNumber<10000){
      fileName+="";
    }
    
    fileName += frameNumber+".bmp";
    
    graph.saveFrame(fileName);
    frameNumber++;
  }
  
  public void reset(){
    setupDisplayableObjects();
  }
}