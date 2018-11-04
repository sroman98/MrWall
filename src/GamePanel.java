/*
 * 14/09/18 - 16/09/18
 * Esta clase implementa Runnable y utiliza un thread para crear
 * un ciclo de animación que utiliza Update() Render() Sleep()
 */
 import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener {
	/**
	 * 
	 */
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
	private Background background;
	private Background foreground;
	private Background middleground;
	private Juanito juanito;
	private Obstaculos obstaculos;

	private SoundLoader soundloader = new SoundLoader("/jean.wav");

	public GamePanel() {
		setBackground(Color.white); //white background
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));

		setFocusable(true);
		requestFocus(); //JPanel now receives keyEvents;

		//we create our objects here
		foreground = new Background(0,0,"img/foreground3.png");
		middleground = new Background(0,0,"img/middleground3.png");
		background = new Background(0,0,"img/background3.png");
		juanito = new Juanito(0, 600, 10, 120, "img/juanito.gif");
		obstaculos = new Obstaculos();

		addKeyListener(this);
	} //End of GamePanel()

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
		            while (isPaused && running) {wait();} 
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
		if(!gameOver) {juanito.move(foreground, middleground, background, obstaculos);}/*if game is not over*/
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
		background.draw(dbg);
		middleground.draw(dbg);
		foreground.draw(dbg);
		juanito.draw(dbg);
		obstaculos.draw(dbg);
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
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {juanito.move(e);}
	@Override
	public void keyReleased(KeyEvent e) {juanito.stop(e);}
}