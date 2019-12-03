import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed=10;
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	     if (gotImage) {
	    		g.drawImage(image, x, y, width, height, null);
	    	} else {
	    		g.setColor(Color.BLUE);
	    		g.fillRect(x, y, width, height);
	    	}
	}
}
