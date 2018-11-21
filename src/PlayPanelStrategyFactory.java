/*
 * This class is the factory that creates the PlayPanel Strategies
 */

public class PlayPanelStrategyFactory {

	// estructura de datos que albergue los constructores
		private static PlayPanelStrategyFactory instance;

		public static synchronized PlayPanelStrategyFactory getInstance() {//singleton pattern
			if(instance == null) {instance = new PlayPanelStrategyFactory();}
			return instance;
		}										
		public PlayPanelStrategy getPlayPanelState(String tipo, PlayPanelStrategyContext ppsc) {
			PlayPanelStrategy pps= null;
			try {
				pps = (PlayPanelStrategy) Class.forName(tipo).getDeclaredConstructor(PlayPanelStrategyContext.class).newInstance(ppsc); //get declared constructor no es necesario
			}catch (Exception ex) {
				ex.printStackTrace();
			}if(pps==null){
				return new Nivel1(ppsc); /*prevengo que no retorne un null*/
			}else {return pps;  /*js no es null*/}
		}
		
}
