package com.letspay.load;

import com.letspay.load.Load;
import com.letspay.load.LoadDao;

public class LoadDaoServices implements LoadServices {

  private Load dao;

  public LoadDaoServices() {
    dao = new LoadDao();
  }

  @Override
  public void getAllMenuMasterList() {
    // TODO Auto-generated method stub
    dao.getAllMenuMasterList();
  }

  @Override
  public void getAllContextValues() {
    // TODO Auto-generated method stub
    dao.getAllContextValues();
  }
}
