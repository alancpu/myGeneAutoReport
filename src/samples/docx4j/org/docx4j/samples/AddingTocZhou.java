package org.docx4j.samples;

import java.io.File;
import java.io.OutputStream;

import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.samples.AbstractSample;
import org.docx4j.toc.TocGenerator;
import org.docx4j.toc.TocHelper;

public class AddingTocZhou {
	
	static String inputfilepath = System.getProperty("user.dir") + "/reportBeforeS.docx";
	static String outputfilepath = System.getProperty("user.dir") + "/OUT_TocAdd01.docx";
	
    public static final String TOC_STYLE_MASK = "TOC%s";
    
    public static void main(String[] args) throws Exception{
    	
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(inputfilepath));

        
        TocGenerator tocGenerator = new TocGenerator(wordMLPackage);
        
        //tocGenerator.generateToc( 0,    "TOC \\h \\z \\t \"comh1,1,comh2,2,comh3,3,comh4,4\" ", true);
        
        //tocGenerator.generateToc( 0,    "TOC \\o \"1-3\" \\h \\z \\u ", true);
        tocGenerator.generateToc(0,"TOC \\o \"1-1\" \\h \\z \\u \\h",true);
        
        //tocGenerator.generateToc( 0, " TOC \\o \"1-3\" \\h \\z \\u ", false);
        
        wordMLPackage.save(new java.io.File(outputfilepath) );
        
        

    }
	
	
}
