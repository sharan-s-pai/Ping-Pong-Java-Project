package gameFolder;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends Rectangle {
	
	int id;
	int velY;
	int speed=8;
	public Paddle(int x, int y, int paddleWidth, int paddleHeight,int id) {
		super(x,y,paddleWidth,paddleHeight);
		this.id =  id;
	}

	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setY(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setY(speed);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setY(speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setY(-speed);
				move();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setY(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setY(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setY(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setY(0);
				move();
			}
		}
	}
	public void setY(int y) {
		velY = y;
	}
	
	public void draw(Graphics g) {
		if(id==1) {
			g.setColor(Color.GREEN);
		}else {
			g.setColor(Color.blue);
		}
		g.fillRect(x, y, width, height);
	}
	
	public void move() {
		// This will update the y position at a rate of the velY=10
		y+=velY;
	}
}
