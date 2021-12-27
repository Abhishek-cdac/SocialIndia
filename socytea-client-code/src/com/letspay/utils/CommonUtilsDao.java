package com.letspay.utils;


import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class CommonUtilsDao implements CommonUtils {
  private static Logger log = Logger.getLogger(CommonUtilsDao.class);

  @Override
  public String checkNull(String str) {

    if (str == null) {
      return "";
    }
    return str;
  }

  @Override
  public Date getCurrentDateTime(String dateformattype) {
    Date date = new Date();
    try {
      DateFormat dateFormat = new SimpleDateFormat(dateformattype);
      Date dateFor = new Date();
      date = dateFormat.parse(dateFormat.format(dateFor));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return date;
  }

  @Override
  public String stringToMD5(String password) {
    String result = null;
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes(Charset.forName("UTF-8")));
      result = String.format(Locale.ROOT, "%032x",
          new BigInteger(1, md.digest()));
    } catch (NoSuchAlgorithmException ex) {
      throw new IllegalStateException(ex);
    }

    return result;
  }

  @Override
  public String getRandomval(String type, int dataLength) {
    // TODO Auto-generated method stub
    char[] result = new char[dataLength];
    try {
      final char[] charset_aZ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
          .toCharArray();

      final char[] charset_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
          .toCharArray();

      final char[] charset_09 = "0123456789".toCharArray();
      char[] characterSet = null;
      if (type != null && !type.equalsIgnoreCase("")
          && !type.equalsIgnoreCase("null")) {
        if (type.equalsIgnoreCase("aZ")) {
          characterSet = charset_aZ;
        } else if (type.equalsIgnoreCase("AZ_09")) {
          characterSet = charset_AZ_09;
        } else if (type.equalsIgnoreCase("09")) {
          characterSet = charset_09;
        } else if (type.equalsIgnoreCase("aZ_09")) {
          characterSet = charset_09;
        }
      }
      Random random = new SecureRandom();

      for (int i = 0; i < result.length; i++) {
        // picks a random index out of character set > random character
        int randomCharIndex = random.nextInt(characterSet.length);
        result[i] = characterSet[randomCharIndex];
      }

    } catch (Exception ex) {
      System.out.println("Exception found ger random number:" + ex);
    }
    return new String(result);
  }

  @Override
  public String getStrCurrentDateTime(String dateformattype) {
    String dateformat = "";
    DateFormat fileDateFormat = null;
    Date fileDate = null;
    try {
      fileDateFormat = new SimpleDateFormat(dateformattype);
      fileDate = new Date();
      dateformat = fileDateFormat.format(fileDate);
    } catch (Exception ex) {
      System.out.println("Exception found in getStrCurrentDateTime:" + ex);
    }finally{
      fileDateFormat = null;fileDate = null;
    }
    return dateformat;
  }

  @Override
  public boolean checkEmpty(String str) {
    // TODO Auto-generated method stub
    boolean retval = false;
    try {
      if (str != null && !str.equalsIgnoreCase("")
          && !str.equalsIgnoreCase("null")) {
        retval = true;
      }
    } catch (Exception ex) {
      System.out.println("Exception found in checkEmpty:" + ex);
    }
    return retval;
  }

  /**
   * randInt.
   */
  public String randInt(int min, int max) {

    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return Integer.toString(randomNum);
  }
  
  public String toFirstCharUpper(String changedstr) {
      if (changedstr != null && changedstr.length() > 0) { //changedstr = changedstr.toLowerCase();
          changedstr = Character.toString(changedstr.charAt(0)).toUpperCase() + changedstr.substring(1).toLowerCase();
      } else {
    	  changedstr = "";
      }
      return changedstr;
  }

  

}
