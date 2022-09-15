package com.capstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hello")
public class MessageHubModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MSGSEQNO")
	private String message_id;  //MSGSEQNO
	
	@Column(name = "SENDSTATUS")
	private String receive_status; //SENDSTATUS
	// private String
	
	@Column(name = "SENDTYPE")
	private String user_pref; //SENDTYPE
	
	@Column(name = "TELCOMM")
	private String phone_num; //TELCOMM
	
	@Column(name = "SYSTEMNAME")
	private String sendSystemType; //SYSTEMNAME
	
	@Column(name = "CHARTNAME")
	private String patName; //CHARTNAME
	
	@Column(name = "CHARTNO")
	private String patNo; //CHARTNO
	
	@Column(name = "COLUMN_1")
	private String message_type; //COLUMN_1
	
	@Column(name = "MSGTEXT")
	private String message; //MSGTEXT
	
	@Column(name = "MAIL")
	private String pat_email; //MAIL
	
	@Column(name = "CREATEDATE")
	private String createDate;
	
	@Column(name = "CREATETIME")
	private String CreateTime;
	
	@Column(name = "SHIPPINGDATE")
	private String ShippingDate;
	
	@Column(name = "SHIPPINGTIME")
	private String ShippingTime;
	
	
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getReceive_status() {
		return receive_status;
	}
	public void setReceive_status(String receive_status) {
		this.receive_status = receive_status;
	}
	public String getUser_pref() {
		return user_pref;
	}
	public void setUser_pref(String user_pref) {
		this.user_pref = user_pref;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getSendSystemType() {
		return sendSystemType;
	}
	public void setSendSystemType(String sendSystemType) {
		this.sendSystemType = sendSystemType;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatNo() {
		return patNo;
	}
	public void setPatNo(String patNo) {
		this.patNo = patNo;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPat_email() {
		return pat_email;
	}
	public void setPat_email(String pat_email) {
		this.pat_email = pat_email;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getShippingDate() {
		return ShippingDate;
	}
	public void setShippingDate(String shippingDate) {
		ShippingDate = shippingDate;
	}
	public String getShippingTime() {
		return ShippingTime;
	}
	public void setShippingTime(String shippingTime) {
		ShippingTime = shippingTime;
	} 
}
