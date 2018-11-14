/*
 * Author: Daniel Elias Becerra
 * This class is a parent abstract class from which Rectangles and possible other figures will extend
 */

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// abstract as it only encapsulates the attributes and methods shared by different types of shapes
public abstract class Shape {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Color c;
	protected Rectangle rect;
	protected RoundRectangle2D roundedRectangle;

	public abstract void draw(Graphics g);

	public Shape(int x, int y, int width, int height, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.width = width;
		this.height = height;
		//RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width, height, 10, 10);
		roundedRectangle = new RoundRectangle2D.Float(100, 100, 240, 160, 10, 10);
		//rect = new Rectangle(x,y,width,height);
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

}
