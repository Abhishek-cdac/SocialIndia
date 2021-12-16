package com.letspay.common;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.CountryMasterTblVo;
import com.letspay.vo.IsdcodeMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.letspay.vo.SysconfigChecklistDocTblVo;
import com.letspay.vo.SystemRuleTblVo;

import java.util.Date;
import java.util.List;

/**
 * Implementing interface services for common action.
 * 
 * @author PL0043
 * 
 */
public class CommonDaoService implements CommonService {
  private Common dao;

  public CommonDaoService() {
    dao = new CommonDao();
  }

  @Override
  public boolean commonUpdate(String sql) {
    // TODO Auto-generated method stub
    return dao.commonUpdate(sql);
  }

  @Override
  public String commonDelete(int uid, String pojonam, String colmn) {
    // TODO Auto-generated method stub
    return dao.commonDelete(uid, pojonam, colmn);
  }

  @Override
  public List<CountryMasterTblVo> commonCountyData() {
    // TODO Auto-generated method stub
    return dao.commonCountyData();
  }

  @Override
  public List<StateMasterTblVo> commonStateData(int countryidkey) {
    // TODO Auto-generated method stub
    return dao.commonStateData(countryidkey);
  }

  @Override
  public List<CityMasterTblVo> commonCityData(int stateidkey) {
    // TODO Auto-generated method stub
    return dao.commonCityData(stateidkey);
  }

  @Override
  public List<IsdcodeMasterTblVo> commonIsdCodeData(int countryidkey) {
    // TODO Auto-generated method stub
    return dao.commonIsdCodeData(countryidkey);
  }

  @Override
  public List<SysconfigChecklistDocTblVo> checkListDocType() {
    // TODO Auto-generated method stub
    return dao.checkListDocType();
  }

  @Override
  public String getSwiftcode(int branchid) {
    // TODO Auto-generated method stub
    return dao.getSwiftcode(branchid);
  }

  @Override
  public String getSinglevalfromtbl(String tblname, String colname, String val) {
    // TODO Auto-generated method stub
    return dao.getSinglevalfromtbl(tblname, colname, val);
  }

  @Override
  public String commonAutogenval(int lengthval, String type, String tblname,
      String colname) {
    // TODO Auto-generated method stub
    return dao.commonAutogenval(lengthval, type, tblname, colname);
  }

  public int gettotalcount(String sql) {
    // TODO Auto-generated method stub
    return dao.gettotalcount(sql);
  }

  @Override
  public SystemRuleTblVo commonSystemRuleData(String ruleid) {
    // TODO Auto-generated method stub
    return dao.commonSystemRuleData(ruleid);
  }

  @Override
  public List<IsdcodeMasterTblVo> isdDataByjsonvalue() {
    // TODO Auto-generated method stub
    return dao.isdDataByjsonvalue();
  }

  @Override
  public String getPath(String contextPath, String requestUri,
      StringBuffer requestUrl) {
    // TODO Auto-generated method stub
    return dao.getPath(contextPath, requestUri, requestUrl);
  }

@Override
public Date getCurrentDateTime(String dateformattype) {
	// TODO Auto-generated method stub
	return dao.getCurrentDateTime(dateformattype);
}




}
