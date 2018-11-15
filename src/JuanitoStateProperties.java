


public class JuanitoStateProperties {
	private String stringClase;
	JuanitoStateProperties(){
		stringClase= "JuanitoStaticState";
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
			default:
				stringClase= "JuanitoStaticState";
				return stringClase;
		}
	}
}
