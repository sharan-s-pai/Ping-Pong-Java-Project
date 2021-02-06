package gameFolder;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame implements ActionListener,Runnable {
	GameFrame g1;
	JTextField t1,t2;
	JLabel lb;
	Thread t;
	boolean flag=true;
	public StartFrame() {
		super("Start Frame");
		ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("gameFolder/Icons/Pong-icon.jpg"));
		Image i = im.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		im = new ImageIcon(i);
		JLabel l = new JLabel(im);
		//System.out.println(l);
		lb = new JLabel("First to score 5 Wins!!!");
		lb.setForeground(Color.white);
		lb.setFont(new Font("TimesRoman",Font.BOLD,50));
		JLabel lb1 = new JLabel("Enter Player-1 name (Default name - Player-1) :");
		lb1.setForeground(Color.white);
		lb1.setBorder(new EmptyBorder(20,0,20,0));
		t1 = new JTextField(20);
		JLabel lb2 = new JLabel("Enter Player-2 name (Default name - Player-2) :");
		lb2.setForeground(Color.white);
		lb2.setBorder(new EmptyBorder(35,0,35,0));
		t2 = new JTextField(20);
		this.add(l);
		this.add(lb);
		this.add(lb1);
		this.add(t1);
		this.add(lb2);
		this.add(t2);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(600, 500);
		t = new Thread(this);
		t.start();
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.black);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton b1 = new JButton("Start");
		b1.setBorder(new EmptyBorder(10,250,10,250));
		b1.setBackground(Color.DARK_GRAY);
		b1.setForeground(Color.WHITE);
		this.add(b1);
		b1.addActionListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(t1.getText().equals("")&&t2.getText().equals("")) {
			GameFrame.player1="Player-1";
			GameFrame.player2="Player-2";
		}else if(t1.getText().equals("")&&!t2.getText().equals("")) {
			GameFrame.player1=t1.getText();
			GameFrame.player2="Player-2";
		}else if(!t1.getText().equals("")&&t2.getText().equals("")) {
			GameFrame.player2=t2.getText();
			GameFrame.player1="Player-1";
		}else {
			GameFrame.player1 = t1.getText();
			GameFrame.player2 = t2.getText();
		}
		this.stop();
		try {
			t.join();
		} catch (Exception e2) {
		}
		this.setVisible(false);
		g1 = new GameFrame();
	}

	@Override
	public void run() {
		while(flag) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(lb.getForeground().equals(Color.WHITE)) {
				lb.setForeground(Color.YELLOW);
			}else {
				lb.setForeground(Color.WHITE);
			}
		}
		
	}
	public void stop() {
		flag=false;
	}

}
