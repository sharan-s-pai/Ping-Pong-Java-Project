package gameFolder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable {
	
	// We have used static because game width is independent of components.
	
	// final will stop us from accidentally changing value of GAME_WIDTH
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH*(0.5555555555));
	
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	
	static final int BALL_DIAMETER  = 20;
	static final int PADDLE_WIDTH  = 25;
	static final int PADDLE_HEIGHT  = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random rand;
	Paddle p1,p2;
	Ball ball;
	Score s;
	public GamePanel() {
		newPaddles();
		newBall();
		s = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new ActionListen());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		
	}
	
	public void newPaddles() {
		p1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		p2 = new Paddle(GAME_WIDTH-(PADDLE_WIDTH+5),(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		p1.draw(g);
		p2.draw(g);
	}
	public void move() {
		
	}
	
	public void checkCollision() {
		if(p1.y<=0) {
			p1.y=0;
		}
		if(p1.y>=(GAME_HEIGHT-PADDLE_HEIGHT-30)) {
			p1.y=(GAME_HEIGHT-PADDLE_HEIGHT-30);
		}
		
		if(p2.y<=0) {
			p2.y=0;
		}
		if(p2.y>=(GAME_HEIGHT-PADDLE_HEIGHT-30)) {
			p2.y=(GAME_HEIGHT-PADDLE_HEIGHT-30);
		}
	}
		
	public class ActionListen extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			p1.keyPressed(e);
			p2.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
			p1.keyReleased(e);
			p2.keyReleased(e);
		}
	}

	@Override
	public void run() {
		// Game Loop
		
		/*
		 * Game Loop of the game. It uses fixed timestep. 
		 * Optimal time represents time needed to update one frame.
		 * Update time represents time actually taken to update one frame.
		 * 
		 * By calculating the difference between optimal and actual time,
		 * we can let Thread to sleep for the exact time we are aiming for, which is 60 FPS.
		 */
		long lastTime = System.nanoTime();
		
		double amountOfTicks = 60.0;
		
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		//System.out.println(ns);
		while(true) {
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime = now;
			if(delta>=1) {
				move();
				checkCollision();
				repaint();
				delta--;
				//System.out.println("Test");
			}
//			System.out.println("TEST");
		}
		
	}
}
