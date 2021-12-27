package com.socialindia.function;

import java.io.Serializable;

public class FunctionMgmtTblVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String functionid;
	private String functionname;
	private String status;
	private String functiontext;
	private int txtid;
	private String functype;
	public FunctionMgmtTblVO(String functionid, String functionname,String status, String functiontext,int txtid,String functype) {
		this.functionid=functionid;
		this.functionname=functionname;
		this.status=status;
		this.functiontext=functiontext;
		this.txtid=txtid;
		this.functype=functype;
	}
	public String getFunctionid() {
		return functionid;
	}
	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}
	public String getFunctionname() {
		return functionname;
	}
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFunctiontext() {
		return functiontext;
	}
	public void setFunctiontext(String functiontext) {
		this.functiontext = functiontext;
	}
	public int getTxtid() {
		return txtid;
	}
	public void setTxtid(int txtid) {
		this.txtid = txtid;
	}
	public String getFunctype() {
		return functype;
	}
	public void setFunctype(String functype) {
		this.functype = functype;
	}
	
	

}
