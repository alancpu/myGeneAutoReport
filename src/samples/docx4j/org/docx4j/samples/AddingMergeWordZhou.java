package org.docx4j.samples;

import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.docx4j.Docx4J;  
import org.docx4j.openpackaging.exceptions.Docx4JException;  
import org.docx4j.openpackaging.exceptions.InvalidFormatException;  
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;  
  
import com.plutext.merge.BlockRange;  
import com.plutext.merge.BlockRange.HfBehaviour;  
import com.plutext.merge.BlockRange.NumberingHandler;  
import com.plutext.merge.BlockRange.SectionBreakBefore;  
import com.plutext.merge.BlockRange.StyleHandler;  
import com.plutext.merge.DocumentBuilder; 

public class AddingMergeWordZhou {

    public static void main(String[] args) throws Docx4JException {  
        // TODO Auto-generated method stub  
        String[] files = { "c:\\tmp\\docs\\2.docx",  
                "c:\\tmp\\docs\\3.docx",  
         "c:\\tmp\\docs\\4.docx",  
         "c:\\tmp\\docs\\5.docx",  
         "c:\\tmp\\docs\\6.docx",  
         "c:\\tmp\\docs\\7.docx"  
        };  
        List<BlockRange> blockRanges = new ArrayList<BlockRange>();  
        for (int i=0 ; i< files.length; i++) {  
            BlockRange block = new BlockRange(WordprocessingMLPackage.load(  
                    new File(files[i])));  
            blockRanges.add(block);  
            block.setStyleHandler(StyleHandler.RENAME_RETAIN);  
            block.setNumberingHandler(NumberingHandler.ADD_NEW_LIST);  
            block.setRestartPageNumbering(false);  
            block.setHeaderBehaviour(HfBehaviour.DEFAULT);  
            block.setFooterBehaviour(HfBehaviour.DEFAULT);  
            block.setSectionBreakBefore(SectionBreakBefore.NEXT_PAGE);  
        }  
          
        // Perform the actual merge  
        DocumentBuilder documentBuilder = new DocumentBuilder();  
        WordprocessingMLPackage output = documentBuilder.buildOpenDocument(blockRanges);  
          
        // Save the result  
        Docx4J.save(output,   
                new File("c:\\tmp\\docs\\x.docx"),   
                Docx4J.FLAG_SAVE_ZIP_FILE);   
  
    }  
	
	
	
}
