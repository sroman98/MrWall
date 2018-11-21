/*
 * This class is the factory that creates the Juanito States
 */
public class JuanitoStateFactory {
	// estructura de datos que albergue los constructores
	private static JuanitoStateFactory instance;

	public static synchronized JuanitoStateFactory getInstance() {//singleton pattern
		if(instance == null) {instance = new JuanitoStateFactory();}
		return instance;
	}		// el context no lo debo que pasar as�
	
	public JuanitoState getJuanitoState(String tipo, JuanitoStateContext jsc) {
		JuanitoState js= null;
		try {
			js = (JuanitoState) Class.forName(tipo).getDeclaredConstructor(JuanitoStateContext.class).newInstance(jsc); //get declared constructor no es necesario
		}catch (Exception ex) {
			ex.printStackTrace();
		}if(js==null){
			return new JuanitoStaticState(jsc); /*prevengo que no retorne un null*/
		}else {return js;  /*js no es null*/}
	}
}