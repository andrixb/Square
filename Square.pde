float ax, ay, az;
MobSquare mobsquare;
AccelerometerManager accel;

void setup() {
 
  int si = 180;
  float sp = si*0.1;
  int x = int(random(si, displayWidth-si));
  int y = int(random(si, displayHeight-si));
 
  mobsquare = new MobSquare(x, y, 0, si, sp);
  
  size(displayWidth, displayHeight, OPENGL);

  accel = new AccelerometerManager(this);
  orientation(PORTRAIT);
}

void draw() {
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

