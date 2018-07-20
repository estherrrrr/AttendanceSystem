package com.etc.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public StudentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andSnumberIsNull() {
			addCriterion("snumber is null");
			return (Criteria) this;
		}

		public Criteria andSnumberIsNotNull() {
			addCriterion("snumber is not null");
			return (Criteria) this;
		}

		public Criteria andSnumberEqualTo(Integer value) {
			addCriterion("snumber =", value, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberNotEqualTo(Integer value) {
			addCriterion("snumber <>", value, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberGreaterThan(Integer value) {
			addCriterion("snumber >", value, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("snumber >=", value, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberLessThan(Integer value) {
			addCriterion("snumber <", value, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberLessThanOrEqualTo(Integer value) {
			addCriterion("snumber <=", value, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberIn(List<Integer> values) {
			addCriterion("snumber in", values, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberNotIn(List<Integer> values) {
			addCriterion("snumber not in", values, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberBetween(Integer value1, Integer value2) {
			addCriterion("snumber between", value1, value2, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnumberNotBetween(Integer value1, Integer value2) {
			addCriterion("snumber not between", value1, value2, "snumber");
			return (Criteria) this;
		}

		public Criteria andSnameIsNull() {
			addCriterion("sname is null");
			return (Criteria) this;
		}

		public Criteria andSnameIsNotNull() {
			addCriterion("sname is not null");
			return (Criteria) this;
		}

		public Criteria andSnameEqualTo(String value) {
			addCriterion("sname =", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameNotEqualTo(String value) {
			addCriterion("sname <>", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameGreaterThan(String value) {
			addCriterion("sname >", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameGreaterThanOrEqualTo(String value) {
			addCriterion("sname >=", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameLessThan(String value) {
			addCriterion("sname <", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameLessThanOrEqualTo(String value) {
			addCriterion("sname <=", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameLike(String value) {
			addCriterion("sname like", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameNotLike(String value) {
			addCriterion("sname not like", value, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameIn(List<String> values) {
			addCriterion("sname in", values, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameNotIn(List<String> values) {
			addCriterion("sname not in", values, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameBetween(String value1, String value2) {
			addCriterion("sname between", value1, value2, "sname");
			return (Criteria) this;
		}

		public Criteria andSnameNotBetween(String value1, String value2) {
			addCriterion("sname not between", value1, value2, "sname");
			return (Criteria) this;
		}

		public Criteria andAidIsNull() {
			addCriterion("aid is null");
			return (Criteria) this;
		}

		public Criteria andAidIsNotNull() {
			addCriterion("aid is not null");
			return (Criteria) this;
		}

		public Criteria andAidEqualTo(Integer value) {
			addCriterion("aid =", value, "aid");
			return (Criteria) this;
		}

		public Criteria andAidNotEqualTo(Integer value) {
			addCriterion("aid <>", value, "aid");
			return (Criteria) this;
		}

		public Criteria andAidGreaterThan(Integer value) {
			addCriterion("aid >", value, "aid");
			return (Criteria) this;
		}

		public Criteria andAidGreaterThanOrEqualTo(Integer value) {
			addCriterion("aid >=", value, "aid");
			return (Criteria) this;
		}

		public Criteria andAidLessThan(Integer value) {
			addCriterion("aid <", value, "aid");
			return (Criteria) this;
		}

		public Criteria andAidLessThanOrEqualTo(Integer value) {
			addCriterion("aid <=", value, "aid");
			return (Criteria) this;
		}

		public Criteria andAidIn(List<Integer> values) {
			addCriterion("aid in", values, "aid");
			return (Criteria) this;
		}

		public Criteria andAidNotIn(List<Integer> values) {
			addCriterion("aid not in", values, "aid");
			return (Criteria) this;
		}

		public Criteria andAidBetween(Integer value1, Integer value2) {
			addCriterion("aid between", value1, value2, "aid");
			return (Criteria) this;
		}

		public Criteria andAidNotBetween(Integer value1, Integer value2) {
			addCriterion("aid not between", value1, value2, "aid");
			return (Criteria) this;
		}

		public Criteria andPwdIsNull() {
			addCriterion("pwd is null");
			return (Criteria) this;
		}

		public Criteria andPwdIsNotNull() {
			addCriterion("pwd is not null");
			return (Criteria) this;
		}

		public Criteria andPwdEqualTo(String value) {
			addCriterion("pwd =", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotEqualTo(String value) {
			addCriterion("pwd <>", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdGreaterThan(String value) {
			addCriterion("pwd >", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdGreaterThanOrEqualTo(String value) {
			addCriterion("pwd >=", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLessThan(String value) {
			addCriterion("pwd <", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLessThanOrEqualTo(String value) {
			addCriterion("pwd <=", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLike(String value) {
			addCriterion("pwd like", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotLike(String value) {
			addCriterion("pwd not like", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdIn(List<String> values) {
			addCriterion("pwd in", values, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotIn(List<String> values) {
			addCriterion("pwd not in", values, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdBetween(String value1, String value2) {
			addCriterion("pwd between", value1, value2, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotBetween(String value1, String value2) {
			addCriterion("pwd not between", value1, value2, "pwd");
			return (Criteria) this;
		}

		public Criteria andEmailIsNull() {
			addCriterion("email is null");
			return (Criteria) this;
		}

		public Criteria andEmailIsNotNull() {
			addCriterion("email is not null");
			return (Criteria) this;
		}

		public Criteria andEmailEqualTo(String value) {
			addCriterion("email =", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotEqualTo(String value) {
			addCriterion("email <>", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThan(String value) {
			addCriterion("email >", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThanOrEqualTo(String value) {
			addCriterion("email >=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThan(String value) {
			addCriterion("email <", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThanOrEqualTo(String value) {
			addCriterion("email <=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLike(String value) {
			addCriterion("email like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotLike(String value) {
			addCriterion("email not like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailIn(List<String> values) {
			addCriterion("email in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotIn(List<String> values) {
			addCriterion("email not in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailBetween(String value1, String value2) {
			addCriterion("email between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotBetween(String value1, String value2) {
			addCriterion("email not between", value1, value2, "email");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table student
	 * @mbggenerated  Fri Jul 20 21:05:12 CST 2018
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table student
     *
     * @mbggenerated do_not_delete_during_merge Fri Jul 20 20:51:53 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}