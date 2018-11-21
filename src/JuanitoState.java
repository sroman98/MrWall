/*
 * This class is the interface implemented by the different Juanito States
 */


interface JuanitoState {
		public void shoot();
		public void moveRight();
		public void moveLeft();
		public void moveJump();

		public void stop();

		public void hurt();
		public void heal();
		
		public void die();
}
