package org.docx4j.zhou;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;

public class AddPageBreakZhou {

	public P addPageBreak(ObjectFactory factory) {
    	
		org.docx4j.wml.P paragraph = factory.createP();
    	org.docx4j.wml.R run = factory.createR();
    	org.docx4j.wml.P p = factory.createP();
        // Create object for r
    	org.docx4j.wml.R r = factory.createR();
        p.getContent().add(r);
        // Create object for br
        org.docx4j.wml.Br br = factory.createBr();
        r.getContent().add(br);
        br.setType(org.docx4j.wml.STBrType.PAGE);
        return p;
    }
	
}
