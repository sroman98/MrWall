/*
 * This State models what Juanito can and cannot do when he is moving. For example the gifs used are the ones portraying a moving Juanito
 */

public class JuanitoMovingState implements JuanitoState {
	JuanitoStateContext jc;

	public JuanitoMovingState(JuanitoStateContext nc) {
		jc = nc;
	}
	
	public void shoot() {
		Juanito.getInstance().getMychancla().getChanclasprite().totalFrames = 10;
		if(Juanito.getInstance().getPerfilDer()) { 
			Juanito.getInstance().getMychancla().setRight(true);
			Juanito.getInstance().getMychancla().setVelx(50);
			SoundLoader.getInstance().playSound("/throw.wav");
		}
		else {
			Juanito.getInstance().getMychancla().setRight(false);
			Juanito.getInstance().getMychancla().setVelx(-50);
			SoundLoader.getInstance().playSound("/throw.wav");
		}
	}/*no cambia de estado*/
	
	public void stop() {
		if(Juanito.getInstance().getPerfilDer())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJstillder());
		else
			Juanito.getInstance().setMygif(Juanito.getInstance().getJstillizq());
		
		jc.setCurrent(jc.getStaticState());
		Juanito.getInstance().setVelx(0);
	}
	public void hurt() {
		jc.setCurrent(jc.getInjuredState());
		System.out.println("Me hirieron");
	}
	public void heal() {
		System.out.println("No se puede heal en moving!");
	}
	public void die() {
		System.out.println("No se puede die en moving!");
	}	
	@Override
	public void moveRight() {
		if(Juanito.getInstance().getMygif() != Juanito.getInstance().getJmoveder())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveder());
		if(Juanito.getInstance().getVelx() < 3)
			Juanito.getInstance().setVelx(3);
		
		Juanito.getInstance().setPerfilDer(true);
		Juanito.getInstance().setPerfilIzq(false);
	}
	@Override
	public void moveLeft() {
		if(Juanito.getInstance().getMygif() != Juanito.getInstance().getJmoveizq())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveizq());
		if(Juanito.getInstance().getVelx()> -3)
			Juanito.getInstance().setVelx(-3);
		
		Juanito.getInstance().setPerfilDer(false);
		Juanito.getInstance().setPerfilIzq(true);
	}
	@Override
	public void moveJump() {
		if(Juanito.getInstance().getPerfilDer()) //Juanito.getInstance().getVelx() >= 0 && 
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltader());
		else
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltaizq());
		if(Juanito.getInstance().getY()==600 || Juanito.getInstance().isCd()) {
			Juanito.getInstance().setVely(-25);
			SoundLoader.getInstance().playSound("/jump.wav");
		}
		
		if(Juanito.getInstance().getY()==550 || Juanito.getInstance().isCd()) { //==600
			Juanito.getInstance().setVely(-25);
			SoundLoader.getInstance().playSound("/jump.wav");
		}
	}
}