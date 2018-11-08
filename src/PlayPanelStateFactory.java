
public class PlayPanelStateFactory {

	// estructura de datos que albergue los constructores
		private static PlayPanelStateFactory instance;

		public static synchronized PlayPanelStateFactory getInstance() {//singleton pattern
			if(instance == null) {instance = new PlayPanelStateFactory();}
			return instance;
		}										
		public PlayPanelState getPlayPanelState(String tipo, PlayPanelStateContext ppsc) {
			PlayPanelState pps= null;
			try {
				pps = (PlayPanelState) Class.forName(tipo).getDeclaredConstructor(PlayPanelStateContext.class).newInstance(ppsc); //get declared constructor no es necesario
			}catch (Exception ex) {
				ex.printStackTrace();
			}if(pps==null){
				return new Nivel1(ppsc); /*prevengo que no retorne un null*/
			}else {return pps;  /*js no es null*/}
		}
		
}
