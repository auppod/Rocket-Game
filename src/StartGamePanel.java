import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class StartGamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 400; 
	static final int SCREEN_HEIGHT = 600; 
	
	StartGamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE , 2));
		this.setFocusable(true);
		this.buttonProperties();
	}
	
	public void buttonProperties() {
		
		JButton button = new JButton("START GAME");
		button.setFont(new Font("Calibre", Font.BOLD, 25));
		button.setBounds(50,100, 300, 100);
		add(button);
		button.addActionListener(this);
		
		Cursor cur = new Cursor(Cursor.HAND_CURSOR);
		button.setCursor(cur);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new RocoFrame();
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.dispose();
	}

}
