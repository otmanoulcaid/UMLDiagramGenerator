package org.mql.java.controller.explorer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;


public class classRepresentation
{
	private List<String> attributes;
	private List<String> methodes;
	private Class<?> cls;

	public classRepresentation(String className) {
		attributes=new Vector<String>();
		methodes=new Vector<String>();
		try {
			this.cls=Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		addAttributes();
		addMethodes();
	}
	
	private void addAttributes()
	{
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields)
				attributes.add(
						getModifier(field.getModifiers())
						+" "+field.getName()
						+":"+field.getType().getSimpleName()
				);		
	}
	
	private void addMethodes()
	{
		Method[] methodes=cls.getDeclaredMethods();
		for (Method methode : methodes)
		{
			String string = getModifier(methode.getModifiers());
			Parameter[] methodeParams = methode.getParameters();
			string += " "+methode.getName()+"(";

			for (int i = 0; i < methodeParams.length - 1; i++)
				string += methodeParams[i].getType().getSimpleName() + ", ";

			if(methodeParams.length > 0)
				string += methodeParams[methodeParams.length - 1].getType().getSimpleName();

			string += ") :" + methode.getReturnType().getSimpleName();
			this.methodes.add(string);
		}
	}

	private String getModifier(int modifier){
			// l'utilisation de 7 ici pour avoir que les 3 premier bit
			// de l'entier qui represente les 3 modificateur d'accèes
			// <PUBLIC, PRIVATE, PROTECTED>
			return (
					switch (modifier & 7)
					{
						case Modifier.PUBLIC -> "+";
						case Modifier.PRIVATE -> "-";
						case Modifier.PROTECTED -> "#";
						default -> "~";
					}
				);
		}

	public List<String> getAttributes() {
		return attributes;
	}

	public List<String> getMethodes() {
		return methodes;
	}

	public static void main(String[] args)
	{
		System.out.println(new classRepresentation("java.lang.String").getMethodes());
	}
}