package org.docx4j.samples;

import java.io.File;

import org.docx4j.wml.ObjectFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

public class PageBreakExampleZhou {
	
    private static ObjectFactory objectFactory = new ObjectFactory();

    public static void main(String[] args) throws InvalidFormatException,
        Docx4JException {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        // create new paragraph with a run containing text and add it to the document.
        org.docx4j.wml.P  paragraph1 = objectFactory.createP(); // create new paragraph
        org.docx4j.wml.R  run1 = objectFactory.createR(); // create new run 
        org.docx4j.wml.Text text1 = objectFactory.createText(); // create text

        text1.setValue("This is text in paragraph 1");
        run1.getContent().add(text1); // add text ton the run
        paragraph1.getContent().add(run1); // add run to paragraph
        wordMLPackage.getMainDocumentPart().addObject(paragraph1);

        addPageBreak(wordMLPackage.getMainDocumentPart());

        // proceed to create another paragraph with a run containing text.
        org.docx4j.wml.P paragraph2 = objectFactory.createP(); // create new paragraph
        org.docx4j.wml.R run2 = objectFactory.createR(); // create new run 
        org.docx4j.wml.Text text2 = objectFactory.createText(); // create text

        text2.setValue("This is text in paragraph 2");
        run2.getContent().add(text2); // add text ton the run
        paragraph2.getContent().add(run2); // add run to paragraph
        wordMLPackage.getMainDocumentPart().addObject(paragraph2); // add to main document part


        wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/two_paragraphs_page_break.docx")); // save
    }

    private static void addPageBreak(MainDocumentPart documentPart) {
    	org.docx4j.wml.P paragraph = objectFactory.createP();
    	org.docx4j.wml.R run = objectFactory.createR();
    	org.docx4j.wml.P p = objectFactory.createP();
        // Create object for r
    	org.docx4j.wml.R r = objectFactory.createR();
        p.getContent().add(r);
        // Create object for br
        org.docx4j.wml.Br br = objectFactory.createBr();
        r.getContent().add(br);
        br.setType(org.docx4j.wml.STBrType.PAGE);
        documentPart.addObject(p);
    }

}
