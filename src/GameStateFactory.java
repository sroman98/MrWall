
public class GameStateFactory {

	// estructura de datos que albergue los constructores
			private static GameStateFactory instance;

			public static synchronized GameStateFactory getInstance() {//singleton pattern
				if(instance == null) {instance = new GameStateFactory();}
				return instance;
			}										
			public GameState getGameState(String tipo, GameStateContext ppsc) {
				GameState pps= null;
				try {
					pps = (GameState) Class.forName(tipo).getDeclaredConstructor(GameStateContext.class).newInstance(ppsc); //get declared constructor no es necesario
				}catch (Exception ex) {
					ex.printStackTrace();
				}if(pps==null){
					return new GameActiveState(ppsc); /*prevengo que no retorne un null*/
				}else {return pps;  /*js no es null*/}
			}
}
