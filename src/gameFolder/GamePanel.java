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
	JButton b1;
	public GamePanel() {
		this.setFocusable(true);
		this.addKeyListener(new ActionListen());		
		this.setPreferredSize(SCREEN_SIZE);
		newPaddles();
		newBall();
		s = new Score(GAME_WIDTH,GAME_HEIGHT);
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		rand = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
	}
	
	public void newPaddles() {
		p1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		p2 = new Paddle(GAME_WIDTH-(PADDLE_WIDTH+5),(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		s.draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		p1.draw(g);
		p2.draw(g);
		ball.draw(g);
	}
	public void move() {
		// By calling move here we are bring the paddles into the game loop.
		
		// this will smoothen the movement of the paddles since movement will be generated twice the speed and 
		//also it will be called every time before repainting
		p1.move();
		p2.move();
		ball.move();
	}
	
	public void checkCollision() {
		// This will check if the ball collides with the border and will set it wo move in opposite direction.
		if(ball.y<=0) {
			ball.setY(-ball.velY);
		}
		if(ball.y>=GAME_HEIGHT-BALL_DIAMETER-20) {
			ball.setY(-ball.velY);
		}
		
		// Thiw will check if the baddles are moving beyond the border and will stop it if it has reached the border.
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
		
		// This will chek is the ball has hit the paddles. If so it will reverse the x and y of the ball
		
		if(ball.intersects(p1)) {
			// Intersects method will check if there is any collision between the 2 objects
			ball.velX=-ball.velX;
			if(ball.velX<10) {
				ball.velX++;// Increase the speed of the ball
				if(ball.velY<0) {
					ball.velY--; // Increase the speed of the ball
				}else {
					ball.velY++;
				}
			}
			ball.setY(ball.velY);
			ball.setX(ball.velX);
		}
		
		if(ball.intersects(p2)) {// Intersects method will check if there is any collision between the 2 objectsw
			ball.velX = Math.abs(ball.velX);
			if(ball.velX<10) {
				ball.velX++;// Increase the speed of the ball
				if(ball.velY<0) {
					ball.velY--; // Increase the speed of the ball
				}else {
					ball.velY++;
				}
			}
			ball.setY(ball.velY);
			ball.setX(-ball.velX);
			
		}
		
		//Give a player 1 point and creates new paddles and ball
		
		if(ball.x<=0) {
			s.player2++;
			newPaddles();
			newBall();
			System.out.println("player1="+s.player2);
		}
		if(ball.x>=GAME_WIDTH-BALL_DIAMETER) {
			s.player1++;
			newPaddles();
			newBall();
			System.out.println("player2="+s.player1);
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
