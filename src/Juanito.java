
public class Juanito {

	private JuanitoStateContext contextoEstado;
	
	public Juanito() {
		this.contextoEstado=new JuanitoStateContext();
		System.out.println("Creaste un juanito e inicializaste su contexto");
	}
	
	public JuanitoStateContext getContextoEstado() {
		System.out.println("Mandaste a pedir el contexto de juanito");
		return this.contextoEstado;
	}

}
