package org.mql.java.controller.explorer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.mql.java.controller.dataType.DataReflexion;

/*
 * Cette classe est utile pour vérifier l'existence des fichiers tests.
 * Tant qu'un fichier test existe dans le projet, cette classe retournera automatique toutes les classes 
 * test disponibles.
 * 
 */

public class FileExplorer
{
	private String packageName;
    private String Path;
    private static Map<String, Vector<DataReflexion>> files = new HashMap<>();
    
    public FileExplorer(String path)
    {
    	this.packageName = "";
        this.Path = path;
    }

    private FileExplorer (String path, String packageName)
    {
    	this.packageName = packageName;
        this.Path = path;
    }

    public void loadPackages ()
    {
    	try
    	{
	        File f = new File(Path);
	        if (!f.exists()) return ;
	        File[] fileList = f.listFiles();
	        if (fileList == null) return ;
	        for (File file : fileList) {
		            if (file.getName().endsWith(".java"))
		            {
		            	String className = file.getName().replace(".java", "");
		            	String key = packageName.substring(0, packageName.length() - 1);
		            	if (!files.containsKey(key))
		            		files.put(key, new Vector<DataReflexion>());
	            		files.get(key).add(new DataReflexion(packageName + className));
		            }
		            else
		            {
		            	String pack = packageName + file.getName()+".";
		            	new FileExplorer(file.getPath(), pack).loadPackages ();
		            }
	    	}
    	} catch (Exception e) {
    		System.out.println("exception : " +e.getMessage());
    	}
    }
    
    public Map<String, Vector<DataReflexion>> getList()
    {
    	return files;
    }
}
