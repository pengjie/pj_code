package com.test;

import java.io.Serializable;

import com.utils.MD5Util;

public class Direct implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String accno ; 
	private String type ;
	private String userid ;
	private Double amt ;
	
	public Direct(){
		
	}
	public Direct(String accno,String type,String userid,Double amt){
		this.accno = accno ;
		this.type = type ;
		this.userid = userid ;
		this.amt = amt ;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}
	
	public String getUUID(){
		StringBuilder buffer = new StringBuilder() ;
		buffer.append(accno == null ? "accno": accno);
		buffer.append(userid == null ? "userid": userid);
		buffer.append(type == null ? "type": type);
		return MD5Util.MD5Encode(buffer.toString(), "UTF-8") ;
	}
}
