package com.letspay.utils;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Write exceptions in log file.
 * 
 * @author PL0043
 *
 */
public class Log {

  //public static Logger write = Logger.getLogger(com.letspay.utils.Log.class);
    public static Logger write = LogManager.getLogger(Log.class);
	public static Date olddate=null;

  /**
   * constructor class.
   */
 /* public Log() {
    try {
      // System.out.println("-----------------------------------------------------------");
      // Context ctx = new InitialContext();
      // if(ctx != null){
      // throw new Exception("No Context");
      // }
      // System.out.println("path---"+getText("external.path.log"));
      // String logPath = (String) ctx.lookup("java:comp/env/logPath");
      // String logPath = (String)
      // ctx.lookup("c://LPayExternal/letspay/log4j.xml");
      // System.out.println("Log path---"+logPath);
      // DOMConfigurator.configure(logPath);
      // ctx.close();
    } catch (Exception ex) {
      System.out.println("com.remittance.utils.Log \t : " + ex);
    }
  }*/

  public Log() {		
		Date loccrntdate = null;
		SimpleDateFormat sdf = null;
  try {    	
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		loccrntdate = new Date();
		loccrntdate=sdf.parse(sdf.format(loccrntdate));
		if(olddate!=null){
			olddate = sdf.parse(sdf.format(olddate));
			//System.out.println("Old Date : "+sdf.format(olddate));
		}		
			//System.out.println("Current Date : "+sdf.format(loccrntdate));
		if(olddate==null || loccrntdate.after(olddate)){
			if(olddate!=null){
				System.out.println("Current Date : ["+sdf.format(loccrntdate)+"] is after Old Date : ["+sdf.format(olddate)+"]"); 
			}else{
				System.out.println("Current Date : ["+sdf.format(loccrntdate)+"] is after Old Date : ["+olddate+"]"); 
			}			
			olddate=loccrntdate;
  		   		    		    
  		DailyRollingFileAppender rollingAppender =null;
 	        PatternLayout layout = new PatternLayout();
	        String conversionPattern = "[%p] %d %c %M - %m%n";
	        layout.setConversionPattern(conversionPattern);
	 
	        String logfilepath =  getPropertyValue("log4j.properties","log4j.appender.file.Filepath");
	      
	        if(logfilepath == null || logfilepath.equalsIgnoreCase("null") || logfilepath.equalsIgnoreCase("")){
	        	logfilepath = "C:/socialindiaclientexternal/logs/";
	        }
	        
	        // creates daily rolling file appender
	        rollingAppender = new DailyRollingFileAppender();			
			String gg=sdf.format(loccrntdate);
			rollingAppender.setFile(logfilepath+gg);	     
	        rollingAppender.setLayout(layout);
	        rollingAppender.activateOptions();
	 
	        // configures the root logger
	        Logger rootLogger = Logger.getRootLogger();
	       // rootLogger.setLevel(Level.DEBUG);
	        rootLogger.addAppender(rollingAppender);    		    		    
  	}else if(loccrntdate.equals(olddate)){
  		//System.out.println("Current Date is equal Old Date");
  	}else if(loccrntdate.before(olddate)){
  		//System.out.println("Current Date is before Old Date");
  	}else{
  		// Exception
  	}    	
  		
 } catch (Exception ex) {
   System.out.println("Log \t : " + ex);
 }
}
  
  public String getPropertyValue(String pFname, String key) { // get value from properties
      String kVal = "";
    InputStream fis = null;
      Properties prop = null;
      try {
          prop = new Properties();           
          fis = getClass().getClassLoader().getResourceAsStream(pFname);
          prop.load(fis);
          kVal = prop.getProperty(key);
          if (kVal == null || kVal.equalsIgnoreCase("")) {
              kVal = key;
          }
      } catch (Exception ex) {            
          kVal = "";
      } finally {
			try {
				if (fis != null) {
					fis.close(); fis = null;
				}
				if (prop != null) {
					prop.clear(); prop = null;
				}
			} catch (Exception ex) {
			}
		}
      return kVal;
  }
  
  /**
   * For write log messages to log file.
   * 
   * @param msg
   *          .
   * @param type
   *          .
   * @param cls
   *          .
   * @return msgs returned.
   */
  public String logMessage(String msg, String type, Class cls) {
    String mesage = "";
    try {   
      mesage = msg;
      write = Logger.getLogger(cls);
      String messageTypeValue = "";
      InputStream inputStream = null;
      try {
        Properties prop = new Properties();
        String propFileName = "log4j.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(
            propFileName);
        if (inputStream != null) {
          prop.load(inputStream);
        } else {
          throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        messageTypeValue = prop.getProperty(type);

      } catch (Exception ex) {
        System.out.println("Exception: " + ex);
      } finally {
        inputStream.close();
      }
      if(write != null){
      if (type.equalsIgnoreCase("info")
          && messageTypeValue.equalsIgnoreCase("1")) {
        write.info(mesage);
      } else if (type.equalsIgnoreCase("warn")
          && messageTypeValue.equalsIgnoreCase("1")) {
        write.warn(mesage);
      } else if (type.equalsIgnoreCase("debug")
          && messageTypeValue.equalsIgnoreCase("1")) {
        write.debug(mesage);
      } else if (type.equalsIgnoreCase("error")
          && messageTypeValue.equalsIgnoreCase("1")) {
        write.error(mesage);
      } else if (type.equalsIgnoreCase("fatal")
          && messageTypeValue.equalsIgnoreCase("1")) {
        write.fatal(mesage);
      } else if (type.equalsIgnoreCase("trace")
          && messageTypeValue.equalsIgnoreCase("1")) {
        write.trace(mesage);
      }
      }
    } catch (Exception ex) {
      System.out.println("Exception in logmessage method--->" + ex);
    }finally{
    	mesage=null;
    }
    return mesage;
  }
}
