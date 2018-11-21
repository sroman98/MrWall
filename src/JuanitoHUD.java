import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JuanitoHUD {
	private static JuanitoHUD hud;
	
	private int puntos=0;
	private int vidas=5;
	private int nivel=0;
	private Color mycolor;
	private BufferedImage heart;
	private BufferedImage noheart;
	private BufferedImage current;
	
	public JuanitoHUD() {		
		 try {
	  		heart = ImageIO.read(getClass().getResource("/heart.png"));
	  	 } catch (IOException e) {
	  		 e.printStackTrace();
	  	 }
		 
		 try {
	  		noheart = ImageIO.read(getClass().getResource("/invisiblejuanito.png"));
	  	 } catch (IOException e) {
	  		 e.printStackTrace();
	  	 }

	  	current=noheart;
	  	setVisible(true);
	}
	
	
	public static synchronized JuanitoHUD getInstance() {
		if(hud==null) {
			
			return hud = new JuanitoHUD();
		}return hud;
	}
	
	public void update(Juanito j) {
		puntos = j.getPuntaje();
		vidas = j.getVida();
		nivel = j.getNivel();
	} /*getNivel(), getPuntos(), getVidas()*/
		
	public void draw(Graphics g) {
		g.setColor(this.mycolor);
		g.setFont(new Font("Pixeled",Font.BOLD,15));
		g.drawString("Level: "+nivel, 20, 26);
		g.drawString(" "+vidas, 1020, 26);
		
		if(              puntos<10){g.drawString("00000000"+puntos, 460, 30);}
		else if(        puntos<100){g.drawString("0000000"+puntos, 460, 30);}
		else if(      puntos<1_000){g.drawString("000000"+puntos, 460, 30);}
		else if(     puntos<10_000){g.drawString("00000"+puntos, 460, 30);}
		else if(    puntos<100_000){g.drawString("0000"+puntos, 460, 30);}
		else if(  puntos<1_000_000){g.drawString("000"+puntos, 460, 30);}
		else if( puntos<10_000_000){g.drawString("00"+puntos, 460, 30);}
		else if(puntos<100_000_000){g.drawString("0"+puntos, 460, 30);}
		else {                      g.drawString(""+puntos, 460, 30);}
		
		g.drawImage(current, 1000, 7, 20, 20, null);
	}
	
	public void setVisible(boolean visible) {
		if(visible) {
			this.mycolor=Color.WHITE;
			current=heart;
		}
		else {
			int alpha = 0; // 50% transparent
			this.mycolor = new Color(255, 0, 0, alpha);
			current = noheart;
		}
	}
}