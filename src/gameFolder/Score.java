package gameFolder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score extends Rectangle {
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1=0,player2=0;
	
	
	public Score(int gameWidth, int gameHeight) {
		GAME_WIDTH = gameWidth;
		GAME_HEIGHT = gameHeight;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), GAME_WIDTH/2-70, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), GAME_WIDTH/2+30, 50);
	}
}
