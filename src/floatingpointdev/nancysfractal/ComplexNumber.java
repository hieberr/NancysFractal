package floatingpointdev.nancysfractal;

public class ComplexNumber {
  float real;
  float imaginary;

  ComplexNumber(float real, float imaginary){
    this.real = real;
    this.imaginary = imaginary;
  }
  
  void set(ComplexNumber v){
    this.real = v.real;
    this.imaginary = v.imaginary;
  }
  
  void set (float real, float imaginary){
    this.real = real;
    this.imaginary = imaginary;
  }
  
  //math with complex numbers
  void add(ComplexNumber v){
    real += v.real;
    imaginary += v.imaginary;
  }
  
  void sub(ComplexNumber v){
    real -= v.real;
    imaginary -= v.imaginary;
  }  
  
  void mul(ComplexNumber v){
    float a = real;
    float b = imaginary;
    
    real = a * v.real - (b * v.imaginary);
    imaginary = a * v.imaginary + b * v.real;
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
}
