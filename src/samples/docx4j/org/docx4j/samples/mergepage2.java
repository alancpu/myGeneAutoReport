package org.docx4j.samples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.samples.AbstractSample;

public class mergepage2 {

	static String dataPath = "C://Users//meifeng//Desktop//";
	//final static String BASE_DIR = System.getProperty("user.dir") + "/sample-docs/word/";

	final static String[] sourceDocxNames = { "mygene01.docx", "mygene02.docx"};  

	static boolean save = true;
	static String outputfilepath = dataPath +"OUT_MergeDocx.docx";		
	
	/**
	 * @param args
	 * @throws Docx4JException 
	 */
	public static void main(String[] args) throws Docx4JException {
		
		// Create list of docx packages to merge
		List<WordprocessingMLPackage> wmlPkgList=new ArrayList<WordprocessingMLPackage>();
		for (int i=0; i<sourceDocxNames.length; i++){
			String filename = dataPath + sourceDocxNames[i] ;
			System.out.println("Loading " + filename); 
			wmlPkgList.add(WordprocessingMLPackage
					.load(new java.io.File(filename)));
		}
		
		try {
			// Use reflection, so docx4j can be built
			// by users who don't have the MergeDocx utility
			Class<?> documentBuilder = Class.forName("com.plutext.merge.DocumentBuilder");			
			//Method method = documentBuilder.getMethod("merge", wmlPkgList.getClass());			
			Method[] methods = documentBuilder.getMethods(); 
			Method method = null;
			for (int j=0; j<methods.length; j++) {
				System.out.println(methods[j].getName());
				if (methods[j].getName().equals("merge")) {
					method = methods[j];
					break;
				}
			}			
			if (method==null) throw new NoSuchMethodException();
			
			WordprocessingMLPackage resultPkg = (WordprocessingMLPackage)method.invoke(null, wmlPkgList);

			if (save) {		
				SaveToZipFile saver = new SaveToZipFile(resultPkg);
				saver.save(outputfilepath);
				System.out.println("Generated " + outputfilepath);
			} else {
				String result = XmlUtils.marshaltoString(resultPkg.getMainDocumentPart().getJaxbElement(), true, true);
				System.out.println(result);				
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			extensionMissing(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			extensionMissing(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 

	}
	
	public static void extensionMissing(Exception e) {
		System.out.println("\n" + e.getClass().getName() + ": " + e.getMessage() + "\n");
		System.out.println("* You don't appear to have the MergeDocx paid extension,");
		System.out.println("* which is necessary to merge docx, or process altChunks (of type docx).");
		System.out.println("* Purchases of this extension support the docx4j project.");
		System.out.println("* Please visit www.plutext.com if you want to buy it.");
	}
	
}

