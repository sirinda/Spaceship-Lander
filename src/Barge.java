import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Barge {
	private int xPos = 100;
	private int yPos = 480;
	private int dx;
	private static int WIDTH = 256/3;
	private static int HEIGHT = 64/3;
	private ImageIcon bargeIcon = new ImageIcon(this.getClass().getResource("/barge.png"));
	//private ImageIcon bargeIcon = new ImageIcon("/barge.png");

	Image bargeImage = bargeIcon.getImage();
	
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public static int getWIDTH() {
		return WIDTH;
	}
	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
	}
	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}
	public void draw(Graphics g) {
		g.drawImage(bargeImage, xPos, yPos, WIDTH, HEIGHT, null);
	}
	
	public void move() {
		if ((xPos <= 0 && dx <= 0) || (xPos + WIDTH >= RocketLanderGame.WIDTH && dx >= 0)) {
			dx = -dx;
		}
		xPos += dx;
	}
	
	public boolean isCollision(Rocket rocket) {
		return (rocket.getyPos() + rocket.getHEIGHT() > yPos && (rocket.getxPos() + rocket.getWIDTH()/4 > xPos && rocket.getxPos() + rocket.getWIDTH()/4 < xPos + WIDTH));
			
	}

}
