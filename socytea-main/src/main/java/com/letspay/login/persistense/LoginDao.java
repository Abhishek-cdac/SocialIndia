package com.letspay.login.persistense;

import com.letspay.load.HibernateUtil;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.RightsMasterTblVo;
import com.letspay.utils.CommonUtils;
import com.letspay.utils.CommonUtilsDao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LoginDao implements Login {

  @Override
  public UserMasterTblVo userCheck(UserMasterTblVo userInfo) {
	   UserMasterTblVo userDetails = new UserMasterTblVo(null, null, null,null, null, null,null,null, 0, null, null, null, null, 0,null);
    Session session = HibernateUtil.getSession();
    try {
      String qry = " from UserMasterTblVo where userName=:USER_NAME"
          + " and password=:PASSWORD and statusFlag=:STATUS_FLAG";    
      Query query = session.createQuery(qry);
      query.setString("USER_NAME", userInfo.getUserName());
      query.setString("PASSWORD", userInfo.getPassword());
      query.setInteger("STATUS_FLAG", 1);
      userDetails = (UserMasterTblVo) query.uniqueResult();
      System.out.println("userDetails======" + userDetails);
    } catch (HibernateException ex) {
      ex.printStackTrace();
    } finally {
      session.close();
    }
    return userDetails;
  }

  @Override
  public List<RightsMasterTblVo> getUserRights(UserMasterTblVo userInfo) {
    List<RightsMasterTblVo> rightsList = new ArrayList<RightsMasterTblVo>();
    Session session = HibernateUtil.getSession();
    try {
      System.out.println("grou id====" + userInfo.getGroupId().getGroupCode());
      String qry = "from RightsMasterTblVo where groupCode=:GROUP_CODE"
          + " order by menuId.rootDesc ASC";
      Query query = session.createQuery(qry);
      query.setInteger("GROUP_CODE", userInfo.getGroupId().getGroupCode());
      // q.setInteger("GROUP_CODE", userInfo.getGroupId().getGroupCode());
      rightsList = query.list();
      System.out.println("rightsList=========" + rightsList.size());
    } catch (Exception ex) {
      System.out.println("exception occur=============" + ex);
      ex.printStackTrace();
    } finally {
      session.close();
    }
    return rightsList;
  }

 

  @Override
  public boolean customerCreationUpdate(int custid, String accrefid) {
    // TODO Auto-generated method stub
    Session session = HibernateUtil.getSession();
    Transaction tx = null;
    try {
      System.out.println("Customer update after payment");
      CommonUtils common = new CommonUtilsDao();
      Date date = common.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");

      tx = session.beginTransaction();
      // String qry =
      
      String qry = "update CustomerAccountTblVO set isValidated=:IS_VALID,"
          + " validatedDate=:VALID_DATE ";
      Query query = session.createQuery(qry);
      query.setInteger("IS_VALID", 1);
      query.setDate("VALID_DATE", date);
      // q.setInteger("CUST_ID", custid);
      // q.setString("ACCREF_ID", accrefid);
      query.executeUpdate();

      // String qry1 =
      
      String qry1 = "update CustomerRegTblVO set isvalidated=:IS_VALID, validatedDate=:VALID_DATE ";
      query = session.createQuery(qry1);
      query.setInteger("IS_VALID", 1);
      query.setDate("VALID_DATE", date);
      // q.setInteger("CUST_ID", custid);
      // q.setString("ACCREF_ID", accrefid);
      query.executeUpdate();

      tx.commit();
    } catch (Exception ex) {
      System.out.println("Exception---" + ex);
      if (tx != null) {
        tx.rollback();
      }
      ex.printStackTrace();
      return false;
    } finally {
      session.close();
    }
    return true;
  }

}
