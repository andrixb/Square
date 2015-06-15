package processing.test.square;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Square extends PApplet {

float ax, ay, az;
MobSquare mobsquare;
AccelerometerManager accel;

public void setup() {
 
  int si = 180;
  float sp = si*0.1f;
  int x = PApplet.parseInt(random(si, displayWidth-si));
  int y = PApplet.parseInt(random(si, displayHeight-si));
 
  mobsquare = new MobSquare(x, y, 0, si, sp);
  
 

  accel = new AccelerometerManager(this);
  orientation(PORTRAIT);
}

public void draw() {
  background(0);

  mobsquare.update();
}

public void resume() {
  if (accel != null) {
    accel.resume();
  }
}


public void pause() {
  if (accel != null) {
    accel.pause();
  }
}


public void shakeEvent(float force) {
  println("shake : " + force);
}

public void accelerationEvent(float x, float y, float z) {
  //  println("acceleration: " + x + ", " + y + ", " + z);
  ax = x;
  ay = y;
  az = z;
  redraw();
}

class MobSquare {
  int x, y, z, msize, r, g, b;
  float speed;
  boolean bord, light;
  MobSquare(int xa, int ya, int za, int si, float sp) {
    r = 255;
    g = 0;
    b = 0;
    x=xa;
    y=ya;
    z=za;
    msize=si;
    speed=sp;
  }


  public void update() {


    pushMatrix(); //push values in a matrix
    stroke(255);


    if (mousePressed == false) {
      fill(180, 0, 0, 50);
      light = false;
      translate(x, y);
      rotateX(x*0.01f);
      rotateY(y*0.01f);
    }
    if (mousePressed == true && light == false) {
      fill(255, 0, 0, 127);
      light = true;
      translate(x, y);
      rotateX(x*0.01f);
      rotateY(y*0.01f);
    }
    if (bord == true) {
      fill(255, 0, 0, 127);
    }
    if (mousePressed == true && light == true) {
      fill(180, 0, 0, 127);
      light = false;
      translate(x, y);
      rotateX(x*0.01f);
      rotateY(y*0.01f);
    }
    
    box(180); //cube shape

    x -= ax*speed;
    y += ay*speed;


    //------COLLISION DECTECTION------

    if (x > (displayWidth - msize) || mouseX > (displayWidth - msize)) {
      x = displayWidth - msize;
      mouseX = displayWidth - msize;
      bord = true;
      light = true;
    }
    if (x < msize || mouseX < msize) {
      x = msize;
      mouseX = msize;
      bord = false;
      light = false;
    }

    if (y > displayHeight - msize || mouseY > displayHeight - msize) {
      y = displayHeight - msize;
      mouseY = displayHeight - msize;
      bord = true;
      light = true;
    }
    if (y < msize || mouseY < msize) {
      y = msize;
      mouseY = msize;
      bord = false;
      light = false;
    }

    //------COLLITION DETECTION ENDS-----
    popMatrix();//remove values in a matrix
  }
}


  public int sketchWidth() { return displayWidth; }
  public int sketchHeight() { return displayHeight; }
  public String sketchRenderer() { return OPENGL; }
}
