package org.mql.java.controller.data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Vector;

public class DataReflexion
{
	DataType type = DataType.CLASS;
	private String name;
	private Class<?> cls;
	private Class<?> superCls;
	private Vector<Class<?>> fields;
	private Vector<Class<?>> annotatios;
	private Vector<Class<?>> implementedInterfaces;
	
	{
		fields = new Vector<>();
		annotatios = new Vector<>();
		implementedInterfaces = new Vector<>();
	}
	
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
		superCls = cls.getSuperclass();
		fieldRelation();
		interfacesRelation();
		annotationRealtion();
	}
	
	private void annotationRealtion()
	{
		Annotation [] ans = cls.getAnnotations();
		for (Annotation an : ans)
			annotatios.add(an.getClass());
	}
	
	private void interfacesRelation()
	{
		Class<?> [] interfaces = cls.getInterfaces();
		for (Class<?> iCls : interfaces)
			implementedInterfaces.add(iCls);
	}
	
	private void fieldRelation()
	{
		Field []fs = cls.getDeclaredFields();
		for (Field field : fs)
			if (!field.getType().isPrimitive())
				fields.add(field.getType());
	}
	
	public Class<?> getCls()
	{
		return cls;
	}
	
	public Vector<Class<?>> getFieldClasses()
	{
		return fields;
	}
	
	public Vector<Class<?>> getImplementedInterfaces()
	{
		return implementedInterfaces;
	}
	
	public Vector<Class<?>> getSuperCls()
	{
		Vector<Class<?>> vect = new Vector<>();
		vect.add(superCls);
		return vect;
	}
	
	public Vector<Class<?>> getAnnotations()
	{
		return annotatios;
	}

	public String getName()
	{
		return name;
	}

}
