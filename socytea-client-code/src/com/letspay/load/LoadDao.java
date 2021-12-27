package com.letspay.load;

import com.letspay.uam.persistense.MenuMasterTblVo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class LoadDao implements Load {

  private static Logger log = Logger.getLogger(LoadDao.class);

  @Override
  public void getAllMenuMasterList() {
    Session session = HibernateUtil.getSession();
    List<MenuMasterTblVo> menu = new ArrayList<MenuMasterTblVo>();
    try {
      ServletContext context = ServletActionContext.getServletContext();
      String qry = " From MenuMasterTblVo order by rootDesc ASC";
      Query query = session.createQuery(qry);
      menu = query.list();   
      context.setAttribute("MENUTYP", menu);
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error("Eexeption found in LoadDao.getAllMenuMasterList() : " + ex);
    } finally {
      session.close();
    }
  }

  @Override
  public void getAllContextValues() { 
    getAllMenuMasterList();
  }

}
