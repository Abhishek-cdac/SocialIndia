package com.letspay.login.persistense;

import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.RightsMasterTblVo;

import java.util.List;


public interface LoginServices {

  UserMasterTblVo userCheck(UserMasterTblVo userInfo);

  List<RightsMasterTblVo> getUserRights(UserMasterTblVo userInfo);


  public boolean customerCreationUpdate(int custid, String accrefid);


}
