import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Random.*;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener,KeyListener{
    private class Tile{ //create a class to keep track of the all the X and Y position for each tile
        int x;
        int y;

        Tile(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    int boardwidth;
    int boardheight;
    int tilesize=25;
   //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    //Food
    Tile food;
    Random random;

    //game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver=false;

SnakeGame(int boardwidth,int boardheight){
    this.boardwidth=boardwidth;
    this.boardheight=boardheight;
    setPreferredSize(new Dimension(this.boardwidth,this.boardheight));
    setBackground(Color.blue);
    addKeyListener(this);
    setFocusable(true);

    snakeHead=new Tile(5,5);
    snakeBody=new ArrayList<Tile>();

    food=new Tile(10,10);
    random=new Random();
    placeFood();

    velocityX=0;
    velocityY=0;

    gameLoop=new Timer(100,this);
    gameLoop.start();

}
public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
}
public void draw(Graphics g){
    for(int i=0;i<boardwidth/tilesize;i++){
        g.drawLine(i*tilesize,0,i*tilesize,boardheight);
        g.drawLine(0,i*tilesize,boardwidth,i*tilesize);
    }
    //Food
    g.setColor(Color.red);
    g.fillRect(food.x* tilesize,food.y * tilesize,tilesize,tilesize);

    g.setColor(Color.green);
    g.fillRect(snakeHead.x* tilesize, snakeHead.y*tilesize,tilesize,tilesize);

    //Snake Body
    for(int i=0;i<snakeBody.size();i++){
        Tile snakePart=snakeBody.get(i);
        g.fillRect(snakePart.x * tilesize, snakePart.y * tilesize, tilesize, tilesize );
    }
    //score
    g.setFont(new Font("Arial",Font.PLAIN,16));
    if(gameOver){
        g.setColor(Color.red);
        g.drawString("Game Over:"+String.valueOf(snakeBody.size()),tilesize-16,tilesize);
    }
    else{
        g.drawString("Score:"+String.valueOf(snakeBody.size()),tilesize-16,tilesize);
    

    }
    }

public void placeFood(){
    food.x=random.nextInt(boardwidth/tilesize);
    food.y=random.nextInt(boardheight/tilesize);
}
//To detect the collision between food and Snake
public boolean collision(Tile tile1,Tile tile2){
    return tile1.x==tile2.x && tile1.y==tile2.y;
}
public void move(){
    //eat food
    if(collision(snakeHead, food)){
        snakeBody.add(new Tile(food.x,food.y));
        placeFood();
    }
    //Snake Body
    for(int i=snakeBody.size()-1;i>=0;i--){
        Tile snakePart=snakeBody.get(i);
        if(i==0){
            snakePart.x=snakeHead.x;
            snakePart.y=snakeHead.y;
        }
    }
    //Snake Head
    snakeHead.x+=velocityX;
    snakeHead.y+=velocityY;

    //game over condition
    for(int i=0;i<snakeBody.size();i++){
        Tile snakePart=snakeBody.get(i);
        //collide with the snake head
        if(collision(snakeHead,snakePart)){
            gameOver=true;
        }
    }
    if(snakeHead.x*tilesize<0 || snakeHead.x*tilesize>boardwidth||
       snakeHead.y*tilesize<0||snakeHead.y*tilesize>boardheight){
       gameOver=true;
       }
       
}
@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    move();
    repaint();
    if(gameOver){
        gameLoop.stop();
    }
}
@Override
public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1){
        velocityX=0;
        velocityY=-1;
    }
    else if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityY!=-1){
        velocityX=0;
        velocityY=1;
    }
    else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityY!=1){
        velocityX=-1;
        velocityY=0;
    }
    else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityY!=-1){
        velocityX=-1;
        velocityY=0;
    
}
}
@Override
public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
}

@Override
public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
}
  }

