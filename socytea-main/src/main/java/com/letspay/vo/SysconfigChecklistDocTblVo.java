package com.letspay.vo;

import java.io.Serializable;
import java.util.Date;

public class SysconfigChecklistDocTblVo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer docid;
  private String namedoc;
  private String allowedtype;
  private Integer statusflg;
  private Integer entryby;
  private Date entrydate;
  private Date modifydate;

  public SysconfigChecklistDocTblVo() {
  }

  public Integer getDocid() {
    return docid;
  }

  public void setDocid(Integer docid) {
    this.docid = docid;
  }

  public String getNamedoc() {
    return namedoc;
  }

  public void setNamedoc(String namedoc) {
    this.namedoc = namedoc;
  }

  public String getAllowedtype() {
    return allowedtype;
  }

  public void setAllowedtype(String allowedtype) {
    this.allowedtype = allowedtype;
  }

  public Integer getStatusflg() {
    return statusflg;
  }

  public void setStatusflg(Integer statusflg) {
    this.statusflg = statusflg;
  }

  public Integer getEntryby() {
    return entryby;
  }

  public void setEntryby(Integer entryby) {
    this.entryby = entryby;
  }

  public Date getEntrydate() {
    return entrydate;
  }

  public void setEntrydate(Date entrydate) {
    this.entrydate = entrydate;
  }

  public Date getModifydate() {
    return modifydate;
  }

  public void setModifydate(Date modifydate) {
    this.modifydate = modifydate;
  }

}
