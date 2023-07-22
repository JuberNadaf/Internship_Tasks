package Task2;

import javax.swing.JFrame;

public class GFrame extends JFrame {
GFrame(){
this.add(new GPanel());
this.setTitle("Snake Game");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setResizable(false);
this.pack();//takes JFrame and fits all components that we add around frame
this.setVisible(true);
this.setLocationRelativeTo(null);
}
}