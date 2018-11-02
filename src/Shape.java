/*
 * 16/09/18
 * Esta clase es la clase padre de la cual se heredan las figuras.
 */

import java.awt.*;

public abstract class Shape {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Color c;
	protected Rectangle rect;

	public abstract void draw(Graphics g);
	public abstract void move();

	public Shape(int x, int y, int width, int height, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.width = width;
		this.height = height;
		rect = new Rectangle(x,y,width,height);
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getRectangle() {
		return rect;
	}

	public boolean collision(Rectangle r) {
		Rectangle rec = this.getRectangle();
		System.out.println(r.getX()+r.getY());
		return rec.contains(r.getX(), r.getY());
	}

}
