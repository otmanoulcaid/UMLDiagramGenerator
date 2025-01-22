package org.mql.java.ui;

import java.util.List;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.mql.java.controller.explorer.ClassRepresentation;

public class ClassPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	int maxWidth;
	
	{		
		maxWidth = 10; //we map each character by 10 pixels
	}
	
	public ClassPanel(Class<?> cls)
	{
		ClassRepresentation  clsDetails = new ClassRepresentation(cls);
		List<String> fields = clsDetails.getAttributes();
		List<String> methods = clsDetails.getMethodes();
		int maxMethod = getMaxLength(methods);
		int maxField = getMaxLength(fields);
		maxWidth *= maxMethod > maxField ? maxMethod : maxField;

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new LineBorder(Color.BLACK));
		
		panel.add(new JLabel(cls.getSimpleName()));
		panel.add(linePanel());

		for (String str : fields)
			panel.add(new JLabel(str));
		
		panel.add(linePanel());
		
		for (String str : methods)
			panel.add(new JLabel(str));

		add(panel);
	}
	
	JPanel linePanel()
	{
		return new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(0, 5, maxWidth , 5);
			}
		};
	}
	
	private int getMaxLength(List<String> strings)
	{
		return strings.stream()
				.mapToInt(String::length) // Map each string to its length
				.max()                    // Find the maximum length
				.orElse(0);         
	}
}