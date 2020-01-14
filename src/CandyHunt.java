import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CandyHunt{
	GamePanel gp;
	JFrame frame;
	public static final int width=803;
	public static final int height=523;
public static void main(String[] args) {
	CandyHunt ch=new CandyHunt();
	ch.setup();
}
CandyHunt(){
	this.frame=new JFrame();
	this.gp=new GamePanel(frame);
}
void setup() {
	frame.add(gp);
	frame.setSize(width, height);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.addKeyListener(gp);
}
}

