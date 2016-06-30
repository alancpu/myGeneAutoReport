package org.docx4j.samples;

import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.docx4j.zhou.DBHelper;
import org.docx4j.zhou.StringReverse;
import org.docx4j.zhou.removeDuplicateFromArrayList; 


public class MysqlWordZhou {

        static String sql = null;
        static DBHelper db1 = null;  
        static ResultSet ret = null;  
    	static String table = null;
    	static String clumName = null;
    	static String[] locDir = null;
		private static String genotypeReverse;
		private static Integer subKey;
        /**
         * @param args
         * @throws SQLException 
         */
        
        public static void main(String[] args) throws SQLException {  
      
        	getRepDetails("CCTV","talent","sdfsdfs");
        	
        	/*
        	//sql = "show tables;";//SQL语句  
            sql = "SELECT a.probability,b.dchname,b.denname,b.dsystem,a.risk_level,b.ddesc FROM hd_user_rep_disease as a,hd_com_disease as b WHERE sid = 'Q134936_chenyaqinnv'  AND a.denname=b.denname ORDER BY a.probability desc";
        	db1 = new DBHelper(sql);//创建DBHelper对象  
      
            try {  
                ret = db1.pst.executeQuery();//执行语句，得到结果集  
                while (ret.next()) {
                    String uid = ret.getString(3);
                    System.out.println(uid);
                }//显示数据  
                ret.close();
                db1.close();//关闭连接  
            } catch (SQLException e) {  
                e.printStackTrace();
            }
            */
        	
        }
        
        
        //获得具体报告的详细信息
        public static void getRepDetails(String clumInfo , String  repType , String sid) throws SQLException{
        	
        	//判断
        	if ( repType.equals("disease")) 
        	{
        		table = "link_disease_loc";
        		clumName = "denname";
        	}
        	if ( repType.equals("inhert")) 
        	{
        		table = "link_inhert_loc";
        		clumName = "denname";
        	}
        	if ( repType.equals("talent")) 
        	{
        		table = "link_talent_loc";
        		clumName = "talenten";
        	}
        	if ( repType.equals("drug")) 
        	{
        		table = "link_drug_loc";
        		clumName = "drugen";
        	}
        	
        	System.out.println(table);
        	
        	sql = "SELECT hd_link_disease_loc.`locid`,genotype,repute,gene_name,rs,chr,start,end,ref FROM hd_link_disease_loc JOIN hd_com_locinfo WHERE hd_link_disease_loc.`locid` = hd_com_locinfo.`locid` AND hd_link_disease_loc.denname =\""+"Glaucoma\"";
        	
        	System.out.println(sql);
        	
        	ResultSet getresult01= mysqlRes(sql);
        	
        	//定义arrayList 及 hashMap
        	ArrayList<String> itemAll = new ArrayList<String>();  //所有locid的存储
        	Map<Integer, HashMap<String,String>> mapRes = new HashMap<Integer, HashMap<String,String>>();
        	Integer i =0;  //循环自增变量
        	while (getresult01.next()) {
            	i = i + 1;
            	//第一层hashMap
            	HashMap<String,String> temp_hash=new HashMap<String,String>();   //定义一个新的hashMap
            	//取值
            	String locid = getresult01.getString(1);
            	String genotype = getresult01.getString(2);
            	String repute = getresult01.getString(3);
            	String gene_name = getresult01.getString(4);
            	String rs = getresult01.getString(5);
            	String chr = getresult01.getString(6);
            	String start = getresult01.getString(7);
            	String end = getresult01.getString(8);
            	String ref = getresult01.getString(9);
            	//赋值
            	temp_hash.put("locid", locid);
            	temp_hash.put("genotype", genotype);
            	temp_hash.put("repute", repute);
            	temp_hash.put("gene_name", gene_name);
            	temp_hash.put("rs", rs);
            	temp_hash.put("chr", chr);
            	temp_hash.put("start", start);
            	temp_hash.put("end", end);
            	temp_hash.put("ref", ref);
            	//大hashMap
            	mapRes.put(i, temp_hash);
            	//存储
            	itemAll.add(locid);
            }//显示数据 
        	//Map<Integer, HashMap<String, String>> cc = mapRes;
        	Iterator<Map.Entry<Integer, HashMap<String, String>>> cc = mapRes.entrySet().iterator();
        	
			//去除itemAll里重复的locid
        	new removeDuplicateFromArrayList();
        	@SuppressWarnings("rawtypes")
			ArrayList itemAllDup = removeDuplicateFromArrayList.removeDuplicateWithOrder(itemAll);
            
        	sql = "SELECT  locid,genotype FROM  hd_user_variant WHERE   sid =\"Q134844_xudongnan\" AND locid IN (";  //清空sql
        	Integer sum = itemAllDup.size() - 1;
            for(i=0;i<itemAllDup.size() -1;i++){ 
            	System.out.println(itemAllDup.get(i));
            	sql = sql +"\""+itemAllDup.get(i)+"\",";
            }
            sql = sql + "\"" + itemAllDup.get(sum) + "\")";
            System.out.println(sql);
            
            //从表hd_user_variant读取指定sid和locid的数据
            getresult01= mysqlRes(sql); //执行获取数据
            while (getresult01.next()) {
            	
            	//从数据集中取数
            	String subLocid = getresult01.getString(1);
            	String subGenotype = getresult01.getString(2);
            	
            	Integer isNormal=0;  //判断用户的基因型是否特殊
        		for (Entry<Integer, HashMap<String,String>> entry : mapRes.entrySet()) {  //第一大层
        			subKey = entry.getKey();
        			HashMap<String, String> subHashMap = entry.getValue();
        			//entry.getKey();
        			String locidNomal = subHashMap.get("locid"); 
        			if(subLocid.equals(locidNomal)){
        				String genotypeNomal = subHashMap.get("genotype");  //基因型正向
						genotypeReverse = new StringReverse().StrgRev(genotypeNomal); //基因型反向
        				if(subGenotype.equals(genotypeNomal)||subGenotype.equals(genotypeReverse)){
        					isNormal=1;
        					//数据合适存储需要的变量			
        				}else{
        					cc.remove();
        				}
        				//System.out.println(entry.getValue());
        				System.out.println(subLocid);
        				System.out.println(locidNomal);
        				//System.out.println(entry.getValue());
        			}
        		}
        		
        		//for (Entry<Integer, HashMap<String,String>> entry : mapRes.entrySet()) { 
        			//System.out.println(entry.getValue());
        		//}
            }

        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        //自定义函数
        public static  ResultSet mysqlRes(String sql){
        	db1 = new DBHelper(sql);//创建DBHelper对象  
            try {  
                ResultSet rets = db1.pst.executeQuery();//执行语句，得到结果集  
                
                //while (ret.next()) {
                    //String uid = ret.getString(3);
                    //System.out.println(uid);
                //}//显示数据  
                
                return rets;
                
                //rets.close();
               // db1.close();//关闭连接  
                
            } catch (SQLException e) {  
                e.printStackTrace();
            }
			return ret;
        }
        
}
