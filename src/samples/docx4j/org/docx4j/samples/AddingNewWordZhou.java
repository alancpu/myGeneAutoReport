package org.docx4j.samples;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class AddingNewWordZhou {
	
	public static void main(String[] args) throws Exception {
	
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
	
		wordMLPackage.getMainDocumentPart().addParagraphOfText("Hello Word!"); 
		
		wordMLPackage.getMainDocumentPart().addParagraphOfText("Hello Word 02!"); 
		
		wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "Hello Word!");
		
		wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle","This is a subtitle!"); 
		
		
		wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/AddingNewWordZhou01.docx"));
	
	}
}
