import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RocketLanderPanel extends JPanel implements ActionListener {
	public static final int DEFAULT_Y_SPEED = 10;
	Rocket rocket = new Rocket(600, 25, 0, DEFAULT_Y_SPEED);
	Barge barge = new Barge();
	private Timer timer;
	Image backgroundImage;
	private boolean gameHasStarted;
	private boolean gameIsLost;
	private boolean gameIsWon;
	private boolean levelIsPassedAndGameIsPaused;
	private ArrayList<Bird> birds = new ArrayList<Bird>();
	private int numBirds = 4;
	private int level = 0;

	public RocketLanderPanel() {
		backgroundImage = new ImageIcon(this.getClass().getResource("/background.png")).getImage();

		this.addKeyListener(new KeyListener());
		this.addMouseListener(new MouseListener());
		setFocusable(true);
		timer = new Timer(60, this);

	}



	public void transition(Graphics g) {

		g.setFont(new Font("SansSerif", Font.BOLD, 20));
		if (level == 0 && !gameHasStarted) {
			g.drawImage(backgroundImage, 0, 0, 800, 600, this);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));

			g.drawString("Welcome to the Rocket Lander game!",
					RocketLanderGame.WIDTH / 2 - 190, 100);
			g.drawString(
					"In this game, you will use the arrow keys to safely land",
					RocketLanderGame.WIDTH / 2 - 300, 130);
			g.drawString(
					"your rocket ship on the barge. This game has 4 levels, each one ",
					RocketLanderGame.WIDTH / 2 - 310, 160);
			g.drawString(
					"harder than the last. Keep an eye on your fuel level",
					RocketLanderGame.WIDTH / 2 - 260, 190);
			g.drawString("since you only have a limited amount!",
					RocketLanderGame.WIDTH / 2 - 250, 220);
			g.drawString("To begin playing, click anywhere.",
					RocketLanderGame.WIDTH / 2 - 190, 310);

		} else if (level == 4 && gameIsWon) {
			gameIsWon = true;
			winningEndOfGame(g);
		} else if (level!= 4){
			g.setFont(new Font("SansSerif", Font.BOLD, 20));
			g.drawString("Completed Level " + level,
					RocketLanderGame.WIDTH / 2 - 190, 100);

		}


	}

	public void replayMenu(Graphics g) {
		// repaint();
		g.setFont(new Font("SansSerif", Font.BOLD, 20));
		g.drawString("Game over. Click to restart at level 0!",
				RocketLanderGame.WIDTH / 2 - 190, 100);

	}
	public void winningEndOfGame(Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.drawString("You won the game!!", RocketLanderGame.WIDTH / 2 - 190,
				100);

	}

	public void paintComponent(Graphics g) {
		if (!gameHasStarted || levelIsPassedAndGameIsPaused) {
			transition(g);
			repaint();
			return;

		}

		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 800, 600, this);
		barge.draw(g);
		rocket.draw(g);
		for (Bird b : birds) {
			b.draw(g);
		}
		g.setFont(new Font("SansSerif", Font.BOLD, 20));

		g.drawString("fuel level:" + rocket.getFuelLevel(), 20, 30);

		if (rocket.getxPos() > RocketLanderGame.WIDTH || rocket.getxPos() < 0
				|| rocket.getyPos() < 0) {
			g.setFont(new Font("SansSerif", Font.BOLD, 20));

			g.drawString("The rocket is out of frame!", 100, 100);
		}
		if (gameIsLost) {
			replayMenu(g);
		}
		if (gameIsWon) {
			winningEndOfGame(g);
		}

	}

	public void reset(int lev) {
		level = lev;

		rocket = new Rocket(600, 25, 0, DEFAULT_Y_SPEED);
		barge = new Barge();
		levelIsPassedAndGameIsPaused = false;
		if (level != 4) {
			birds.clear();
		}
		if (level == 2) {

			barge.setDx(5);
		}
		if (level == 3) {
			barge.setDx(-5);
			;
		}
		if (level == 4) {
			birds = new ArrayList<Bird>();
			for (int i = 0; i < numBirds; i++) {
				int x = (int) (Math.random() * RocketLanderGame.WIDTH);
				int y = (int) (Math.random() * (RocketLanderGame.HEIGHT - 200));
				int dx = (int) (Math.random() * 5) - 10;
				int dy = (int) (Math.random() * 5) - 10;
				birds.add(new Bird(x, y, dx, dy));
			}
		}
		repaint();

	}

	public class MouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
			if (levelIsPassedAndGameIsPaused && level == 4 && !gameIsLost) {
				gameIsWon = true;
				return;
			}
			if (!gameHasStarted || levelIsPassedAndGameIsPaused) {
				gameHasStarted = true;
				reset(level + 1);
				timer.start();
			}
			if (gameIsLost) {
				gameIsLost = false;
				reset(0);
				timer.start();
			}
		}
	}

	public class KeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent evt) {
			int keyCode = evt.getKeyCode();
			if (gameIsLost) return;
			if (keyCode == KeyEvent.VK_LEFT) {
				rocket.setDx(-3);
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				rocket.setDx(3);

			}
			if (keyCode == KeyEvent.VK_UP) {
				rocket.setUpPressed(true);
				if (rocket.getFuelLevel() <= 0) {
					return;
				}


			}

		}

		public void keyReleased(KeyEvent evt) {
			int key = evt.getKeyCode();

			if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
				rocket.setDx(0);

			else if (key == KeyEvent.VK_UP) {
				rocket.setUpPressed(false);
				rocket.setDy(DEFAULT_Y_SPEED);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		rocket.move();
		if (level == 3) {
			double randomDirectionChange = Math.random();
			if (randomDirectionChange >= .975) {
				barge.setDx(-barge.getDx());
			}
		}
		if (!gameIsLost) {
			barge.move();
		}
		for (Bird b : birds) {
			b.move();
		}
		boolean birdCollision = false;
		for (Bird b : birds) {
			if (b.isCollision(rocket)) {
				birdCollision = true;
			}
		}
		boolean collision = barge.isCollision(rocket);
		if (collision) {
			rocket.setDy(0);
			rocket.setyPos(barge.getyPos() - rocket.getHEIGHT());
			levelIsPassedAndGameIsPaused = true;

		}
		if ((rocket.isGameOver() && !collision) || birdCollision) {
			repaint();

			gameIsLost = true;
			return;
		}
		repaint();
	}

}
