package Task2;


import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
public class GPanel extends JPanel implements ActionListener {

    //declaring globally
static final int sWidth = 600;
static final int sHeight = 600;
static final int uSize = 20;
static final int gUnits = (sWidth*sHeight)/uSize;
static final int delay  = 200; //sets the pace of the game
final int x[] = new int[gUnits]; // these arrays hold coordinates of the snake body parts
final int y[] = new int[gUnits];// we set the size of game units so the snake isnt bigger than the game
int bodyParts = 5;
int applesEaten;
int appleX;
int appleY;
char direction = 'D';
boolean running = false;
Timer timer;
Random random;



    GPanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(sWidth,sHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        GameStart();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
     if(running){
         moveSnake();
         checkFood();
         checkCollision();

     }
     repaint();
    }

    public void GameStart(){
      randomApple();
      running= true;
      timer = new Timer(delay,this);// we use this because of the action interface listener
        timer.start();
    }
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      draw(g);

    }
    public void draw(Graphics g) {
        if (running) {
            g.setColor(new Color(random.nextInt(255),random.nextInt(50),50));
            g.fillOval(appleX, appleY, uSize, uSize);

            for (int i = 0; i < bodyParts; i++) {//snake body part
                if (i == 0) {//head of the snake
                    g.setColor(new Color(20, 100, 32));
                    g.fillRect(x[i], y[i], uSize, uSize);
                } else {
                    g.setColor(Color.BLUE);
                    g.setColor(new Color(124, 252, 0));
                    g.fillRect(x[i],y[i],uSize,uSize);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("West Java", Font.BOLD,35));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score:"+applesEaten, (sWidth- metrics.stringWidth("Score:"+applesEaten))/2,g.getFont().getSize());
        }
        else{
            gameEnd(g);
        }
    }
    public void randomApple(){
     appleX = random.nextInt((sWidth/uSize))*uSize;
     appleY = random.nextInt((sHeight/uSize))*uSize;

    }
    public void moveSnake(){
     for(int i=bodyParts; i>0; i--){
         x[i] = x[i-1];
         y[i] = y[i-1];
     }
     switch(direction){
         case 'U':
             y[0] = y[0]- uSize;
             break;
         case 'D':
             y[0] = y[0]+uSize;
             break;
         case 'L':
             x[0] = x[0]- uSize;
             break;
         case 'R':
             x[0] = x[0]+ uSize;
             break;
     }
    }
    public void checkFood(){
      if((x[0]== appleX) && (y[0]== appleY)){
          bodyParts++;
          applesEaten++;
          randomApple();

      }

    }
    public void checkCollision(){
     for(int i= bodyParts;i>0;i--){
         if((x[0]== x[i])&& (y[0]==y[i])){//this means the head collided with the body
             running = false;
         }
     }
     //checking if head touches borders
        if(x[0]<0){//left border
            running=false;

        }
        if(x[0]>sWidth){//right border
            running= false;
        }
        if(y[0]<0){//top border
            running= false;
        }
        if(y[0]>sHeight){//bottom border
            running= false;
        }
        if(!running){
            timer.stop();
        }
    }
    public void gameEnd(Graphics g){
        //score
        g.setColor(Color.RED);
        g.setFont(new Font("West Java", Font.BOLD,35));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:"+applesEaten, (sWidth- metrics1.stringWidth("Score:"+applesEaten))/2,g.getFont().getSize());
        //gameover screen
        g.setColor(Color.RED);
        g.setFont(new Font("West Java", Font.BOLD,60));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (sWidth- metrics2.stringWidth("Game Over"))/2,sHeight/2);

    }
public class MyKeyAdapter extends KeyAdapter{
      //this is an inner class
    @Override
    public void keyPressed(KeyEvent e){
     switch(e.getKeyCode()){
         case KeyEvent.VK_LEFT :
             if(direction!='R'){
                 direction = 'L';
             }
             break;
         case KeyEvent.VK_RIGHT:
             if(direction!='L'){
                 direction = 'R';
             }
             break;
         case KeyEvent.VK_UP:
             if(direction!='D'){
                 direction = 'U';
             }
             break;
         case KeyEvent.VK_DOWN :
             if(direction!='U'){// IF DIRECTIONS DOES NOT = U FOR UP THEN YOU CAN GO DOWN
                 direction = 'D';
             }
             break;
     }
    }
}


}