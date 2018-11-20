import java.awt.*;
import java.net.*;

public class Sprite {
	  public Image image;
	  public boolean alive;
	  public Point position;
	  public Point velocity;
	  public double rotationRate;
	  public int currentState;
	  public int currentFrame, totalFrames;
	  public int animationDirection;
	  public int frameCount, frameDelay;
	  public int frameWidth, frameHeight, columns;
	  public double moveAngle, faceAngle;

	  public Sprite() {
	    image = null;
	    alive = true;
	    position = new Point(0, 0);
	    velocity = new Point(0, 0);
	    rotationRate = 0.0;
	    currentState = 0;
	    currentFrame = 0;
	    totalFrames = 1;
	    animationDirection = 1;
	    frameCount = 0;
	    frameDelay = 0;
	    frameWidth = 0;
	    frameHeight = 0;
	    columns = 1;
	    moveAngle = 0.0;
	    faceAngle = 0.0;
	  }

	  public void setImage(Image _image) { image = _image; }

	  public int getWidth() {
	    if (image != null)
	      return image.getWidth(null);
	    else
	      return 0;
	  }

	  public int getHeight() {
	    if (image != null)
	      return image.getHeight(null);
	    else
	      return 0;
	  }

	  public double getCenterX() {
	    return position.x + getWidth() / 2;
	  }
	  public double getCenterY() {
	    return position.y + getHeight() / 2;
	  }
	  public Point getCenter() {
	    int x = (int)getCenterX();
	    int y = (int)getCenterY();
	    return(new Point(x,y));
	  }
	  private URL getURL(String filename) {
	    URL url = null;
	    try {
	      url = this.getClass().getResource(filename);
	    }
	    catch (Exception e) { }
	    return url;
	  }

	  public Rectangle getBounds() {
	    return (new Rectangle((int)position.x, (int)position.y, getWidth(), getHeight()));
	  }

	  public void load(String filename, int _columns, int _totalFrames, int _width, int _height) {
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    image = tk.getImage(getURL(filename));
	    while(image.getWidth(null) <= 0);
	    columns = _columns;
	    totalFrames = _totalFrames;
	    frameWidth = _width;
	    frameHeight = _height;
	  }

	  protected void update() {
	    //update position
	    position.x += velocity.x;
	    position.y += velocity.y;

	    //update rotation
	    if (rotationRate > 0.0) {
	      faceAngle += rotationRate;
	      if (faceAngle < 0)
	        faceAngle = 360 - rotationRate;
	      else if (faceAngle > 360)
	        faceAngle = rotationRate;
	    }

	    //update animation
	    if (totalFrames > 1) {
	      frameCount++;
	      if (frameCount > frameDelay) {
	        frameCount = 0;
	        currentFrame += animationDirection;
	        if (currentFrame > totalFrames - 1) {
	          currentFrame = 0;
	        }
	        else if (currentFrame < 0) {
	          currentFrame = totalFrames - 1;
	        }
	      }
	    }
	  }

	  //draw bounding rectangle around sprite
	  public void drawBounds(Graphics g) {
	    g.setColor(Color.WHITE);
	    g.drawRect((int)position.x, (int)position.y, getWidth(), getHeight());
	  }

	  public void draw(Graphics g) {
	    update();
	    //get the current frame
	    int frameX = (currentFrame % columns) * frameWidth;
	    int frameY = (currentFrame / columns) * frameHeight;
	    //draw the frame
	    g.drawImage(image, position.x, position.y, position.x+frameWidth, position.y+frameHeight, frameX, frameY, frameX+frameWidth, frameY+frameHeight, null);
	  }

	  //check for collision with a rectangular shape
	  public boolean collidesWith(Rectangle rect) {
	    return (rect.intersects(getBounds()));
	  }
	  //check for collision with another sprite
	  public boolean collidesWith(Sprite sprite) {
	    return (getBounds().intersects(sprite.getBounds()));
	  }
	  //check for collision with a point
	  public boolean collidesWith(Point point) {
	    return (getBounds().contains(point.x, point.y));
	  }
}
