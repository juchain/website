package com.bootdo.enterprise.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 16:44:41
 */
public class EnterpriseInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//创建时间
	private Date created;
	private String createdString;
	//修改时间
	private Date updated;
	//状态 0:禁用，1:正常
	private Integer status;
	// 企业名称
	private String enterpriseName;
	//企业编号
	private String enterpriseCode;
	//官网url
	private String webUrl;
	//营业执照照片url
	private String url;
	//法人姓名
	private String name;
	//法人手机号
	private String mobile;
	//身份证号码
	private String certNo;
	//  身份证正面url
	private String frontUrl;
	//身份证反面面url
	private String backUrl;
	//邮箱
	private String email;
	//备注
	private String remark;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * 设置：状态 0:禁用，1:正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0:禁用，1:正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置： 企业名称
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	/**
	 * 获取： 企业名称
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}
	/**
	 * 设置：企业编号
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	/**
	 * 获取：企业编号
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	/**
	 * 设置：官网url
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	/**
	 * 获取：官网url
	 */
	public String getWebUrl() {
		return webUrl;
	}
	/**
	 * 设置：营业执照照片url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：营业执照照片url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：法人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：法人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：法人手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：法人手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：身份证号码
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getCertNo() {
		return certNo;
	}
	/**
	 * 设置：  身份证正面url
	 */
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	/**
	 * 获取：  身份证正面url
	 */
	public String getFrontUrl() {
		return frontUrl;
	}
	/**
	 * 设置：身份证反面面url
	 */
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	/**
	 * 获取：身份证反面面url
	 */
	public String getBackUrl() {
		return backUrl;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	public String getCreatedString() {
		return createdString;
	}

	public void setCreatedString(String createdString) {
		this.createdString = createdString;
	}
}
