# NancysFractal
 An fun experiment with a z->z^2 type fractal that I made for a now departed friend.
 
 The features change dramatically through several stages as you zoom in and zoom out. When you zoom all the way in there is interesting banding / aliasing as you hit the limit of floating point number precision.
 
 #### Processing note
 This example uses an older version of the processing.org library (2.x) and has not been updated for the newer versions. The older library is included in the /lib folder.  It should be simple to update I just haven't done it. The primary problem is that PApplet can no longer be added to Swing Components which is how this application embeds the Processing context in the main JFrame. 

#### Controls

##### Zoom
Zoom in on point: Left Mouse Click

Zoom out from point: Right Mouse Click

##### Modify Values
Hold the key to rapidly increase / decrease the value. Each parameter is a mathematical variable in the fractal algorithm.

Param1 increase / decrease :         q / a

Parma2 increase / decrease :         w / s

Parma3 increase / decrease :          e / d

Parma4 increase / decrease :          r / f


Color Red increase / decrease :      u / j

Color Green increase / decrease :   i / k

Color Blue increase / decrease :     o / l
