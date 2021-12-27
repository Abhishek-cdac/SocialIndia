package com.letspay.common;

import com.letspay.load.HibernateUtil;
import com.letspay.utils.CommonUtils;
import com.letspay.utils.CommonUtilsDao;
import com.letspay.utils.Log;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.CountryMasterTblVo;
import com.letspay.vo.IsdcodeMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.letspay.vo.SysconfigChecklistDocTblVo;
import com.letspay.vo.SystemRuleTblVo;
import com.socialindia.generalmgnt.persistance.Commonutility;
/*import com.remittance.vo.OtpConfigTblVo;
 import com.remittance.vo.OtpDetailTblVo;*/



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * For using common actions.
 *
 * @author PL0043
 *
 */
public class CommonDao implements Common {

	// public static Logger log = Logger.getLogger(DbUtil.class);
	static Log log = new Log();
	CommonUtils common = new CommonUtilsDao();

	@Override
	public boolean commonUpdate(String sql) {
		boolean result = false;
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				Query qy = session.createQuery(sql);
				int cnt = qy.executeUpdate();
				tx.commit();
				if (cnt > 0) {
					result = true;
				} else {
					result = false;
				}
				// log.logMessage("Common Update Query--->"+sql, "info",
				// CommonDao.class);
			} catch (HibernateException ex) {
				if (tx != null) {
					tx.rollback();
				}
				ex.printStackTrace();
				result = false;
			} finally {
				session.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.logMessage("Exception Common Update Query--->" + sql + " \t- Exception-" + ex, "error", CommonDao.class);
			result = false;
		}
		return result;
	}

	/**
	 * Delete action.
	 */
	public String commonDelete(int sno, String tblpojoname, String whr) {

		String pojonam = "";
		String wherecolumn = "";
		Query qy = null;

		pojonam = tblpojoname;
		wherecolumn = whr;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			qy = session.createQuery("delete from " + pojonam + " where "
					+ wherecolumn + "=" + sno + "");
			qy.executeUpdate();
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			log.logMessage("Exception Common Delete Query--->" + qy
					+ " \t- Exception-" + ex, "error", CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return "Successfully Deleted";
	}

	/**
	 * Call procedure fucntion.
	 *
	 * @param callproc
	 *            .
	 * @param procName
	 *            .
	 * @param ArrayList
	 *            .
	 * @param connection
	 *            .
	 * @return Resultset returned.
	 */
	public static ResultSet call_proc(String procName, ArrayList al,
			Connection cn) {
		String qst = "call " + procName + "(";
		String dbug = "call " + procName + "(";
		String proc = "";
		ResultSet rst = null;
		try {
			for (int i = 0; i < al.size(); i++) {
				if (i == (al.size() - 1)) {
					qst += "?)";
					dbug += "'" + al.get(i) + "')";
				} else {
					qst += "?,";
					dbug += "'" + al.get(i) + "',";
				}
			}
			proc += qst;
			log.logMessage("Call proc--->" + dbug, "info", CommonDao.class);
			CallableStatement cst = cn.prepareCall(proc);
			for (int i = 0; i < al.size(); i++) {
				cst.setObject(i + 1, al.get(i));
			}
			rst = cst.executeQuery();
		} catch (Exception ex) {
			System.out.println("Exception : " + ex);
			try {
				String errMsg = "";
				ResultSet rset = cn.createStatement().executeQuery(
						"show warnings");
				int cc = rset.getMetaData().getColumnCount();
				while (rset != null && rset.next()) {
					errMsg += rset.getString("Level") + "  |  "
							+ rset.getString("Code") + "  |  "
							+ rset.getString("Message") + "\n";
				}
				log.logMessage("Exception Callproc - " + dbug
						+ " \t- Exception-" + ex, "error", CommonDao.class);
				log.logMessage("Exception Callproc - " + errMsg
						+ " \t- Exception-" + ex, "error", CommonDao.class);
			} catch (Exception exp) {
				log.logMessage("Exception 2exception Callproc - " + proc
						+ " \t- Exception-" + exp, "error", CommonDao.class);
			} finally {
				log = null;
			}
		}
		return rst;
	}

	@Override
	public List<CountryMasterTblVo> commonCountyData() {
		// TODO Auto-generated method stub
		List<CountryMasterTblVo> cntrymst = new ArrayList<CountryMasterTblVo>();
		Session session = HibernateUtil.getSession();
		try {
			String qry = " from CountryMasterTblVo";
			Query qy = session.createQuery(qry);
			cntrymst = qy.list();
		} catch (Exception ex) {
			log.logMessage("Exception in commonCountyData()--->" + ex, "error",
					CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return cntrymst;
	}

	@Override
	public List<StateMasterTblVo> commonStateData(int countryidkey) {
		// TODO Auto-generated method stub

		List<StateMasterTblVo> statmst = new ArrayList<StateMasterTblVo>();
		Session session = HibernateUtil.getSession();
		System.out.println("countryidkey-------- "+countryidkey);
		try {
			String qry = " from StateMasterTblVo where countryId=:CNTRY_ID";
			System.out.println("enter statemstrtbl---" + countryidkey);
			Query qy = session.createQuery(qry);
			qy.setInteger("CNTRY_ID", countryidkey);
			statmst = qy.list();
			System.out.println("end---" + statmst);
		} catch (Exception ex) {
			log.logMessage("Exception in commonStateData()--->" + ex, "error",
					CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return statmst;
	}

	@Override
	public List<CityMasterTblVo> commonCityData(int stateidkey) {
		// TODO Auto-generated method stub
		List<CityMasterTblVo> citymst = new ArrayList<CityMasterTblVo>();
		Session session = HibernateUtil.getSession();
		try {
			String qry = " from CityMasterTblVo where stateId=:STATE_ID";
			System.out.println("enter statemstrtbl---" + stateidkey);
			Query qy = session.createQuery(qry);
			qy.setInteger("STATE_ID", stateidkey);
			citymst = qy.list();
			System.out.println("end---" + citymst);
		} catch (Exception ex) {
			log.logMessage("Exception in commonCityData()--->" + ex, "error",
					CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return citymst;
	}

	@Override
	public List<IsdcodeMasterTblVo> commonIsdCodeData(int countryidkey) {
		// TODO Auto-generated method stub
		List<IsdcodeMasterTblVo> isdmstrlist = new ArrayList<IsdcodeMasterTblVo>();
		// CountryMasterTblVo cntrymstrlist = new CountryMasterTblVo();
		Session session = HibernateUtil.getSession();
		try {
			String qry = " from IsdCodeMasterTblVo where countryId=:CNTRY_ID";
			System.out.println(qry + "<-enter isdmstrtbl--->" + countryidkey);
			// cntrymstrlist.setCountryId(countryidkey);
			Query qy = session.createQuery(qry);
			qy.setInteger("CNTRY_ID", countryidkey);
			isdmstrlist = qy.list();
			System.out.println("end---" + isdmstrlist);
		} catch (Exception ex) {
			System.out.println("Exception ---" + ex);
			log.logMessage("Exception in commonIsdCodeData()--->" + ex,
					"error", CommonDao.class);
			ex.printStackTrace();
		} finally {
			session.close();
			log = null;
		}
		return isdmstrlist;
	}

	@Override
	public List<SysconfigChecklistDocTblVo> checkListDocType() {
		// TODO Auto-generated method stub
		List<SysconfigChecklistDocTblVo> doctypelist = new ArrayList<SysconfigChecklistDocTblVo>();
		Session session = HibernateUtil.getSession();
		try {
			String qry = " from SysconfigChecklistDocTblVo";
			Query qy = session.createQuery(qry);
			doctypelist = qy.list();
		} catch (Exception ex) {
			log.logMessage("Exception in checkListDocType()--->" + ex, "error",
					CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return doctypelist;
	}

	/**
	 * Getting swift code for a particular branch.
	 */
	public String getSwiftcode(int branchid) {
		// TODO Auto-generated method stub
		String swiftcode = "";
		Session session = HibernateUtil.getSession();
		try {
			String qry = "select swiftcode from BranchMasterTblVo where branchid=:BRANCHID";
			Query qy = session.createQuery(qry);
			qy.setInteger("BRANCHID", branchid);
			qy.setMaxResults(1);
			swiftcode = (String) qy.uniqueResult();
			System.out.println("swiftcode value :" + swiftcode);
		} catch (Exception ex) {
			log.logMessage("Exception in getSwiftcode()--->" + ex, "error",
					CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return swiftcode;
	}

	@Override
	public String getSinglevalfromtbl(String tblname, String colname, String val) {
		// TODO Auto-generated method stub
		String retval = "";
		Session session = HibernateUtil.getSession();
		try {
			System.out.println("-----Enter to get username--:" + colname);
			String qry = "select " + colname + " from " + tblname + " where "
					+ colname + "=:PVALUE";
			Query qy = session.createQuery(qry);
			qy.setString("PVALUE", val);
			qy.setMaxResults(1);
			retval = (String) qy.uniqueResult();
			System.out.println("retval  ------:" + retval);
		} catch (Exception ex) {
			System.out.println("Exeption found in getSinglevalfromtbl:" + ex);
			log.logMessage("Exception in getSinglevalfromtbl()--->" + ex,
					"error", CommonDao.class);
		}
		return retval;
	}

	@Override
	public String commonAutogenval(int lengthval, String type, String tblname,
			String colname) {
		String retval = "";// common
		try {
			System.out.println("----1");
			boolean flg = true;
			int ilen = 0;
			while (flg) {
				System.out.println("----2");
				String keyval = common.getRandomval(type, lengthval);
				System.out.println("----3 keyval:" + keyval);
				String rettblval = getSinglevalfromtbl(tblname, colname, keyval);
				System.out.println("----3:" + keyval);
				if (!common.checkEmpty(rettblval)) {
					retval = keyval;
					flg = false;
				} else {
					if (ilen == 50) {
						lengthval = lengthval + 5;
					}
				}
				ilen++;
			}
			System.out.println("-----retval:" + retval);

		} catch (Exception ex) {
			System.out.println("Exeption found in commonAutogenval:" + ex);
			log.logMessage("Exception in commonAutogenval()--->" + ex, "error",
					CommonDao.class);
		}
		return retval;
	}

	@Override
	public int gettotalcount(String sql) {
		int totcnt = 0;
		Session session = HibernateUtil.getSession();
		try {
			Query qy = session.createQuery(sql);
			totcnt = ((Number) qy.uniqueResult()).intValue();
			System.out.println("totalcount------------" + totcnt);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.logMessage("Exception in gettotalcount()--->" + ex, "error",
					CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return totcnt;
	}

	@Override
	public SystemRuleTblVo commonSystemRuleData(String ruleid) {
		// TODO Auto-generated method stub
		SystemRuleTblVo sysruleObj = new SystemRuleTblVo();
		Session session = HibernateUtil.getSession();
		try {
			System.out
					.println("Enter into systemrule data, ruleid---" + ruleid);
			String qry = "From SystemRuleTblVo where sysruleCode=:SYS_CODE";
			System.out.println("====qry===" + qry);
			Query qy = session.createQuery(qry);
			qy.setString("SYS_CODE", ruleid);
			System.out.println("====qry===" + qy);
			sysruleObj = (SystemRuleTblVo) qy.uniqueResult();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			log.logMessage("Exception in commonSystemRuleData()--->" + ex,
					"error", CommonDao.class);
		} finally {
			session.close();
			log = null;
		}
		return sysruleObj;

	}

	/*
	 * @Override public boolean otpInsertTable(OtpDetailTblVo itpInsertObj) { //
	 * TODO Auto-generated method stub boolean result = false; Session session =
	 * HibernateUtil.getSession(); Transaction tx = null; try { tx =
	 * session.beginTransaction(); session.save(itpInsertObj); tx.commit();
	 * result = true; } catch (Exception ex) { if (tx != null) { tx.rollback();
	 * } ex.printStackTrace();
	 * log.logMessage("Exception in otpInsertTable()--->" + ex, "error",
	 * CommonDao.class); result = false; } finally { session.close(); log =
	 * null; } return result; }
	 */

	/*
	 * @Override public OtpConfigTblVo otpconfigGetallData(OtpConfigTblVo
	 * otpConfig) { // TODO Auto-generated method stub OtpConfigTblVo
	 * getResultobj = new OtpConfigTblVo(); Session session =
	 * HibernateUtil.getSession(); try { String qry =
	 * "From OtpConfigTblVo where merchantKey='" + otpConfig.getMerchantKey() +
	 * "' and isvcOtp='" + otpConfig.getIsvcOtp() + "' and statusFlg=" +
	 * otpConfig.getStatusFlg() + ""; Query qy = session.createQuery(qry);
	 * getResultobj = (OtpConfigTblVo) qy.uniqueResult(); } catch
	 * (HibernateException ex) { ex.printStackTrace();
	 * log.logMessage("Exception in otpconfigGetallData()--->" + ex, "error",
	 * CommonDao.class); } finally { session.close(); log = null; } return
	 * getResultobj; }
	 */
	/*
	 * @Override public OtpDetailTblVo getotpDetail(OtpDetailTblVo itpInsertObj)
	 * { // TODO Auto-generated method stub OtpDetailTblVo otpdetail = new
	 * OtpDetailTblVo(); Session session = HibernateUtil.getSession(); try {
	 * String qry = "From OtpDetailTblVo where vcOtpId=" +
	 * itpInsertObj.getVcOtpId() + ""; Query qy = session.createQuery(qry);
	 * otpdetail = (OtpDetailTblVo) qy.uniqueResult(); } catch
	 * (HibernateException ex) { ex.printStackTrace();
	 * log.logMessage("Exception in getotpDetail()--->" + ex, "error",
	 * CommonDao.class); } finally { session.close(); log = null; } return
	 * otpdetail; }
	 */

	@Override
	public List<IsdcodeMasterTblVo> isdDataByjsonvalue() {
		// TODO Auto-generated method stub
		List<IsdcodeMasterTblVo> isdlist = new ArrayList<IsdcodeMasterTblVo>();
		Session session = HibernateUtil.getSession();
		try {
			String qry = "From IsdcodeMasterTblVo";
			Query qy = session.createQuery(qry);
			isdlist = qy.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			log.logMessage("Exception inisdDataByjsonvalue()--->" + ex,
					"error", CommonDao.class);
		} finally {
			session.close();
		}
		return isdlist;
	}

	@Override
	public String getPath(String contextPath, String requestUri,
			StringBuffer requestUrl) {
		// TODO Auto-generated method stub
		String path = "";
		try {
			if (contextPath != null && requestUri != null && requestUrl != null) {
				path = requestUrl.toString().replaceAll(requestUri, "");
				path = path + contextPath;
			}
		} catch (Exception ex) {
			log.write.error("path==" + path + "\n ex==" + ex);
		}
		return path;
	}

	@Override
	public String getRandomval(String type, int dataLength) {
		char[] result = new char[dataLength];
		try {
			// final char[] CHARSET_aZ_09 =
			// "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
			// .toCharArray();

			final char[] charSetAz = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
					.toCharArray();
			final char[] charSetAz09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
					.toCharArray();
			final char[] charSetHex = "0123456789ABCDEF".toCharArray();
			final char[] charSetSpecial = { '!', 'A', 'B' };
			final char[] charSet09 = "0123456789".toCharArray();
			final char[] charSetaZ09sp = "abcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+{}|[]?/><,.;':ABCDEFGHIJKLMNOPQRSTUVWXYZ"
					.toCharArray();

			final char[] charSetAZaz09 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
					.toCharArray();
			final char[] charSetAZazSP = "abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+{}|[]?/><,.;':ABCDEFGHIJKLMNOPQRSTUVWXYZ"
					.toCharArray();
			final char[] charSet09SP = "0123456789~!@#$%^&*()_+{}|[]?/><,.;':"
					.toCharArray();
			final char[] charSetSP = "~!@#$%^&*()_+{}|[]?/><,.;':"
					.toCharArray();

			char[] characterSet = null;
			if (type != null && !type.equalsIgnoreCase("")
					&& !type.equalsIgnoreCase("null")) {
				if (type.equalsIgnoreCase("aZ")) {
					characterSet = charSetAz;
				} else if (type.equalsIgnoreCase("AZ_09")) {
					characterSet = charSetAz09;
				} else if (type.equalsIgnoreCase("09")) {
					characterSet = charSet09;
				} else if (type.equalsIgnoreCase("aZ_09")) {
					characterSet = charSet09;
				} else if (type.equalsIgnoreCase("aZ_09_sp")) {
					characterSet = charSetaZ09sp;
				} else if (type.equalsIgnoreCase("AZaz09")) {
					characterSet = charSetAZaz09;
				} else if (type.equalsIgnoreCase("AZazSP")) {
					characterSet = charSetAZazSP;
				} else if (type.equalsIgnoreCase("09SP")) {
					characterSet = charSet09SP;
				} else if (type.equalsIgnoreCase("SP")) {
					characterSet = charSetSP;
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
	public String jsonRequest(String URL, String data) {
		StringBuilder response = null;
		URL getpseudo = null;
		HttpURLConnection con = null;
		BufferedReader bfreader = null;
		OutputStream locvOutStrmObj = null;
		try {

			Commonutility.toWriteConsole("Request Url : " + URL);
			Commonutility.toWriteConsole("Request Data : " + data);
			getpseudo = new URL(URL);
			con = (HttpURLConnection) getpseudo.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// con.setRequestProperty("Content-Type", "multipart/form-data;");
			con.setRequestProperty("charset", "utf-8");
			con.setRequestProperty("Content-Length", "" + Integer.toString(data.getBytes().length));
			con.setUseCaches(false);
			locvOutStrmObj = con.getOutputStream();
			locvOutStrmObj.write(data.getBytes("UTF-8"));
			locvOutStrmObj.close();
			locvOutStrmObj = null;
			int responsecode = con.getResponseCode();
			bfreader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			response = new StringBuilder();
			String ipline = "";
			while ((ipline = bfreader.readLine()) != null) {
				response.append(ipline);
			}
			bfreader.close();
			return response.toString();
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Service Url not connect.");
			Commonutility.toWriteConsole("Exception found CommonDao.jsonRequest() Service Request : " + ex);
			return "error";
		} finally {
			try {
				if (bfreader != null) {
					bfreader.close();
					bfreader = null;
				}
				if (con != null) {
					con = null;
				}
				if (getpseudo != null) {
					getpseudo = null;
				}
				if (locvOutStrmObj != null) {
					locvOutStrmObj.close();
					locvOutStrmObj = null;
				}
				if (response != null) {
					response = null;
				}
			} catch (Exception ex) {
			}
		}

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
		public String getDateobjtoFomatDateStr(Date pDataobj, String pDateformat) {
			SimpleDateFormat locSimpFrmt=null;
			String locRtnDatestr=null;
			try{
				if(pDataobj!=null){
					if(pDateformat!=null && !pDateformat.equalsIgnoreCase("null") && !pDateformat.equalsIgnoreCase("")){
						locSimpFrmt=new SimpleDateFormat(pDateformat);
					}else{
						locSimpFrmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					}
					locRtnDatestr=locSimpFrmt.format(pDataobj);
				}else{
					locRtnDatestr="";
				}

				return locRtnDatestr;
			}catch(Exception e){
				locRtnDatestr="";
				return locRtnDatestr;
			}finally{
				locSimpFrmt=null;locRtnDatestr=null;
			}
		}

}
