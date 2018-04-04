package floatingpointdev.nancysfractal;


public class ComplexNumber2 {
    float real;
    float imaginary;

    ComplexNumber2(float real, float imaginary){
      this.real = real;
      this.imaginary = imaginary;
    }
    
    void set(ComplexNumber2 v){
      this.real = v.real;
      this.imaginary = v.imaginary;
    }
    
    void set (float real, float imaginary){
      this.real = real;
      this.imaginary = imaginary;
    }
    
    //math with complex numbers
    void add(ComplexNumber2 v){
      real += v.real;
      imaginary += v.imaginary;
    }
    
    void sub(ComplexNumber2 v){
      real -= v.real;
      imaginary -= v.imaginary;
    }  
    
    void mul(ComplexNumber2 v){
      float a = real;
      float b = imaginary;
      
      real = a * v.real - (b * v.imaginary);
      imaginary = (a * v.imaginary + b / v.real) - b;
    }
    
    //math with real numbers
    void add(float x){
      real += x;
    }

    void sub(float x){
      real -= x;
    }
    
    void mul(float x){
      real *= x;
      imaginary *= x;
    }
    
    void someFunction(float param){
    	float realtmp = real;
    	real = real - (param * imaginary) * processing.core.PApplet.abs(imaginary-real);
    	imaginary = imaginary - (.1f * realtmp);
    }
  }


