package floatingpointdev.nancysfractal;
import processing.core.*;

public class Fractal extends DisplayableObject {
  float theta = .1f;
  float param2 = .35f;
  float colorRAmp = 3;
  float colorGAmp = 1;
  float colorBAmp = 3;
  float someFunctParam2 = .8f;
  float colorMod = 60f;
  float X = 128;
  
  Graph graphContext;
  PImage pixelBuffer;

  Fractal(PApplet processingContext){
    super(processingContext);
    this.graphContext = graphContext;
  }
  
  protected void draw(){
  }

  public int[] calculatePoint(float x, float y){
    int iterations = 20;
    float threshhold = 10; 
    float magnitude = 0;
    
    ComplexNumber2 u = new ComplexNumber2(theta,param2);
    ComplexNumber2 z = new ComplexNumber2(0,0);
    

      z.set(x, y);
        int i = 0;
        for(i = 0; i <= iterations; ++i){
        //  for(int k = 0; k<=i; ++k){
            z.mul(z);
           //for(int mul = 0; mul <= X; ++ mul){
            z.someFunction(someFunctParam2);
            z.mul(u);
            //z.someFunction(someFunctParam2);          
           //}//
            //z.mul(u);
            //z.mul(u);
            z.sub(u);
            z.mul(.5f);
        //  }

          //check if z is heading off to infinity
          magnitude = processingContext.sqrt(z.real * z.real + z.imaginary * z.imaginary);
          //magnitude = z.real;
          if(magnitude >= threshhold){
            break;
          }
        }
        
        //processingContext.color(i,255,i);  //color is based on the number of iterations until until we reached the threshhold.
        int color = (int)(processingContext.abs((int)z.real % colorMod) * colorRAmp);
        int color2 = (int)(processingContext.abs((int)z.imaginary % colorMod) * colorGAmp);
        int color3 = (int)(magnitude * colorBAmp);
        
        //color = restrict(0,255,color);
        //color2 = restrict(0,255,color2);
        //color3 = restrict(0,255,color3);
        //processingContext.fill(magnitude%256,magnitude%256,magnitude%256);
        //processingContext.fill(color,color2,color3);
        //processingContext.rect(graphContext.getScreenCoordinateX(ireal), graphContext.getScreenCoordinateY(iimaginary), pixelWidth*1.01f, pixelHeight*1.01f);
        
        int[] colorArray = new int[3];
        colorArray[0] = color;
        colorArray[1] = color2;
        colorArray[2] = color3;

         //graphContext.plotPt(ireal,iimaginary,color,color2,color3);
         return(colorArray);
      //}
    //}
  }
  
  public int restrict(int low, int high, int val){
    if(val >= high){
      val = high;
    } else if(val <= low){
      val = low;
    }
    return val;
  }

  //void incrementTheta(){
  //  theta += .01;
  //}
  //void decrementTheta(){
  //  theta -= .01;
  //}
  
  //void incrementParam2(){
  //  theta += .01;
  //}
  
  //void decrementParam2(){
   // theta -= .01;  
  //}
  
  float scale(float param, float x){
    float mod = 0;
    if(param != 0){
      mod = x*(param)*2;
    }
    mod += .001 *x;
    return mod;
  }
  
  void modifyParam1(float x){
      theta += scale(theta,x);
  }
  
  void modifyParam2(float x){
    param2 += scale(param2,x);
  }
  
  void modifyColorRAmp(float x){
	  colorRAmp *= x;
  }
  void modifyColorGAmp(float x){
	  colorGAmp *= x;
  }  
  void modifyColorBAmp(float x){
	  colorBAmp *= x;
  }

	public void modifyX(float x) {
		X += x;
	}

	public void modifySomeFunctParam(float x) {
		someFunctParam2 += scale(someFunctParam2,x);
	}
	
	public void modifyColorMod(float x){
	  colorMod += x;//scale(colorMod,x);
	}
}
