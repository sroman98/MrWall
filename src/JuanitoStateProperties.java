/*
 * This class is "extra" for better handling of the Juanito States, you can get the Juanito States by knowing their name
 * This class is only used internally by the JuanitoStateContext
 */

public class JuanitoStateProperties {
	private String stringClase;
	JuanitoStateProperties(){
		stringClase= "JuanitoPausedState";
	}	
	
	public String getClase(int n) {
		switch(n) {
			case 0:
				stringClase= "JuanitoStaticState";
				return stringClase;
			case 1:
				stringClase= "JuanitoMovingState";
				return stringClase;
			case 2:
				stringClase= "JuanitoInjuredState";
				return stringClase;
			case 3:
				stringClase= "JuanitoDeadState";
				return stringClase;
			case 4:
				stringClase= "JuanitoPausedState";
				return stringClase;
			default:
				stringClase= "JuanitoStaticState";
				return stringClase;
		}
	}
}
