
public class Physics implements Runnable {
	private Thread thread;
	private int gravity;
	private long prevTime;
	private long currTime;
	private long dt;
	private long period; //1s/numfps
	
	public Physics(long gamePPeriod, Personaje juan) {
		gravity = -2;
		prevTime = 0;
		currTime = java.lang.System.nanoTime();
		dt = 0;
		period = gamePPeriod;
		
		if (thread==null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	
	//JUMP
	/* posf = posi + dt * v  && v = vi + dt * g */

	public void run() {
		while(true) {
			prevTime = currTime;
			currTime = java.lang.System.nanoTime();
			
			dt = currTime-prevTime;
			if(dt > period)
				dt = period;
		}
		
	}
	
	public void jump(Personaje juan) {
		int y0 = juan.y;
		while(juan.y <= y0) {
			juan.x = (int) (juan.x + dt * juan.velx); 
			juan.y = (int) (juan.y + dt * juan.vely);
			juan.vely = (int) (juan.vely + dt * gravity);
		}
	}

}
