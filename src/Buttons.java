import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Buttons{
	
	private ArrayList<Button> buttons;
	
	public Buttons() {
		buttons = new ArrayList<Button>();
	}

	public Button getButton(String label){
		for(int i=0; i<buttons.size(); i++) {
			if(buttons.get(i).getLabel()==label) {
				return buttons.get(i);
			}
		}
		return buttons.get(0);
	}
	
	public Button getButton(int i) {
		return buttons.get(i);
	}
	
	public void addButton(int x, int y, int width, int height, Color c, String label, int sizefont, String hexcode){
		buttons.add(new Button(x,y,width,height,c,label,sizefont, hexcode));
	}
	
	public void draw(Graphics g){
		for(int i=0; i<buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
	}
	
	public int getSize(){
		return buttons.size();
	}
}
	