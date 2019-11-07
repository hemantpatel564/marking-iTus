// This #include statement was automatically added by the Particle IDE.
#include <InternetButton.h>

InternetButton b = InternetButton();

void setup() {
    
     b.begin();
    Particle.function("faces",showFaces);
    
}

void loop() {

    

}

int showFaces(String time)
 {
      int t = time.toInt();
      
    if(t = 0)
    {
     
     b.allLedsOff();
     b.ledOn(1,255,0,0);
     b.ledOn(11,255,0,0);
     b.ledOn(3,255,0,0);
     b.ledOn(4,255,0,0);
     b.ledOn(5,255,0,0);
     b.ledOn(6,255,0,0);
     b.ledOn(7,255,0,0);
     b.ledOn(8,255,0,0);
     b.ledOn(9,255,0,0);
    }
    
    else if(t=4)
    {
    
     b.allLedsOff();
     b.ledOn(1,255,0,0);
     b.ledOn(11,255,0,0);
     b.ledOn(4,255,0,0);
     b.ledOn(5,255,0,0);
     b.ledOn(6,255,0,0);
     b.ledOn(7,255,0,0);
     b.ledOn(8,255,0,0);
  
    }
         
    else if(t=8)
        {
            b.allLedsOff();
            
         b.ledOn(1,255,0,0);
         b.ledOn(11,255,0,0);
        b.ledOn(5,255,0,0);
        b.ledOn(6,255,0,0);
        b.ledOn(7,255,0,0);
            
        }
        
          
          
      else if(t=12)
        {
            
            b.allLedsOff();
            
         b.ledOn(1,255,0,0);
         b.ledOn(11,255,0,0);
         b.ledOn(6,255,0,0);
        }
        
        else if(t=16)
        {
            b.allLedsOff();
         b.ledOn(1,255,0,0);
         b.ledOn(11,255,0,0);
        }
        
        else 
        {
            b.allLedsOff();
        }

 }