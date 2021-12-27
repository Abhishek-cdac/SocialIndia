package com.socialindia.eNewsmgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.tendermgmt.TenderDocTblVO;


public class eNewsMgmtTbl extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{
	private static final long serialVersionUID = 1L;
	private String idtDisplayStart;
	private String idtDisplayLength;
	private String sdtEcho;
	private String idtSortCol0;
	private String sdtSortDir0;
	private String sdtSearch;
	private String usersearchname;
	private String society;
	private String searchval;
	private String slectfield;
	private JSONObject result = new JSONObject();
	JSONArray aaData = new JSONArray();
	private String idtTotalDisplayRecords = "";
	private String idtTotalRecords = "";
	private HttpServletResponse response;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String sqlQuery;
	private String CmpyProfileImage="";
	List<EeNewsTblVO> eNewsList = new ArrayList<EeNewsTblVO>();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao(); 
	 List<EeNewsDocTblVO> EnewsdocList =new ArrayList<EeNewsDocTblVO>();
	public String execute() {
		JSONArray lvrArray = null;
	    try {
	      System.out.println("TenderMgmtTbl.class Called for Datatable.");
	      String icol = "eNewsId";
	      String table = "EeNewsTblVO";
	      String manualsearch = "";
	      sdtEcho = request.getParameter("sEcho");
	      sdtSearch = request.getParameter("sSearch");
	      idtDisplayStart = request.getParameter("iDisplayStart");
	      String sAmount = request.getParameter("iDisplayLength");
	      Map sessionMap = ActionContext.getContext().getSession();
	      if (sdtSearch == null) {
	          sdtSearch = "";
	      }
	      lvrArray = new JSONArray();
	      int amount = 10, start = 0, echo = 0, col = 0;
	      String globalsearch = "";
	      String userfilter = "";
	      String dir = "Desc";	    	    
	      if (idtDisplayStart != null) {
	        start = Integer.parseInt(idtDisplayStart);
	        if (start < 0) {
	          start = 0;
	        }
	      } else {
	        if (request.getParameter("iDisplayStart") != null) {
	          start = Integer.parseInt(request.getParameter("iDisplayStart"));
	        }
	      }

	      if (idtDisplayLength != null) {
	        amount = Integer.parseInt(idtDisplayLength);
	        if (amount < 5 || amount > 100) {
	          amount = 5;
	        }
	      } else {
	        if (request.getParameter("iDisplayLength") != null) {
	          amount = Integer.parseInt(request.getParameter("iDisplayLength"));
	        }
	      }
	      if (sdtEcho != null) {
	        echo = Integer.parseInt(sdtEcho);
	      }

	      if (idtSortCol0 != null) {
	        col = Integer.parseInt(idtSortCol0);
	        if (col < 0 || col > 3) {
	          col = 0;
	        }
	      }
	      if (sdtSortDir0 != null) {
	        if (!sdtSortDir0.equals("asc")) { dir = "desc";}
	      }
	      int total = 0;
	      int totalAfterFilter = 0;
	      JSONArray ar =null;
	      JSONObject dataJson = null;
	      JSONObject lvrFinjsonObj = null; 
	      String lcsocitid=String.valueOf(sessionMap.get("sSoctyId"));
	      String grpcode=String.valueOf(sessionMap.get("GROUPCODE"));
	      JSONArray lvrJaArray = null;
	      try {	       
	    	dataJson=new JSONObject();
	  		dataJson.put("eNewsstatus", "1");
	  		if (society==null || society.equalsIgnoreCase("") || society.equalsIgnoreCase("undefined")){// session
	  			if(lcsocitid != null && !lcsocitid.equalsIgnoreCase("-1") ){
		  			dataJson.put("societyid", lcsocitid);
		  		}else{
		  			dataJson.put("societyid", society);
		  		}
	  		}else{//
	  			dataJson.put("societyid", society);
	  		}	  			  		
	  		//dataJson.put("eNewssocietyid", String.valueOf(sessionMap.get("sSoctyId")));	  		
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));					
			if(usersearchname!=null){
				dataJson.put("srchFieldval", usersearchname);	
			}else{
				dataJson.put("srchFieldval", "");
			}
			
			if(slectfield!=null){
				dataJson.put("slectfield", slectfield);
			}else{
				dataJson.put("slectfield", "");
			}			
			lvrFinjsonObj = new JSONObject();
			lvrFinjsonObj.put("servicecode", "SI11006");		
			lvrFinjsonObj.put("data", dataJson);	     
	        String jsonTextFinal = lvrFinjsonObj.toString();	 
	        System.out.println("req==  "+jsonTextFinal);
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.eNewsmgmtselectall.action");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			total =  json_data.getInt("InitCount");
			ar = json_data.getJSONArray("eNewsdetails");
			JSONObject jsonList = new JSONObject();
			JSONArray docnamearr = new JSONArray();
			String eNewstitle =null,eNewsstartdate=null,eNewsdesc=null,eNewsenddate=null,eNewsposedby=null,eNewsid="",action="",eNewssocietyname=null,filename="",filepath="",filename3=null,filename4=null;		
	        String imagepath= "";
	        int lvrSno =1;
			for(int i=0;i<ar.length();i++){	imagepath="";
				jsonList = null;
				docnamearr=null;
				jsonList = ar.getJSONObject(i);
				eNewsid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "eNewsid");
				eNewstitle = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"eNewstitle");
				eNewsdesc = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"eNewsdesc");			
				eNewsposedby = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"eNewsusername");
				eNewssocietyname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"eNewssocietyname");				
				docnamearr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(jsonList, "jArry_doc_files");
					if (docnamearr != null && docnamearr.length() > 0) {
						filename = "";
						filepath = "";
						for (int j = 0; j < docnamearr.length(); j++) {						
							filepath +=(String) Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(j),"filepath")+"<SR>";
							filename +=(String) Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(j),"filesname")+"<SR>";
						}
						eNewsList.add(new EeNewsTblVO(eNewstitle, eNewsdesc, eNewsposedby,Integer.parseInt(eNewsid),eNewssocietyname,filepath,filename));
					} else {
						filepath="";filename="";
					    eNewsList.add(new EeNewsTblVO(eNewstitle, eNewsdesc, eNewsposedby,Integer.parseInt(eNewsid),eNewssocietyname,filepath,filename));
				   }
					lvrJaArray = null;
					 lvrJaArray = new JSONArray();
					if(filename!=null && !filename.equalsIgnoreCase("") && !filename.equalsIgnoreCase("null")){	 	            	
	 	            	if(filename.contains("<SR>")){
	 	            		String splFname[]=filename.split("<SR>");
	 	            		for(int incc =0; incc<splFname.length;incc++){
	 	            			imagepath+="<img id=\"myImg1_"+eNewsid+"\" name=EnewImageFileName\" class=\"webimageenewsdatable hoverable\" "
	 		 		   	           		 + "src=\"/templogo/enews/mobile/"+eNewsid+"/"+splFname[incc]+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/enews/web/"+eNewsid+"/"+splFname[incc]+"');\">";
	 	            		}
	 	            	}else{
	 	            		imagepath+="";
	 	            	}
	 	            }else {
	 	            	imagepath+="";
	 	            }      
	 	        	action += "<div class=\"vieweNewsaction\"><a class=\"\" title=\"\"  style=\"margin-right:10px; cursor: pointer;\" onclick=\"viewenewaction('" + eNewsid + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" "
		                   + "data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";
	           
	 	        	if(!grpcode.equalsIgnoreCase("0") && !grpcode.equalsIgnoreCase("1") && !grpcode.equalsIgnoreCase("2")){
	 	        	action += "<a class=\"\" title=\"\"  role=\"button\" style=\"margin-right: 10px; cursor: pointer;\" onclick=\"editenewaction('"
	            		   + eNewsid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
	                       + "data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	 	        	}
	 	        	action += "<a class=\"\" style=\"margin-right: 10px; cursor: pointer;\" role=\"button\" "
	                       + " title=\"\" onclick=\"deleteeNewsaction('" + eNewsid
	                       + "');\"> <i class=\"tooltipped "+getText("button.color.delete")+"\" "
	                       + "data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i></a>";	 
	           		     
	            
	            lvrJaArray.put(lvrSno);
	            lvrJaArray.put(eNewssocietyname);
	            lvrJaArray.put(eNewstitle);
	            lvrJaArray.put(imagepath);
	            lvrJaArray.put(action);
	            lvrArray.put(lvrJaArray);
	            lvrJaArray = null;
	            action = "";
	            lvrSno++;
					
					
			}
			   totalAfterFilter =  json_data.getInt("countAfterFilter");
			   System.out.println("totalAfterFilter : " + totalAfterFilter);
			} catch (Exception ex) {
				System.out.println("Exception Found in TenderMgmtTbl.class : "
						+ ex);
			} finally {
				dataJson = null;
				ar = null;
				lvrFinjsonObj = null;
			} 
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", lvrArray);
			getResponse();
	      /*
			try {	       	       
	        sqlQuery = globalsearch;
	        ActionContext.getContext().getSession().put("globquery", userfilter);	        	      	       
	        String eNewstitle = "";
	        String eNewsfilename = "";
	        String eNewssoc_name = "";
	        String eNewscurntusrid="";
	        //String action = "";
	        String imagepath="";
	        EeNewsTblVO userObj;
	        EeNewsDocTblVO docObj;
	        JSONArray lvrJaArray = null;
	        try {
	       
	        	int sno = start;
	          System.out.println("Eenews List Size : "+eNewsList.size());
	          for (Iterator<EeNewsTblVO> it = eNewsList.iterator(); it.hasNext();) {
	        	  imagepath="";
	            userObj = it.next();
	            sno++;	            
	            lvrJaArray = new JSONArray();
	            eNewscurntusrid=String.valueOf(userObj.getIvrBnENEWS_ID());
	            eNewstitle=userObj.getIvrBnTITLE();
	            eNewssoc_name=userObj.getSocietyname();
	            eNewsfilename =userObj.getFilename();
	            
	 	        	if(eNewsfilename!=null && !eNewsfilename.equalsIgnoreCase("") && !eNewsfilename.equalsIgnoreCase("null")){	 	            	
	 	            	if(eNewsfilename.contains("<SR>")){
	 	            		String splFname[]=eNewsfilename.split("<SR>");
	 	            		for(int incc =0; incc<splFname.length;incc++){
	 	            			imagepath+="<img id=\"myImg1_"+eNewscurntusrid+"\" name=EnewImageFileName\" class=\"webimageenewsdatable\" "
	 		 		   	           		 + "src=\"/templogo/enews/mobile/"+eNewscurntusrid+"/"+splFname[incc]+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/enews/web/"+eNewscurntusrid+"/"+splFname[incc]+"');\">";
	 	            		}
	 	            	}else{
	 	            		imagepath+="";
	 	            	}
	 	            }else {
	 	            	imagepath+="";
	 	            }      
	 	        	action += "<div class=\"vieweNewsaction\"><a class=\"\" title=\"\"  style=\"margin-right:15px; cursor: pointer;\" onclick=\"viewenewaction('" + eNewscurntusrid + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" "
		                   + "data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";
	           
	 	        	action += "<a class=\"\" title=\"\"  role=\"button\" style=\"margin-right: 15px; cursor: pointer;\" onclick=\"editenewaction('"
	            		   + eNewscurntusrid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
	                       + "data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	           
	 	        	action += "<a class=\"\" style=\"margin-right: 15px; cursor: pointer;\" role=\"button\" "
	                       + " title=\"\" onclick=\"deleteeNewsaction('" + eNewscurntusrid
	                       + "');\"> <i class=\"tooltipped "+getText("button.color.delete")+"\" "
	                       + "data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i></a>";	 
	           		     
	            
	            lvrJaArray.put(sno);
	            lvrJaArray.put(eNewssoc_name);
	            lvrJaArray.put(eNewstitle);
	            lvrJaArray.put(imagepath);
	            lvrJaArray.put(action);
	            lvrArray.put(lvrJaArray);
	            lvrJaArray = null;
	            action = "";
	          }
	        } catch (Exception ex) {
	        	System.out.println("Inner 1 - Exception found in TenderMgmt.class : "+ex);
	        }finally{
	        	userObj = null;
	        }	        
	        result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", lvrArray);
	        getResponse();
	      } catch (Exception ex) {
	        System.out.println("Inner 2 - Exception found in TenderMgmt.class : "+ex);
	      }finally{
	    	  
	      }*/
		} catch (Exception ex) {
			System.out.println("outter - Exception found in TenderMgmt.class : " + ex);
		}
		return SUCCESS;
	}

	/**
	 * Response.
	 */
	public void getResponse() {
		PrintWriter out;
		try {
			out = response.getWriter();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			out.print(result);
			out.close();
		} catch (IOException ex) {
		}

	}

	public String getIdtDisplayStart() {
		return idtDisplayStart;
	}

	public void setIdtDisplayStart(String idtDisplayStart) {
		this.idtDisplayStart = idtDisplayStart;
	}

	public String getIdtDisplayLength() {
		return idtDisplayLength;
	}

	public void setIdtDisplayLength(String idtDisplayLength) {
		this.idtDisplayLength = idtDisplayLength;
	}

	public String getSdtEcho() {
		return sdtEcho;
	}

	public void setSdtEcho(String sdtEcho) {
		this.sdtEcho = sdtEcho;
	}

	public String getIdtSortCol0() {
		return idtSortCol0;
	}

	public void setIdtSortCol0(String idtSortCol0) {
		this.idtSortCol0 = idtSortCol0;
	}

	public String getSdtSortDir0() {
		return sdtSortDir0;
	}

	public void setSdtSortDir0(String sdtSortDir0) {
		this.sdtSortDir0 = sdtSortDir0;
	}

	public String getSdtSearch() {
		return sdtSearch;
	}

	public void setSdtSearch(String sdtSearch) {
		this.sdtSearch = sdtSearch;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getIdtTotalDisplayRecords() {
		return idtTotalDisplayRecords;
	}

	public void setIdtTotalDisplayRecords(String idtTotalDisplayRecords) {
		this.idtTotalDisplayRecords = idtTotalDisplayRecords;
	}

	public String getIdtTotalRecords() {
		return idtTotalRecords;
	}

	public void setIdtTotalRecords(String idtTotalRecords) {
		this.idtTotalRecords = idtTotalRecords;
	}

	public JSONArray getAaData() {
		return aaData;
	}

	public void setAaData(JSONArray aaData) {
		this.aaData = aaData;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public String getCmpyProfileImage() {
		return CmpyProfileImage;
	}

	public void setCmpyProfileImage(String CmpyProfileImage) {
		this.CmpyProfileImage = CmpyProfileImage;
	}

	public String getUsersearchname() {
		return usersearchname;
	}

	public void setUsersearchname(String usersearchname) {
		this.usersearchname = usersearchname;
	}

	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}

	public String getSearchval() {
		return searchval;
	}

	public void setSearchval(String searchval) {
		this.searchval = searchval;
	}

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}

	public List<EeNewsDocTblVO> getEnewsdocList() {
		return EnewsdocList;
	}

	public void setEnewsdocList(List<EeNewsDocTblVO> enewsdocList) {
		EnewsdocList = enewsdocList;
	}

}
