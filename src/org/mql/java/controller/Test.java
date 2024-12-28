package org.mql.java.controller;

import java.util.Map;
import java.util.Vector;

import org.mql.java.controller.data.DataReflexion;
import org.mql.java.controller.explorer.FileExplorer;

public class Test
{
	private static final String RESET = "\033[0m";
	private static final String RED = "\033[31m";
	private static final String GREEN = "\033[32m";
	private static final String YELLOW = "\033[33m";
	private static final String BLUE = "\033[34m";

	private static void putPackage(Vector<DataReflexion> vect)
	{
		for (DataReflexion type : vect)
		{
			System.out.println("\t" + GREEN + type.getCls() + RESET);
			System.out.println("\t\t superClass : " + type.getSuperCls());
			System.out.println("\t\t annotationsClass : " + type.getAnnotations());
			System.out.println("\t\t fieldsClass : " + type.getFieldClasses());
			System.out.println("\t\t implementedInterfaces : " + type.getImplementedInterfaces());
		}
	}

	public static void main(String[] args)
	{
//		FileExplorer f = new FileExplorer("C:\\\\Users\\\\OULCAID\\\\Desktop\\\\UMLDiagramGenerator\\src");
		FileExplorer f = new FileExplorer(".\\src");
		f.loadPackages();
		Map<String, Vector<DataReflexion>> map = f.getPackages();
		for(String key : map.keySet())
		{
			System.out.println(RED + key + ":" + RESET);
			putPackage(map.get(key));
		}
	}
}
