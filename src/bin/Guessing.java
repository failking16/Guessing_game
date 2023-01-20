package bin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.JComponent;

import javax.swing.JFrame;

public class Guessing {

	private JFrame guess;
	private JButton button;
	private JTextField field;
	private JLabel lab;
	private int tries=10;
	private static int randomizing;
	
	
	public static void main(String[] args) {
		randomizing = (int) (Math.random()*(1000-1)+1);
		new Guessing();
	}

	Guessing(){
		guess= new JFrame("guessing");
		Clicklistener click = new Clicklistener();
		button = new JButton("Check");
		field= new JTextField();
		lab= new JLabel("Left Attempts: "+tries);
		
		guess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guess.setLayout(null);
		guess.setSize(300,300);
		guess.setLocationRelativeTo(null);
		guess.setVisible(true);
		
		button.setBounds(100,80,100,80);
		field.setBounds(100, 200, 100, 20);
		lab.setBounds(100, 50, 100, 20);
		
		guess.add(lab);
		guess.add(button);
		guess.add(field);
		button.addActionListener(click);
	}
	
	public void paint(Graphics g) {
		lab=new JLabel("U r right");
	}
	
	class Clicklistener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				if(!field.getText().equals("")) {
					if(checking()) {
						guess.remove(lab);
						lab=new JLabel("U r right");
						lab.setBounds(100, 50, 100, 20);
						guess.repaint();
						guess.add(lab);
						paint(null);
					}else {
						tries--;
						guess.remove(lab);
						lab=new JLabel("left tries "+tries+"("+high_low()+")");
						lab.setBounds(100, 50, 100, 20);
						guess.repaint();
						guess.add(lab);
						if(tries<0) {
							System.out.print(randomizing);
							System.exit(0);
						}
					}
				}else {
					guess.remove(lab);
					lab=new JLabel("enter the number");
					lab.setBounds(100, 50, 100, 20);
					guess.repaint();
					guess.add(lab);
				}
			}
		}
	}
	
	public boolean checking() {
		boolean checking=true;
		if(Integer.parseInt(field.getText())!=randomizing) checking=false;
		return checking;
	}
	
	public String high_low() {
		if(Integer.parseInt(field.getText()) <randomizing) return "low";
		return "high";
	}
}
