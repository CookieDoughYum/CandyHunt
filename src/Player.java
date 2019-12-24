import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	public static BufferedImage image;
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed=10;
		
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	 public void right() {
	        x+=speed;
	    }
	 public void left() {
	        x+=speed;
	    }
	 public void down() {
	        y+=speed;
	    }
	 public void up() {
	        y+=speed;
	    }
	 
	 
	void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	
	    	//	g.drawImage(image, x, y, width, height, null);
	    	
	    		g.setColor(Color.BLUE);
	    		g.fillRect(x, y, width, height);
	    	}
	
}
