package com.etc.dao;

import com.etc.entity.Class;
import com.etc.entity.ClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int countByExample(ClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int deleteByExample(ClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int insert(Class record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int insertSelective(Class record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    List<Class> selectByExample(ClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    Class selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int updateByExampleSelective(@Param("record") Class record, @Param("example") ClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int updateByPrimaryKeySelective(Class record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated Thu Jul 19 10:15:56 CST 2018
     */
    int updateByPrimaryKey(Class record);
}