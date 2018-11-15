
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Inherits Shape methods and attributes, it also implements Observer to detect collisions with the main character (our circle)
public class Button extends Shape {
	
	private int width; // private to force Encapsulation
	private int height; 
	private	int addX = -10;
	private	int addY = -10;
	private String label;
	private int sizefont;
	private String hexcode;
	
	public Button(int x, int y, int width, int height, Color c, String label, int sizefont, String hexcode){
		super(x, y, 80, 80, c);// super reference to inherit the Shape Constructor
		this.c = Color.blue;
		this.width=width;
		this.height=height;
		this.label=label;
		this.sizefont=sizefont;
		this.hexcode=hexcode;
	}
	
	public void setColor(String hexcode) {
		this.hexcode=hexcode;
	}
	
	public void draw(Graphics g){ // to draw the rectangle and its collision rectangle
		g.setColor(Color.decode(hexcode));
		g.fillRoundRect(x, y, width, height, 15, 15);
	    g.setColor(Color.white);
	    g.setFont(new Font("Pixeled",Font.BOLD, sizefont));
        g.drawString(label, this.x+15+sizefont, this.y+(this.height/2)+10);
	}
	
	
	// checks if mouse is over rounded rectangle
	public boolean contains(double xmouse, double ymouse) {
		if((x<=xmouse && xmouse<=x+width) && (y<=ymouse && ymouse<=y+height)) {
			return true;
		}
		
		return false;
	}

	public int getAddX() {
		return addX;
	}

	public void setAddX(int addX) {
		this.addX = addX;
	}

	public int getAddY() {
		return addY;
	}

	public void setAddY(int addY) {
		this.addY = addY;
	}
	
}


