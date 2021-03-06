package com.zq.shop.web.bean;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table zq_star
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Star {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zq_star.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zq_star.star_id
     *
     * @mbg.generated
     */
    private Integer starId;

    /**
     * Database Column Remarks:
     *   0：用户 1：文章 2：评论
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zq_star.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zq_star.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zq_star.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zq_star.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zq_star.id
     *
     * @return the value of zq_star.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zq_star.id
     *
     * @param id the value for zq_star.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zq_star.star_id
     *
     * @return the value of zq_star.star_id
     *
     * @mbg.generated
     */
    public Integer getStarId() {
        return starId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zq_star.star_id
     *
     * @param starId the value for zq_star.star_id
     *
     * @mbg.generated
     */
    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zq_star.type
     *
     * @return the value of zq_star.type
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zq_star.type
     *
     * @param type the value for zq_star.type
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zq_star.user_id
     *
     * @return the value of zq_star.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zq_star.user_id
     *
     * @param userId the value for zq_star.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zq_star.create_time
     *
     * @return the value of zq_star.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zq_star.create_time
     *
     * @param createTime the value for zq_star.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zq_star.update_time
     *
     * @return the value of zq_star.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zq_star.update_time
     *
     * @param updateTime the value for zq_star.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}