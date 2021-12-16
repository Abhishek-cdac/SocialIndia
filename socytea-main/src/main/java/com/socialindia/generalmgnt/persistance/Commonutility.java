package com.socialindia.generalmgnt.persistance;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.utils.CommonUtils;
import com.letspay.utils.CommonUtilsDao;

public class Commonutility {
	public static Boolean toCheckIsJSON(String pStrval){
		boolean iVBrst=false;
		if(pStrval!=null && pStrval.startsWith("{") && pStrval.endsWith("}")){
			iVBrst=true;
		}else{
			iVBrst=false;
		}		
		return iVBrst;
	}
	
	public static Object toHasChkJsonRtnValObj(JSONObject pJsonObjVal, String pJsonKey){// to check json object key to has() method
		Object locObj=null;
		try{
			if(pJsonObjVal!=null && pJsonObjVal.has(pJsonKey)){
				locObj = pJsonObjVal.get(pJsonKey);
			}else{
				locObj=null;
			}
		}catch(Exception e){locObj=null;}finally{pJsonObjVal=null;pJsonKey=null;}
		return locObj;
	}
	public static String toJSONArraytoString(JSONArray pJSONAryob){
		//JSONArray lineItems = jj.getJSONArray("lss");		
		String fin="";
		for (int i = 0; i < pJSONAryob.length(); i++) {
			JSONArray temm;			
			try {
				temm = pJSONAryob.getJSONArray(i);
				int indx=(Integer)temm.get(0);
				String vl=(String)temm.get(1);
				fin+="<option value=\""+indx+"\">"+vl+"</option>";
				System.out.println("<option value=\""+indx+"\">"+vl+"</option>");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
							
		}	
		return fin;
	}
	public static String toJSONArraytoString1(JSONArray pJSONAryob){
		//JSONArray lineItems = jj.getJSONArray("lss");		
		String fin="";
		for (int i = 0; i < pJSONAryob.length(); i++) {
			JSONArray temm;			
			try {
				temm = pJSONAryob.getJSONArray(i);
				int indx=(Integer)temm.get(0);
				String vl=(String)temm.get(1);
				//fin+="<option value=\""+indx+"\">"+vl+"</option>";
				//System.out.println("<option value=\""+indx+"\">"+vl+"</option>");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
							
		}	
		return fin;
	}
	
	public static String toCheckNullEmpty(Object pChkval){
		if(pChkval!=null){
			if(!pChkval.toString().equalsIgnoreCase("null") && !pChkval.toString().equalsIgnoreCase("")){			
				if(toCheckisNumeric(String.valueOf(pChkval))){
					return String.valueOf(pChkval);	
				}else{
					return pChkval.toString();	
				}
						
			}else{
				return "";
			}
		}else{
			return "";
		}
   }
	public static boolean toCheckisNumeric(String pNmckval){
		try
		   {
		      Integer.parseInt(pNmckval);
		      return true;
		   }
		   catch( Exception e)
		   {
		      return false;
		   }
	}
	
	public static String toGetCurrentDatetime(String pFmtType){
		 CommonUtils lvrCmutil = null;
		 String lvrRtnStr = null;
		try {
			lvrCmutil = new CommonUtilsDao();
			lvrRtnStr = lvrCmutil.getStrCurrentDateTime(pFmtType);
		}catch(Exception e) {
			lvrRtnStr = null;
		}finally {
			lvrCmutil = null;
		}
		return lvrRtnStr;
	}
	
	public static void toGetJavaHeapMemory(){
		try{
			int mb = 1024*1024;
			
			//Getting the runtime reference from system
			Runtime runtime = Runtime.getRuntime();
			
			System.out.println("##### Heap utilization statistics [MB] #####");
			
			//Print used memory
			System.out.println("Used Memory:" 
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

			//Print free memory
			System.out.println("Free Memory:" 
				+ runtime.freeMemory() / mb);
			
			//Print total available memory
			System.out.println("Total Memory:" + runtime.totalMemory() / mb);

			//Print Maximum available memory
			System.out.println("Max Memory:" + runtime.maxMemory() / mb);
		} catch(Exception e) {
			
		} finally {
			
		}
	}
	
	public static boolean checkempty(String str) {
		boolean retval = false;
		try {
			if (str != null) {
				str = str.trim();
				if (!str.equalsIgnoreCase("") && !str.equalsIgnoreCase("null")) {
					retval = true;
				}
			}
		} catch (Exception ex) {
			System.out.println("Exception found in checkEmpty:" + ex);
		}
		return retval;
	}
	public static Double toRoundedVal(double pRondVl) {
		double locRtnDbl = 0;
		DecimalFormat df = new DecimalFormat("#.###");
		try {
			locRtnDbl = Double.valueOf(df.format(pRondVl));
		} catch (Exception e) {
		} finally {
		}
		return locRtnDbl;
	}
	
	public static void toWriteConsole(Object pwritemsg){		
		System.out.println("["+Commonutility.toGetCurrentDatetime("yyyy-MM-dd HH:mm:ss")+"] [Client] " + pwritemsg);
	}
	
	public static String toByteAryToBase64EncodeStr(byte[] pFileBytes){
 		StringBuilder locRtnEncodeStr=null;
 		try{
 			locRtnEncodeStr = new StringBuilder();
 			locRtnEncodeStr.append(Base64.encodeBase64String(pFileBytes));
 		}catch(Exception e){
 			System.out.println("Exception toByteAryToBase64EncodeStr() : "+e);
 		}finally{
 			pFileBytes=null;
 		}
 		return locRtnEncodeStr.toString();
 	}
	
	public static byte[] toReadFiletoBytes(File filepath) {// read file to byte	       
        byte toredbyts[] = null;
        byte[] toreturnBytes = null;
        FileInputStream fins = null;
        ByteArrayOutputStream bout = null;
        try {
            toredbyts = new byte[(int)filepath.length()];
            fins = new FileInputStream(filepath);
            bout = new ByteArrayOutputStream();
            for (int readNum; (readNum = fins.read(toredbyts)) != -1;) {
                bout.write(toredbyts, 0, readNum); //no doubt here is 0 
            }
            toreturnBytes = bout.toByteArray();
        } catch (Exception e) {
        } finally {
            try {
                if(fins!=null){fins.close();fins=null;}
                if(bout!=null){bout.close();bout=null;}               
            } catch (IOException e) {}
        }
        return toreturnBytes;
    }
	public static String tochangeonefrmtoanother(String fromformat,String toformat, String pDate) {
		String datestr="";
		SimpleDateFormat frmFrmtsdft = null, toFrmtsdft = null;
		Date frmdate = null;
		try {
			if (Commonutility.checkempty(fromformat) && Commonutility.checkempty(toformat) && Commonutility.checkempty(pDate)) {
			 frmFrmtsdft = new SimpleDateFormat(fromformat);
			 toFrmtsdft  = new SimpleDateFormat(toformat);				
				frmdate = frmFrmtsdft.parse(pDate);
				datestr = toFrmtsdft.format(frmdate);
			} else {
				datestr = pDate;
			}
		} catch (Exception e) {
		} finally {
			frmFrmtsdft = null;
			toFrmtsdft = null;
			frmdate = null;
		}
		return datestr;
	}
	
	public static void crdDir(String dirPathandName) {
		File loc_nFile = null;
		File loc_nFile_fcrt = null;
		String fName = "";
		String pathh = "";
		try {
			if (dirPathandName.contains(".")) {
				fName = dirPathandName.substring(
						dirPathandName.lastIndexOf("/"),
						dirPathandName.length());
				pathh = dirPathandName.substring(0,
						dirPathandName.lastIndexOf("/"));
			} else {
				pathh = dirPathandName;
			}
			loc_nFile = new File(pathh);
			if (loc_nFile.exists()) {
				if (loc_nFile.isDirectory()) {
					System.out.println("Directory already Exists");
				} else {
					loc_nFile.mkdirs();
					System.out.println("Directory Created Successfully");
				}
			} else {
				loc_nFile.mkdirs();
				System.out.println("Directory Created Successfully");
			}
			if (!fName.equalsIgnoreCase("")) {
				loc_nFile_fcrt = new File(pathh + "/" + fName);
				if (!loc_nFile_fcrt.exists()) {
					loc_nFile_fcrt.createNewFile();
					System.out.println("File Created Successfully");
				} else {
					System.out.println("File already Exists");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception found in Utilities crdDir() : " + e);
		} finally {
		}
	}

	public static void  TextFileWritee(String filepath,String txtvalue) {
		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		try{
			crdDir(filepath);
			writer = new FileWriter(filepath, true);
			bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(txtvalue);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch(Exception e){
			Commonutility.toWriteConsole("Exception : "+ e);	
		} finally {
			
		}
	}

}

