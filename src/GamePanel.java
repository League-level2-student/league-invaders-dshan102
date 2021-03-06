import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	@Override	
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font titleFont1;
	Timer frameDraw;
	Timer alienSpawn;
	RocketShip RS = new RocketShip(250,500,50,50);
	ObjectManager OM = new ObjectManager(RS);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public GamePanel () {
	    titleFont = new Font("Arial", Font.PLAIN, 48);
	    titleFont1 = new Font("Arial", Font.PLAIN, 24);
	    frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	    alienSpawn = new Timer(1000 , OM);
	    if (needImage) {
	        loadImage ("space.png");
	    }
	}
    public void updateMenuState() {  
    	
    }
    public void updateGameState() { 
    if (RS.isActive == false) {
    currentState = END;
    }
    OM.update();
  
    }
    public void updateEndState()  { 
    	
    }
    public void drawMenuState(Graphics g) { 
    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("League Invaders", 60, 100);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press ENTER to start", 120, 300);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press SPACE for instructions", 80, 425);
    	
    }
    public void drawGameState(Graphics g) {
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	if (gotImage) {
    		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
    	} else {
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	}
    	
    	OM.draw(g);
	    g.drawString(OM.getScore() + "", 50, 50);

    }
    public void drawEndState(Graphics g)  { 
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	g.setFont(titleFont);
    	g.setColor(Color.YELLOW);
    	g.drawString("GAME OVER", 60, 100);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("You killed " + OM.getScore() + " enemie(s)", 120, 300);
    	g.setFont(titleFont1);
    	g.setFont(titleFont1);
    	g.setColor(Color.YELLOW);
    	g.drawString("Press ENTER to restart", 110, 425);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        RS = new RocketShip(250,500,50,50);
		        OM = new ObjectManager(RS);
		        alienSpawn = new Timer(1000 , OM);
		        
		    } 
		    else {
		        currentState++;
		    }
		    if(currentState == GAME) {
			    alienSpawn.start();
		    }
		    if(currentState == END) {
		    	alienSpawn.stop();
		    }
		} 
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			if (currentState == GAME) {
				RS.up();
			    System.out.println("UP");
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			if (currentState == GAME) {
				RS.down();
			    System.out.println("DOWN");
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if (currentState == GAME) {
				RS.left();
			    System.out.println("LEFT");
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if (currentState == GAME) {
				RS.right();
			    System.out.println("RIGHT");
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentState == GAME) {
				OM.addProjectile(RS.getProjectile());
			    System.out.println("SHOOT");
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
}
