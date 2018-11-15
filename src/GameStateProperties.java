
public class GameStateProperties {
	private String stringClase;
	GameStateProperties(){
		stringClase= "GameActiveState";
	}	
	public String getClase(int n) {
		switch(n) {
			case 0:
				stringClase= "GameActiveState";
				return stringClase;
			case 1:
				stringClase= "GamePausedState";
				return stringClase;
			default:
				stringClase= "GameActiveState";
				return stringClase;
		}
	}
}
