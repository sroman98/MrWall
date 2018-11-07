import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JuanitoHUD {
	private static JuanitoHUD hud;
	
	private int puntos=0;
	private int vidas=5;
	private int nivel=1;
	
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
		BufferedImage img;
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman",Font.BOLD,18));
		g.drawString("Nivel: "+nivel, 15, 18);
		g.drawString(" "+vidas, 1040, 18);
		
		if(              puntos<10){g.drawString("00000000"+puntos, 500, 18);}
		else if(        puntos<100){g.drawString("0000000"+puntos, 500, 18);}
		else if(      puntos<1_000){g.drawString("000000"+puntos, 500, 18);}
		else if(     puntos<10_000){g.drawString("00000"+puntos, 500, 18);}
		else if(    puntos<100_000){g.drawString("0000"+puntos, 500, 18);}
		else if(  puntos<1_000_000){g.drawString("000"+puntos, 500, 18);}
		else if( puntos<10_000_000){g.drawString("00"+puntos, 500, 18);}
		else if(puntos<100_000_000){g.drawString("0"+puntos, 500, 18);}
		else {                      g.drawString(""+puntos, 500, 18);}
		
		File srcFile = new File("img/heart.png");
	  	try {
	  		img = ImageIO.read(srcFile);
	  		g.drawImage(img, 1020, 2, 20, 20, null);
	  	}catch (IOException e){e.printStackTrace();}
	}
}