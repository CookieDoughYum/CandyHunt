import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font enterFont;
	Font spaceFont;
	Font gameOver;
	Font enemies;
	Font scoreFont;
	Font restart;
	Font hintFont;
	Timer frameDraw;
	Timer alienSpawn;
	JPanel panel = new JPanel();
	JButton button;
	JButton button1;
	JFrame frame;
	Player p = new Player(250, 300, 50, 50);
	ObjectManager m = new ObjectManager(p);
	public static BufferedImage image;
	public static BufferedImage Candy1;
	public static BufferedImage Candy2;
	public static BufferedImage Candy3;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static boolean buttonPanelAdded = false;

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	GamePanel(JFrame frame) {
		this.frame = frame;
		titleFont = new Font("Arial", Font.PLAIN, 40);
		enterFont = new Font("Arial", Font.PLAIN, 20);
		spaceFont = new Font("Arial", Font.PLAIN, 20);
		scoreFont = new Font("Arial", Font.PLAIN, 50);
		hintFont = new Font("Arial", Font.PLAIN, 27);
		enemies = new Font("Arial", Font.PLAIN, 20);
		restart = new Font("Arial", Font.PLAIN, 20);
		gameOver = new Font("Arial", Font.PLAIN, 35);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.addActionListener(this);
		frameDraw.start();
		button = new JButton("Play Again");
		button.addActionListener(this);
		button1 = new JButton("Exit");
		button1.addActionListener(this);
	}

	void updateMenuState() {

	}

	void updateGameState() {
		m.update();
		if (p.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {
		if (currentState == END) {
			if (!buttonPanelAdded) {
			panel.add(button);
			panel.add(button1);
				this.add(panel);
				frame.setSize(CandyHunt.width + 1, CandyHunt.height);
			}
		}
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, CandyHunt.width, CandyHunt.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("WELCOME TO CANDY HUNT", 50, 100);
		g.setFont(enterFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 175, 350);
		g.setFont(spaceFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 150, 450);
	}

	void drawGameState(Graphics g) {
		// g.drawImage(image, 0, 0, CandyHunt.width, CandyHunt.height, null);
		m.draw(g);
		g.setFont(scoreFont);
		g.drawString(" Score: " + m.getScore(), 30, 43);
		g.setFont(hintFont);
		g.drawString("Press h for hint", 575, 475);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, CandyHunt.width, CandyHunt.height);
		g.setFont(gameOver);
		g.setColor(Color.BLACK);
		g.drawString("Congratulations! You found all of the treasure!", 50, 200);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		button.setFocusable(false);
		button1.setFocusable(false);
		repaint();
		if (e.getSource() == button) {
			m.setScore(0);
			p = new Player(250, 300, 50, 50);
			m = new ObjectManager(p);
			 currentState=GAME;
			this.remove(panel);
			m.treasureMap=TreasureMap.GenerateRandomMap();
		}
		if(e.getSource()==button1) {
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				p = new Player(250, 700, 50, 50);
				m = new ObjectManager(p);
				m.score = 0;
				currentState = MENU;
			} else {
				currentState++;
				if (currentState == GAME) {
					startGame();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (p.y >= p.speed) {
				p.y -= 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (p.y <= (CandyHunt.height - p.height) - p.speed) {
				p.y += 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (p.x >= p.speed) {
				p.x -= 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (p.x <= (CandyHunt.width - p.width) - p.speed) {
				p.x += 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// m.addProjectile(p.getCandy());
			if (currentState==GAME && m.checkForTreasure()) {
				int hemp = m.getScore();
				m.setScore(++hemp);
			}
			else if(currentState==MENU) {
				JOptionPane.showMessageDialog(null, "Try to find as many candies on the treasure map as you can. Use the arrow keys to move. Press SPACE to pick up a candy, and press 'h' for a hint. Your goal is to try to find all 13 candies");
			}
			if (m.score == 13) {
				currentState = END;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_H) {
		      giveHint();
		}
		
	}
	void giveHint() {
		int x=0;
		int y=0;
		int[][] currentMap=m.treasureMap.treasureLocations;
		outer:for(int i=0; i<currentMap.length; i++) {
			for(int j=0; j<currentMap[i].length; j++) {
			if(currentMap[i][j]>0) {
				y=i;
				x=j;
				break outer;
			}
			}
		}
		if(x>=0 && x<=6) {
			if(y>=0 && y<=4) {
				JOptionPane.showMessageDialog(null, "Look toward the upper left side of the ship");
			}
			if(y>=5 && y<=10) {
				JOptionPane.showMessageDialog(null, "Look toward the lower left side of the ship");
			}
		}
		if(x>=7 && x<=11) {
			if(y<=10 && y>=5) {
				JOptionPane.showMessageDialog(null, "Look around the steering wheel");
			}
			if(y<=4 && y>=0) {
				JOptionPane.showMessageDialog(null, "Look around the upper middle part of the ship");
			}
		}
		if(x>=12 && x<=16) {
			if(y>=0 && y<=10) {
				JOptionPane.showMessageDialog(null, "Look around the right side of the ship");
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

	void startGame() {

	}

}
