package com.etc.entity;

public class Course {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column course.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column course.cid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private Integer cid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column course.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	private String snumber;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column course.id
	 * @return  the value of course.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column course.id
	 * @param id  the value for course.id
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column course.cid
	 * @return  the value of course.cid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column course.cid
	 * @param cid  the value for course.cid
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column course.snumber
	 * @return  the value of course.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public String getSnumber() {
		return snumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column course.snumber
	 * @param snumber  the value for course.snumber
	 * @mbggenerated  Tue Jul 24 19:37:17 CST 2018
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
}