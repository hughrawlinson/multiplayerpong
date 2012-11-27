class Ball{
  int xCoordinate;
  int yCoordinate;
  boolean direction;
  int slope;
  int slopevar = 1;
  
  void drawBall(){
    ellipse(xCoordinate,yCoordinate,20,20);
  }
  
  void moveBall(){
    yCoordinate = yCoordinate + slope;
    if(yCoordinate < 10|| yCoordinate > height - 10){
      slope = slope * -1;
    }
    if (direction == true){
      xCoordinate = xCoordinate + 5;
    }
    else{
      xCoordinate = xCoordinate - 5;
    }
    if (xCoordinate - 10 < 0 || xCoordinate + 10 > width){
      if (direction  == true){
        one.pointsIncrease(100);
        ball.reset();
      }
      else{
        two.pointsIncrease(100);
        ball.reset();
      }
    }
    yCoordinate = yCoordinate + slope;
    //bounce left
    if(one.position() <= yCoordinate && yCoordinate <= one.position() + playerHeight && xCoordinate < playerWidth+5){
      ball.bounce();
      one.setbcount();
      if(yCoordinate < one.position() + playerHeight/3){
        slope = slope - slopevar;
      }
      else if(yCoordinate > one.position() + 2*(playerHeight/3)){
        slope = slope + slopevar;
      }
    }
    //Bounce right
    else if(two.position() <= yCoordinate && yCoordinate <= two.position() + playerHeight && xCoordinate >= width - (playerWidth+5)){
      ball.bounce();
      two.setbcount();
      if(yCoordinate < two.position() + playerHeight/3){
        slope = slope - slopevar;
      }
      else if(yCoordinate > two.position() + 2*(playerHeight/3)){
        slope = slope + slopevar;
      }
    }
  }
  void reset(){
    xCoordinate = width/2;
    yCoordinate = int(random(50,height-50));
    delay(800);
    slope = 0;
    direction = !direction;
  }
  
  void bounce(){
    direction = !direction;
  }
  
  Ball(){
    direction = true;
    xCoordinate = width/2;
    yCoordinate = int(random(50,height-50));
  }
}

