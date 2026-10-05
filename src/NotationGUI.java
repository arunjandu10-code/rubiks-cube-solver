import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NotationGUI {

	public static JLabel label;
	public static JFrame frame;
	public static JPanel panel;
	public static String szText = "<html>This is the notation you must know to use this solver:"
			+ "<br/>R = Turn the right side of the rubix cube upwards"
			+ "<br/>L = Turn the left side of the rubix cube downwards"
			+ "<br/>R' = Turn the right side of the rubix cube downwards"
			+ "<br/>L' = Turn the left side of the rubix cube upwards"
			+ "<br/>F = Move the face of the rubix cube facing you to the right"
			+ "<br/>F' = Move the face of the rubix cube facing you to the left"
			+ "<br/>B = Move the face of the rubix cube facing away from you to the left"
			+ "<br/>B' = Move the face of the rubix cube facing away from you to the right</html>";
	
	//utilities
	public static void Build()
	{
		frame = new JFrame() ;
		
		panel = new JPanel() ;
		
		panel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
		panel.setLayout(new GridLayout(0, 1));
		
		label = new JLabel(szText);
		
		panel.add(label) ;
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Rubix Cube Basic Notation");
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {

	}
	
	

}
