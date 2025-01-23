package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.controller.data.DataReflexion;

public class PackagePanel extends JPanel
{
	private static final long	serialVersionUID = 1L;

	public PackagePanel()
	{}

	public PackagePanel(String name, List<DataReflexion> packageClasses)
	{
		JPanel panel = new JPanel();

		panel.setBorder(new TitledBorder(
				new LineBorder(Color.blue), 
				" " + name + " : "
				)
			);

		panel.setLayout(new GridLayout(2, 3, 20, 20));

		for (DataReflexion classe : packageClasses)
			panel.add(new ClassPanel(classe.getCls()));

		Dimension d = panel.getPreferredSize();
		panel.setPreferredSize(new Dimension(d.width + 50, d.height + 50));
		add(panel);
	}
}
