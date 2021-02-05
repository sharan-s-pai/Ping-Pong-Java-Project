package gameFolder;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame implements ActionListener {
	GameFrame g1;
	JTextField t1,t2;
	public StartFrame() {
		super("Start Frame");
		JLabel lb1 = new JLabel("Enter Player-1 name (Default name - Player-1) :");
		lb1.setForeground(Color.white);
		lb1.setBorder(new EmptyBorder(20,0,20,0));
		t1 = new JTextField(20);
		JLabel lb2 = new JLabel("Enter Player-2 name (Default name - Player-2) :");
		lb2.setForeground(Color.white);
		lb2.setBorder(new EmptyBorder(20,0,20,0));
		t2 = new JTextField(20);
		this.add(lb1);
		this.add(t1);
		this.add(lb2);
		this.add(t2);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.black);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton b1 = new JButton("Start");
		b1.setBorder(new EmptyBorder(10,250,10,250));
		this.add(b1);
		b1.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		g1 = new GameFrame();
	}

}
