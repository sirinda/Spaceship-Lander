import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Rocket {
	private int xPos;
	private int yPos;
	private int dx;
	private int dy;
	private int fuelLevel;
	private double angle;
	boolean gameOver;
	private static int WIDTH = 131 / 2;
	private static int HEIGHT = 132 / 2;
	private boolean upPressed;
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	private ImageIcon rocketWithoutThrustIcon = new ImageIcon(this.getClass().getResource("/rocket.png"));
	//private ImageIcon rocketWithoutThrustIcon = new ImageIcon("/rocketWithoutThrust.png");

	Image rocketWithoutThrustImage = rocketWithoutThrustIcon.getImage();

	private ImageIcon flameIcon = new ImageIcon(this.getClass().getResource("/flame.png"));
	//private ImageIcon flameIcon = new ImageIcon("/flame.png");

	Image flameImage = flameIcon.getImage();
	Image rocketImage;
	Image thrustImage = new ImageIcon(this.getClass().getResource("/thrust.png")).getImage();
	//Image thrustImage = new ImageIcon("/thrust.png").getImage();


	public Rocket(int xPos, int yPos, int dx, int dy) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.dx = dx;
		this.dy = dy;
		this.fuelLevel = 150;

	}

	public int getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

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

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public Image getImage() {
		if (gameOver) {
			return flameImage;
		}
		return rocketWithoutThrustImage;
	}

	public void draw(Graphics g) {

		g.drawImage(getImage(), xPos, yPos, WIDTH, HEIGHT, null);
		if (dy < RocketLanderPanel.DEFAULT_Y_SPEED && dy != 0 && yPos <= 445) {

			g.drawImage(thrustImage, xPos + 1, yPos + 10, WIDTH, HEIGHT, null);
		}
	}

	public void move() {
		if (yPos > 440) {
			yPos = 440;
			dy = 0;
			gameOver = true;
			fuelLevel = 0;
		}
		if (fuelLevel <= 0 && dy < 0) {
			dy = RocketLanderPanel.DEFAULT_Y_SPEED;
			yPos += dy;
			fuelLevel = 0;
			return;
		}
		if (fuelLevel <= 0) {
			dy = RocketLanderPanel.DEFAULT_Y_SPEED;
			yPos += dy;
			fuelLevel = 0;

			return;
		}
		if (gameOver) {
			return;
		}
		xPos += dx;
		if (upPressed) {
			dy--;
			fuelLevel--;
		}
		yPos += dy;

	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
