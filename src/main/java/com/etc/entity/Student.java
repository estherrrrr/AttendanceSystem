package com.etc.entity;

public class Student {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column student.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column student.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private String snumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column student.sname
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private String sname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column student.aid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer aid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column student.pwd
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private String pwd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column student.email
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private String email;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column student.id
	 * @return  the value of student.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column student.id
	 * @param id  the value for student.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column student.snumber
	 * @return  the value of student.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public String getSnumber() {
		return snumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column student.snumber
	 * @param snumber  the value for student.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column student.sname
	 * @return  the value of student.sname
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column student.sname
	 * @param sname  the value for student.sname
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column student.aid
	 * @return  the value of student.aid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getAid() {
		return aid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column student.aid
	 * @param aid  the value for student.aid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setAid(Integer aid) {
		this.aid = aid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column student.pwd
	 * @return  the value of student.pwd
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column student.pwd
	 * @param pwd  the value for student.pwd
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column student.email
	 * @return  the value of student.email
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column student.email
	 * @param email  the value for student.email
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", snumber=" + snumber + ", sname=" + sname + ", aid=" + aid + ", pwd=" + pwd
				+ ", email=" + email + "]";
	}
	
}