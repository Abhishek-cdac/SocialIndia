package com.socialindia.login.persistense;

import com.letspay.uam.persistense.RightsMasterTblVo;
import com.socialindia.login.persistense.UserMasterTblVo;

import java.util.List;


public interface LoginServices {

  UserMasterTblVo userCheck(UserMasterTblVo userInfo);

  List<RightsMasterTblVo> getUserRights(UserMasterTblVo userInfo);


  public boolean customerCreationUpdate(int custid, String accrefid);


}
