package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.controller.data.DataPersistence;
import org.mql.java.controller.data.DataReflexion;
import org.mql.java.controller.explorer.FileExplorer;

public class UMLDiagramGenerator extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel;
	PathBrowser browser;
	
	{
		browser = new PathBrowser();
		mainPanel = new JPanel();
	}

	private JPanel getPackagesPanel(Map<String, Vector<DataReflexion>> map)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 4, 20, 20));
		for (String name : map.keySet())
			panel.add(new PackagePanel(name, map.get(name)));
		return panel;
	}

	private void handlEvent()
	{
		JPanel render = new JPanel();
		String path = browser.getInput().getText();
		FileExplorer f = new FileExplorer(path);
		try
		{
			f.loadPackages();
			DataPersistence dp = new DataPersistence();
			dp.persistData(f.getPackages());
			render = getPackagesPanel(f.getPackages());
		}
		catch (Exception e)
		{
			render.add(new JLabel(e.getMessage() + " : \'" + path + "\'"));
		}
		
		JScrollPane scrollPane = new JScrollPane(render);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.removeAll();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		// Refresh the panel
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public UMLDiagramGenerator()
	{
        mainPanel.setLayout(new BorderLayout());
        browser.getButton().addActionListener(e->handlEvent());

        add(browser, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(1500, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
