import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Bird {
	private int xPos;
	private int yPos;
	private int dy;
	private int dx;
	private int WIDTH = 927/20;
	private int HEIGHT = 633/20;
	private Image birdImage = new ImageIcon(this.getClass().getResource("/bird.png")).getImage();
	//private Image birdImage = new ImageIcon("/bird.png").getImage();

	public Bird(int x, int y, int dx, int dy) {
		this.xPos = x;
		this.yPos = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(birdImage, xPos, yPos, WIDTH, HEIGHT, null);
	}
	public void move() {
		if ((xPos <= 0 && dx <= 0) || (xPos + WIDTH >= RocketLanderGame.WIDTH && dx >= 0)) {
			dx = -dx;
		}
		if ((yPos <= 0 && dy <= 0) || (yPos + HEIGHT >= RocketLanderGame.HEIGHT - 200 && dy >= 0)) {
			dy = -dy;
		}
		xPos += dx;
		yPos += dy;
	}
	
	
	public boolean isCollision(Rocket rocket) {
		return (xPos > rocket.getxPos() && xPos < rocket.getxPos() + rocket.getWIDTH() - 10 && yPos > rocket.getyPos() && yPos < rocket.getyPos() + rocket.getHEIGHT() - 10);

	}
}
