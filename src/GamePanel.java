import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
	Timer frameDraw;
	Timer alienSpawn;
	Player p= new Player(250, 300, 50, 50);
	ObjectManager m = new ObjectManager(p);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
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

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 40);
		enterFont = new Font("Arial", Font.PLAIN, 20);
		spaceFont = new Font("Arial", Font.PLAIN, 20);
		scoreFont = new Font("Arial", Font.PLAIN, 50);
		enemies = new Font("Arial", Font.PLAIN, 20);
		restart = new Font("Arial", Font.PLAIN, 20);
		gameOver= new Font("Arial", Font.PLAIN, 35);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.addActionListener(this);
		frameDraw.start();
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
  
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, CandyHunt.width, CandyHunt.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 50, 100);
		g.setFont(enterFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 175, 350);
		g.setFont(spaceFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 150, 450);
	}

	void drawGameState(Graphics g) {
		//g.drawImage(image, 0, 0, CandyHunt.width, CandyHunt.height, null);
		m.draw(g);
		g.setFont(scoreFont);
		g.drawString(" Score: " + m.getScore(), 30, 43);
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
		repaint();
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
			    m.score=0;
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
			//m.addProjectile(p.getCandy());
			if(m.checkForTreasure()) {
				int hemp=m.getScore();
				m.setScore(++hemp);
			}
			if(m.score==13) {
				currentState=END;	
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
