package gameFolder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Ball extends Rectangle {
	
	Random rand;
	int velX;
	int velY;
	int velocity=2;
	public Ball(int i, int j, int width, int height) {
		super(i,j,width,height);
		rand = new Random();
		int randXDirection = rand.nextInt(2);// it will be either 0 or 1. 2 is just an upper limit.
		if(randXDirection==0) {
			randXDirection--;
		}
		setX(randXDirection*velocity);
		int randYDirection = rand.nextInt(2);// it will be either 0 or 1. 2 is just an upper limit.
		if(randYDirection==0) {
			randYDirection--;
		}
		setY(randYDirection*velocity);
		
	}

	public void setX(int randomX) {
		velX = randomX;
	}
	
	public void setY(int randomY) {
		velY = randomY;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}
	
	public void move() {
		x+=velX;
		y+=velY;
	}
	
}
