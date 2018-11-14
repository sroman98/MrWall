/*
 * 14/09/18
 * Main que empieza el juego
 */

import javax.swing.*;

public class Main {
	public static void main(String args[]) {
                JFrame frame = new JFrame();
                PlayPanel p = new PlayPanel();
                frame.setSize(1080, 710);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
                frame.add(p);
                frame.setVisible(true);
    }
}