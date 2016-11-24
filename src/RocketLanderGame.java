
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RocketLanderGame extends JFrame{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
    public static void main(String[] args) {
        JFrame window = new JFrame("Rocket Lander Game");
        window.setContentPane(new RocketLanderPanel());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(0, 0);
        window.setSize(WIDTH,HEIGHT);
        window.setVisible(true);
    }
}
