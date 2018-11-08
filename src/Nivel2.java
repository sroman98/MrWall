
public class Nivel2 implements PlayPanelState {
	PlayPanelStateContext context;

	public Nivel2(PlayPanelStateContext context){
		this.context=context;
	}
	
	public void mytitle(){
		System.out.println("ESTAS JUGANDO EL NIVEL 2");
		
	}
	
	public void myenemies() {
		System.out.println("ENEMIGOS 20");
	}
	
	
}
