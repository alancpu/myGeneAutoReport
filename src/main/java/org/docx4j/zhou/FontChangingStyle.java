package org.docx4j.zhou;

import java.math.BigInteger;
import java.util.List;

import org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Style;
import org.docx4j.wml.Styles;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class FontChangingStyle {
	
	private static WordprocessingMLPackage  wordMLPackage;
	
    public static void alterStyleSheet() {  
        StyleDefinitionsPart styleDefinitionsPart =  wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart();  
        Styles styles = styleDefinitionsPart.getJaxbElement();  
   
        List<Style>  stylesList = styles.getStyle();  
        for (Style style : stylesList) {  
            if (style.getStyleId().equals("Normal")) {  
                alterNormalStyle(style);  
            } else if (style.getStyleId().equals("Heading2")) {  
                alterHeading2Style(style);  
            } else if (style.getStyleId().equals("Heading1") ||  
                    style.getStyleId().equals("Heading3") ||  
                    style.getStyleId().equals("Title") ||  
                    style.getStyleId().equals("Subtitle")) {  
                	getRunPropertiesAndRemoveThemeInfo(style);  
            }  
        }  
    }  
   
    /** 
     *  First we create a run properties object as we want to remove nearly all of 
     *  the existing styling. Then we change the font and font size and set the 
     *  run properties on the given style. As in previous examples, the font size 
     *  is defined to be in half-point size. 
     */  
    private static void alterNormalStyle(Style style) {  
        // we want to change (or remove) almost all the run properties of the  
        // normal style, so we create a new one.  
        RPr rpr = new RPr();  
        changeFontToArial(rpr);  
        changeFontSize(rpr, 20);  
        style.setRPr(rpr);  
    }  
   
    /** 
     *  For this style, we get the existing run properties from the style and 
     *  remove the theme font information from them. Then we also remove the bold 
     *  styling, change the font size (half-points) and add an underline. 
     */  
    private static void alterHeading2Style(Style style) {  
        RPr rpr = getRunPropertiesAndRemoveThemeInfo(style);  
        removeBoldStyle(rpr);  
        changeFontSize(rpr, 24);  
        addUnderline(rpr);  
    }  
   
    private static RPr getRunPropertiesAndRemoveThemeInfo(Style style) {  
        // We only want to change some settings, so we get the existing run  
        // properties from the style.  
        RPr rpr = style.getRPr();  
        removeThemeFontInformation(rpr);  
        return rpr;  
    }  
   
    /** 
     *  Change the font of the given run properties to Arial. 
     * 
     *  A run font specifies the fonts which shall be used to display the contents 
     *  of the run. Of the four possible types of content, we change the styling of 
     *  two of them: ASCII and High ANSI. 
     *  Finally we add the run font to the run properties. 
     * 
     *  @param runProperties 
     */  
    private static void changeFontToArial(RPr runProperties) {  
        RFonts runFont = new RFonts();  
        runFont.setAscii("Arial");  
        runFont.setHAnsi("Arial");  
        runProperties.setRFonts(runFont);  
    }  
   
    /** 
     * Change the font size of the given run properties to the given value. 
     * 
     * @param runProperties 
     * @param fontSize  Twice the size needed, as it is specified as half-point value 
     */  
    private static void changeFontSize(RPr runProperties, int fontSize) {  
        HpsMeasure size = new HpsMeasure();  
        size.setVal(BigInteger.valueOf(fontSize));  
        runProperties.setSz(size);  
    }  
   
    /** 
     * Removes the theme font information from the run properties. 
     * If this is not removed then the styles based on the normal style won't 
     * inherit the Arial font from the normal style. 
     * 
     * @param runProperties 
     */  
    private static void removeThemeFontInformation(RPr runProperties) {  
        runProperties.getRFonts().setAsciiTheme(null);  
        runProperties.getRFonts().setHAnsiTheme(null);  
    }  
   
    /** 
     * Removes the Bold styling from the run properties. 
     * 
     * @param runProperties 
     */  
    private static void removeBoldStyle(RPr runProperties) {  
        runProperties.getB().setVal(false);  
    }  
   
    /** 
     * Adds a single underline to the run properties. 
     * 
     * @param runProperties 
     */  
    private static void addUnderline(RPr runProperties) {  
        U underline = new U();  
        underline.setVal(UnderlineEnumeration.SINGLE);  
        runProperties.setU(underline );  
    } 
	
}
