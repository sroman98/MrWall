/*
 * This class is "extra" for better handling of the Play Panel Strategies, you can get the Strategy by knowing their name
 * This class is only used internally by the PlayPanelStrategyContext
 */
public class PlayPanelStrategyProperties {
	
	private String stringClase;
	PlayPanelStrategyProperties(){
		stringClase= "Nivel1";
	}	
	public String getClase(int n) {
		switch(n) {
			case 0:
				stringClase = "NivelMenu";
				return stringClase;
			case 1:
				stringClase= "Nivel1";
				return stringClase;
			case 2:
				stringClase= "Nivel2";
				return stringClase;
			case 3:
				stringClase="NivelRules";
				return stringClase;
			default:
				stringClase= "Nivel1";
				return stringClase;
		}
	}

}
