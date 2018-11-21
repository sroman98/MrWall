/*
 * This class is the Game State Context that permits direct communication between the PlayPanel and the Juanito tates
 * You can get the current state of Juanito as well as the States created for him
 */
public class JuanitoStateContext {
	private JuanitoState estados[]= new JuanitoState[6];
	private JuanitoStateProperties propiedades;
	private JuanitoState current;

	public JuanitoStateContext() {
		propiedades = new JuanitoStateProperties();
		estados[0] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(0), this); //JuanitoStaticState
		estados[1] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(1), this); //JuanitoMovingState
		estados[2] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(2), this); //JuanitoInjuredState
		estados[3] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(3), this); //JuanitoDeadState
		estados[4] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(4), this); //JuanitoPausedState
		current = estados[0]; //Initial State: JuanitoStaticState
	}
	public void setCurrent(JuanitoState js) {current= js;}
	public JuanitoState getCurrent() {return current;}
	public JuanitoStaticState getStaticState() {return (JuanitoStaticState) estados[0];}
	public JuanitoMovingState getMovingState() {return (JuanitoMovingState) estados[1];}
	public JuanitoInjuredState getInjuredState() {return (JuanitoInjuredState) estados[2];}
	public JuanitoDeadState getDeadState() {return (JuanitoDeadState) estados[3];}
	public JuanitoPausedState getPausedState() {return (JuanitoPausedState) estados[4];}
}
