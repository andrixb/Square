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


  void update() {


    pushMatrix(); //push values in a matrix
    stroke(255);


    if (mousePressed == false) {
      fill(180, 0, 0, 50);
      light = false;
      translate(x, y);
      rotateX(x*0.01);
      rotateY(y*0.01);
    }
    if (mousePressed == true && light == false) {
      fill(255, 0, 0, 127);
      light = true;
      translate(x, y);
      rotateX(x*0.01);
      rotateY(y*0.01);
    }
    if (bord == true) {
      fill(255, 0, 0, 127);
    }
    if (mousePressed == true && light == true) {
      fill(180, 0, 0, 127);
      light = false;
      translate(x, y);
      rotateX(x*0.01);
      rotateY(y*0.01);
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

