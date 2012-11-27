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

void setup(){
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

void draw(){
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

void playAgain(){
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

void intro(){
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
void keyPressed(){
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
