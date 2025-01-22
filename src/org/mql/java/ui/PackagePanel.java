package org.mql.java.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.controller.data.DataReflexion;

public class PackagePanel extends JPanel
{
	private static final long	serialVersionUID = 1L;
	List<ClassPanel>			classPanels;

	{
		classPanels = new Vector<>();
	}

	public PackagePanel()
	{}

	public PackagePanel(String name, List<DataReflexion> packageClasses)
	{
		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(
						new LineBorder(Color.blue), 
						" " + name + " : "
						)
				);
		panel.setLayout(new GridLayout(2, 3, 20, 20));

		for (DataReflexion classe : packageClasses)
			panel.add(new ClassPanel(classe.getCls()));

		add(panel);
	}
}
