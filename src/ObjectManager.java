import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
Player p;
ArrayList<Treasure>treasure=new ArrayList<Treasure>();
Random a=new Random();
int score=0;

int getScore() {
	return score;
}

ObjectManager(Player p){
	this.p=p;
}

void addProjectile(Treasure t) {
	treasure.add(t);
}

void update() {
	for(int i=0; i<treasure.size(); i++) {
		treasure.get(i).update();
	}
	p.update();
	checkCollision();
	purgeObjects();
}

void draw(Graphics g) {
	p.draw(g);
	for(int i=0; i<treasure.size(); i++) {
		treasure.get(i).draw(g);
	}
}

void purgeObjects() {
	for(int i=0; i<treasure.size(); i++) {
		if(treasure.get(i).isActive==false) {
		   treasure.remove(i);
		}
	}
}



void checkCollision() {
	for(int i=0; i<treasure.size(); i++) {
		if(p.collisionBox.intersects(treasure.get(i).collisionBox)) {
			treasure.get(i).isActive=false;
			p.isActive=false;
		}
	}
	
}

}
