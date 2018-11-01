
public class JuanitoStateFactory {
	private static JuanitoStateFactory instance;

	public static synchronized JuanitoStateFactory getInstance() {
		if(instance == null) {
			instance = new JuanitoStateFactory();
		}
		return instance;
	}

	public JuanitoState getJuanitoState(String tipo, JuanitoStateContext jsc) {
		JuanitoState js = null;

		try {
			js = (JuanitoState) Class.forName(tipo).getDeclaredConstructor(JuanitoStateContext.class).newInstance(jsc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return js;
	}
}
