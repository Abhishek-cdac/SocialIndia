package com.letspay.common;

import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.CountryMasterTblVo;
import com.letspay.vo.IsdcodeMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.letspay.vo.SysconfigChecklistDocTblVo;
import com.letspay.vo.SystemRuleTblVo;
/*import com.remittance.vo.OtpConfigTblVo;
import com.remittance.vo.OtpDetailTblVo;*/



import java.util.Date;
import java.util.List;

public interface Common {

  public boolean commonUpdate(String sql);

  public String commonDelete(int uid, String pojonam, String colmn);

  public List<CountryMasterTblVo> commonCountyData();

  public List<StateMasterTblVo> commonStateData(int countryidkey);

  public List<CityMasterTblVo> commonCityData(int stateidkey);

  public List<IsdcodeMasterTblVo> commonIsdCodeData(int countryidkey);

  public List<SysconfigChecklistDocTblVo> checkListDocType();

  public String getSwiftcode(int branchid);

  public String getSinglevalfromtbl(String tblname, String colname, String val);

  public String commonAutogenval(int lengthval, String type, String tblname, String colname);

  public int gettotalcount(String sql);

  public SystemRuleTblVo commonSystemRuleData(String ruleid);

  public List<IsdcodeMasterTblVo> isdDataByjsonvalue();

  public String getPath(String contextPath, String requestUri, StringBuffer requestUrl);

  public String getRandomval(String password,int size);

  public String jsonRequest(String URL, String data);

  public Date getCurrentDateTime(String dateformattype);
  public String getDateobjtoFomatDateStr(Date pDataobj, String pDateformat);
}
