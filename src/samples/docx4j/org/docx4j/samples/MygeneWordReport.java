package org.docx4j.samples;

import java.math.BigInteger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTRel;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.toc.TocGenerator;
import org.docx4j.toc.TocHelper;

import org.docx4j.zhou.CreateSummaryTableZhou;
import org.docx4j.zhou.FontChangingStyle;
import org.docx4j.zhou.AddPageBreakZhou;
import org.docx4j.zhou.HeadFooterNumberZhou;

/**
 * Create a docx which says "mygene word report"
 * 
 * @author Meifeng Zhou
 * @since 3.3.0 
 */

public class MygeneWordReport  extends AbstractSample {
	
	public static final String TOC_STYLE_MASK = "TOC%s";
	
	public static void main(String[] args) throws Exception {
		
		boolean landscape = false;
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);
		ObjectFactory factory = Context.getWmlObjectFactory();
		new FontChangingStyle();
		MainDocumentPart wmp = wordMLPackage.getMainDocumentPart();
		
		wmp.addStyledParagraphOfText("Heading1", "检测报告解读说明");
		wmp.addStyledParagraphOfText("Normal","1. 个人信息：检测报告中出现的个人信息为用户在“一脉基因（www.mygene.com）”注册是所填写的信息。");
		wmp.addStyledParagraphOfText("Normal", "2. 位点分析汇总表：对本次检测位点及解读病种的简要说明。");
		wmp.addStyledParagraphOfText("Normal", "3. 疾病风险评估结果：按照疾病所属系统分类，用颜色表示您相关疾病的易感性。其中绿、蓝、橙、红色分别对应该疾病的易感性为正常、低、中、高。其中风险根据用户数据覆盖到的位点进行计算。");
		wmp.addStyledParagraphOfText("Normal", "4. 疾病单项检测结果：列出了您疾病的易感性的检测结果，以及疾病的概述和相关预防保健建议。基因位点的具体检测结果参见电子版报告。");
		wmp.addStyledParagraphOfText("Heading1", "风险提示");
		wmp.addStyledParagraphOfText("Normal","一脉基因仅就基因检测报告的真实性、准确性负责，该报告用于评估个体抗病能力，并非医学诊断措施，而基于基因检测过程及结果所带来的被检测者在生理、心理和生活状态上的或有变化，以及由此可能产生的相应法律后果北京基云惠康科技有限公司不承担责任。");
		wmp.addStyledParagraphOfText("Normal","如果基因检测某疾病的结果为正常风险，则说明您没有与该疾病相关的位点没有突变或者突变起保护作用。低、中风险，意味从您的基因信息检测到您有可能感染该疾病，但并不表示您已经患上疾病，请不必过分担心。更为重要的是仔细阅读个性化健康指导，进行有针对性的健康管理。有必要时，请到医院寻求诊疗帮助。检测结果为高风险，也并不意味着您一定会患病，而是表示您受其他因素影响而患病的可能性比较高，需要注意及早预防和保健。");
		wmp.addStyledParagraphOfText("Normal","对于您的基因数据，限于目前的研究水平，还可能有其它相关的未知基因信息暂时无法解读。因此，您的健康仍可能受到其目前研究不明确的基因的影响。");
		
		
		wmp.addStyledParagraphOfText("Heading1", "疾病抗病能力评估结果");
		//创建疾病评估结果表格
		String imgFilePath = "C://red.png";
		CreateSummaryTableZhou st = new CreateSummaryTableZhou();
		Tbl table = st.createTableWithContent(wordMLPackage, factory,imgFilePath);
		wmp.addObject(table);
		
		
		//分页
		AddPageBreakZhou pb = new AddPageBreakZhou();
		P adb = pb.addPageBreak(factory);
	    wmp.addObject(adb);
	    
	    //TOC
	    TocGenerator tocGenerator = new TocGenerator(wordMLPackage);
	    
	    
	    P cc = wmp.addStyledParagraphOfText("Heading1", "疾病风险部分");
	    Jc jc = factory.createJc();
	    jc.setVal(JcEnumeration.CENTER);
	    cc.getPPr().setJc(jc);
	    
	    wmp.addStyledParagraphOfText("Heading2", "多动症");
	    wmp.addStyledParagraphOfText("Heading3", "风险结论");
	    wmp.addStyledParagraphOfText("Normal", "中等风险");
		
		//分页
	    wmp.addObject(adb);
	    
	    wmp.addStyledParagraphOfText("Heading2", "小动症");
	    wmp.addStyledParagraphOfText("Heading3", "风险结论");
	    wmp.addStyledParagraphOfText("Normal", "中等风险");
	    
	    //tocGenerator.generateToc( 0,    "TOC \\o \"1-2\" \\h \\z \\u ", false);
	    wmp.addObject(adb);
	    
	   
//		ProtectDocument protection = new ProtectDocument(wordMLPackage);
//		protection.restrictEditing(STDocProtect.READ_ONLY, "foobaa");
	    
	    
	    //添加页码及版权信息
	    HeadFooterNumberZhou adtd = new HeadFooterNumberZhou();
	    adtd.addFooterToDocument(wordMLPackage, "Mygene");
	    
	    //生成TOC
	    tocGenerator.generateToc( 0,    "TOC \\o \"1-2\" \\h \\z \\u ", false);
		String filename = System.getProperty("user.dir") + "/mygene.docx";
		Docx4J.save(wordMLPackage, new java.io.File(filename), Docx4J.FLAG_SAVE_ZIP_FILE); 
		System.out.println("Saved " + filename);
		
		
		
	}
	
	
}
