package org.mql.java.ui;

import java.awt.GridLayout;

import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingContainer;

import org.mql.java.controller.data.DataReflexion;
import org.mql.java.controller.explorer.FileExplorer;

@SwingContainer
public class UMLDiagramGenerator extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel;

//	private void setScrollPane() {
//		scrollPane = new JScrollPane(contentPanel);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		
//		this.add(scrollPane);
//	}

	private JPanel getPackagesPanel(Map<String, Vector<DataReflexion>> map)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 4, 20, 20));
		for (String name : map.keySet())
			panel.add(new PackagePanel(name, map.get(name)));
		return panel;
	}
	
	public UMLDiagramGenerator(String path)
	{
		FileExplorer f = new FileExplorer(path);
		f.loadPackages();

		JPanel panel = getPackagesPanel(f.getPackages());
		JScrollPane scrollPane = new JScrollPane(panel);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scrollPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new UMLDiagramGenerator(".\\src");
	}

}
