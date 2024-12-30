package org.mql.java.controller.data;

import java.io.File;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DataPersistence
{

	Document document;
	
	public DataPersistence()
	{
		document = getDocumentBuilder();
	}

	public void persistData(Map<String, Vector<DataReflexion>> map)
	{
		Element root = document.createElement("packages");
        for (String key : map.keySet())
        	root.appendChild(createPackageElement(key, map.get(key)));
        document.appendChild(root);
        
        
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("ressources/data.xml"));

        try
        {
        	Transformer transformer = getTransformer();
        	transformer.transform(domSource, streamResult);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	private Document getDocumentBuilder()
	{
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newDefaultInstance();
			return docFactory.newDocumentBuilder().newDocument();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Transformer getTransformer() throws Exception
	{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			return transformer;
	}
	
	private Element getChilds(Vector<Class<?>> vector, String relation)
	{
		Element element = document.createElement(relation);
		for (Class<?> cls : vector)
		{
			Element type = document.createElement("type");
			type.appendChild(document.createTextNode(cls.getName()));
			element.appendChild(type);
		}
		
		return element;
	}
	
	private Element createClassElement(DataReflexion df)
	{
		Element classElement = document.createElement("class");
		classElement.setAttribute("name", df.getName());

		Vector<Class<?>> extention = df.getImplementedInterfaces();
		extention.addAll(df.getSuperCls());
		classElement.appendChild(getChilds(extention, "extention"));

		classElement.appendChild(getChilds(df.getFieldClasses(), "agregation"));
		classElement.appendChild(getChilds(df.getAnnotations(), "utilisation"));

		return classElement;
	}

	private Element createPackageElement(String packName, Vector<DataReflexion> classes)
	{
		Element packageTag = document.createElement("package");
		packageTag.setAttribute("name", packName);

		for (DataReflexion cls : classes)
			packageTag.appendChild(createClassElement(cls));

		return packageTag;
	}
}
