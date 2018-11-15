import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PlayPanel extends JPanel implements Runnable, KeyListener {
	
	private boolean playnivel1;
	private boolean playnivel2;
	private PlayPanelStateContext playpanelstatecontext;
	
	 
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
	//Our objects
	
	private Background background; //////////////VA EN NIVELES
	private Background foreground; //////////////VA EN NIVELES
	private Background middleground; //////////////VA EN NIVELES
	private Background background3; //////////////VA EN NIVELES
	private Background foreground3; //////////////VA EN NIVELES
	private Background middleground3; //////////////VA EN NIVELES
	private Background currentforeground;
	private Background currentmiddleground;
	private Background currentbackground;
	private Background currentstatebackground;
	private Background stopbackground;
	private Background runbackground;
	
	private Obstaculos obstaculos; //////////////VA EN NIVELES
	private SoundLoader soundloader;
	private JuanitoHUD hud;
	
	private Button pausebutton;
	private Button resumebutton;
	
	private boolean pausedstate;//QUITAR DESPUES

	public PlayPanel() {
		setBackground(Color.white); //white background
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
		setFocusable(true);
		requestFocus(); //JPanel now receives keyEvents;
		//we create our objects here
		
		//createFont("fonts/Pix.ttf");
		
		soundloader=new SoundLoader("/jean.wav");
		
		foreground = new Background(0,0,"img/foreground1.png"); //////////////VA EN NIVELES
		middleground = new Background(0,0,"img/middleground1.png"); //////////////VA EN NIVELES
		background = new Background(0,0,"img/background1.png"); //////////////VA EN NIVELES
		
		foreground3 = new Background(0,0,"img/foreground3.png"); //////////////VA EN NIVELES
		middleground3 = new Background(0,0,"img/middleground3.png"); //////////////VA EN NIVELES
		background3 = new Background(0,0,"img/background3.png"); //////////////VA EN NIVELES
		
		stopbackground = new Background(0,0, "img/pausedbackground.png");
		runbackground = new Background(0,0, "img/runningbackground.png");
		
		currentforeground = foreground;
		currentmiddleground = middleground;
		currentbackground = background;
		currentstatebackground = runbackground;
		
		
		obstaculos = new Obstaculos(); //////////////VA EN NIVELES
		hud = JuanitoHUD.getInstance();
		addKeyListener(this);
		
		/////////////////////////
		playnivel1=false;
		playnivel2=false;
		playpanelstatecontext= new PlayPanelStateContext();
		System.out.println("CREASTE UN PLAY PANEL E INICIALIZASTE SU CONTEXTO");
		
		pausebutton = new Button(200,5,140,40,Color.CYAN, "pause",15, "#4372e8");
		resumebutton = new Button(460,350,160,40,Color.CYAN, "resume",15, "#4372e8");
		pausedstate=false; //agregar luego este estado;
		 
		////////////////////////////////////////////////////////////////
		//MOUSE LISTENERS - NEEDS REFACTOR CHANGE LOCATION;
		this.addMouseMotionListener( new MouseAdapter(){ 
			public void mouseMoved(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				
				 if(pausebutton.contains(x, y) || resumebutton.contains(x, y)) {
					 pausebutton.setColor("#385fc1");
					 resumebutton.setColor("#385fc1");

				 }
				 else {
					 pausebutton.setColor("#4372e8");
					 resumebutton.setColor("#4372e8");
				 }
				
			}
		});
		
		this.addMouseListener( new MouseAdapter(){ 
			public void mouseClicked(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				 if(pausebutton.contains(x,y) ) {
					 currentstatebackground=stopbackground;
					 pausebutton.setColor("#213a7a");
					 pausedstate=true;
					 resumebutton.setColor("#4372e8");
					 
				 }
				 if(resumebutton.contains(x,y) ) {
					 currentstatebackground=runbackground;
					 resumebutton.setColor("#213a7a");
					 pausedstate=false;
					 pausebutton.setColor("#4372e8");
				 }
				
			
			}
			
		});
		///////////////////////////////////////////////////////////////////

	} //End of GamePanel()
	
	public PlayPanelStateContext getPlayPanelContextoEstado() {
		return this.playpanelstatecontext;
	}
	
	public void checkLevelChange(){
		if(Juanito.getInstance().getX()>=980 && Juanito.getInstance().getNivel()==1) {
			playpanelstatecontext.setCurrent(playpanelstatecontext.getNivel2());
			currentbackground.setX(0);
			currentmiddleground.setX(0);
			currentforeground.setX(0);
			Juanito.getInstance().setX(100);
			Juanito.getInstance().setNivel(2);
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
		}soundloader.startMusic();
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
				if (isPaused) {
					synchronized(this) {
						while (isPaused && running) {
							wait();
						} 
		           	}
		        }
		    } // of try block
			catch (InterruptedException e){}

			gameUpdate(); // game state is updated
			gameRender(); // render to buffer
			paintScreen(); // paint with the buffer
			paintScreen(); // paint with the buffer

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
			
			Juanito.getInstance().move(currentforeground, currentmiddleground, currentbackground, obstaculos);
			Juanito.getInstance().jump();

			hud.update(Juanito.getInstance());
			if(playpanelstatecontext.getCurrent()==playpanelstatecontext.getNivel2()) {
				currentbackground=background3;
				currentmiddleground=middleground3;
				currentforeground=foreground3;
			}
		}/*if game is not over*/
		//System.out.println(isPaused);
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
		
		Juanito.getInstance().draw(dbg);
		
		obstaculos.draw(dbg);
		hud.draw(dbg);
		
		
		if(pausedstate){
			currentstatebackground.draw(dbg);
			resumebutton.draw(dbg); 
		}
		
		else {
			pausebutton.draw(dbg);
		}
		
		
	} // end of gameRender()
	
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
		if(e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
			Juanito.getInstance().getJuanitoStateContext().getCurrent().moveJump();
		}	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().moveRight();
		if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().moveLeft();

	    Juanito.getInstance().setPuntaje(100); /*Esto era para sumar 1 pto a cada paso y testear el HUD*/
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT || e.getExtendedKeyCode() == KeyEvent.VK_LEFT)
			Juanito.getInstance().getJuanitoStateContext().getCurrent().stop();
	}
	
	//setters & getters
	public boolean isPlaynivel1() {
		return playnivel1;
	}

	public void setPlaynivel1(boolean playnivel1) {
		this.playnivel1 = playnivel1;
	}

	public boolean isPlaynivel2() {
		return playnivel2;
	}

	public void setPlaynivel2(boolean playnivel2) {
		this.playnivel2 = playnivel2;
	}	

}