 import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TreasureMap{
	public static BufferedImage mapImage;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int[][] treasureLocations;
	
	public static TreasureMap initializeMap1() {
    TreasureMap t=new TreasureMap();
   int [][] temp=
	   {
                  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                  { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 ,0, 0, 0, 1, 0, 0},
                  { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    		      { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    		      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
    		      { 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    		      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		      { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    		      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    		      { 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1}
    };
    t.treasureLocations=temp;
    try {
		mapImage = ImageIO.read(new TreasureMap().getClass().getResourceAsStream("PirateShip.jpg"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return t;
		// TODO Auto-generated constructor stub
	}
	
	void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	    		g.drawImage(mapImage, 0, 0, CandyHunt.width, CandyHunt.height, null);
	    	}
	}

