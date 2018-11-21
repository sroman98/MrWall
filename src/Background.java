/*
 * 16/09/18
 * Esta clase crea un background para ser usado en el GamePanel
 * Utiliza un Image Loader para obtener la imagen del background
 * Tambien tiene método de dibujar y mover para manipular su posición y que se recorra
 * en el GamePanel
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Background {
  private int x;
  private int y;
  private String path;
  private BufferedImage img;

   public Background(int x, int y, String path) {
  	 this.x=x;
  	 this.y=y;
  	 this.setPath(path);
  	 
  	 try {
  		img = ImageIO.read(getClass().getResource(path));
  	 } catch (IOException e) {
  		 e.printStackTrace();
  	 }
   }
   public void setX(int n) {
	  int aux= this.x+n;
	  if((aux<=0) && (aux>=-3240)) {this.x += n;}
   }
   public void setY(int n) {this.y += n;}
   public void setPath(String path) {this.path = path;}
   public int getX() {return x;}
   public int getY() {return y;}
   public String getPath() {return path;}
   public void draw(Graphics g) {g.drawImage(img, x, y, null);}
   
   public void restart() {
	   x=0;
   }
}