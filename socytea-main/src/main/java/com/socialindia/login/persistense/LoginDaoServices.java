package com.socialindia.login.persistense;

import com.letspay.uam.persistense.RightsMasterTblVo;
import com.socialindia.login.persistense.UserMasterTblVo;

import java.util.List;

public class LoginDaoServices implements LoginServices {

  private Login dao;

  public LoginDaoServices() {
    dao = new LoginDao();
  }

  @Override
  public UserMasterTblVo userCheck(UserMasterTblVo userInfo) {
    // TODO Auto-generated method stub
    return dao.userCheck(userInfo);
  }

  @Override
  public List<RightsMasterTblVo> getUserRights(UserMasterTblVo userInfo) {
    // TODO Auto-generated method stub
    return dao.getUserRights(userInfo);
  }

  
  @Override
  public boolean customerCreationUpdate(int custid, String accrefid) {
    // TODO Auto-generated method stub
    return dao.customerCreationUpdate(custid, accrefid);
  }

 
}
