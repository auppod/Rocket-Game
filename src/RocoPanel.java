import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.Random;


public class RocoPanel extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH=400;
	static final int SCREEN_HEIGHT=600;
	static final int H_UNITS=20;
	static final int V_UNITS=20;
	static final int TIMER_DELAY=1;
	static final int ROCKET_MOVE_UNITS=10;
	int bgX1 =0;
	int bgY1 =0;
	int bgX2 =0;
	int bgY2 =SCREEN_HEIGHT;
	int meteorX;
	int meteorY;
	int rocketX= (SCREEN_WIDTH/2)-(H_UNITS);
	int rocketY=500;
	Timer timer1;
	Random random;
	boolean running = false;
	char r_move = 'S';
	int score=0;
	int spacer=1;
	
	
	RocoPanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE , 2));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	
	public void startGame() {
		newMeteor();
		running = true;
		timer1 = new Timer(TIMER_DELAY,this);
		timer1.start();
	}

	
	private void newBackGroundImage1() { 
		bgX1 = 0; 
		bgY1 = SCREEN_HEIGHT;
	}
	
	
	private void newBackGroundImage2() { 
		bgX2 = 0; 
		bgY2 = SCREEN_HEIGHT;
	}
	
	
	private void newMeteor() {
		meteorX = (random.nextInt((int)((SCREEN_WIDTH-(20))/H_UNITS)))*H_UNITS;
		meteorY = -V_UNITS;
		score++;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			draw(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	

	private void draw(Graphics g) throws IOException {
		if(running) {
			
		
		//Background image play
		Image backGroundImage1 = ImageIO.read(new File("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\BG11.jpg"));
		g.drawImage(backGroundImage1, bgX1, bgY1, SCREEN_WIDTH, SCREEN_HEIGHT,null);	
		Image backGroundImage2 = ImageIO.read(new File("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\BG22.jpg"));
		g.drawImage(backGroundImage2, bgX2, bgY2, SCREEN_WIDTH, SCREEN_HEIGHT,null);		
		
			
		
		/*	
		//Vertical Reference Lines
		for(int i =0;i< SCREEN_WIDTH/H_UNITS;i++) {
		g.drawLine(((i*H_UNITS)),0,((i*H_UNITS)),(SCREEN_HEIGHT)); }
		
			
		//Horizontal Reference Lines
		for(int i =0;i< SCREEN_HEIGHT/V_UNITS;i++) {
		g.drawLine(0,((i*V_UNITS)),(SCREEN_WIDTH),((i*V_UNITS))); }
		*/
		
			
		/*
		//Meteor Design 1.0
		g.setColor(Color.GRAY);
		g.fillOval(meteorX+2,meteorY,H_UNITS+2,V_UNITS);
		g.fillOval(meteorX+H_UNITS+2,meteorY,H_UNITS+2,V_UNITS);
		g.fillOval(meteorX+(H_UNITS/2)+2,(meteorY-V_UNITS),H_UNITS+2,V_UNITS);
		*/
			
			
		//Meteor Design 2.0
		Graphics2D g2d = (Graphics2D) g;
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Image meteorImage = toolkit.getImage("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\Meteor.gif");
	    g2d.drawImage(meteorImage, meteorX, meteorY, 40, 100, null);
		
	    
		/*
		//Rocket Design 1.0 
		g.setColor(Color.white);
		g.fillRect(rocketX,rocketY,H_UNITS,V_UNITS*2);
		*/	
		
		
		//Rocket Design 2.0
		Image rocketImage = ImageIO.read(new File("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\RocketImage1.png"));
		g.drawImage(rocketImage, rocketX, rocketY, H_UNITS*2, V_UNITS*3,null);
		
		
		//Title & Score Bar
		g.setColor(Color.red);
		g.setFont(new Font("Calibre", Font.BOLD, 25));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("SCORE = " + score ,(SCREEN_WIDTH - metrics.stringWidth("SCORE = " + score+"  ")), 25);
		
		
		}else { 
			gameOver(g);
			playAgain();
		}
		
	}	
	
	
	//Game Over Screen
	public void gameOver(Graphics g) throws IOException {
	
	
	//Background image play
	Image backGroundImage1 = ImageIO.read(new File("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\BG11.jpg"));
	g.drawImage(backGroundImage1, bgX1, bgY1, SCREEN_WIDTH, SCREEN_HEIGHT,null);	
	Image backGroundImage2 = ImageIO.read(new File("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\BG22.jpg"));
	g.drawImage(backGroundImage2, bgX2, bgY2, SCREEN_WIDTH, SCREEN_HEIGHT,null);		
						
					
	g.setColor(Color.red);
	g.setFont(new Font("Calibre", Font.BOLD, 60));
	FontMetrics metrics1 = getFontMetrics(g.getFont());
	g.drawString("GAME OVER",( SCREEN_WIDTH - metrics1.stringWidth("GAME OVER"))/2, (SCREEN_HEIGHT/2)-50);
	
	g.setColor(Color.green);
	g.setFont(new Font("Calibre", Font.BOLD, 50));
	FontMetrics metrics2 = getFontMetrics(g.getFont());
	g.drawString("SCORE = " + score ,( SCREEN_WIDTH - metrics2.stringWidth("SCORE = " + score))/2, (SCREEN_HEIGHT/2)+50);
	
	//Rocket Design 2.0
	Image rocketImage = ImageIO.read(new File("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\RocketImage1.png"));
	g.drawImage(rocketImage, rocketX, rocketY, H_UNITS*2, V_UNITS*3,null);
		
	//Fire Explosion 
	Graphics2D fE = (Graphics2D) g;
	Toolkit toolkit2 = Toolkit.getDefaultToolkit();
	Image fireImage = toolkit2.getImage("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\FireExplosion.gif");
	fE.drawImage(fireImage, rocketX+20-50, rocketY-140, 100, 200, null);
		
	//Meteor Design 2.0
//	Graphics2D mI = (Graphics2D) g;
//	Toolkit toolkit1 = Toolkit.getDefaultToolkit();
//	Image meteorImage = toolkit1.getImage("C:\\Users\\uppod\\eclipse-workspace\\RockGame\\Meteor.gif");
//	mI.drawImage(meteorImage, meteorX, meteorY, 40, 100, null);
	
	}
	
	public void playAgain() {
		
		JButton playAgain = new JButton("PLAY AGAIN");
		playAgain.setFont(new Font("Calibre", Font.BOLD, 25));
		playAgain.setBounds((SCREEN_WIDTH-300)/2, 100, 300, 50);
		add(playAgain);
		playAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disposeWindow();
			}
		});
	
		Cursor cur = new Cursor(Cursor.HAND_CURSOR);
		playAgain.setCursor(cur);
	}

	protected void disposeWindow() {
		// TODO Auto-generated method stub
		new RocoFrame();
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.dispose();
	}


	public void backGroundImagePlay1() {
		bgY1 -= 1; 
		if(bgY1 == -SCREEN_HEIGHT) {
			newBackGroundImage1(); 
		} 
	}
	
	
	public void backGroundImagePlay2() {
		bgY2 -= 1; 
		if(bgY2 == -SCREEN_HEIGHT) {
			newBackGroundImage2(); 
		} 
	}
	
	
	public void move() {
		switch(r_move) {
		case 'L' : 
			if(rocketX !=0) {rocketX -= ROCKET_MOVE_UNITS;}
		break;
		case 'R' : 
			if(rocketX != (SCREEN_WIDTH-(H_UNITS*2))) {rocketX += ROCKET_MOVE_UNITS;}
		break;
		case 'S' : rocketX = rocketX;
		break;
		}
	}


	private void meteorMove() {
		meteorY += V_UNITS*2;
		if(meteorY >= SCREEN_HEIGHT) {
			newMeteor();
		}
	}
	
	
	private void collisionCheck() {
		if(meteorX-20 <= rocketX && rocketX <= meteorX+20 && meteorY+60 == rocketY+V_UNITS) {
			//timer1.stop();
			running=false;
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			meteorMove();
			collisionCheck();
			backGroundImagePlay1();
			backGroundImagePlay2();
		}
		repaint();
	}
	
	
	public void pause() {
		timer1.stop();
	}


	public void resume() {
	    timer1.restart();
	}

	
	public class MyKeyAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				r_move = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				r_move = 'R';
				break;	
			case KeyEvent.VK_SPACE:
				spacer++;
				if(spacer%2==0) {
					pause();
				}else {
					resume();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				r_move = 'S';
				break;
			case KeyEvent.VK_RIGHT:
				r_move = 'S';
				break;				
			}
		}
	}
}
	