import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayPanel extends JPanel implements Runnable, KeyListener, ActionListener {
	
	private PlayPanelStrategyContext playpanelstatecontext;
	private GameStateContext gamestatecontext;
	 
	private static final long serialVersionUID = 1L;
	private static final int NO_DELAYS_PER_YIELD = 16;
	/* Number of frames with a delay of 0 ms before the
	animation thread yields to other running threads. */
	private static int MAX_FRAME_SKIPS = 5;
	// no. of frames that can be skipped in any one animation loop
	// i.e the games state is updated but not rendered
	private static final int PWIDTH = 1080; // largo del panel
	private static final int PHEIGHT = 720; // alto del panel
	
	private Thread animator; // for the animation
	private boolean running = false; // stops the animation

	private boolean gameOver = false; // for game termination
	private boolean isPaused = false;
	
	// global variables for off-screen rendering
	private Graphics dbg;
	private Image dbImage = null;
	
	private Background currentforeground;
	private Background currentmiddleground;
	private Background currentbackground;
	private Background currentstatebackground;
	
	private Obstaculos currentobstaculos;
	//private SoundLoader soundloader;
	private JuanitoHUD hud;
	
	private Button currentstatebutton;
	private Buttons currentbuttons;
	
	private Enemigos currentenemigos;
	
	private Timer t;

	public PlayPanel() {
		setBackground(Color.white); //white background
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
		setFocusable(true);
		requestFocus(); //JPanel now receives keyEvents;
		//we create our objects here
		
		SoundLoader.getInstance();
		
		playpanelstatecontext = new PlayPanelStrategyContext();
		gamestatecontext = new GameStateContext();
		
		currentforeground = playpanelstatecontext.getCurrent().getForeground();
		currentmiddleground = playpanelstatecontext.getCurrent().getMiddleground();
		currentbackground = playpanelstatecontext.getCurrent().getBackground();
		currentstatebackground = gamestatecontext.getCurrent().getBackground();
		
		currentobstaculos = playpanelstatecontext.getCurrent().getObstaculos();
		currentbuttons = playpanelstatecontext.getCurrent().getButtons();
		currentenemigos = playpanelstatecontext.getCurrent().getEnemigos();
		
		hud = JuanitoHUD.getInstance();
		hud.setVisible(false);
		Juanito.getInstance().getJuanitoStateContext().setCurrent(Juanito.getInstance().getJuanitoStateContext().getPausedState());
		addKeyListener(this);
		
		t = new Timer(3000,this);
		
		currentstatebutton = gamestatecontext.getCurrent().getButton();
		Juanito.getInstance().setVisible(false);
		
		addMouseMotionListener( new MouseAdapter(){ 
			public void mouseMoved(MouseEvent e) {
				Point p = new Point(e.getX(),e.getY());
				checkButtonState(p, currentstatebutton);
				checkButtonArrayState(p, currentbuttons);
			}
		});
		
		addMouseListener( new MouseAdapter(){ 
			public void mouseClicked(MouseEvent e) {
				Point p = new Point(e.getX(),e.getY());
				checkGameState(p);
				checkMenuClick(p);
			}
		});
		
	} //End of GamePanel() Constructor
	
	
	public PlayPanelStrategyContext getPlayPanelContextoEstado() {
		return this.playpanelstatecontext;
	}
	
	public void checkLevelChange(){
		if(Juanito.getInstance().getX()>=980 && Juanito.getInstance().getNivel()==1) {
			playpanelstatecontext.setCurrent(playpanelstatecontext.getNivel2());
			playpanelstatecontext.getNivel1().deleteStuff();
			currentforeground = playpanelstatecontext.getCurrent().getForeground();
			currentmiddleground = playpanelstatecontext.getCurrent().getMiddleground();
			currentbackground = playpanelstatecontext.getCurrent().getBackground();
			currentobstaculos = playpanelstatecontext.getCurrent().getObstaculos();
			currentbuttons = playpanelstatecontext.getCurrent().getButtons();
			currentenemigos = playpanelstatecontext.getCurrent().getEnemigos();
			currentbackground.setX(0);
			currentmiddleground.setX(0);
			currentforeground.setX(0);
			Juanito.getInstance().setX(10);
			Juanito.getInstance().setNivel(2);
			playpanelstatecontext.getNivel2().createStuff();
			Juanito.getInstance().getMychancla().chanclaToJuanito();
		}
	}
	
	public void checkButtonState(Point p, Button b) {
		 if(b.contains(p.getX(),p.getY())) {
			 b.setColor("#385fc1");
		 }
		 else {
			 b.setColor("#4372e8");
		 }
	}
	
	public void checkButtonArrayState(Point p, Buttons b) {
		for(int i=0; i<b.getSize();i++) {
			if(b.getButton(i).contains(p.getX(),p.getY())) {
				 b.getButton(i).setColor("#385fc1");
			 }
			 else {
				 b.getButton(i).setColor("#4372e8");
			 }
		} 
	}
	
	public void checkMenuClick(Point p){
		for(int i=0; i<currentbuttons.getSize();i++) {
			if(currentbuttons.getButton(0).contains(p.getX(),p.getY())) {
				playpanelstatecontext.setCurrent(playpanelstatecontext.getNivel1());
				currentforeground = playpanelstatecontext.getCurrent().getForeground();
				currentmiddleground = playpanelstatecontext.getCurrent().getMiddleground();
				currentbackground = playpanelstatecontext.getCurrent().getBackground();
				currentobstaculos = playpanelstatecontext.getCurrent().getObstaculos();
				currentbuttons = playpanelstatecontext.getCurrent().getButtons();
				currentenemigos = playpanelstatecontext.getCurrent().getEnemigos();
				currentbackground.setX(0);
				currentmiddleground.setX(0);
				currentforeground.setX(0);
				Juanito.getInstance().getJuanitoStateContext().setCurrent(Juanito.getInstance().getJuanitoStateContext().getStaticState());
				Juanito.getInstance().setX(10);
				Juanito.getInstance().setNivel(1);
				Juanito.getInstance().setVisible(true);
				Juanito.getInstance().setActive(true);
				hud.setVisible(true);
			}
		}
	}
	
	public void checkGameState(Point p) {
		 if(currentstatebutton.contains(p.getX(),p.getY()) && gamestatecontext.getCurrent()==gamestatecontext.getGameActiveState()) {
			 gamestatecontext.setCurrent(gamestatecontext.getGamePausedState());
			 currentstatebutton = gamestatecontext.getCurrent().getButton();
			 currentstatebackground = gamestatecontext.getCurrent().getBackground();
			 Juanito.getInstance().getJuanitoStateContext().setCurrent(Juanito.getInstance().getJuanitoStateContext().getPausedState());
			 Juanito.getInstance().setActive(false);
		 }
		 
		 else if(currentstatebutton.contains(p.getX(),p.getY()) && gamestatecontext.getCurrent()==gamestatecontext.getGamePausedState()) {
			 gamestatecontext.setCurrent(gamestatecontext.getGameActiveState());
			 currentstatebutton = gamestatecontext.getCurrent().getButton();
			 currentstatebackground = gamestatecontext.getCurrent().getBackground();
			 Juanito.getInstance().getJuanitoStateContext().setCurrent(Juanito.getInstance().getJuanitoStateContext().getStaticState());
			 Juanito.getInstance().setActive(true);
		 }
	}

	public void addNotify() { // waits for the JPanel to be added to the JFrame/JApplet before starting;
		super.addNotify(); //creates the peer
		startGame(); // start the thread
	} //end of addNotify()

	public void startGame() {// initialize and start the thread
		if (animator==null || !running) {
			animator = new Thread(this);
			animator.start();
		}
		SoundLoader.getInstance().startMusic();
	} //end of startGame()
	
	public void stopGame() {/* called by the user to stop execution*/
		running=false;
	} //end of stopGame()

	public void pauseGame() {isPaused = true;} //end of pauseGame()
	public void resumeGame() {isPaused = false;}//end of resumeGame()
	
	public void run() {
	/* Repeatedly update, render, sleep so loop takes close
		to period nsecs. Sleep inaccuracies are handled.
		The timing calculation uses java.lang.System.nanoTime().

		Overruns in update/renders will cause extra updates
    to be carried out so UPS ~== requested FPS
	*/
		long beforeTime, afterTime, timeDiff, sleepTime;
		long period = 1000000000/85; //period = 1000/desiredFPS
		long overSleepTime = 0L;
		int noDelays = 0;
		long excess = 0L;

		beforeTime = java.lang.System.nanoTime();

		running = true;
		while(running) {
			try {
				if (isPaused) { //Pausa el juego
					synchronized(this) {
						while (isPaused && running) {
							wait();
						} 
		           	}
		        }
		    } // of try block
			catch (InterruptedException e){}
			
			gameRender(); // render to buffer
			paintScreen(); // paint with the buffer
			paintScreen(); // paint with the buffer
			gameUpdate(); // game state is updated
			

			afterTime = java.lang.System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period - timeDiff) - overSleepTime; //time left in this loop

			if (sleepTime > 0) {   // some time left in this cycle
				try {
					Thread.sleep(sleepTime/1000000L); // nano -> ms
				}catch(InterruptedException ex){}
				overSleepTime = (java.lang.System.nanoTime() - afterTime) - sleepTime;
			}else {    // sleepTime <= 0; frame took longer than the period
				excess -= sleepTime;  // store excess time value
		        overSleepTime = 0L;
		        if (++noDelays >= NO_DELAYS_PER_YIELD) {
		          Thread.yield();   // give another thread a chance to run
		          noDelays = 0;
		        }
			}beforeTime = java.lang.System.nanoTime();
			/* If frame animation is taking too long, update the game state
			   without rendering it, to get the updates/sec nearer to
			   the required FPS. */
			int skips = 0;

			while((excess > period) && (skips <= MAX_FRAME_SKIPS)) {
		      excess -= period;
		      gameUpdate();
		      skips++;
			} //end of while2
		} //end of while1
		System.exit(0);
	} //end of run

	private void gameUpdate() {
		if(!gameOver) {
			checkLevelChange();
			
			Juanito.getInstance().move(currentforeground, currentmiddleground, currentbackground, currentobstaculos, currentenemigos);
			Juanito.getInstance().jump();
			currentenemigos.move();

			hud.update(Juanito.getInstance());
			
			if(playpanelstatecontext.getCurrent()==playpanelstatecontext.getNivel2()) {
				currentbackground= playpanelstatecontext.getCurrent().getBackground();
				currentmiddleground= playpanelstatecontext.getCurrent().getMiddleground();
				currentforeground= playpanelstatecontext.getCurrent().getForeground();
			}
		}/*if game is not over*/
	}

	private void gameRender(){	// draw the current frame to an image buffer
		if (dbImage == null){  // create the buffer
			dbImage = createImage(PWIDTH, PHEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}else {dbg = dbImage.getGraphics();}
		}
	    // clear the background
	    dbg.setColor(Color.white);
	    dbg.fillRect (0, 0, PWIDTH, PHEIGHT);
	    
	    // draw game elements
		currentbackground.draw(dbg);
		currentmiddleground.draw(dbg);
		currentforeground.draw(dbg);
		
		currentobstaculos.draw(dbg);
		
		hud.draw(dbg);
		
		Juanito.getInstance().draw(dbg);
		
		currentstatebackground.draw(dbg);
		

		if(playpanelstatecontext.getCurrent()==playpanelstatecontext.getNivel2() && playpanelstatecontext.getNivel2().getObstaculos().level2done()) {
			dbg.drawString("YOU WON", 400, 300);
			ImageIcon explosion = createImageIcon("/explosion.gif");
			explosion.paintIcon(null, dbg, 400, 250);
			Juanito.getInstance().setActive(false);
		}
		
		
		
		if(playpanelstatecontext.getCurrent()==playpanelstatecontext.getNivel1() || playpanelstatecontext.getCurrent()==playpanelstatecontext.getNivel2()) {
			currentstatebutton.draw(dbg);
		}
		
		
		currentbuttons.draw(dbg);
		
		currentenemigos.draw(dbg);
		if(currentenemigos.getAtrapado()){
			ImageIcon deportedgif = createImageIcon("/pausedbackground.png");
			deportedgif.paintIcon(null, dbg, 0, 0);
			dbg.drawString("YOU HAVE BEEN DEPORTED!", 340, 300);
			if(!t.isRunning())
				deport();
		}
		
		
	} // end of gameRender()
	
	public void deport() {
		Juanito.getInstance().getJuanitoStateContext().getCurrent().stop();
		Juanito.getInstance().getJuanitoStateContext().setCurrent(Juanito.getInstance().getJuanitoStateContext().getPausedState());
		Juanito.getInstance().setVisible(false);
		playpanelstatecontext.getCurrent().getEnemigos().eliminarTodos();
		playpanelstatecontext.getCurrent().getObstaculos().eliminarTodos();
		t.start();
		currentbackground.restart();
		currentmiddleground.restart();
		currentforeground.restart();
		Juanito.getInstance().setX(10);
		Juanito.getInstance().getMychancla().chanclaToJuanito();
	}
	
	private void paintScreen(){	// actively render the buffer image to the screen
		Graphics g;
		try {
			g = this.getGraphics();  // get the panel’s graphic context
			if ((g != null) && (dbImage != null)) {g.drawImage(dbImage, 0, 0, null);}
			g.dispose();
	    }catch (Exception e){ System.out.println("Graphics context error: " + e);  }
	} // end of paintScreen()
	
	@Override
	public void keyTyped(KeyEvent e){
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().moveRight();
		if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().moveLeft();
		if(e.getExtendedKeyCode() == KeyEvent.VK_UP)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().moveJump();
		if(e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
			Juanito.getInstance().getJuanitoStateContext().getCurrent().shoot();
		}
	    Juanito.getInstance().setPuntaje(Juanito.getInstance().getPuntaje()+1); /*Esto era para sumar 1 pto a cada paso y testear el HUD*/
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT || e.getExtendedKeyCode() == KeyEvent.VK_LEFT)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().stop();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==t) {
			Juanito.getInstance().setPuntaje(Juanito.getInstance().getPuntaje()-500);
			Juanito.getInstance().getJuanitoStateContext().setCurrent(Juanito.getInstance().getJuanitoStateContext().getStaticState());
			Juanito.getInstance().setVisible(true);
			playpanelstatecontext.getCurrent().createStuff();
			currentenemigos.setAtrapado(false);
			t.stop();
		}
		
	}
	
	//JAR adapted imageicon creator
	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
}