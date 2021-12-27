package com.socialindia.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import com.opensymphony.xwork2.ActionSupport;

public class commomServices  extends  ActionSupport{
	
	
	public static String maskActivaionKey(String key) {
		int mobLen = key.length()-4;
		String firstHalf = key.substring(key.length()-4);	
		String mask = "";
	for (int i = 0; i < mobLen; i++) {
			mask += "X";
		}
		return mask + firstHalf;
		
	}
	public static String toByteAryToBase64EncodeStr(byte[] pFileBytes){
 		String locRtnEncodeStr=null;
 		try{
 			locRtnEncodeStr= Base64.encodeBase64String(pFileBytes);
 		}catch(Exception e){
 			System.out.println("Exception toByteAryToBase64EncodeStr() : "+e);
 		}finally{
 			pFileBytes=null;
 		}
 		return locRtnEncodeStr;
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

}
