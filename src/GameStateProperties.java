/*
 * This class is "extra" for better handling of the Game States, you can get the Game States by knowing their name
 * This class is only used internally by the GameStateContext
 */
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
