package com.etc.entity;

import java.util.Date;

public class Attendance {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column attendance.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column attendance.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private String snumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column attendance.cid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer cid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column attendance.astatus
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer astatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column attendance.adate
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Date adate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column attendance.id
	 * @return  the value of attendance.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column attendance.id
	 * @param id  the value for attendance.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column attendance.snumber
	 * @return  the value of attendance.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public String getSnumber() {
		return snumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column attendance.snumber
	 * @param snumber  the value for attendance.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column attendance.cid
	 * @return  the value of attendance.cid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column attendance.cid
	 * @param cid  the value for attendance.cid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column attendance.astatus
	 * @return  the value of attendance.astatus
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getAstatus() {
		return astatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column attendance.astatus
	 * @param astatus  the value for attendance.astatus
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setAstatus(Integer astatus) {
		this.astatus = astatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column attendance.adate
	 * @return  the value of attendance.adate
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Date getAdate() {
		return adate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column attendance.adate
	 * @param adate  the value for attendance.adate
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setAdate(Date adate) {
		this.adate = adate;
	}
}