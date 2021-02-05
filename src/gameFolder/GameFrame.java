package gameFolder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GameFrame extends JFrame {
	JButton b1;
	GamePanel panel;
	public GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		/* 
		  This function will make sure that the dimension of the frame will fit
		  according to the size of the panel in it perfectly and decide the 
		  size of window appropriately.
		*/
		this.setSize(GamePanel.SCREEN_SIZE);
		//this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
}
