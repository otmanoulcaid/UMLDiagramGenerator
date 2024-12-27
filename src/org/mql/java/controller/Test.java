package org.mql.java.controller;

import java.util.Map;
import java.util.Vector;

import org.mql.java.controller.dataType.DataReflexion;
import org.mql.java.controller.explorer.FileExplorer;

public class Test
{
	public static void main(String[] args)
	{
//		FileExplorer f = new FileExplorer("C:\\\\Users\\\\OULCAID\\\\Desktop\\\\UMLDiagramGenerator\\src");
		FileExplorer f = new FileExplorer(".\\src");
		f.loadPackages();
		Map<String, Vector<DataReflexion>> map = f.getList();
		for(String key : map.keySet())
		{
			System.out.println(key + ":");
			for (DataReflexion type : map.get(key))
				System.out.println("\t" + type.getClassObject());
		}
	}
}
