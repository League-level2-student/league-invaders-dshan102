import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	RocketShip RS;
	ArrayList <Projectile> ps = new ArrayList <Projectile>();
	ArrayList <Alien> as = new ArrayList <Alien>();
	Random rand = new Random();
	public ObjectManager (RocketShip RS) {
		this.RS = RS;
		}
	public void addProjectile(Projectile p1) {
		ps.add(p1);
	}
	public void addAlien() {
		as.add(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));	
	}
	public void update() {
		for(int i=0; i<as.size(); i++) {
			as.get(i).update();
		if(as.get(i).y==LeagueInvaders.HEIGHT) {
			as.get(i).isActive = false;
			}
		}
		for(int i1=0; i1<ps.size();i1++) {
			ps.get(i1).update();
		if(ps.get(i1).y==LeagueInvaders.HEIGHT) {
			ps.get(i1).isActive = false;
		}
		purgeObjects();
		checkCollision();
		}

	}
	public void draw(Graphics g) {
		RS.draw(g);
		for(int i=0; i<as.size(); i++) {
			as.get(i).draw(g);
	}
		for(int i=0; i<ps.size(); i++) {
			ps.get(i).draw(g);
		}
		
	}
	public void purgeObjects() {
		
	}
	public void checkCollision() {
		for(int i=0; i<as.size(); i++) {
			if(RS.collisionBox.intersects(as.get(i).collisionBox)) {
				as.get(i).isActive = false;
				RS.isActive = false;
			}
			else if (ps.get(i).collisionBox.intersects(as.get(i).collisionBox)) {
				as.get(i).isActive = false;
				ps.get(i).isActive = false;
			}

		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
	

