package org.mql.java.controller.data;

import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DataPersistence
{

	Document documentBuilder;
	
	public DataPersistence()
	{
		
	}
	
	public void persistData(/*structure to pass*/)
	{
		//persist the dom content into an xml file
	}
	
	private DOMSource loadDomContent()
	{
		//load the data and return the DOMSource object
		return null;
	}
	
	private Element createClassElement(/*class structure to pass*/)
	{
		return null;
	}
	
	private Element createPackageElement(/*package structure to pass*/)
	{
		return null;
	}
	
}
