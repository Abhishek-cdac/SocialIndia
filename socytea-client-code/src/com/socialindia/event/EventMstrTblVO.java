package com.socialindia.event;

public class EventMstrTblVO {
	private String evevideopath;
	private String evecurntusrid;
	private String eveenddate;
	private String eveendtime;
	private String eventid;
	private String evefilename;
	private String eveentryby;
	private String eveshdesc;
	private String evedesc;
	private String eveentrydate;
	private String evetitle;
	private String evestartdate;
	private String evecurntgrpid;
	private String evestarttime;
	private String inviteid;
	private String inviteName;
	private String evelocation;
	private String evelatlong;
	private String eventisrvp;
	private String eventfunctionid;
	private String eventfuntempateid;
	private String eventfuntempatetext;
	private String facitityid;
	private String eventfuname;

	public EventMstrTblVO(String evetitle, String evestartdate,
			String evestarttime, String eveenddate, String eveendtime,
			String eventid) {
		this.evetitle = evetitle;
		this.evestartdate = evestartdate;
		this.evestarttime = evestarttime;
		this.eveenddate = eveenddate;
		this.eveendtime = eveendtime;
		this.eventid = eventid;
	}

	public String getEvelatlong() {
		return evelatlong;
	}

	public void setEvelatlong(String evelatlong) {
		this.evelatlong = evelatlong;
	}

	public String getEventisrvp() {
		return eventisrvp;
	}

	public void setEventisrvp(String eventisrvp) {
		this.eventisrvp = eventisrvp;
	}

	public String getEventfunctionid() {
		return eventfunctionid;
	}

	public void setEventfunctionid(String eventfunctionid) {
		this.eventfunctionid = eventfunctionid;
	}

	public String getEvevideopath() {
		return evevideopath;
	}

	public void setEvevideopath(String evevideopath) {
		this.evevideopath = evevideopath;
	}

	public String getEvecurntusrid() {
		return evecurntusrid;
	}

	public void setEvecurntusrid(String evecurntusrid) {
		this.evecurntusrid = evecurntusrid;
	}

	public String getEveenddate() {
		return eveenddate;
	}

	public void setEveenddate(String eveenddate) {
		this.eveenddate = eveenddate;
	}

	public String getEveendtime() {
		return eveendtime;
	}

	public void setEveendtime(String eveendtime) {
		this.eveendtime = eveendtime;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getEvefilename() {
		return evefilename;
	}

	public void setEvefilename(String evefilename) {
		this.evefilename = evefilename;
	}

	public String getEveentryby() {
		return eveentryby;
	}

	public void setEveentryby(String eveentryby) {
		this.eveentryby = eveentryby;
	}

	public String getEveshdesc() {
		return eveshdesc;
	}

	public void setEveshdesc(String eveshdesc) {
		this.eveshdesc = eveshdesc;
	}

	public String getEveentrydate() {
		return eveentrydate;
	}

	public void setEveentrydate(String eveentrydate) {
		this.eveentrydate = eveentrydate;
	}

	public String getEvetitle() {
		return evetitle;
	}

	public void setEvetitle(String evetitle) {
		this.evetitle = evetitle;
	}

	public String getEvestartdate() {
		return evestartdate;
	}

	public void setEvestartdate(String evestartdate) {
		this.evestartdate = evestartdate;
	}

	public String getEvecurntgrpid() {
		return evecurntgrpid;
	}

	public void setEvecurntgrpid(String evecurntgrpid) {
		this.evecurntgrpid = evecurntgrpid;
	}

	public String getEvestarttime() {
		return evestarttime;
	}

	public void setEvestarttime(String evestarttime) {
		this.evestarttime = evestarttime;
	}

	public String getEvedesc() {
		return evedesc;
	}

	public void setEvedesc(String evedesc) {
		this.evedesc = evedesc;
	}

	public String getInviteid() {
		return inviteid;
	}

	public void setInviteid(String inviteid) {
		this.inviteid = inviteid;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	public String getEvelocation() {
		return evelocation;
	}

	public void setEvelocation(String evelocation) {
		this.evelocation = evelocation;
	}

	public synchronized String getEventfuntempateid() {
		return eventfuntempateid;
	}

	public synchronized void setEventfuntempateid(String eventfuntempateid) {
		this.eventfuntempateid = eventfuntempateid;
	}

	public synchronized String getEventfuntempatetext() {
		return eventfuntempatetext;
	}

	public synchronized void setEventfuntempatetext(String eventfuntempatetext) {
		this.eventfuntempatetext = eventfuntempatetext;
	}

	public String getFacitityid() {
		return facitityid;
	}

	public void setFacitityid(String facitityid) {
		this.facitityid = facitityid;
	}

	public String getEventfuname() {
		return eventfuname;
	}

	public void setEventfuname(String eventfuname) {
		this.eventfuname = eventfuname;
	}

}
