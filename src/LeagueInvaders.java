import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
JFrame frame;
GamePanel GP;
public static final int WIDTH = 500;
public static final int HEIGHT = 800;
public static void main(String[] args) {
	LeagueInvaders LI = new LeagueInvaders();
	LI.setup();
}

public void setup() {
	frame = new JFrame();
	GP = new GamePanel();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(WIDTH, HEIGHT);
	frame.add(GP);
	frame.addKeyListener(GP);
	frame.setVisible(true);
}

}


