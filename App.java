import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        int boardwidth=500;
        int boardheight=boardwidth;

        JFrame frame=new JFrame("Snake");
        frame.setVisible(true);//to make the window visible
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);//to open the window at centre
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame=new SnakeGame(boardwidth, boardheight);
        frame.add(snakeGame);
        frame.pack();//it will place the jpanel inside the frame with full dimension
        snakeGame.requestFocus();





    }
}
