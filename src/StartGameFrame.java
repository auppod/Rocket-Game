
import java.awt.Window;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartGameFrame extends JFrame{
	
	StartGameFrame() {
		StartGamePanel sgp = new StartGamePanel();
		this.add(sgp);
		this.setTitle("RocketGame");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}