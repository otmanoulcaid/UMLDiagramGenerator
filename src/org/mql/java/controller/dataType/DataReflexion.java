package org.mql.java.controller.dataType;

public class DataReflexion
{
	DataType type = DataType.CLASS;
	private String name;
	private Class<?> cls;
	
	@SuppressWarnings("unused")
	private DataReflexion()
	{
		
	}
	
	public DataReflexion(String filePath)
	{
		name = filePath;
		try
		{
			cls = Class.forName(filePath);
		}
		catch(Exception e)
		{
			cls = null;
		}
	}
	
	public Class<?> getClassObject()
	{
		return cls;
	}

	public String getName() {
		return name;
	}

}
