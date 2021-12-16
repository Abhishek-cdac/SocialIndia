<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%--@page import="java.util.ResourceBundle"--%>
<%@page import="javax.servlet.http.*"%>
<%@page trimDirectiveWhitespaces="true" %>
<%
String tmpltN="residentsampleexcel";
//String contextPath = request.getContextPath();
//System.out.println("Context Path " + contextPath);
/* 
ResourceBundle rb=ResourceBundle.getBundle("applicationResources");
String apppath = rb.getString("ApplicationAbsolutePath");
String projpath=rb.getString("Proj.path");
String webpath = rb.getString("WebContent");
String resourcepath= rb.getString("Resource.path");*/
String XlsxPath = request.getSession().getServletContext().getRealPath("/resources/jsp/sampleexcel/samplexlsxformat.xlsx");
//String XlsxPath=apppath+projpath+webpath+resourcepath+"/jsp/sampleexcel/samplexlsxformat.xlsx";
//String tmplViewName1 ="tmplViewName";
OutputStream responseOutputStream = null;
File XlsxFile = null;
FileInputStream fis = null;
FileOutputStream fileStream = null;
OutputStreamWriter writer = null;
try {
 		XlsxFile = new File(XlsxPath);// pdf file read and write to response 
 		System.out.println("XlsxPath-------------"+XlsxPath);
    	fis = new FileInputStream(XlsxFile);
    	fileStream = new FileOutputStream(new File(tmpltN+".xlsx"));
    	writer = new OutputStreamWriter(fileStream, "UTF-8");    	
    	String rptname = XlsxFile.getName();
    	responseOutputStream = response.getOutputStream();
    	//response.setContentType("application/xlsx");
    	response.setHeader("Content-Disposition", "attachment;filename=" + tmpltN + ".xlsx");
    	response.setHeader("Pragma", "public");
    	 response.setHeader("Content-Length", String.valueOf(XlsxFile.length()));
    	response.setContentType("application/octet-stream");
    	//response.setHeader("Cache-Control", "max-age=0");
    	response.addHeader("Cache-Control", "no-transform, max-age=0");
    	int nRead;
    	byte[] data = new byte[(int)XlsxFile.length()];
    	while ((nRead = fis.read(data, 0, data.length)) != -1) {
    		responseOutputStream.write(data, 0, nRead);
    		responseOutputStream.flush();   
    	}       
    	
}catch(Exception ex){
	System.out.println("Exception ::::::::::::: "+ex);
	   
}finally{
	
	if(responseOutputStream!=null){responseOutputStream.close();}
	responseOutputStream=null;
	if(writer!=null){writer.close();writer=null;}
	if(fileStream!=null){fileStream.close();fileStream=null;}
	if(fis!=null){fis.close(); }
}

    
 
%>