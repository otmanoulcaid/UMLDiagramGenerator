package org.mql.java.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PathBrowser extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextField input;
	private JButton button;
	{
		input = new JTextField(30);
		button = new JButton("Denerate Diagram");		
	}
	
	public PathBrowser() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JLabel("package : "));
		panel.add(input);
		panel.add(button);
		add(panel);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
	}

	public JTextField getInput() {
		return input;
	}

	public JButton getButton() {
		return button;
	}
	
	
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.add(new PathBrowser());
//		frame.pack();
//		frame.setVisible(true);
//	}
}
