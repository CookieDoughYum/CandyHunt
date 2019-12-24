import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
    
public class ObjectManager {
Player p;
TreasureMap treasureMap=TreasureMap.initializeMap1();
public boolean checkForTreasure() {
	int playerX=p.getX()+(p.width/2);
	int playerY=p.getY()+(p.height/2);
	int i=playerY/p.height;
	int j=playerX/p.width;
	//System.out.println(i+","+j);
	//System.out.println(treasureMap.treasureLocations[i][j]);
     if(treasureMap.treasureLocations[i][j]==1) {
		return true;
	}
	else {
		return false;
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
	treasureMap.draw(g);
	for(int i=0; i<16; i++) {
		for(int j=0; j<10; j++) {
			if(treasureMap.treasureLocations[j][i]==1) {
				g.setColor(Color.red);
				g.fillRect(i*p.width, j*p.height, p.width, p.height);
			}
			g.setColor(Color.blue);
			g.drawRect(i*p.width, j*p.height, p.width, p.height);
		}
	}
	p.draw(g);
}

	
}


