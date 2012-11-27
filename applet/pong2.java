import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class pong2 extends PApplet {

Player one,two;
Ball ball;
int playerHeight = 80;
int playerWidth = 6;
PFont font;

int introTime = 3;
boolean introPlayed = false;

boolean exit = false;
boolean finishedGame = false;
boolean playAgain = false;
int timeout = 5;
boolean decKey = false;

public void setup(){
  hint(ENABLE_NATIVE_FONTS);
  font = loadFont("Courier.vlw");
  textFont(font);
  background(0);
  fill(0,255,0);
  size(750,500);
  one = new Player (1, height/2-playerHeight/2,playerWidth,playerHeight);
  two = new Player (2, height/2-playerHeight/2,playerWidth,playerHeight);
  ball = new Ball();
  smooth();
}

public void draw(){
  if(introPlayed == false){
    intro();
    delay(1000);
  }
  if(finishedGame == false && introPlayed == true){
    background(0);
    rect((width/2)-1,0,2,height);
    one.drawPlayer();
    two.drawPlayer();
    ball.drawBall();
    if (one.score() >= 500){
      one.win();
    }
    if (two.score() >= 500){
      two.win();
    }
    ball.moveBall();
    delay(10);
    if(playAgain == true){
      playAgain = false; 
    }
  }
  else if(finishedGame == true){
    playAgain();
    delay(1000);
  }
}

public void playAgain(){
  if (timeout == -1 || decKey == true){
    if(playAgain == true){
      one = new Player (1, height/2-playerHeight/2,playerWidth,playerHeight);
      two = new Player (2, height/2-playerHeight/2,playerWidth,playerHeight);
      ball = new Ball();
      finishedGame = false;
    }
    if(playAgain == false){
      System.exit(0);
    }
  }
  else{
    background(0);
    textFont(font);
    text("Press 'y' to restart", 50, 75);
    text("Timeout: " + timeout, 50, 175);
    timeout = timeout - 1;
  }
}

public void intro(){
  background(0);
  textFont(font);
  text("Welcome to Pong 0.0.1 beta by Hugh Rawlinson", 50, 75);
  text("Play in: " + introTime, 50, 175);
  println("Play in: " + introTime);
  introTime = introTime - 1;
  if(introTime == 0){
    introPlayed = true;
  }
}
public void keyPressed(){
  if(key=='s'){
    one.down();
  }
  if(key=='w'){
    one.up();
  }
  if(key == CODED && keyCode == UP){
    two.up();
  }
  if(key == CODED && keyCode == DOWN){
    two.down();
  }
  if(key == 'y'){
    playAgain = true;
    decKey = true;
  }
}
class Ball{
  int xCoordinate;
  int yCoordinate;
  boolean direction;
  int slope;
  int slopevar = 1;
  
  public void drawBall(){
    ellipse(xCoordinate,yCoordinate,20,20);
  }
  
  public void moveBall(){
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
  public void reset(){
    xCoordinate = width/2;
    yCoordinate = PApplet.parseInt(random(50,height-50));
    delay(800);
    slope = 0;
    direction = !direction;
  }
  
  public void bounce(){
    direction = !direction;
  }
  
  Ball(){
    direction = true;
    xCoordinate = width/2;
    yCoordinate = PApplet.parseInt(random(50,height-50));
  }
}

class Player{
  int pID;
  int playerPosition;
  int playerHeight;
  int playerWidth;
  int score;
  int bcount;
  
  public int position(){
    return playerPosition;
  }
  
  public void setPosition(int arg){
    playerPosition = arg;
  }
  
  public void drawPlayer(){
    textFont(font);
    if(pID == 1){
      text(score,5,30);
    }
    if(pID == 2){
      text(score,width-100,30);
    }
    int x;
    if (pID == 1){
      x = 0;
    }
    else {
      x = width - playerWidth;
    }
    rect(x,playerPosition,playerWidth,playerHeight);
    
  }
  
  public int score(){
    return score;
  }
  
  public void down(){
    if(playerPosition < height - playerHeight){
      playerPosition = playerPosition + 30;
    }
  }
  
  public void up(){
    if(playerPosition > 0){
      playerPosition = playerPosition - 30;
    }
  }
  
  public void win(){
    textFont(font);
    text("Player " + pID + " Wins!!!",height/3,width/3);
    finishedGame = true;
  }
  
  public void pointsIncrease(int points){
    score = score + points;
  }
  
  public void setbcount(){
    bcount = bcount + 1;
  }
  
  Player(int pIDa, int position, int pwidth, int pheight){
    pID = pIDa;
    playerPosition = position;
    playerWidth = pwidth;
    playerHeight = pheight;
    score = 0;
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "pong2" });
  }
}
