class Player{
  int pID;
  int playerPosition;
  int playerHeight;
  int playerWidth;
  int score;
  int bcount;
  
  int position(){
    return playerPosition;
  }
  
  void setPosition(int arg){
    playerPosition = arg;
  }
  
  void drawPlayer(){
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
  
  int score(){
    return score;
  }
  
  void down(){
    if(playerPosition < height - playerHeight){
      playerPosition = playerPosition + 30;
    }
  }
  
  void up(){
    if(playerPosition > 0){
      playerPosition = playerPosition - 30;
    }
  }
  
  void win(){
    textFont(font);
    text("Player " + pID + " Wins!!!",height/3,width/3);
    finishedGame = true;
  }
  
  void pointsIncrease(int points){
    score = score + points;
  }
  
  void setbcount(){
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
