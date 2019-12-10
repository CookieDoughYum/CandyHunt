import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
Player p;
TreasureMap TreasureMap;
public boolean checkForTreasure() {
	int playerX=p.getX();
	int playerY=p.getY();
	int i=playerX/p.width;
	int j=playerY/p.height;
	if(TreasureMap.treasureLocations[i][j]==1) {
		
	}
}
Random a=new Random();
int score=0;

int getScore() {
	return score;
}

ObjectManager(Player p){
	this.p=p;
}

void update() {
	p.update();
	checkForTreasure();
}

void draw(Graphics g) {
	p.draw(g);
}

	
}


