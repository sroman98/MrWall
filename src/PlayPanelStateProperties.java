
public class PlayPanelStateProperties {
	
	private String stringClase;
	PlayPanelStateProperties(){
		stringClase= "Nivel1";
	}	
	public String getClase(int n) {
		switch(n) {
			case 0:
				stringClase= "Nivel1";
				return stringClase;
			case 1:
				stringClase= "Nivel2";
				return stringClase;
			default:
				stringClase= "Nivel1";
				return stringClase;
		}
	}

}
