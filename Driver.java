import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	// size of jframe
	//int screen_width 	= 1920;
	//int screen_height 	= 1800;
	
	int screen_width 	= 920;
	int screen_height 	= 800;

	// reminder of primitive types
	// setup player variables
	int pr = 30;
	// position variables
	int px = screen_width/2;   // relative to screen_width and player width
	int py = screen_height/2;
	// player velocity variables
	int pvx = 0;
	int pvy = 0;

	// player velocity
	int pv = 15;

	// setup variables for Cells
	int totalEs = 20;
	
	// left side = declare a reference to an array
	int [] eXs = new int [totalEs];
	int [] eYs = new int [totalEs];
	int [] eWs = new int [totalEs];
	Color[]eCs = new Color [totalEs];
	
	//enemy velocities
	int[]eVx = new int[totalEs];// numerical types initialized to zero!!
	int[]eVy = new int[totalEs];
	
	// food variables and arrays
	int totalFood = 1000;
	int [] fx = new int [totalFood];
	int [] fy = new int [totalFood];
	Color[] fc = new Color [totalFood];
	// food size / radius
	int fr = 10;

	
	
	// reading a val from a 1d array
	// System.out.print( x[0]); //reading value
	// x[0] = 5; //writing is similar to regular variables but now you have to specify WHERE
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screen_width, screen_height);
		// paint the player
	
		//g.setColor(new Color(0, 125, 125));


		for (int i = 0; i < eXs.length; i++){
			g.setColor(eCs[i]); // set color for this Cell!!
			g.fillOval(eXs[i], eYs[i], eWs[i], eWs[i]);
		}
		for (int i = 0; i<fx.length; i++){
			g.setColor(fc[i]);
			g.fillOval(fx[i], fy[i], fr, fr);
		}

		g.setColor(Color.CYAN);
		g.fillOval(px, py, pr, pr);

	}// end of paint method - put code above for anything dealing with drawing -
	
	
	
	Font font = new Font ("Courier New", 1, 50);
	public void update() {
		//px += pvx; // x position is affected by velocity x
		//py += pvy; // y position is affected by velocity y
		
		
		//write the loop to visit all elements in the enemy x array
		//update each element so that they increase by the x velocity
		for(int i = 0; i < eXs.length;i++){
			eXs[i] += eVx[i];	
			eYs[i] += eVy[i];	
			
			//enemy x and y now are also altered
			//due to player "velocity"
			// eXs[i] += pvx;
			// eYs[i] +=pvy;
			
			//int x1 = eXs;// food or enemy x and y
			//int y1 = eYs;
			//int x2 = pvx;// player x and y
			//int y2 = pvy;
			//int r1 = ?; //food radius
			//int r2 = ?; //player radius
			//double distance = ;
			//if (distance < r1+r2) {
				//teleport the food to some random location
				
			
			
			
		}
		
		// update food based on player "moving"
        double xDiff =0;
		double yDiff = 0;
        double distSq = 0;

		for (int i = 0; i <fx.length; i++)
		{
			// determine player and food collision/eating
			// use the circle center point for diff calculations
			xDiff = (px + pr/2) - (fx[i] + fr/2);
			yDiff = (py + pr/2) - (fy[i] + fr/2);
			distSq = xDiff * xDiff + yDiff * yDiff;

			if ( distSq < 0.25 * (pr + fr) * (pr + fr))
			{
				System.out.print("player (" + px + "," + py + ") food (" + fx[i] + "," + fy[i] + ") - ");
                System.out.println("distSq= " + distSq + " pr=" + pr);
				// EAT!
				// player gets bigger
				pr += 1;
				// player gets slower
				pv--;
				if (pv < 2) {
					pv = 2;
				}
				// respawn the food
				fx[i] = (int) (Math.random()*(3*screen_width)+(-1*screen_width));
				fy[i] = (int) (Math.random()*(3*screen_height)+(-1*screen_height));
			}

			fx[i] = fx[i] + pvx;
			fy[i] = fy[i] + pvy;
		}








	}// end of update method - put code above for any updates on variable
		
	
	// ==================code above ===========================
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	public Driver(){
		JFrame f = new JFrame();
		f.setTitle("Agario");
		f.setSize(screen_width, screen_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		
		
			
		
		
		//visit every position in our X array
		//generate random numbers
		
		int cntr = 0;
		while (cntr < eXs.length){
			eXs[cntr] = (int)(Math.random()*(screen_width)+0);
			eYs[cntr] = (int)(Math.random()*(screen_height)+0);
			eWs[cntr] = 30;
			eVx[cntr] = (int)(Math.random()*(7+1)+0);
			// add y velocity
			eVy[cntr] = (int)(Math.random()*(7+1)+0);
			cntr++;
		}
		//loops for something i forgot
		int cntr2 = 0;
		int red = 0, green = 0, blue = 0;
		Color newColor;

		while(cntr2< fx.length) {
			fx[cntr2] = (int) (Math.random()*(3*screen_width)+(-1*screen_width));
			fy[cntr2] = (int) (Math.random()*(3*screen_height)+(-1*screen_height));

			red = (int)(Math.random()*(255+1)+0);
			green = (int)(Math.random()*(255+1)+0);
			blue = (int)(Math.random()*(255+1)+0);
			newColor = new Color(red, green, blue);
            fc[cntr2] = newColor;

			cntr2++;
		}
		
		
		//for - loop time
		//for(int yCounter = 0; yCounter < eYs.length; yCounter++){
		//	eYs[yCounter] = (int)(Math.random()*(screen_height)+0);
	    //		eWs[yCounter] = 30;
		//}
		
		
		//colors - are on you! to do - student
		for (int c = 0; c < eCs.length; c++) {
			red = (int)(Math.random()*(255+1)+0);
			green = (int)(Math.random()*(255+1)+0);
			blue = (int)(Math.random()*(255+1)+0);
			newColor = new Color(red, green, blue);
			eCs[c] = newColor;
		}
		

		f.add(this);
		
		
		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 32) {
		    // space button
		    // shrink player
		    pr--;
		    // min player size is 30
			if (pr < 30) {
				pr = 30;
			}
			// player gets faster
			pv++;

			if (pvx > 0) {
				pvx = pv;
			}
			else {
				pvx = -1 * pv;
			}
			if (pvy > 0) {
				pvy = pv;
			}
			else {
				pvy = -1 * pv;
			}


		}

		// velocity is going to be how we want objects to move
		// relative to the player
		if (e.getKeyCode() == 37) {
			System.out.println("left:" + e.getKeyCode());
			pvx = pv;
		}
 		else if(e.getKeyCode() == 38) {
			System.out.println("up:" + e.getKeyCode());
			pvy = pv;

		} else if (e.getKeyCode() == 39) {
			System.out.println("right:" + e.getKeyCode());
			pvx = -1 * pv;
		}
		else if(e.getKeyCode() == 40) {
			System.out.println("down:" + e.getKeyCode());
			pvy = -1 * pv;

		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
			@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 

	
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
        pvx = 0;
        pvy = 0;
		
	}
	@Override
	public void mouseDragged(MouseEvent m) {
		System.out.println("md: " + m.getX()+":" + m.getY());


		System.out.println("px: " + pvx + "py:" + pvy);

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


